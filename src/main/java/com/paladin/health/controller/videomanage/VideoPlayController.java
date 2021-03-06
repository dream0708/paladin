package com.paladin.health.controller.videomanage;

import java.util.Calendar;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.model.videomanage.VideoPlay;
import com.paladin.health.service.org.OrgAgencyService;
import com.paladin.health.service.videomanage.VideoPlayService;
import com.paladin.health.service.videomanage.dto.VideoPlayQueryDTO;
import com.paladin.health.service.videomanage.dto.VideoPlayDTO;
import com.paladin.health.service.videomanage.vo.VideoPlayVO;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.framework.utils.uuid.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;

@Controller
@RequestMapping("/health/video/play")
public class VideoPlayController extends ControllerSupport {

    @Autowired
    private VideoPlayService videoPlayService;
    
    @Autowired
    private OrgAgencyService orgAgencyService;

    @RequestMapping("/index")
    public String index(String id,String name,Model model) {
    	model.addAttribute("id", id);
    	model.addAttribute("name", name);
    	model.addAttribute("agencyList", orgAgencyService.findAll());
        return "/health/videomanage/video_play_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(VideoPlayQueryDTO query) {
        return CommonResponse.getSuccessResponse(videoPlayService.selectByQuery(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(videoPlayService.getOne(id), new VideoPlayVO()));
    }
    
    @RequestMapping("/add")
    public String addInput(String id,String name,Model model) {
    	model.addAttribute("videoId", id);
    	model.addAttribute("videoName", name);
		Calendar calendar = Calendar.getInstance();//日历对象
		model.addAttribute("workCycle", calendar.get(Calendar.YEAR));
    	HealthUserSession userSession = HealthUserSession.getCurrentUserSession();
		model.addAttribute("agencyId", userSession.getAgencyId());
		model.addAttribute("agencyList", orgAgencyService.findAll());
        return "/health/videomanage/video_play_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
    	model.addAttribute("agencyList", orgAgencyService.findAll());
        return "/health/videomanage/video_play_detail";
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid VideoPlayDTO videoPlayDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        VideoPlay model = beanCopy(videoPlayDTO, new VideoPlay());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		model.setPlayDuration(model.getPlayEndTime().getTime() - model.getPlayStartTime().getTime());
		if (videoPlayService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(videoPlayService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid VideoPlayDTO videoPlayDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = videoPlayDTO.getId();
		VideoPlay model = beanCopy(videoPlayDTO, videoPlayService.get(id));
		model.setPlayDuration(model.getPlayEndTime().getTime() - model.getPlayStartTime().getTime());
		if (videoPlayService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(videoPlayService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(videoPlayService.removeByPrimaryKey(id));
    }
}
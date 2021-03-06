package com.paladin.health.controller.origin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.model.origin.OriginDiseaseName;
import com.paladin.health.service.origin.OriginDiseaseKnowledgeContentService;
import com.paladin.health.service.origin.OriginDiseaseKnowledgeService;
import com.paladin.health.service.origin.OriginDiseaseNameService;

@Controller
@RequestMapping("/health/knowledge")
public class DiseaseKnowledgeController {

	@Autowired
	OriginDiseaseKnowledgeService diseaseKnowledgeService;

	@Autowired
	OriginDiseaseNameService diseaseNameService;

	@Autowired
	OriginDiseaseKnowledgeContentService diseaseKnowledgeContentService;

	@RequestMapping("/index")
	public String index(@RequestParam(required = false) String diseaseKey, @RequestParam(required = false) String diseaseName, Model model) {
		if (diseaseKey != null && diseaseKey.length() != 0) {
			OriginDiseaseName name = diseaseNameService.getDiseaseName(diseaseKey);
			model.addAttribute("diseaseKey", diseaseKey);
			model.addAttribute("diseaseName", name.getName());
			return "/health/origin/disease_knowledge";
		}

		if (diseaseName != null && diseaseName.length() != 0) {
			diseaseKey = diseaseNameService.getDiseaseByName(diseaseName);
			model.addAttribute("diseaseKey", diseaseKey);
			model.addAttribute("diseaseName", diseaseName);
			return "/health/origin/disease_knowledge";
		}

		return "/health/origin/disease_knowledge_index";
	}

	@RequestMapping("/list/disease")
	@ResponseBody
	public Object knowledgeList(@RequestParam String diseaseKey) {
		return CommonResponse.getSuccessResponse(diseaseKnowledgeService.findAllDiseaseKnowledge(diseaseKey));
	}

	@RequestMapping("/content")
	@ResponseBody
	public Object knowledgeContent(@RequestParam String knowledgeId) {
		return CommonResponse.getSuccessResponse(diseaseKnowledgeContentService.findKnowledgeContent(knowledgeId));
	}

	@RequestMapping("/content/disease")
	@ResponseBody
	public Object diseaseContent(@RequestParam String diseaseKey) {
		return CommonResponse.getSuccessResponse(diseaseKnowledgeContentService.findDiseaseContent(diseaseKey));
	}

}

package com.paladin.common.controller.org;

import com.paladin.common.model.org.OrgRole;
import com.paladin.common.service.org.OrgPermissionService;
import com.paladin.common.service.org.OrgRolePermissionService;
import com.paladin.common.service.org.OrgRoleService;
import com.paladin.common.service.org.dto.OrgRoleDTO;
import com.paladin.common.service.org.dto.OrgRoleQueryDTO;
import com.paladin.common.service.org.vo.OrgRoleVO;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/health/org/role")
public class OrgRoleController extends ControllerSupport {

	@Autowired
	private OrgRoleService orgRoleService;

	@Autowired
	private OrgPermissionService orgPermissionService;

	@Autowired
	private OrgRolePermissionService orgRolePermissionService;

	@RequestMapping("/index")
	public String index() {
		return "/health/org/org_role_index";
	}

	@RequestMapping("/find/page")
	@ResponseBody
	public Object findPage(OrgRoleQueryDTO query) {
		return CommonResponse.getSuccessResponse(orgRoleService.searchPage(query));
	}

	@RequestMapping("/get")
	@ResponseBody
	public Object getDetail(@RequestParam String id, Model model) {
		return CommonResponse.getSuccessResponse(beanCopy(orgRoleService.get(id), new OrgRoleVO()));
	}

	@RequestMapping("/add")
	public String addInput() {
		return "/health/org/org_role_add";
	}

	@RequestMapping("/detail")
	public String detailInput(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/health/org/org_role_detail";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(@Valid OrgRoleDTO orgRoleDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		OrgRole model = beanCopy(orgRoleDTO, new OrgRole());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (orgRoleService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(orgRoleService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping("/update")
	@ResponseBody
	public Object update(@Valid OrgRoleDTO orgRoleDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = orgRoleDTO.getId();
		OrgRole model = beanCopy(orgRoleDTO, orgRoleService.get(id));
		if (orgRoleService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(orgRoleService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam String id) {
		return CommonResponse.getResponse(orgRoleService.removeByPrimaryKey(id));
	}

	@RequestMapping("/grant/input")
	public String grantAuthorizationInput(@RequestParam String id, Model model) {
		model.addAttribute("roleId", id);
		return "/common/org/org_role_grant";
	}

	@RequestMapping("/grant")
	@ResponseBody
	public Object getGrantAuthorization(@RequestParam String id) {
		Map<String, Object> result = new HashMap<>(2);
		result.put("permissions", orgPermissionService.findAll());
		result.put("hasPermissions", orgRolePermissionService.getPermissionByRole(id));
		return CommonResponse.getSuccessResponse(result);
	}

	@RequestMapping("/grant/save")
	@ResponseBody
	public Object grantAuthorization(@RequestParam("roleId") String roleId, @RequestParam("permissionId[]") String[] permissionIds) {
		return CommonResponse.getResponse(orgRolePermissionService.grantAuthorization(roleId, permissionIds));
	}
}
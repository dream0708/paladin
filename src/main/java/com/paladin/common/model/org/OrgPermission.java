package com.paladin.common.model.org;

import com.paladin.framework.common.UnDeleteBaseModel;
import javax.persistence.Id;

public class OrgPermission extends UnDeleteBaseModel {

	// id
	@Id
	private String id;

	// 权限名称
	private String name;

	// 权限类型1：菜单2：功能3:分类
	private Integer type;

	// 表现形式1：URL2：CODE
	private Integer expressionType;

	// 表现内容
	private String expressionContent;

	// 权限描述
	private String description;

	// 父ID
	private String parentId;

	// 列表顺序
	private Integer listOrder;

	// 是否系统管理员权限
	private Integer isAdminMenu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getExpressionType() {
		return expressionType;
	}

	public void setExpressionType(Integer expressionType) {
		this.expressionType = expressionType;
	}

	public String getExpressionContent() {
		return expressionContent;
	}

	public void setExpressionContent(String expressionContent) {
		this.expressionContent = expressionContent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public Integer getIsAdminMenu() {
		return isAdminMenu;
	}

	public void setIsAdminMenu(Integer isAdminMenu) {
		this.isAdminMenu = isAdminMenu;
	}

}
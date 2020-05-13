package com.glory.demo.project.system.menu.domain;

import com.glory.demo.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * author : glory
 * date : 2019/12/9 21:23
 * description :
 */
@Data
public class Menu  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 菜单ID */
    private Long menuId;

    /** 菜单名称 */
    private String menuName;

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private Long parentId;

    /** 显示顺序 */
    private String orderNum;

    /** 菜单URL */
    private String url;

    /** 打开方式：menuItem页签 menuBlank新窗口 */
    private String target;

    /** 类型:0目录,1菜单,2按钮 */
    private String menuType;

    /** 菜单状态:0显示,1隐藏 */
    private String visible;

    /** 权限字符串 */
    private String perms;

    /** 菜单图标 */
    private String icon;

    /** 子菜单 */
    private List<Menu> children = new ArrayList<Menu>();
}

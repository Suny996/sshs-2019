-- Create table 系统管理-菜单表

create table SYS_MENU
(
	MENU_CODE	VARCHAR2(32),
	MENU_NAME	VARCHAR2(100),
	MENU_TYPE	VARCHAR2(2),
	MENU_URL	VARCHAR2(256),
	PARENT_MENU_CODE	VARCHAR2(32),
	IS_RELATIVE_PATH	VARCHAR2(10),
	MENU_LEVEL	NUMBER(22),
	MENU_SEQ	NUMBER(22),
	MENU_ICON	VARCHAR2(100),
	MENU_ICONA	VARCHAR2(100),
	IS_VISIBLE	VARCHAR2(10),
	OPEN_MODE	VARCHAR2(20),
	MENU_TIP	VARCHAR2(100),
	IS_START	VARCHAR2(10),
	CRT_USER_CODE	VARCHAR2(32),
	CRT_ORG_CODE	VARCHAR2(32),
	CRT_DATE	DATE(7),
	UPD_USER_CODE	VARCHAR2(32),
	UPD_ORG_CODE	VARCHAR2(32),
	UPD_DATE	DATE(7),
	MENU_MODULE	VARCHAR2(32),
);
-- Add comments to the table 
comment on table SYS_MENU
  is '系统管理-菜单表';
-- Add comments to the columns 
comment on column SYS_MENU.MENU_CODE
  is '菜单编码';
comment on column SYS_MENU.MENU_NAME
  is '菜单名称';
comment on column SYS_MENU.MENU_TYPE
  is '菜单类型:0-节点，1-功能';
comment on column SYS_MENU.MENU_URL
  is '菜单路径URL';
comment on column SYS_MENU.PARENT_MENU_CODE
  is '所属父菜单';
comment on column SYS_MENU.IS_RELATIVE_PATH
  is '是否相对路径';
comment on column SYS_MENU.MENU_LEVEL
  is '菜单层次';
comment on column SYS_MENU.MENU_SEQ
  is '同级菜单顺序号';
comment on column SYS_MENU.MENU_ICON
  is '菜单图标路径/或样式';
comment on column SYS_MENU.MENU_ICONA
  is '菜单激活图标路径/或样式';
comment on column SYS_MENU.IS_VISIBLE
  is '是否可视:1-是,0-否';
comment on column SYS_MENU.OPEN_MODE
  is '主窗口打开、弹出窗口打开';
comment on column SYS_MENU.MENU_TIP
  is '提示信息';
comment on column SYS_MENU.IS_START
  is '是否启用:1-是,0-否';
comment on column SYS_MENU.CRT_USER_CODE
  is '创建人';
comment on column SYS_MENU.CRT_ORG_CODE
  is '创建机构';
comment on column SYS_MENU.CRT_DATE
  is '创建日期';
comment on column SYS_MENU.UPD_USER_CODE
  is '修改人';
comment on column SYS_MENU.UPD_ORG_CODE
  is '修改机构';
comment on column SYS_MENU.UPD_DATE
  is '修改日期';
comment on column SYS_MENU.MENU_MODULE
  is '所属模块';

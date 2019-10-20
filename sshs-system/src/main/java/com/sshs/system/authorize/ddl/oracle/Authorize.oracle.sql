-- Create table 系统管理-角色权限表

create table SYS_AUTHORIZE
(
	AUTHORIZE_ID	varchar  (32) ,
	CRT_DATE	timestamp ,
	UPD_USER_CODE	varchar  (32) ,
	UPD_ORG_CODE	varchar  (32) ,
	UPD_DATE	timestamp ,
	RESOURCE_ID	varchar  (32) ,
	RESOURCE_NAME	varchar  (100) ,
	ROLE_CODE	varchar  (32) ,
	RESOURCE_TYPE	varchar  (10) ,
	AUTHORIZE_TYPE	varchar  (2) ,
	DATA_AUTH_TYPE	varchar  (10) ,
	CRT_USER_CODE	varchar  (32) ,
	CRT_ORG_CODE	varchar  (32) ,
);
-- Add comments to the table 
comment on table SYS_AUTHORIZE
  is '系统管理-角色权限表';
-- Add comments to the columns 
comment on column SYS_AUTHORIZE.AUTHORIZE_ID
  is '权限ID';
comment on column SYS_AUTHORIZE.CRT_DATE
  is '创建日期';
comment on column SYS_AUTHORIZE.UPD_USER_CODE
  is '修改人';
comment on column SYS_AUTHORIZE.UPD_ORG_CODE
  is '修改机构';
comment on column SYS_AUTHORIZE.UPD_DATE
  is '修改日期';
comment on column SYS_AUTHORIZE.RESOURCE_ID
  is '资源ID(包括菜单ID和按钮ID)';
comment on column SYS_AUTHORIZE.RESOURCE_NAME
  is '资源名称';
comment on column SYS_AUTHORIZE.ROLE_CODE
  is '角色编号';
comment on column SYS_AUTHORIZE.RESOURCE_TYPE
  is '资源类型:01-菜单,02-按钮';
comment on column SYS_AUTHORIZE.AUTHORIZE_TYPE
  is '授权类型:01-操作,02-授权,03-全部';
comment on column SYS_AUTHORIZE.DATA_AUTH_TYPE
  is '权限级别，对应机构级别（RESOURCE_TYPE为01且AUTHORIZE_TYPE为01或03时该字段可用）';
comment on column SYS_AUTHORIZE.CRT_USER_CODE
  is '创建人';
comment on column SYS_AUTHORIZE.CRT_ORG_CODE
  is '创建机构';

-- Create table 系统管理-角色表

create table SYS_ROLE
(
	ROLE_ID	varchar  (32) ,
	UPD_USER_CODE	varchar  (32) ,
	UPD_ORG_CODE	varchar  (32) ,
	UPD_DATE	timestamp ,
	ROLE_CODE	varchar  (32) ,
	ROLE_NAME	varchar  (100) ,
	REMARK	varchar  (1,000) ,
	ROLE_LEVEL	varchar  (10) ,
	STATUS	varchar  (10) ,
	CRT_USER_CODE	varchar  (32) ,
	CRT_ORG_CODE	varchar  (32) ,
	CRT_DATE	timestamp ,
);
-- Add comments to the table 
comment on table SYS_ROLE
  is '系统管理-角色表';
-- Add comments to the columns 
comment on column SYS_ROLE.ROLE_ID
  is '角色ID';
comment on column SYS_ROLE.UPD_USER_CODE
  is '修改人';
comment on column SYS_ROLE.UPD_ORG_CODE
  is '修改机构';
comment on column SYS_ROLE.UPD_DATE
  is '修改日期';
comment on column SYS_ROLE.ROLE_CODE
  is '角色编码';
comment on column SYS_ROLE.ROLE_NAME
  is '角色名称';
comment on column SYS_ROLE.REMARK
  is '角色描述';
comment on column SYS_ROLE.ROLE_LEVEL
  is '角色级别:对应机构级别';
comment on column SYS_ROLE.STATUS
  is '状态:01--启用，02--停用';
comment on column SYS_ROLE.CRT_USER_CODE
  is '创建人';
comment on column SYS_ROLE.CRT_ORG_CODE
  is '创建机构';
comment on column SYS_ROLE.CRT_DATE
  is '创建日期';

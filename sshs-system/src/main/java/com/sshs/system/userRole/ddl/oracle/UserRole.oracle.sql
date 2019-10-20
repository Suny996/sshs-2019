-- Create table 系统管理-用户角色对照表

create table SYS_USER_ROLE
(
	USER_ROLE_ID	varchar  (32) ,
	USER_CODE	varchar  (32) ,
	ROLE_CODE	varchar  (32) ,
	CRT_USER_CODE	varchar  (32) ,
	CRT_ORG_CODE	varchar  (32) ,
	CRT_DATE	timestamp ,
	UPD_USER_CODE	varchar  (32) ,
	UPD_ORG_CODE	varchar  (32) ,
	UPD_DATE	timestamp ,
);
-- Add comments to the table 
comment on table SYS_USER_ROLE
  is '系统管理-用户角色对照表';
-- Add comments to the columns 
comment on column SYS_USER_ROLE.USER_ROLE_ID
  is 'ID';
comment on column SYS_USER_ROLE.USER_CODE
  is '用户编号';
comment on column SYS_USER_ROLE.ROLE_CODE
  is '角色编号';
comment on column SYS_USER_ROLE.CRT_USER_CODE
  is '创建人';
comment on column SYS_USER_ROLE.CRT_ORG_CODE
  is '创建机构';
comment on column SYS_USER_ROLE.CRT_DATE
  is '创建日期';
comment on column SYS_USER_ROLE.UPD_USER_CODE
  is '修改人';
comment on column SYS_USER_ROLE.UPD_ORG_CODE
  is '修改机构';
comment on column SYS_USER_ROLE.UPD_DATE
  is '修改日期';

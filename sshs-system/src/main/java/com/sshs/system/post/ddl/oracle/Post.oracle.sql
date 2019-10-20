-- Create table 系统管理-岗位表

create table SYS_POST
(
	POST_ID	varchar  (32) ,
	CRT_DATE	timestamp ,
	UPD_USER_CODE	varchar  (32) ,
	UPD_ORG_CODE	varchar  (32) ,
	UPD_DATE	timestamp ,
	POST_NO	varchar  (32) ,
	POST_NAME	varchar  (50) ,
	POST_SERIAL	varchar  (32) ,
	POST_SERIAL_CHILD	varchar  (32) ,
	POST_DESC	varchar  (255) ,
	STAND_ORG_CODE	varchar  (36) ,
	CRT_USER_CODE	varchar  (32) ,
	CRT_ORG_CODE	varchar  (32) ,
);
-- Add comments to the table 
comment on table SYS_POST
  is '系统管理-岗位表';
-- Add comments to the columns 
comment on column SYS_POST.POST_ID
  is '岗位主键';
comment on column SYS_POST.CRT_DATE
  is '创建日期';
comment on column SYS_POST.UPD_USER_CODE
  is '修改人';
comment on column SYS_POST.UPD_ORG_CODE
  is '修改机构';
comment on column SYS_POST.UPD_DATE
  is '修改日期';
comment on column SYS_POST.POST_NO
  is '岗位编号';
comment on column SYS_POST.POST_NAME
  is '岗位名称';
comment on column SYS_POST.POST_SERIAL
  is '岗位序列:管理序列、专业序列、营销序列、技能序列';
comment on column SYS_POST.POST_SERIAL_CHILD
  is '岗位子序列';
comment on column SYS_POST.POST_DESC
  is '岗位描述';
comment on column SYS_POST.STAND_ORG_CODE
  is '所属标准部门';
comment on column SYS_POST.CRT_USER_CODE
  is '创建人';
comment on column SYS_POST.CRT_ORG_CODE
  is '创建机构';

-- Create table 系统管理-系统操作日志表

create table SYS_LOG
(
	LOG_ID	varchar  (32) ,
	CRT_DATE	timestamp ,
	UPD_USER_CODE	varchar  (32) ,
	UPD_ORG_CODE	varchar  (32) ,
	UPD_DATE	timestamp ,
	USER_CODE	varchar  (32) ,
	USER_NAME	varchar  (32) ,
	MODULE	varchar  (50) ,
	ACTION	varchar  (1,024) ,
	DICT_DESC	varchar  (100) ,
	LEGAL_ORG	varchar  (32) ,
	CRT_USER_CODE	varchar  (32) ,
	CRT_ORG_CODE	varchar  (32) ,
);
-- Add comments to the table 
comment on table SYS_LOG
  is '系统管理-系统操作日志表';
-- Add comments to the columns 
comment on column SYS_LOG.LOG_ID
  is 'ID';
comment on column SYS_LOG.CRT_DATE
  is '创建日期';
comment on column SYS_LOG.UPD_USER_CODE
  is '修改人';
comment on column SYS_LOG.UPD_ORG_CODE
  is '修改机构';
comment on column SYS_LOG.UPD_DATE
  is '修改日期';
comment on column SYS_LOG.USER_CODE
  is '操作用户编号';
comment on column SYS_LOG.USER_NAME
  is '操作用户名称';
comment on column SYS_LOG.MODULE
  is '模块';
comment on column SYS_LOG.ACTION
  is '操作内容';
comment on column SYS_LOG.DICT_DESC
  is '描述';
comment on column SYS_LOG.LEGAL_ORG
  is '法人行社';
comment on column SYS_LOG.CRT_USER_CODE
  is '创建人';
comment on column SYS_LOG.CRT_ORG_CODE
  is '创建机构';

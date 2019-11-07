package com.sshs.system.user.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.apache.ibatis.type.Alias;

/** 
 * SYS_USER->nullbean User类
 * @author sunyonggang
 * @date 2019/11/07
 */
@Alias("User")
@Table(name="SYS_USER")
public class User implements Serializable {

private static final long serialVersionUID = 1L;


}
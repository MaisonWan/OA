package com.icss.hit.hibernate.vo;

import java.util.HashSet;
import java.util.Set;


/**
 * SysPosition entity. @author MyEclipse Persistence Tools
 */

public class SysPosition  implements java.io.Serializable {


    // Fields    

     private Long spsId;
     private String spsName;
     private Set sysUsers = new HashSet(0);


    // Constructors

    /** default constructor */
    public SysPosition() {
    }

	/** minimal constructor */
    public SysPosition(String spsName) {
        this.spsName = spsName;
    }
    
    /** full constructor */
    public SysPosition(String spsName, Set sysUsers) {
        this.spsName = spsName;
        this.sysUsers = sysUsers;
    }

   
    // Property accessors

    public Long getSpsId() {
        return this.spsId;
    }
    
    public void setSpsId(Long spsId) {
        this.spsId = spsId;
    }

    public String getSpsName() {
        return this.spsName;
    }
    
    public void setSpsName(String spsName) {
        this.spsName = spsName;
    }

    public Set getSysUsers() {
        return this.sysUsers;
    }
    
    public void setSysUsers(Set sysUsers) {
        this.sysUsers = sysUsers;
    }
   








}
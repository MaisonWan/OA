package com.icss.hit.hibernate.vo;

import java.util.HashSet;
import java.util.Set;


/**
 * SysDept entity. @author MyEclipse Persistence Tools
 */

public class SysDept  implements java.io.Serializable {


    // Fields    

     private Long sdId;
     private String sdName;
     private String sdInfo;
     private String sdTel;
     private Set sysUsers = new HashSet(0);


    // Constructors

    /** default constructor */
    public SysDept() {
    }

	/** minimal constructor */
    public SysDept(String sdName) {
        this.sdName = sdName;
    }
    
    /** full constructor */
    public SysDept(String sdName, String sdInfo, String sdTel, Set sysUsers) {
        this.sdName = sdName;
        this.sdInfo = sdInfo;
        this.sdTel = sdTel;
        this.sysUsers = sysUsers;
    }

   
    // Property accessors

    public Long getSdId() {
        return this.sdId;
    }
    
    public void setSdId(Long sdId) {
        this.sdId = sdId;
    }

    public String getSdName() {
        return this.sdName;
    }
    
    public void setSdName(String sdName) {
        this.sdName = sdName;
    }

    public String getSdInfo() {
        return this.sdInfo;
    }
    
    public void setSdInfo(String sdInfo) {
        this.sdInfo = sdInfo;
    }

    public String getSdTel() {
        return this.sdTel;
    }
    
    public void setSdTel(String sdTel) {
        this.sdTel = sdTel;
    }

    public Set getSysUsers() {
        return this.sysUsers;
    }
    
    public void setSysUsers(Set sysUsers) {
        this.sysUsers = sysUsers;
    }
   








}
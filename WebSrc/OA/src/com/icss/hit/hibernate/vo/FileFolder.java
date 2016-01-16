package com.icss.hit.hibernate.vo;

import java.util.HashSet;
import java.util.Set;


/**
 * FileFolder entity. @author MyEclipse Persistence Tools
 */

public class FileFolder  implements java.io.Serializable {


    // Fields    

     private Long ffId;
     private SysUser sysUser;
     private String ffName;
     private String ffShare;
     private Set fileses = new HashSet(0);


    // Constructors

    /** default constructor */
    public FileFolder() {
    }

	/** minimal constructor */
    public FileFolder(SysUser sysUser, String ffName, String ffShare) {
        this.sysUser = sysUser;
        this.ffName = ffName;
        this.ffShare = ffShare;
    }
    
    /** full constructor */
    public FileFolder(SysUser sysUser, String ffName, String ffShare, Set fileses) {
        this.sysUser = sysUser;
        this.ffName = ffName;
        this.ffShare = ffShare;
        this.fileses = fileses;
    }

   
    // Property accessors

    public Long getFfId() {
        return this.ffId;
    }
    
    public void setFfId(Long ffId) {
        this.ffId = ffId;
    }

    public SysUser getSysUser() {
        return this.sysUser;
    }
    
    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getFfName() {
        return this.ffName;
    }
    
    public void setFfName(String ffName) {
        this.ffName = ffName;
    }

    public String getFfShare() {
        return this.ffShare;
    }
    
    public void setFfShare(String ffShare) {
        this.ffShare = ffShare;
    }

    public Set getFileses() {
        return this.fileses;
    }
    
    public void setFileses(Set fileses) {
        this.fileses = fileses;
    }
   








}
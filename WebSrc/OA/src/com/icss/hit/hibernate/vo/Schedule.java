package com.icss.hit.hibernate.vo;

import java.util.Date;


/**
 * Schedule entity. @author MyEclipse Persistence Tools
 */

public class Schedule  implements java.io.Serializable {


    // Fields    

     private Long schId;
     private SysUser sysUserBySuIdTo;
     private SysUser sysUserBySuIdFrom;
     private String schTitle;
     private String schContent;
     private Date schBegintime;
     private Date schEndtime;
     private String schRead;
     private String schComplete;


    // Constructors

    /** default constructor */
    public Schedule() {
    }

	/** minimal constructor */
    public Schedule(SysUser sysUserBySuIdTo, SysUser sysUserBySuIdFrom, String schRead, String schComplete) {
        this.sysUserBySuIdTo = sysUserBySuIdTo;
        this.sysUserBySuIdFrom = sysUserBySuIdFrom;
        this.schRead = schRead;
        this.schComplete = schComplete;
    }
    
    /** full constructor */
    public Schedule(SysUser sysUserBySuIdTo, SysUser sysUserBySuIdFrom, String schTitle, String schContent, Date schBegintime, Date schEndtime, String schRead, String schComplete) {
        this.sysUserBySuIdTo = sysUserBySuIdTo;
        this.sysUserBySuIdFrom = sysUserBySuIdFrom;
        this.schTitle = schTitle;
        this.schContent = schContent;
        this.schBegintime = schBegintime;
        this.schEndtime = schEndtime;
        this.schRead = schRead;
        this.schComplete = schComplete;
    }

   
    // Property accessors

    public Long getSchId() {
        return this.schId;
    }
    
    public void setSchId(Long schId) {
        this.schId = schId;
    }

    public SysUser getSysUserBySuIdTo() {
        return this.sysUserBySuIdTo;
    }
    
    public void setSysUserBySuIdTo(SysUser sysUserBySuIdTo) {
        this.sysUserBySuIdTo = sysUserBySuIdTo;
    }

    public SysUser getSysUserBySuIdFrom() {
        return this.sysUserBySuIdFrom;
    }
    
    public void setSysUserBySuIdFrom(SysUser sysUserBySuIdFrom) {
        this.sysUserBySuIdFrom = sysUserBySuIdFrom;
    }

    public String getSchTitle() {
        return this.schTitle;
    }
    
    public void setSchTitle(String schTitle) {
        this.schTitle = schTitle;
    }

    public String getSchContent() {
        return this.schContent;
    }
    
    public void setSchContent(String schContent) {
        this.schContent = schContent;
    }

    public Date getSchBegintime() {
        return this.schBegintime;
    }
    
    public void setSchBegintime(Date schBegintime) {
        this.schBegintime = schBegintime;
    }

    public Date getSchEndtime() {
        return this.schEndtime;
    }
    
    public void setSchEndtime(Date schEndtime) {
        this.schEndtime = schEndtime;
    }

    public String getSchRead() {
        return this.schRead;
    }
    
    public void setSchRead(String schRead) {
        this.schRead = schRead;
    }

    public String getSchComplete() {
        return this.schComplete;
    }
    
    public void setSchComplete(String schComplete) {
        this.schComplete = schComplete;
    }
   








}
package com.icss.hit.hibernate.vo;



/**
 * ScheduleConfig entity. @author MyEclipse Persistence Tools
 */

public class ScheduleConfig  implements java.io.Serializable {


    // Fields    

     private Long scId;
     private SysUser sysUserBySuIdTo;
     private SysUser sysUserBySuIdFrom;


    // Constructors

    /** default constructor */
    public ScheduleConfig() {
    }

    
    /** full constructor */
    public ScheduleConfig(SysUser sysUserBySuIdTo, SysUser sysUserBySuIdFrom) {
        this.sysUserBySuIdTo = sysUserBySuIdTo;
        this.sysUserBySuIdFrom = sysUserBySuIdFrom;
    }

   
    // Property accessors

    public Long getScId() {
        return this.scId;
    }
    
    public void setScId(Long scId) {
        this.scId = scId;
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
   








}
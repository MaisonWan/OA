package com.icss.hit.hibernate.vo;



/**
 * SysRolePower entity. @author MyEclipse Persistence Tools
 */

public class SysRolePower  implements java.io.Serializable {


    // Fields    

     private Long srpId;
     private SysPower sysPower;
     private SysRole sysRole;


    // Constructors

    /** default constructor */
    public SysRolePower() {
    }

    
    /** full constructor */
    public SysRolePower(SysPower sysPower, SysRole sysRole) {
        this.sysPower = sysPower;
        this.sysRole = sysRole;
    }

   
    // Property accessors

    public Long getSrpId() {
        return this.srpId;
    }
    
    public void setSrpId(Long srpId) {
        this.srpId = srpId;
    }

    public SysPower getSysPower() {
        return this.sysPower;
    }
    
    public void setSysPower(SysPower sysPower) {
        this.sysPower = sysPower;
    }

    public SysRole getSysRole() {
        return this.sysRole;
    }
    
    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }
   








}
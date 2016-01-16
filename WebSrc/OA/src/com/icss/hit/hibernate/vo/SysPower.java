package com.icss.hit.hibernate.vo;

import java.util.HashSet;
import java.util.Set;


/**
 * SysPower entity. @author MyEclipse Persistence Tools
 */

public class SysPower  implements java.io.Serializable {


    // Fields    

     private Long spId;
     private SysPowerType sysPowerType;
     private String spName;
     private String spValue;
     private Set sysRolePowers = new HashSet(0);


    // Constructors

    /** default constructor */
    public SysPower() {
    }

	/** minimal constructor */
    public SysPower(SysPowerType sysPowerType, String spName) {
        this.sysPowerType = sysPowerType;
        this.spName = spName;
    }
    
    /** full constructor */
    public SysPower(SysPowerType sysPowerType, String spName, String spValue, Set sysRolePowers) {
        this.sysPowerType = sysPowerType;
        this.spName = spName;
        this.spValue = spValue;
        this.sysRolePowers = sysRolePowers;
    }

   
    // Property accessors

    public Long getSpId() {
        return this.spId;
    }
    
    public void setSpId(Long spId) {
        this.spId = spId;
    }

    public SysPowerType getSysPowerType() {
        return this.sysPowerType;
    }
    
    public void setSysPowerType(SysPowerType sysPowerType) {
        this.sysPowerType = sysPowerType;
    }

    public String getSpName() {
        return this.spName;
    }
    
    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSpValue() {
        return this.spValue;
    }
    
    public void setSpValue(String spValue) {
        this.spValue = spValue;
    }

    public Set getSysRolePowers() {
        return this.sysRolePowers;
    }
    
    public void setSysRolePowers(Set sysRolePowers) {
        this.sysRolePowers = sysRolePowers;
    }
   








}
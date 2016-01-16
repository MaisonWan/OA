package com.icss.hit.hibernate.vo;

import java.util.HashSet;
import java.util.Set;


/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */

public class SysRole  implements java.io.Serializable {


    // Fields    

     private Long srId;
     private String srName;
     private Set sysRolePowers = new HashSet(0);
     private Set sysUsers = new HashSet(0);


    // Constructors

    /** default constructor */
    public SysRole() {
    }

	/** minimal constructor */
    public SysRole(String srName) {
        this.srName = srName;
    }
    
    /** full constructor */
    public SysRole(String srName, Set sysRolePowers, Set sysUsers) {
        this.srName = srName;
        this.sysRolePowers = sysRolePowers;
        this.sysUsers = sysUsers;
    }

   
    // Property accessors

    public Long getSrId() {
        return this.srId;
    }
    
    public void setSrId(Long srId) {
        this.srId = srId;
    }

    public String getSrName() {
        return this.srName;
    }
    
    public void setSrName(String srName) {
        this.srName = srName;
    }

    public Set getSysRolePowers() {
        return this.sysRolePowers;
    }
    
    public void setSysRolePowers(Set sysRolePowers) {
        this.sysRolePowers = sysRolePowers;
    }

    public Set getSysUsers() {
        return this.sysUsers;
    }
    
    public void setSysUsers(Set sysUsers) {
        this.sysUsers = sysUsers;
    }
   








}
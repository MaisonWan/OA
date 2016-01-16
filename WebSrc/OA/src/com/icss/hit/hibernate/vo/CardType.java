package com.icss.hit.hibernate.vo;

import java.util.HashSet;
import java.util.Set;


/**
 * CardType entity. @author MyEclipse Persistence Tools
 */

public class CardType  implements java.io.Serializable {


    // Fields    

     private Long ctId;
     private SysUser sysUser;
     private String ctName;
     private Set cards = new HashSet(0);


    // Constructors

    /** default constructor */
    public CardType() {
    }

	/** minimal constructor */
    public CardType(SysUser sysUser, String ctName) {
        this.sysUser = sysUser;
        this.ctName = ctName;
    }
    
    /** full constructor */
    public CardType(SysUser sysUser, String ctName, Set cards) {
        this.sysUser = sysUser;
        this.ctName = ctName;
        this.cards = cards;
    }

   
    // Property accessors

    public Long getCtId() {
        return this.ctId;
    }
    
    public void setCtId(Long ctId) {
        this.ctId = ctId;
    }

    public SysUser getSysUser() {
        return this.sysUser;
    }
    
    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getCtName() {
        return this.ctName;
    }
    
    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public Set getCards() {
        return this.cards;
    }
    
    public void setCards(Set cards) {
        this.cards = cards;
    }
   








}
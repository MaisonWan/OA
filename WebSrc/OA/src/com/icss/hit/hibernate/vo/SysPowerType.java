package com.icss.hit.hibernate.vo;

import java.util.HashSet;
import java.util.Set;


/**
 * SysPowerType entity. @author MyEclipse Persistence Tools
 */

public class SysPowerType  implements java.io.Serializable {


    // Fields    

     private Long sptId;
     private String sptName;
     private Set sysPowers = new HashSet(0);


    // Constructors

    /** default constructor */
    public SysPowerType() {
    }

    
    /** full constructor */
    public SysPowerType(String sptName, Set sysPowers) {
        this.sptName = sptName;
        this.sysPowers = sysPowers;
    }

   
    // Property accessors

    public Long getSptId() {
        return this.sptId;
    }
    
    public void setSptId(Long sptId) {
        this.sptId = sptId;
    }

    public String getSptName() {
        return this.sptName;
    }
    
    public void setSptName(String sptName) {
        this.sptName = sptName;
    }

    public Set getSysPowers() {
        return this.sysPowers;
    }
    
    public void setSysPowers(Set sysPowers) {
        this.sysPowers = sysPowers;
    }
   








}
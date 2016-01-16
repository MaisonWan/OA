package com.icss.hit.hibernate.vo;

import java.util.HashSet;
import java.util.Set;


/**
 * Room entity. @author MyEclipse Persistence Tools
 */

public class Room  implements java.io.Serializable {


    // Fields    

     private Long RId;
     private String RName;
     private Long RContain;
     private String RInfo;
     private Set roomRegs = new HashSet(0);
     private Set meetings = new HashSet(0);


    // Constructors

    /** default constructor */
    public Room() {
    }

	/** minimal constructor */
    public Room(String RName, Long RContain) {
        this.RName = RName;
        this.RContain = RContain;
    }
    
    /** full constructor */
    public Room(String RName, Long RContain, String RInfo, Set roomRegs, Set meetings) {
        this.RName = RName;
        this.RContain = RContain;
        this.RInfo = RInfo;
        this.roomRegs = roomRegs;
        this.meetings = meetings;
    }

   
    // Property accessors

    public Long getRId() {
        return this.RId;
    }
    
    public void setRId(Long RId) {
        this.RId = RId;
    }

    public String getRName() {
        return this.RName;
    }
    
    public void setRName(String RName) {
        this.RName = RName;
    }

    public Long getRContain() {
        return this.RContain;
    }
    
    public void setRContain(Long RContain) {
        this.RContain = RContain;
    }

    public String getRInfo() {
        return this.RInfo;
    }
    
    public void setRInfo(String RInfo) {
        this.RInfo = RInfo;
    }

    public Set getRoomRegs() {
        return this.roomRegs;
    }
    
    public void setRoomRegs(Set roomRegs) {
        this.roomRegs = roomRegs;
    }

    public Set getMeetings() {
        return this.meetings;
    }
    
    public void setMeetings(Set meetings) {
        this.meetings = meetings;
    }
   








}
package com.icss.hit.hibernate.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Meeting entity. @author MyEclipse Persistence Tools
 */

public class Meeting  implements java.io.Serializable {


    // Fields    

     private Long mtId;
     private SysUser sysUser;
     private Room room;
     private String mtTitle;
     private String mtContent;
     private Date mtBegintime;
     private Date mtEndtime;
     private Set meetingAttends = new HashSet(0);


    // Constructors

    /** default constructor */
    public Meeting() {
    }

	/** minimal constructor */
    public Meeting(SysUser sysUser, Room room) {
        this.sysUser = sysUser;
        this.room = room;
    }
    
    /** full constructor */
    public Meeting(SysUser sysUser, Room room, String mtTitle, String mtContent, Date mtBegintime, Date mtEndtime, Set meetingAttends) {
        this.sysUser = sysUser;
        this.room = room;
        this.mtTitle = mtTitle;
        this.mtContent = mtContent;
        this.mtBegintime = mtBegintime;
        this.mtEndtime = mtEndtime;
        this.meetingAttends = meetingAttends;
    }

   
    // Property accessors

    public Long getMtId() {
        return this.mtId;
    }
    
    public void setMtId(Long mtId) {
        this.mtId = mtId;
    }

    public SysUser getSysUser() {
        return this.sysUser;
    }
    
    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public Room getRoom() {
        return this.room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }

    public String getMtTitle() {
        return this.mtTitle;
    }
    
    public void setMtTitle(String mtTitle) {
        this.mtTitle = mtTitle;
    }

    public String getMtContent() {
        return this.mtContent;
    }
    
    public void setMtContent(String mtContent) {
        this.mtContent = mtContent;
    }

    public Date getMtBegintime() {
        return this.mtBegintime;
    }
    
    public void setMtBegintime(Date mtBegintime) {
        this.mtBegintime = mtBegintime;
    }

    public Date getMtEndtime() {
        return this.mtEndtime;
    }
    
    public void setMtEndtime(Date mtEndtime) {
        this.mtEndtime = mtEndtime;
    }

    public Set getMeetingAttends() {
        return this.meetingAttends;
    }
    
    public void setMeetingAttends(Set meetingAttends) {
        this.meetingAttends = meetingAttends;
    }
   








}
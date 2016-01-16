package com.icss.hit.hibernate.vo;



/**
 * MeetingAttend entity. @author MyEclipse Persistence Tools
 */

public class MeetingAttend  implements java.io.Serializable {


    // Fields    

     private Long maId;
     private SysUser sysUser;
     private Meeting meeting;


    // Constructors

    /** default constructor */
    public MeetingAttend() {
    }

    
    /** full constructor */
    public MeetingAttend(SysUser sysUser, Meeting meeting) {
        this.sysUser = sysUser;
        this.meeting = meeting;
    }

   
    // Property accessors

    public Long getMaId() {
        return this.maId;
    }
    
    public void setMaId(Long maId) {
        this.maId = maId;
    }

    public SysUser getSysUser() {
        return this.sysUser;
    }
    
    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public Meeting getMeeting() {
        return this.meeting;
    }
    
    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
   








}
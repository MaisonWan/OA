package com.icss.hit.hibernate.vo;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;

import org.hibernate.Hibernate;


/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */

public class SysUser  implements java.io.Serializable {


    // Fields    
	 /**
	 * 自己添加一个头像生成目录
	 */
	 private String path = null;
     private Long suId;
     private SysPosition sysPosition;
     private SysRole sysRole;
     private SysDept sysDept;
     private String suUid;
     private String suUsername;
     private String suSex;
     private Blob suPhoto;
     private String suTel;
     private String suCellphone;
     private String suEmail;
     private String suHobby;
     private Clob suInfo;
     private String suPassword;
     private Set roomRegs = new HashSet(0);
     private Set messages = new HashSet(0);
     private Set schedulesForSuIdFrom = new HashSet(0);
     private Set scheduleConfigsForSuIdTo = new HashSet(0);
     private Set meetingAttends = new HashSet(0);
     private Set scheduleConfigsForSuIdFrom = new HashSet(0);
     private Set schedulesForSuIdTo = new HashSet(0);
     private Set fileFolders = new HashSet(0);
     private Set meetings = new HashSet(0);
     private Set receiverInfos = new HashSet(0);
     private Set cardTypes = new HashSet(0);


    // Constructors

    /** default constructor */
    public SysUser() {
    }

	/** minimal constructor */
    public SysUser(SysRole sysRole, SysDept sysDept, String suUid, String suUsername, String suSex, String suPassword) {
        this.sysRole = sysRole;
        this.sysDept = sysDept;
        this.suUid = suUid;
        this.suUsername = suUsername;
        this.suSex = suSex;
        this.suPassword = suPassword;
    }
    
    /** full constructor */
    public SysUser(SysPosition sysPosition, SysRole sysRole, SysDept sysDept, String suUid, String suUsername, String suSex, Blob suPhoto, String suTel, String suCellphone, String suEmail, String suHobby, Clob suInfo, String suPassword, Set roomRegs, Set messages, Set schedulesForSuIdFrom, Set scheduleConfigsForSuIdTo, Set meetingAttends, Set scheduleConfigsForSuIdFrom, Set schedulesForSuIdTo, Set fileFolders, Set meetings, Set receiverInfos, Set cardTypes) {
        this.sysPosition = sysPosition;
        this.sysRole = sysRole;
        this.sysDept = sysDept;
        this.suUid = suUid;
        this.suUsername = suUsername;
        this.suSex = suSex;
        this.suPhoto = suPhoto;
        this.suTel = suTel;
        this.suCellphone = suCellphone;
        this.suEmail = suEmail;
        this.suHobby = suHobby;
        this.suInfo = suInfo;
        this.suPassword = suPassword;
        this.roomRegs = roomRegs;
        this.messages = messages;
        this.schedulesForSuIdFrom = schedulesForSuIdFrom;
        this.scheduleConfigsForSuIdTo = scheduleConfigsForSuIdTo;
        this.meetingAttends = meetingAttends;
        this.scheduleConfigsForSuIdFrom = scheduleConfigsForSuIdFrom;
        this.schedulesForSuIdTo = schedulesForSuIdTo;
        this.fileFolders = fileFolders;
        this.meetings = meetings;
        this.receiverInfos = receiverInfos;
        this.cardTypes = cardTypes;
    }

	
	/**
	 * 自己编写的，对于Clob读取操作的封装。便于返回信息的实体类
	 * @return 返回自我介绍的详细信息
	 */
	public String getSuInfos(){
		try {
			if( this.suInfo == null )
				return null;
			return this.suInfo.getSubString(1, (int)suInfo.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 自己编写的，对于Clob写入的操作的封装。便于返回信息的实体类
	 * @param suInfo 自我介绍的信息
	 */
	public void setSuInfos( String suInfo ){
		this.suInfo = Hibernate.createClob(suInfo);
	}
	
	public String getSuPhotos(){
		// 如果数据库中没有记录，则返回null
		if( suPhoto == null )
			return null;
		try {
			// 获得二进制的流
			InputStream is = this.suPhoto.getBinaryStream();
			// 创建文件输出流
			FileOutputStream fos = new FileOutputStream(path + this.suId + ".jpg");
			byte[] buf = new byte[102400];
			int len;
			while( (len = is.read(buf)) != -1 ){
				fos.write(buf,0,len);
			}
			fos.close();
			is.close();
			return this.suId + ".jpg";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	// Property accessors

    public Long getSuId() {
        return this.suId;
    }
    
    public void setSuId(Long suId) {
        this.suId = suId;
    }

    public SysPosition getSysPosition() {
        return this.sysPosition;
    }
    
    public void setSysPosition(SysPosition sysPosition) {
        this.sysPosition = sysPosition;
    }

    public SysRole getSysRole() {
        return this.sysRole;
    }
    
    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public SysDept getSysDept() {
        return this.sysDept;
    }
    
    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    public String getSuUid() {
        return this.suUid;
    }
    
    public void setSuUid(String suUid) {
        this.suUid = suUid;
    }

    public String getSuUsername() {
        return this.suUsername;
    }
    
    public void setSuUsername(String suUsername) {
        this.suUsername = suUsername;
    }

    public String getSuSex() {
        return this.suSex;
    }
    
    public void setSuSex(String suSex) {
        this.suSex = suSex;
    }

    public Blob getSuPhoto() {
        return this.suPhoto;
    }
    
    public void setSuPhoto(Blob suPhoto) {
        this.suPhoto = suPhoto;
    }

    public String getSuTel() {
        return this.suTel;
    }
    
    public void setSuTel(String suTel) {
        this.suTel = suTel;
    }

    public String getSuCellphone() {
        return this.suCellphone;
    }
    
    public void setSuCellphone(String suCellphone) {
        this.suCellphone = suCellphone;
    }

    public String getSuEmail() {
        return this.suEmail;
    }
    
    public void setSuEmail(String suEmail) {
        this.suEmail = suEmail;
    }

    public String getSuHobby() {
        return this.suHobby;
    }
    
    public void setSuHobby(String suHobby) {
        this.suHobby = suHobby;
    }

    public Clob getSuInfo() {
        return this.suInfo;
    }
    
    public void setSuInfo(Clob suInfo) {
        this.suInfo = suInfo;
    }

    public String getSuPassword() {
        return this.suPassword;
    }
    
    public void setSuPassword(String suPassword) {
        this.suPassword = suPassword;
    }

    public Set getRoomRegs() {
        return this.roomRegs;
    }
    
    public void setRoomRegs(Set roomRegs) {
        this.roomRegs = roomRegs;
    }

    public Set getMessages() {
        return this.messages;
    }
    
    public void setMessages(Set messages) {
        this.messages = messages;
    }

    public Set getSchedulesForSuIdFrom() {
        return this.schedulesForSuIdFrom;
    }
    
    public void setSchedulesForSuIdFrom(Set schedulesForSuIdFrom) {
        this.schedulesForSuIdFrom = schedulesForSuIdFrom;
    }

    public Set getScheduleConfigsForSuIdTo() {
        return this.scheduleConfigsForSuIdTo;
    }
    
    public void setScheduleConfigsForSuIdTo(Set scheduleConfigsForSuIdTo) {
        this.scheduleConfigsForSuIdTo = scheduleConfigsForSuIdTo;
    }

    public Set getMeetingAttends() {
        return this.meetingAttends;
    }
    
    public void setMeetingAttends(Set meetingAttends) {
        this.meetingAttends = meetingAttends;
    }

    public Set getScheduleConfigsForSuIdFrom() {
        return this.scheduleConfigsForSuIdFrom;
    }
    
    public void setScheduleConfigsForSuIdFrom(Set scheduleConfigsForSuIdFrom) {
        this.scheduleConfigsForSuIdFrom = scheduleConfigsForSuIdFrom;
    }

    public Set getSchedulesForSuIdTo() {
        return this.schedulesForSuIdTo;
    }
    
    public void setSchedulesForSuIdTo(Set schedulesForSuIdTo) {
        this.schedulesForSuIdTo = schedulesForSuIdTo;
    }

    public Set getFileFolders() {
        return this.fileFolders;
    }
    
    public void setFileFolders(Set fileFolders) {
        this.fileFolders = fileFolders;
    }

    public Set getMeetings() {
        return this.meetings;
    }
    
    public void setMeetings(Set meetings) {
        this.meetings = meetings;
    }

    public Set getReceiverInfos() {
        return this.receiverInfos;
    }
    
    public void setReceiverInfos(Set receiverInfos) {
        this.receiverInfos = receiverInfos;
    }

    public Set getCardTypes() {
        return this.cardTypes;
    }
    
    public void setCardTypes(Set cardTypes) {
        this.cardTypes = cardTypes;
    }

	public void setPath(String path) {
		this.path = path;
	}
   








}
package com.icss.hit.hibernate.vo;

import java.util.Date;

/**
 * RoomReg entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class RoomReg implements java.io.Serializable {

	// Fields

	private Long rrId;
	private SysUser sysUser;
	private Room room;
	private String rrTitle;
	private String rrContent;
	private Date rrBegintime;
	private Date rrEndtime;
	private String rrInfo;
	private String rrPass;
	private Long rrAttendsum;

	// Constructors

	/** default constructor */
	public RoomReg() {
	}

	/** minimal constructor */
	public RoomReg(SysUser sysUser, Room room) {
		this.sysUser = sysUser;
		this.room = room;
	}

	/** full constructor */
	public RoomReg(SysUser sysUser, Room room, String rrTitle,
			String rrContent, Date rrBegintime, Date rrEndtime, String rrInfo,
			String rrPass, Long rrAttendsum) {
		this.sysUser = sysUser;
		this.room = room;
		this.rrTitle = rrTitle;
		this.rrContent = rrContent;
		this.rrBegintime = rrBegintime;
		this.rrEndtime = rrEndtime;
		this.rrInfo = rrInfo;
		this.rrPass = rrPass;
		this.rrAttendsum = rrAttendsum;
	}

	// Property accessors

	public Long getRrId() {
		return this.rrId;
	}

	public void setRrId(Long rrId) {
		this.rrId = rrId;
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

	public String getRrTitle() {
		return this.rrTitle;
	}

	public void setRrTitle(String rrTitle) {
		this.rrTitle = rrTitle;
	}

	public String getRrContent() {
		return this.rrContent;
	}

	public void setRrContent(String rrContent) {
		this.rrContent = rrContent;
	}

	public Date getRrBegintime() {
		return this.rrBegintime;
	}

	public void setRrBegintime(Date rrBegintime) {
		this.rrBegintime = rrBegintime;
	}

	public Date getRrEndtime() {
		return this.rrEndtime;
	}

	public void setRrEndtime(Date rrEndtime) {
		this.rrEndtime = rrEndtime;
	}

	public String getRrInfo() {
		return this.rrInfo;
	}

	public void setRrInfo(String rrInfo) {
		this.rrInfo = rrInfo;
	}

	public String getRrPass() {
		return this.rrPass;
	}

	public void setRrPass(String rrPass) {
		this.rrPass = rrPass;
	}

	public Long getRrAttendsum() {
		return this.rrAttendsum;
	}

	public void setRrAttendsum(Long rrAttendsum) {
		this.rrAttendsum = rrAttendsum;
	}

}
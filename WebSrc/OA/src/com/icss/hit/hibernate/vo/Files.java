package com.icss.hit.hibernate.vo;

import java.util.Date;

/**
 * Files entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Files implements java.io.Serializable {

	// Fields

	private Long FId;
	private FileFolder fileFolder;
	private String FName;
	private Date FTime;

	// Constructors

	/** default constructor */
	public Files() {
	}

	/** minimal constructor */
	public Files(FileFolder fileFolder) {
		this.fileFolder = fileFolder;
	}

	/** full constructor */
	public Files(FileFolder fileFolder, String FName, Date FTime) {
		this.fileFolder = fileFolder;
		this.FName = FName;
		this.FTime = FTime;
	}

	// Property accessors

	public Long getFId() {
		return this.FId;
	}

	public void setFId(Long FId) {
		this.FId = FId;
	}

	public FileFolder getFileFolder() {
		return this.fileFolder;
	}

	public void setFileFolder(FileFolder fileFolder) {
		this.fileFolder = fileFolder;
	}

	public String getFName() {
		return this.FName;
	}

	public void setFName(String FName) {
		this.FName = FName;
	}

	public Date getFTime() {
		return this.FTime;
	}

	public void setFTime(Date FTime) {
		this.FTime = FTime;
	}

}
package com.icss.hit.hibernate.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Message entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MessageReceivers implements java.io.Serializable {

	// Fields

	private Long msId;
	private String msTitle;
	private String msContent;
	private String msFile;
	private Date msSendtime;
	private String msBox;
	private String msDelete;
	private String[] receivers;    //收件人姓名
	private Long[] receiverIDs;    //收件人主键ID
	private String[] empno;        //收件人的员工号
	// Constructors

	public Long[] getReceiverIDs() {
		return receiverIDs;
	}

	public void setReceiverIDs(Long[] receiverIDs) {
		this.receiverIDs = receiverIDs;
	}

	/** default constructor */
	public MessageReceivers() {
	}

	/** minimal constructor */
	public MessageReceivers( Date msSendtime, String msBox,
			String msDelete) {
		this.msSendtime = msSendtime;
		this.msBox = msBox;
		this.msDelete = msDelete;
	}

	/** full constructor */
	public MessageReceivers( String msTitle, String msContent,
			String msFile, Date msSendtime, String msBox, String msDelete,
			String[] receiverInfos) {
		this.msTitle = msTitle;
		this.msContent = msContent;
		this.msFile = msFile;
		this.msSendtime = msSendtime;
		this.msBox = msBox;
		this.msDelete = msDelete;
		this.receivers = receiverInfos;
	}

	// Property accessors

	public Long getMsId() {
		return this.msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}


	public String getMsTitle() {
		return this.msTitle;
	}

	public void setMsTitle(String msTitle) {
		this.msTitle = msTitle;
	}

	public String getMsContent() {
		return this.msContent;
	}

	public void setMsContent(String msContent) {
		this.msContent = msContent;
	}

	public String getMsFile() {
		return this.msFile;
	}

	public void setMsFile(String msFile) {
		this.msFile = msFile;
	}

	public Date getMsSendtime() {
		return this.msSendtime;
	}

	public void setMsSendtime(Date msSendtime) {
		this.msSendtime = msSendtime;
	}

	public String getMsBox() {
		return this.msBox;
	}

	public void setMsBox(String msBox) {
		this.msBox = msBox;
	}

	public String getMsDelete() {
		return this.msDelete;
	}

	public void setMsDelete(String msDelete) {
		this.msDelete = msDelete;
	}

	public String[] getReceiverInfos() {
		return this.receivers;
	}

	public void setReceiverInfos(String[] receiverInfos) {
		this.receivers = receiverInfos;
	}

	public String[] getEmpno() {
		return empno;
	}

	public void setEmpno(String[] empno) {
		this.empno = empno;
	}

}
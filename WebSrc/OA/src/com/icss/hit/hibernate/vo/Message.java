package com.icss.hit.hibernate.vo;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;


/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message  implements java.io.Serializable {


    // Fields    

     private Long msId;
     private SysUser sysUser;
     private String msTitle;
     private Clob msContents;
     private String msFile;
     private Date msSendtime;
     private String msBox;
     private String msDelete;
     private Set receiverInfos = new HashSet(0);


    // Constructors

    
    /** default constructor */
    public Message() {
    }

	/** minimal constructor */
    public Message(SysUser sysUser, Date msSendtime, String msBox, String msDelete) {
        this.sysUser = sysUser;
        this.msSendtime = msSendtime;
        this.msBox = msBox;
        this.msDelete = msDelete;
    }
    
    /** full constructor */
    public Message(SysUser sysUser, String msTitle, Clob msContents, String msFile, Date msSendtime, String msBox, String msDelete, Set receiverInfos) {
        this.sysUser = sysUser;
        this.msTitle = msTitle;
        this.msContents = msContents;
        this.msFile = msFile;
        this.msSendtime = msSendtime;
        this.msBox = msBox;
        this.msDelete = msDelete;
        this.receiverInfos = receiverInfos;
    }

   
    // Property accessors

    public Long getMsId() {
        return this.msId;
    }
    
    public void setMsId(Long msId) {
        this.msId = msId;
    }

    public SysUser getSysUser() {
        return this.sysUser;
    }
    
    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getMsTitle() {
        return this.msTitle;
    }
    
    public void setMsTitle(String msTitle) {
        this.msTitle = msTitle;
    }

    public Clob getMsContents() {
        return this.msContents;
    }
    
    public void setMsContents(Clob msContents) {
        this.msContents = msContents;
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

    public Set getReceiverInfos() {
        return this.receiverInfos;
    }
    
    public void setReceiverInfos(Set receiverInfos) {
        this.receiverInfos = receiverInfos;
    }

	public String getMsContent() {
		try {
			if( this.msContents == null )
				return null;
			return this.msContents.getSubString(1, (int)msContents.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void setMsContent(String msContent) {
		this.msContents = Hibernate.createClob(msContent);
	}
   








}
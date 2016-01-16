package com.icss.hit.hibernate.vo;



/**
 * ReceiverInfo entity. @author MyEclipse Persistence Tools
 */

public class ReceiverInfo  implements java.io.Serializable {


    // Fields    

     private Long riId;
     private SysUser sysUser;
     private Message message;
     private String riBox;
     private String riRead;
     private String riDelete;


    // Constructors

    /** default constructor */
    public ReceiverInfo() {
    }

    
    /** full constructor */
    public ReceiverInfo(SysUser sysUser, Message message, String riBox, String riRead, String riDelete) {
        this.sysUser = sysUser;
        this.message = message;
        this.riBox = riBox;
        this.riRead = riRead;
        this.riDelete = riDelete;
    }

   
    // Property accessors

    public Long getRiId() {
        return this.riId;
    }
    
    public void setRiId(Long riId) {
        this.riId = riId;
    }

    public SysUser getSysUser() {
        return this.sysUser;
    }
    
    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public Message getMessage() {
        return this.message;
    }
    
    public void setMessage(Message message) {
        this.message = message;
    }

    public String getRiBox() {
        return this.riBox;
    }
    
    public void setRiBox(String riBox) {
        this.riBox = riBox;
    }

    public String getRiRead() {
        return this.riRead;
    }
    
    public void setRiRead(String riRead) {
        this.riRead = riRead;
    }

    public String getRiDelete() {
        return this.riDelete;
    }
    
    public void setRiDelete(String riDelete) {
        this.riDelete = riDelete;
    }
   








}
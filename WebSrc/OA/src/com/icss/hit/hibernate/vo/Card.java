package com.icss.hit.hibernate.vo;

import java.sql.Clob;
import java.sql.SQLException;

import org.hibernate.Hibernate;


/**
 * Card entity. @author MyEclipse Persistence Tools
 */

public class Card  implements java.io.Serializable {


    // Fields    

     private Long cdId;
     private CardType cardType;
     private String cdName;
     private String cdSex;
     private String cdCompany;
     private String cdPosition;
     private String cdTel;
     private String cdCellphone;
     private String cdEmail;
     private String cdFax;
     private Clob cdInfo;
     private String cdShare;


    // Constructors

    /** default constructor */
    public Card() {
    }

	/** minimal constructor */
    public Card(CardType cardType, String cdName, String cdShare) {
        this.cardType = cardType;
        this.cdName = cdName;
        this.cdShare = cdShare;
    }
    
    /** full constructor */
    public Card(CardType cardType, String cdName, String cdSex, String cdCompany, String cdPosition, String cdTel, String cdCellphone, String cdEmail, String cdFax, Clob cdInfo, String cdShare) {
        this.cardType = cardType;
        this.cdName = cdName;
        this.cdSex = cdSex;
        this.cdCompany = cdCompany;
        this.cdPosition = cdPosition;
        this.cdTel = cdTel;
        this.cdCellphone = cdCellphone;
        this.cdEmail = cdEmail;
        this.cdFax = cdFax;
        this.cdInfo = cdInfo;
        this.cdShare = cdShare;
    }

	/**
	 * 自己编写的，对于Clob读取操作的封装。便于返回信息的实体类
	 * @return 返回自我介绍的详细信息
	 */
	public String getCdInfos(){
		try {
			if( this.cdInfo != null )
				return this.cdInfo.getSubString(1, (int)cdInfo.length());
			return null;
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
	public void setCdInfos( String cdInfo ){
		this.cdInfo = Hibernate.createClob(cdInfo);
	}   
    // Property accessors

    public Long getCdId() {
        return this.cdId;
    }
    
    public void setCdId(Long cdId) {
        this.cdId = cdId;
    }

    public CardType getCardType() {
        return this.cardType;
    }
    
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCdName() {
        return this.cdName;
    }
    
    public void setCdName(String cdName) {
        this.cdName = cdName;
    }

    public String getCdSex() {
        return this.cdSex;
    }
    
    public void setCdSex(String cdSex) {
        this.cdSex = cdSex;
    }

    public String getCdCompany() {
        return this.cdCompany;
    }
    
    public void setCdCompany(String cdCompany) {
        this.cdCompany = cdCompany;
    }

    public String getCdPosition() {
        return this.cdPosition;
    }
    
    public void setCdPosition(String cdPosition) {
        this.cdPosition = cdPosition;
    }

    public String getCdTel() {
        return this.cdTel;
    }
    
    public void setCdTel(String cdTel) {
        this.cdTel = cdTel;
    }

    public String getCdCellphone() {
        return this.cdCellphone;
    }
    
    public void setCdCellphone(String cdCellphone) {
        this.cdCellphone = cdCellphone;
    }

    public String getCdEmail() {
        return this.cdEmail;
    }
    
    public void setCdEmail(String cdEmail) {
        this.cdEmail = cdEmail;
    }

    public String getCdFax() {
        return this.cdFax;
    }
    
    public void setCdFax(String cdFax) {
        this.cdFax = cdFax;
    }

    public Clob getCdInfo() {
        return this.cdInfo;
    }
    
    public void setCdInfo(Clob cdInfo) {
        this.cdInfo = cdInfo;
    }

    public String getCdShare() {
        return this.cdShare;
    }
    
    public void setCdShare(String cdShare) {
        this.cdShare = cdShare;
    }
   








}
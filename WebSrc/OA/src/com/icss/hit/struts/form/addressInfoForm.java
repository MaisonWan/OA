
package com.icss.hit.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/** 
 * MyEclipse Struts
 * Creation date: 07-31-2009
 * 
 * XDoclet definition:
 * @struts.form name="addressInfoForm"
 */
public class addressInfoForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** type property */
	private String type;

	/** suUser property */
	private String suUser;

	/** seDept property */
	private String suDept;

	/** suSex property */
	private String suSex;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		type = "name";
	}

	/** 
	 * Returns the type.
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/** 
	 * Set the type.
	 * @param type The type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/** 
	 * Returns the suUser.
	 * @return String
	 */
	public String getSuUser() {
		return suUser;
	}

	/** 
	 * Set the suUser.
	 * @param suUser The suUser to set
	 */
	public void setSuUser(String suUser) {
		this.suUser = suUser;
	}

	/** 
	 * Returns the seDept.
	 * @return String
	 */
	public String getSuDept() {
		return suDept;
	}

	/** 
	 * Set the seDept.
	 * @param seDept The seDept to set
	 */
	public void setSuDept(String suDept) {
		this.suDept = suDept;
	}

	/** 
	 * Returns the suSex.
	 * @return String
	 */
	public String getSuSex() {
		return suSex;
	}

	/** 
	 * Set the suSex.
	 * @param suSex The suSex to set
	 */
	public void setSuSex(String suSex) {
		this.suSex = suSex;
	}
}

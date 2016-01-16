/*
 * Created on 2005-8-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.hit.component;

import java.util.Properties;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Table;

/**
 * @author oracle
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PdfTable extends Table {

	/**
	 * @param arg0
	 * @throws com.lowagie.text.BadElementException
	 */
	public PdfTable(int arg0) throws BadElementException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws com.lowagie.text.BadElementException
	 */
	public PdfTable(int arg0, int arg1) throws BadElementException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public PdfTable(Properties arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}

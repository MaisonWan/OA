package com.icss.hit.component;

import java.util.Properties;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Image;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PdfElement extends Chunk {

	/**
	 * 
	 */
	public PdfElement() {
		super();
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public PdfElement(String arg0, Font arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * @param arg0
	 */
	public PdfElement(String arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public PdfElement(char arg0, Font arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * @param arg0
	 */
	public PdfElement(char arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public PdfElement(Image arg0, float arg1, float arg2) {
		super(arg0, arg1, arg2);
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public PdfElement(Image arg0, float arg1, float arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	/**
	 * @param arg0
	 */
	public PdfElement(Properties arg0) {
		super(arg0);
		
	}

	public static void main(String[] args) {
	}
}

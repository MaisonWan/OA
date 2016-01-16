package com.icss.hit.component;

import java.util.Properties;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Element;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PdfCell extends Cell {

	/**
	 * 构造函数生成构造table的元素Cell
	 * @param arg0  构造table的元素Cell
	 * @throws BadElementException
	 */
	public PdfCell(Element arg0) throws BadElementException {
		super(arg0);		
	}

}

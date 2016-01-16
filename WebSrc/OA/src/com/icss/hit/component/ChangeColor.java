package com.icss.hit.component;
import java.awt.Color;

/**
 * @author oracle
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ChangeColor {
	public static Color getColor(String colorValue){
		Color color=Color.white;
		if(colorValue.equals("BLACK")){
			color=Color.BLACK;
		}
		if(colorValue.equals("black")){
			color=Color.black;
		}
		if(colorValue.equals("BLUE")){
			color=Color.BLUE;
		}
		if(colorValue.equals("blue")){
			color=Color.blue;
		}
		if(colorValue.equals("CYAN")){
			color=Color.CYAN;
		}
		if(colorValue.equals("cyan")){
			color=Color.cyan;
		}
		if(colorValue.equals("DARK_GRAY")){
			color=Color.DARK_GRAY;
		}
		if(colorValue.equals("darkGray")){
			color=Color.darkGray;
		}
		if(colorValue.equals("GRAY")){
			color=Color.GRAY;
		}
		if(colorValue.equals("gray")){
			color=Color.gray;
		}
		if(colorValue.equals("GREEN")){
			color=Color.GREEN;
		}
		if(colorValue.equals("green")){
			color=Color.green;
		}
		if(colorValue.equals("LIGHT_GRAY")){
			color=Color.LIGHT_GRAY;
		}
		if(colorValue.equals("lightGray")){
			color=Color.lightGray;
		}
		if(colorValue.equals("MAGENTA")){
			color=Color.MAGENTA;
		}
		if(colorValue.equals("magenta")){
			color=Color.magenta;
		}
		if(colorValue.equals("ORANGE")){
			color=Color.ORANGE;
		}
		if(colorValue.equals("orange")){
			color=Color.orange;
		}
		if(colorValue.equals("PINK")){
			color=Color.PINK;
		}
		if(colorValue.equals("pink")){
			color=Color.pink;
		}
		if(colorValue.equals("RED")){
			color=Color.RED;
		}
		if(colorValue.equals("red")){
			color=Color.red;
		}
		if(colorValue.equals("WHITE")){
			color=Color.WHITE;
		}
		if(colorValue.equals("white")){
			color=Color.white;
		}
		if(colorValue.equals("YELLOW")){
			color=Color.YELLOW;
		}
		if(colorValue.equals("yellow")){
			color=Color.yellow;
		}
		return color;
	}
}

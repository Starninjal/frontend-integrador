package com.wmw.frontend.integrador.components;

import com.wmw.frontend.integrador.util.Colors;
import com.wmw.frontend.integrador.util.Fonts;
import com.wmw.frontend.integrador.util.MaterialConstants;

import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.event.Event;
import totalcross.ui.event.MouseEvent;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class Card extends Container {
	private Image img;
	public Label lbl;
	private ImageControl ic;

	public Card(Image img, String txt) {
		this.img = img;
		lbl = new Label(txt);
	}

	@Override
	public void initUI() {
		this.setBackColor(Colors.BRIGHT_BLUE);
		this.setBorderStyle(BORDER_ROUNDED);
		this.borderColor = (Colors.WHITE);
		this.bringToFront();
		ic = new ImageControl(img);
		ic.transparentBackground = true;
		ic.centerImage = true;
		ic.scaleToFit = true;
		add(ic, LEFT + 10, TOP + 10, PARENTSIZE + 40, PARENTSIZE + 30);

		lbl.transparentBackground = true;
		lbl.setFont(Fonts.latoBoldPlus1);
		lbl.setForeColor(Colors.WHITE);
		add(lbl, LEFT + MaterialConstants.BORDER_SPACING, BOTTOM - MaterialConstants.BORDER_SPACING);
	}

	@Override
	public void onEvent(Event event) {
		if (event.type == MouseEvent.PEN_DOWN) {
			try {
				this.setBackColor(Color.darker(Colors.BRIGHT_BLUE, 20));
				this.repaintNow();
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.setBackColor(Colors.BRIGHT_BLUE);
			this.repaintNow();	
		}
	}

}

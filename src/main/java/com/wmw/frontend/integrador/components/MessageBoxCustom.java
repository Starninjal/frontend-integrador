package com.wmw.frontend.integrador.components;

import com.wmw.frontend.integrador.util.Colors;
import com.wmw.frontend.integrador.util.Fonts;

import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.Window;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class MessageBoxCustom extends Window{
	
	private Label labelTitulo;
	
	private Label labelDescricao;
	
	private Button button;
	
	private Container container;
	
	public MessageBoxCustom(String titulo, String descricao, String textoBotao) {
		this.transparentBackground = true;
		labelTitulo = new Label(titulo);
		labelTitulo.setFont(Fonts.latoBoldPlus1);
		button = new Button(textoBotao, BORDER_ROUNDED);
		button.setBackForeColors(Colors.DARKER_BLUE_BORDER, Color.WHITE);
		button.setFont(Fonts.latoBoldMinus4);
		labelDescricao = new Label(descricao);
		labelDescricao.setFont(Fonts.latoBoldMinus2);
		
		container = new Container();
		setarAtributosContainer();
		add(container, CENTER, CENTER, 290, 150);
		container.add(button, RIGHT - 20, BOTTOM - 20, 130, 40);
		container.add(labelTitulo, LEFT + 10, TOP + 10);
		container.add(labelDescricao, LEFT + 10, AFTER + 20, FILL - 10, PREFERRED, labelTitulo);
		button.addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent e) {
				unpopWindow();
			}
		});
		
	}
	
	public void setarAtributosContainer() {
		container.setBackColor(Colors.BRIGHT_BLUE);
		container.setBorderStyle(BORDER_ROUNDED);
		container.setForeColor(Color.WHITE);
	}
	
	public void unpopWindow() {
		unpop();
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
	
	

	
	
}

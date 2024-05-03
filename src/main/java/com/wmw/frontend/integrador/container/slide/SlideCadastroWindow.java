package com.wmw.frontend.integrador.container.slide;

import com.wmw.frontend.integrador.components.ComboBoxTipoPessoa;
import com.wmw.frontend.integrador.util.Colors;

import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.MaterialWindow;
import totalcross.ui.Presenter;
import totalcross.ui.Window;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.PressListener;

public class SlideCadastroWindow extends MaterialWindow {
	
	private static SlideCadastroWindow janelaAtual;

	public SlideCadastroWindow() {
		super(false, createPresenter());
		janelaAtual = this;
		this.animDir = LEFT;
		this.totalTime = 800;
		this.setBackColor(Colors.BRIGHT_BLUE);
	}
	

	private static Presenter<Container> createPresenter() {
		// TODO Auto-generated method stub
		return new Presenter<Container>() {
			
			@Override
			public Container getView() {
				// TODO Auto-generated method stub
				return new SlideCadastroContainer();
			} 
		};
	}
	
	public static void unpopWindow() {
		janelaAtual.unpop();
	}
	
	
	

	


}

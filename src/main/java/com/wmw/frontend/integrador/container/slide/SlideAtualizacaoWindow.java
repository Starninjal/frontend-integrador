package com.wmw.frontend.integrador.container.slide;

import com.wmw.frontend.integrador.model.cliente.Cliente;
import com.wmw.frontend.integrador.util.Colors;

import totalcross.ui.Container;
import totalcross.ui.MaterialWindow;
import totalcross.ui.Presenter;

public class SlideAtualizacaoWindow extends MaterialWindow{
	
	public SlideAtualizacaoWindow() {
		super(false, createPresenter());
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
				return new SlideAtualizacaoContainer();
			}
		};
	}
}

package com.wmw.frontend.integrador.container.slide;

import com.wmw.frontend.integrador.util.Colors;

import totalcross.ui.Container;
import totalcross.ui.MaterialWindow;
import totalcross.ui.Presenter;

public class SlideSincronizacaoWindow extends MaterialWindow{
	private static SlideSincronizacaoWindow slide;
	public SlideSincronizacaoWindow() {
		super(false, createPresenter());
		slide = this;
		this.animDir = LEFT;
		this.totalTime = 800;
		this.setBackColor(Colors.BRIGHT_BLUE);
	}
	
	public static void unpopSlide() {
		slide.unpop();
	}

	private static Presenter<Container> createPresenter() {
		// TODO Auto-generated method stub
		return new Presenter<Container>() {
			
			@Override
			public Container getView() {
				// TODO Auto-generated method stub
				return new SlideSincronizacaoContainer();
			}
		};
	}
}

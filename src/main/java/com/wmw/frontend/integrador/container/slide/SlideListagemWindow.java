package com.wmw.frontend.integrador.container.slide;

import com.wmw.frontend.integrador.util.Colors;

import totalcross.ui.Container;
import totalcross.ui.MaterialWindow;
import totalcross.ui.Presenter;
import totalcross.ui.icon.Icon;
import totalcross.ui.icon.MaterialIcons;

public class SlideListagemWindow extends MaterialWindow {
	
	private static SlideListagemWindow slide;
	public SlideListagemWindow() {
		super(false, createPresenter());
		this.animDir = LEFT;
		this.totalTime = 800;
		this.setBackColor(Colors.BRIGHT_BLUE);

	}
	public static void get() {
		slide.unpop();
	}

	private static Presenter<Container> createPresenter() {
		return new Presenter<Container>() {
			
			@Override
			public Container getView() {
				return new SlideListagemContainer();
			}
		};
	}
	
	@Override
	public void popup() {
		// TODO Auto-generated method stub
		super.popup();
	}
	
	public SlideListagemContainer getModel() {
		return (SlideListagemContainer) createPresenter().getView();
	}
}

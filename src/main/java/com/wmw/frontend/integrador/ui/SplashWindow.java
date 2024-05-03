package com.wmw.frontend.integrador.ui;

import com.wmw.frontend.integrador.util.Images;

import totalcross.io.IOException;
import totalcross.ui.ImageControl;
import totalcross.ui.Window;
import totalcross.ui.anim.ControlAnimation;
import totalcross.ui.anim.FadeAnimation;
import totalcross.ui.image.ImageException;

public class SplashWindow extends Window {
	
	private ImageControl back, logo;

	public SplashWindow(){

	}

	protected void onPopup() {
		Images.carregarImagens();
		back = new ImageControl(Images.background);
		back.scaleToFit = true;
		back.centerImage = true;
		back.hwScale = true;
		back.strechImage = true;
		add(back, LEFT, TOP, FILL, FILL);

		logo = new ImageControl(Images.imgLogo);
		logo.scaleToFit = true;
		logo.centerImage = true;
		logo.transparentBackground = true;
		add(logo, CENTER, CENTER);

		FadeAnimation.create(logo, true, null, 3000)
				.then(FadeAnimation.create(logo, false, this::onAnimationFinished, 3000)).start();
	}

	public void onAnimationFinished(ControlAnimation anim) {
		this.unpop();
	}

}

package com.wmw.frontend.integrador.util;

import totalcross.io.IOException;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Images {
	public static Image imgLogo, background, addCliente, imgSync;
	
	public static void carregarImagens() {
		try {
			imgLogo = new Image("imagens/imgLogo.png");
			background = new Image("imagens/background.png");
			addCliente = new Image("imagens/addcliente.png");
			imgSync = new Image("imagens/iconsync.png");
		} catch (IOException | ImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

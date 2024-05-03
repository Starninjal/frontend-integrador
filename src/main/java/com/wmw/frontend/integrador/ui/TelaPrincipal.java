package com.wmw.frontend.integrador.ui;

import com.wmw.frontend.integrador.container.MenuPrincipal;
import com.wmw.frontend.integrador.util.Colors;
import com.wmw.frontend.integrador.util.Images;

import totalcross.io.IOException;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class TelaPrincipal extends MainWindow {

	private Button buttonCadastrar, buttonListar;
	private Label labelTitulo;
	private Image imgLogo;

	public TelaPrincipal() throws Exception {
		setUIStyle(Settings.MATERIAL_UI);

	}

	@Override
	public void initUI() {
		SplashWindow spl = new SplashWindow();
		spl.popupNonBlocking();
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		swap(menuPrincipal);
		// TODO Auto-generated method stub
//		carregarImagemTelaPrincipal();
//		labelTitulo = criarLabel("", Colors.BLACK);
//		buttonCadastrar = criarBotao("Cadastrar Cliente", Color.brighter(Colors.BLUE));
//		buttonListar = criarBotao("Listar Cliente", Color.brighter(Colors.BLUE));
//		addButton();
		
		

	}

	

}

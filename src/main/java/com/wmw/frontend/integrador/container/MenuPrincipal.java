package com.wmw.frontend.integrador.container;

import com.wmw.frontend.integrador.components.Card;
import com.wmw.frontend.integrador.container.slide.SlideCadastroWindow;
import com.wmw.frontend.integrador.container.slide.SlideListagemContainer;
import com.wmw.frontend.integrador.container.slide.SlideListagemWindow;
import com.wmw.frontend.integrador.container.slide.SlideSincronizacaoWindow;
import com.wmw.frontend.integrador.util.Colors;
import com.wmw.frontend.integrador.util.Images;
import com.wmw.frontend.integrador.util.MaterialConstants;

import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.SlidingWindow;
import totalcross.ui.Switch;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.MouseEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class MenuPrincipal extends Container {

	private ImageControl imgLogo, imgBackground;
	private Card card1, card2;
	private SlidingWindow slideCadastroWindow, slideListagemWindow;
	private SlideListagemContainer slideListagemContainer = new SlideListagemContainer();
	@Override
	public void initUI() {
		Images.carregarImagens();
		carregarImagemTelaPrincipal();
		carregarCard();
		carregarImagem();
	}

	public Button criarBotao(String label, int color) {
		Button button = new Button(label, Button.BORDER_ROUND);
		button.setBackColor(color);
		return button;
	}

	public Label criarLabel(String labelText, int color) {
		Label label = new Label(labelText);
		label.setForeColor(color);
		return label;
	}

	public void carregarImagemTelaPrincipal() {

		Images.carregarImagens();

		imgBackground = new ImageControl(Images.background);
		imgBackground.scaleToFit = true;
		imgBackground.centerImage = true;
		imgBackground.hwScale = true;
		imgBackground.strechImage = true;
		add(imgBackground, LEFT, TOP, FILL, FILL);
		imgLogo = new ImageControl(Images.imgLogo);
		imgLogo.scaleToFit = true;
		imgLogo.hwScale = true;
		imgLogo.strechImage = true;
		imgLogo.transparentBackground = true;
		add(imgLogo, CENTER, TOP - 30, PARENTSIZE + 40, PARENTSIZE + 25);
	}
	
	public void carregarBotoes() {
		try {
			Image img = new Image("imagens/addcliente.png");
			Button btn = new Button("Cadastro \n de Clientes",  img.scaledBy(0.1, 0.1),RIGHT,10);
			btn.setBorder(BORDER_ROUNDED);
			btn.borderColor = Colors.WHITE;
			add(btn, LEFT + 20, BOTTOM - 20, 200, 50);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void carregarCard() {
		ScrollContainer sc = new ScrollContainer(true, false);
		sc.transparentBackground = true;
		sc.sbH.setVisible(false);
		add(sc, LEFT + MaterialConstants.BORDER_SPACING, BOTTOM - MaterialConstants.BORDER_SPACING,
				FILL - MaterialConstants.BORDER_SPACING, PARENTSIZE + 20);

		Image add = Images.addCliente;

		card1 = new Card(add, "Cadastro de \n Clientes");
		sc.add(card1, LEFT, TOP, PARENTSIZE + 47, FILL - MaterialConstants.COMPONENT_SPACING);

		card2 = new Card(add, "Lista de Clientes");

		sc.add(card2, AFTER + MaterialConstants.COMPONENT_SPACING, TOP, PARENTSIZE + 47,
				FILL - MaterialConstants.COMPONENT_SPACING);
	}
	
	private void carregarImagem() {
		Images.carregarImagens();
		Button btn = null;
		try {
			Image img = new Image("imagens/icon-sync.png");
			btn = new Button("", img.scaledBy(0.2, 0.2), CENTER, CENTER);
			btn.setBorder(BORDER_LOWERED);
			btn.borderColor = Colors.WHITE;
			btn.setBackColor(Colors.BRIGHT_BLUE);
			btn.addPressListener(new PressListener() {
				
				@Override
				public void controlPressed(ControlEvent e) {
					// TODO Auto-generated method stub
					SlideSincronizacaoWindow slideSincronizacao = new SlideSincronizacaoWindow();
					slideSincronizacao.popup();	
				}
			});
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao carregar a imagem.");
		}
		add(btn, RIGHT - 10, TOP + 15, 60, 60);
	}

	public void onEvent(Event event) {
		if (event.target instanceof Card && event.type == MouseEvent.PEN_DOWN) {
			Card card = (Card) event.target;
			if (card.toString().equals(card1.toString())) {
				slideCadastroWindow = new SlideCadastroWindow();
				slideCadastroWindow.popup();
			} else {
				slideListagemWindow = new SlideListagemWindow();
				slideListagemWindow.popup();
			}
		}
	}
}

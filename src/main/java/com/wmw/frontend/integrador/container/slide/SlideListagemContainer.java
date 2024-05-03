package com.wmw.frontend.integrador.container.slide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wmw.frontend.integrador.components.MessageBoxCustom;
import com.wmw.frontend.integrador.model.cliente.Cliente;
import com.wmw.frontend.integrador.model.cliente.TipoPessoa;
import com.wmw.frontend.integrador.service.ClienteService;
import com.wmw.frontend.integrador.util.Colors;
import com.wmw.frontend.integrador.util.Images;
import com.wmw.frontend.integrador.util.UtilCliente;

import de.schlichtherle.truezip.fs.FsSyncOption;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Grid;
import totalcross.ui.ImageControl;
import totalcross.ui.Switch;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.GridEvent;
import totalcross.ui.event.GridListener;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;
import totalcross.ui.layout.HBox;
import totalcross.util.Vector;

public class SlideListagemContainer extends Container {
	private Grid grid;
	private Button buttonAlterar, buttonRemover;
	private ClienteService clienteService;
	private List<Cliente> clientes;
	private MessageBoxCustom mb;
	private String[][] items;
	private ImageControl imgSync;
	private Switch switchBanco;
	public static SlideListagemContainer slideAtual;
	public SlideListagemContainer() {
		clienteService = new ClienteService();
		slideAtual = this;
	}

	@Override
	public void initUI() {
		iniciarComponentes();
	}

	private void iniciarComponentes() {
		iniciarBotoes();
		iniciarGrid();
		carregarDadosGrid();
		iniciarSwitch();
	}

	private void iniciarSwitch() {
		Button button = new Button("?");
		switchBanco = new Switch();
		switchBanco.colorBarOn = Color.darker(Colors.GRAY, 30);
		switchBanco.colorBallOn = Colors.BRIGHT_BLUE;
		switchBanco.colorBarOff = Color.BLACK;
		add(switchBanco, RIGHT - 10, TOP + 10);
		
		HBox hBox = new HBox(HBox.LAYOUT_STACK_LEFT, HBox.ALIGNMENT_STRETCH);
		hBox.add(switchBanco);
		hBox.add(button, 20, 20);
		hBox.setSpacing(10);
		add(hBox, RIGHT -10, TOP + 10);
		
		// TODO Auto-generated method stub
		
	}

	private void iniciarGrid() {
		String[] gridCaptions = { "ID", "Nome", "Tipo", "Documento", "Telefone", "Email" };
		int[] gridWidths = { -20, -50, -50, -50, -50, -50 };
		int[] gridAligns = { LEFT, LEFT, LEFT, LEFT, LEFT, LEFT };
		grid = new Grid(gridCaptions, gridWidths, gridAligns, false);
		grid.verticalLineStyle = Grid.VERT_LINE;
		add(grid, LEFT + 20, TOP + 60, FILL - 20, PREFERRED);

		HBox hBox = new HBox(HBox.LAYOUT_STACK_CENTER, HBox.ALIGNMENT_STRETCH);
		hBox.add(buttonAlterar, 150, 55);
		hBox.add(buttonRemover, 150, 55);
		add(hBox, LEFT + 10, AFTER + 40);
	}

	private void iniciarBotoes() {
		buttonAlterar = new Button("Alterar Cliente");
		buttonRemover = new Button("Remover Cliente");
		buttonAlterar.setBackForeColors(Colors.BRIGHT_BLUE, Colors.WHITE);
		buttonRemover.setBackForeColors(Colors.BRIGHT_BLUE, Colors.WHITE);
		buttonRemover.addPressListener(e -> {
			removerCliente();
		});
		buttonAlterar.addPressListener(e -> {
			alterarCliente();
		});
	}

	private void alterarCliente() {
		if (grid.getSelectedIndex() == -1) {
			mb = new MessageBoxCustom("Erro ao alterar cliente", "Selecione um cliente para alterar!",
					"Tentar novamente!");
			mb.popup();
			return;
		}
		String[] selectedItem = grid.getSelectedItem();
		UtilCliente.clienteParaAtualizar = converterClienteDoItem(selectedItem);
		SlideAtualizacaoWindow slideAtualizacaoWindow = new SlideAtualizacaoWindow();
		slideAtualizacaoWindow.popup();
		atualizarGrid();
	}

	private void removerCliente() {
		try {
			if (grid.getSelectedIndex() == -1) {
				mb = new MessageBoxCustom("Erro ao remover cliente", "Selecione um cliente para remover!",
						"Tentar novamente!");
				mb.popup();
				return;
			}
			
			if(!validarSwitch()) {
				
			}

			String[] selectedItem = grid.getSelectedItem();

			Long id = Long.valueOf(selectedItem[0]);
			clienteService.excluirCliente(id);
			Cliente clienteRemovido = converterClienteDoItem(selectedItem);
			UtilCliente.addClientesDeletados(clienteRemovido);
			atualizarGrid();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} catch (Exception ex) {
			mb = new MessageBoxCustom("Erro ao remover o cliente", "Não foi possível remover o cliente!",
					"Tentar novamente!");
		}
	}

	private void carregarDadosGrid() {
		clientes = clienteService.listarClientes();
		items = converterDadosDosClientes();
		grid.add(items);

		if (clientes.size() == 0) {
			mb = new MessageBoxCustom("Erro ao carregar o grid", "Não foram encontrados clientes no banco de dados!",
					"Entendido!");
			mb.popup();
		}
	}

	public void atualizarGrid() {
		grid.removeAllElements();
		carregarDadosGrid();
	}

	private void validarMensagemGrid(boolean isWifiConectado, List<Cliente> clientes) {
		if (isWifiConectado && clientes.size() == 0) {
			mb = new MessageBoxCustom("Erro ao carregar o grid", "Não foram encontrados clientes no banco online!",
					"Entendido!");

		} else if (!isWifiConectado && clientes.size() > 0) {
			mb = new MessageBoxCustom("Erro ao carregar o grid", "Não foram encontrados clientes no banco local!",
					"Entendido!");
		}
		mb.popup();
	}

	private Cliente converterClienteDoItem(String[] item) {
		Long id = Long.valueOf(item[0]);
		String nome = item[1];
		String tipoPessoa = item[2].toString();
		String documento = item[3];
		String telefone = item[4];
		String email = item[5];
		return new Cliente(id, nome, TipoPessoa.valueOf(tipoPessoa), documento, telefone, email);
	}

	private String[][] converterDadosDosClientes() {
		items = new String[clientes.size()][6];
		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);
			items[i] = new String[] { String.valueOf(cliente.getId()), cliente.getNome(),
					cliente.getTipoPessoa().toString(), cliente.getDocumento(), cliente.getTelefone(),
					cliente.getEmail() };
		}
		return items;
	}

	

	private void adicionarImagem() {
		add(imgSync, RIGHT - 10, BOTTOM - 20, 30, 30);
	}

	private boolean validarSwitch() {
		if(switchBanco.isOn()) {
			return true;
		}
		return false;
	}
}

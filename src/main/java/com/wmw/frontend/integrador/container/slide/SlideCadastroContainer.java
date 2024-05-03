 package com.wmw.frontend.integrador.container.slide;

import com.wmw.frontend.integrador.components.ComboBoxTipoPessoa;
import com.wmw.frontend.integrador.components.MessageBoxCustom;
import com.wmw.frontend.integrador.dto.ClienteDTO;
import com.wmw.frontend.integrador.model.cliente.TipoPessoa;
import com.wmw.frontend.integrador.service.ClienteService;
import com.wmw.frontend.integrador.util.Colors;
import com.wmw.frontend.integrador.util.Fonts;
import com.wmw.frontend.integrador.util.UtilCliente;
import com.wmw.frontend.integrador.util.UtilHTTPCliente;

import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class SlideCadastroContainer extends Container {
	private static Edit editNome, editDocumento, editTelefone, editEmail;
	private static Button buttonCadastrar;
	private ComboBox cbTipoPessoa;
	private ClienteService clienteService;
	private MessageBoxCustom messageBox;
	private Label labelTipoPessoa;
	public SlideCadastroContainer() {
		clienteService = new ClienteService();
	}

	@Override
	public void initUI() {
		editNome = criarEdit("Nome do Cliente", "");
		editTelefone = criarEdit("Telefone do Cliente", "(99) - 999999999");
		editEmail = criarEdit("Email do Cliente", "");
		editDocumento = criarEdit("Documento", "999.999.999-99");
		buttonCadastrar = criarButton("Cadastrar Cliente");
		labelTipoPessoa = new Label("Tipo de Cliente");
		labelTipoPessoa.setForeColor(Color.darker(Colors.GRAY));
		labelTipoPessoa.setFont(Fonts.latoBoldPlus1);
		cbTipoPessoa = new ComboBoxTipoPessoa();
		cbTipoPessoa.setSelectedIndex(0);
		cbTipoPessoa.addPressListener(new PressListener() {

			@Override
			public void controlPressed(ControlEvent e) {
				remove(editDocumento);
				setarTipoDocumentoEdit();
			}
		});
		addContainer();
		buttonCadastrar.addPressListener(new PressListener() {

			@Override
			public void controlPressed(ControlEvent e) {
				// TODO Auto-generated method stub
				salvarCliente();
			}
		});
	}
	
	public void addContainer() {
		add(editNome, LEFT + 10, TOP + 20, FILL - 10, PREFERRED);
		add(labelTipoPessoa, LEFT + 15, AFTER + 20, FILL - 10, PREFERRED);
		add(cbTipoPessoa, SAME, AFTER + 5, FILL - 10, PREFERRED);
		add(editDocumento, LEFT + 10, AFTER + 20, FILL - 10, PREFERRED);
		add(editTelefone, LEFT + 10, AFTER + 20, FILL - 10, PREFERRED);
		editTelefone.setMaxLength(AFTER);
		add(editEmail, LEFT + 10, AFTER + 20, FILL - 10, PREFERRED);
		add(buttonCadastrar, LEFT + 10, AFTER + 20, FILL - 10, PREFERRED);
	}

	private void setarTipoDocumentoEdit() {
		// TODO Auto-generated method stub
		if (cbTipoPessoa.getSelectedItem().equals("FISICA")) {
			editDocumento = criarEdit("Documento", "999.999.999-99");
			editDocumento.setMaxLength(11);
			addEditDocumento();

		} else {
			editDocumento = criarEdit("Documento", "99.999.999/9999-99");
			editDocumento.setMaxLength(14);
			addEditDocumento();
		}

	}

	private void addEditDocumento() {

		add(editDocumento, SAME, AFTER + 20, FILL - 10, PREFERRED, cbTipoPessoa);
	}

	private Edit criarEdit(String label, String mask) {
		Edit edit = new Edit();
		if (!mask.equals("")) {
			edit = new Edit(mask);
		}
		edit.caption = label;
		return edit;
	}

	private Button criarButton(String label) {
		Button button = new Button(label);
		return button;
	}

	private void salvarCliente() {
		try {
			String nome = editNome.getText();
			String tipoPessoa = cbTipoPessoa.getSelectedItem().toString();
			String documento = editDocumento.getText();
			String telefone = editTelefone.getText();
			String email = editEmail.getText();
			ClienteDTO clienteDTO = new ClienteDTO(nome, TipoPessoa.valueOf(tipoPessoa), documento, telefone, email);
			clienteService.cadastrarCliente(clienteDTO);
			validacaoUsuarioExistenteRemovido(clienteDTO);
			messageBox = new MessageBoxCustom("Cadastro bem Sucedido!", "Cliente cadastrado com sucesso!", "Entendido");
			messageBox.popup();
			limparCampos();
		} catch (Exception e2) {
			messageBox = new MessageBoxCustom("Erro ao cadastrar o cliente", e2.getMessage(), "Entendido!");
			messageBox.popup();
		}
	}
	
	
	private void validacaoUsuarioExistenteRemovido(ClienteDTO clienteDTO) {
		boolean clienteExistente = UtilCliente.validarClienteExistenteRemovido(clienteDTO);
		if(clienteExistente) {
			UtilCliente.deletarClienteRemovido(clienteDTO);
		}
	}
	
	private void limparCampos() {
		editNome.clear();
		editDocumento.clear();
		editEmail.clear();
		editTelefone.clear();
	}
	
}
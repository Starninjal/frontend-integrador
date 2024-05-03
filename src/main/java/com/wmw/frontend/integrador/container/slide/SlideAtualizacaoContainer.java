package com.wmw.frontend.integrador.container.slide;

import com.wmw.frontend.integrador.components.ComboBoxTipoPessoa;
import com.wmw.frontend.integrador.components.MessageBoxCustom;
import com.wmw.frontend.integrador.dto.ClienteDTO;
import com.wmw.frontend.integrador.model.cliente.Cliente;
import com.wmw.frontend.integrador.service.ClienteService;
import com.wmw.frontend.integrador.util.Colors;
import com.wmw.frontend.integrador.util.Fonts;
import com.wmw.frontend.integrador.util.UtilCliente;

import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class SlideAtualizacaoContainer extends Container {
	private static Edit editNome, editDocumento, editTelefone, editEmail;
	private static Button buttonCadastrar;
	private ComboBox cbTipoPessoa;
	private ClienteService clienteService;
	private MessageBoxCustom messageBox;
	private Cliente clienteParaAtualizar;

	public SlideAtualizacaoContainer() {
		clienteService = new ClienteService();
		clienteParaAtualizar = UtilCliente.clienteParaAtualizar;

	}

	@Override
	public void initUI() {
		editNome = criarEdit("Nome do Cliente", "");
		editTelefone = criarEdit("Telefone do Cliente", "(99) - 999999999");
		editEmail = criarEdit("Email do Cliente", "");
		editDocumento = criarEdit("Documento", "999.999.999-99");
		buttonCadastrar = criarButton("Cadastrar Cliente");
		Label labelTipoPessoa = new Label("Tipo de Cliente");
		labelTipoPessoa.setForeColor(Color.darker(Colors.GRAY));
		labelTipoPessoa.setFont(Fonts.latoBoldPlus1);
		cbTipoPessoa = new ComboBoxTipoPessoa();

		setarDadosDoCliente();
		cbTipoPessoa.addPressListener(new PressListener() {

			@Override
			public void controlPressed(ControlEvent e) {
				remove(editDocumento);
				setarTipoDocumentoEdit();
			}
		});

		cbTipoPessoa.addPressListener((e) -> {

		});
		add(editNome, LEFT + 10, TOP + 20, FILL - 10, PREFERRED);
		add(labelTipoPessoa, LEFT + 15, AFTER + 20, FILL - 10, PREFERRED);
		add(cbTipoPessoa, LEFT + 10, AFTER + 5, FILL - 10, PREFERRED);
		add(editDocumento, LEFT + 10, AFTER + 20, FILL - 10, PREFERRED);
		add(editTelefone, LEFT + 10, AFTER + 20, FILL - 10, PREFERRED);
		add(editEmail, LEFT + 10, AFTER + 20, FILL - 10, PREFERRED);
		add(buttonCadastrar, LEFT + 10, AFTER + 20, FILL - 10, PREFERRED);
		buttonCadastrar.addPressListener(new PressListener() {

			@Override
			public void controlPressed(ControlEvent e) {
				// TODO Auto-generated method stub
				alterarCliente();
			}

		});
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

	private void setarDadosDoCliente() {
		editDocumento.setText(clienteParaAtualizar.getDocumento());
		editNome.setText(clienteParaAtualizar.getNome());
		editTelefone.setText(clienteParaAtualizar.getTelefone());
		editEmail.setText(clienteParaAtualizar.getEmail());
		cbTipoPessoa.setSelectedItem(clienteParaAtualizar.getTipoPessoa().toString());
		editDocumento.setEnabled(false);
		editNome.setEnabled(false);
		cbTipoPessoa.setEnabled(false);

	}

	private void alterarCliente() {
		try {
			String telefone = editTelefone.getText();
			String email = editEmail.getText();
			ClienteDTO clienteDTO = new ClienteDTO(clienteParaAtualizar.getId(), clienteParaAtualizar.getNome(),
					clienteParaAtualizar.getTipoPessoa(), clienteParaAtualizar.getDocumento(), telefone, email);
			clienteService.alterarCliente(clienteDTO);
			String[] nomeClienteSplit = clienteDTO.getNome().split("");
			String primeiroNomeCliente = nomeClienteSplit[0];
			messageBox = new MessageBoxCustom("Cliente alterado com sucesso!",
					"O cliente " +  primeiroNomeCliente + " foi alterado!", "Entendido!");
			messageBox.popup();
			limparCampos();
		} catch (Exception e) {
			messageBox = new MessageBoxCustom("Erro ao alterar o cliente!", e.getMessage(), "Entendido!");
			messageBox.popup();
		}
	}
	
	private void limparCampos() {
		editNome.clear();
		editDocumento.clear();
		editEmail.clear();
		editTelefone.clear();
	}
	
	
}

package com.wmw.frontend.integrador.container.slide;

import java.util.List;
import java.util.stream.Collectors;

import com.wmw.frontend.integrador.components.MessageBoxCustom;
import com.wmw.frontend.integrador.dto.ClienteDTO;
import com.wmw.frontend.integrador.dto.SincronizacaoDataDTO;
import com.wmw.frontend.integrador.service.ClienteService;
import com.wmw.frontend.integrador.util.Colors;
import com.wmw.frontend.integrador.util.UtilCliente;
import com.wmw.frontend.integrador.util.UtilHTTPCliente;

import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class SlideSincronizacaoContainer extends Container {

	private ClienteService clienteService;
	private MessageBoxCustom messageBox;
	private SlideSincronizacaoWindow parentSlide;

	@Override
	public void initUI() {
		
		clienteService = new ClienteService();
		parentSlide = (SlideSincronizacaoWindow) getParentWindow();
		initComponents();
		super.initUI();
	}

	public void initComponents() {
		Button buttonReceberDados = new Button("Receber Dados");
		buttonReceberDados.setBackColor(Colors.BRIGHT_BLUE);
		buttonReceberDados.setForeColor(Color.WHITE);
		Button buttonEnviarDados = new Button("Enviar Dados");
		buttonEnviarDados.setForeColor(Color.WHITE);
		buttonEnviarDados.setBackColor(Colors.BRIGHT_BLUE);
		add(buttonReceberDados, CENTER , CENTER - 20, 150, PREFERRED);
		add(buttonEnviarDados, SAME, AFTER + 20, 150, PREFERRED);
		
		buttonEnviarDados.addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent e) {
				// TODO Auto-generated method stub]
				try {
					List<ClienteDTO> clientesDTO = clienteService.listarClientes().stream().map(ClienteDTO::fromCliente).collect(Collectors.toList());
					
					List<ClienteDTO> clientesDeletadosDTO = UtilCliente.getClientesDeletados().stream().map(ClienteDTO::fromCliente).collect(Collectors.toList());
					UtilCliente.clearClientesDeletados();
					SincronizacaoDataDTO sincronizacao = new SincronizacaoDataDTO(clientesDTO, clientesDeletadosDTO);
					UtilHTTPCliente.POSTClienteSincronizacao("http://localhost:8080/cliente/sync/mobile", SincronizacaoDataDTO.class, sincronizacao);
					messageBox = new MessageBoxCustom("Dados enviados com sucesso!", "Todos os dados enviados com sucesso!", "Entendido!");
					messageBox.popup();
				} catch (Exception e2) {
					messageBox = new MessageBoxCustom("Dados não foram enviados", e2.getMessage(), "Entendido!");
					messageBox.popup();
				}
					
			}
		});
		
		buttonReceberDados.addPressListener(new PressListener() {
			
			@Override
			public void controlPressed(ControlEvent e) {
				// TODO Auto-generated method stub
				try {
					UtilHTTPCliente.Response<ClienteDTO> responseClientes = UtilHTTPCliente.GETClienteApi("http://localhost:8080/cliente", ClienteDTO.class);
					UtilHTTPCliente.Response<ClienteDTO> responseClientesRemovidos = UtilHTTPCliente.GETClientesRemovidos("http://localhost:8080/cliente/removidos", ClienteDTO.class);
					List<ClienteDTO> clientes = responseClientes.listData;
					List<ClienteDTO> clientesRemovidos = responseClientesRemovidos.listData;
					clienteService.sincronizarClientesApi(clientes, clientesRemovidos);
					messageBox = new MessageBoxCustom("Todos os dados recebidos!", "Os dados foram recebidos com sucesso!", "Entendido!");
					messageBox.popup();
					SlideListagemWindow slideListagemWindow = new SlideListagemWindow();
					System.out.println( slideListagemWindow.getModel());
					slideListagemWindow.popup();
					
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
	}

}

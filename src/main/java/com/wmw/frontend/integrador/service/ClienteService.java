package com.wmw.frontend.integrador.service;

import java.util.List;

import com.wmw.frontend.integrador.dto.ClienteDTO;
import com.wmw.frontend.integrador.model.cliente.Cliente;
import com.wmw.frontend.integrador.model.cliente.TipoPessoa;
import com.wmw.frontend.integrador.repository.ClienteRepository;
import com.wmw.frontend.integrador.util.UtilCliente;

public class ClienteService {
	private ClienteRepository clienteRepository;

	public ClienteService() {
		clienteRepository = new ClienteRepository();
	}

	public boolean cadastrarCliente(ClienteDTO clienteDTO) throws IllegalArgumentException, IllegalAccessException {
		validarCliente(clienteDTO);
		if (validarClienteExistente(clienteDTO)) {
			throw new RuntimeException("Documento existente!");
		}
		Cliente cliente = new Cliente(clienteDTO);
		return clienteRepository.inserir(cliente);
	}

	public List<Cliente> listarClientes() {
		return clienteRepository.listarClientes();
	}

	public Cliente buscarClientePor(Long id) {
		Cliente clienteBuscado = clienteRepository.buscarClientePor(id);
		if (clienteBuscado == null) {
			throw new NullPointerException("Cliente não encontrado!");
		}
		return clienteBuscado;
	}

	public Long buscarPeloIdDoClientePelo(String documento) {
		Long idCliente = clienteRepository.buscarIdClientePelo(documento);

		return idCliente;
	}

	public void alterarCliente(ClienteDTO clienteDTO) throws IllegalArgumentException, IllegalAccessException {
		validarCliente(clienteDTO);
		Cliente clienteExistente = buscarClientePor(clienteDTO.getId());
		clienteExistente.setEmail(clienteDTO.getEmail());
		clienteExistente.setTelefone(clienteDTO.getTelefone());
		if (!clienteRepository.alterarCliente(clienteExistente)) {
			throw new RuntimeException("Houve erro na alteração do cliente!");
		}
	}

	public void excluirCliente(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Cliente selecionado é inválido!");
		}
		clienteRepository.excluirClientePor(id);
	}

	public void excluirVariosClientes(List<ClienteDTO> clientesDeletados) {
		if(clientesDeletados.stream().anyMatch(clienteDeletado -> clienteDeletado.getId() == null)) {
			throw new IllegalArgumentException("Cliente selecionado é inválido!");
		}
		
		for(ClienteDTO clientes: clientesDeletados) {
			clienteRepository.excluirClientePor(clientes.getId());
		}
	}

	public void validarCliente(ClienteDTO clienteDTO) throws IllegalArgumentException, IllegalAccessException {

		UtilCliente.validarCamposVazios(clienteDTO);
		UtilCliente.validarTelefone(clienteDTO);
		UtilCliente.validarEmail(clienteDTO);
		if (clienteDTO.getTipoPessoa() == TipoPessoa.FISICA.toString()) {
			if (!validarCpf(clienteDTO.getDocumento())) {
				throw new NullPointerException("CPF inválido, tente novamente!");
			}
		} else if (clienteDTO.getTipoPessoa() == TipoPessoa.JURIDICA.toString()) {
			if (!validarCnpj(clienteDTO.getDocumento())) {
				throw new NullPointerException("CNPJ inválido, tente novamente!");
			}
		}
	}

	private boolean validarCpf(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", "");

		if (cpf.length() != 11) {
			return false;
		}
		int digitoVerificador1 = UtilCliente.calcularDigitoCPF(cpf, 9, 10);
		int digitoVerificador2 = UtilCliente.calcularDigitoCPF(cpf, 10, 11);
		return Integer.parseInt(String.valueOf(cpf.charAt(9))) == digitoVerificador1
				&& Integer.parseInt(String.valueOf(cpf.charAt(10))) == digitoVerificador2;
	}

	private boolean validarCnpj(String cnpj) {

		if (cnpj == null || cnpj.length() != 18) {
			return false;
		}

		cnpj = cnpj.replaceAll("[^0-9]", "");

		int digitoVerificador1 = UtilCliente.calcularDigitoCNPJ(cnpj, 12);
		int digitoVerificador2 = UtilCliente.calcularDigitoCNPJ(cnpj, 13);

		return Integer.parseInt(String.valueOf(cnpj.charAt(12))) == digitoVerificador1
				&& Integer.parseInt(String.valueOf(cnpj.charAt(13))) == digitoVerificador2;

	}

	public boolean validarClienteExistente(ClienteDTO clienteDTO) {
		List<Cliente> clientes = listarClientes();
		for (Cliente cli : clientes) {
			if (cli.getDocumento().equals(clienteDTO.getDocumento())) {
				return true;
			}
		}

		return false;
	}

	public void sincronizarClientesApi(List<ClienteDTO> clientesDTO, List<ClienteDTO> clientesRemovidos)
			throws IllegalArgumentException, IllegalAccessException {
		List<Cliente> clientesApiAntes = listarClientes();
		if (clientesDTO != null) {
			for (ClienteDTO cliDTO : clientesDTO) {
				if (cliDTO.getTipoPessoa().equals("FISICA") || cliDTO.getTipoPessoa().equals("JURIDICA")) {
					boolean clienteExiste = validarClienteExistente(cliDTO);

					if (!clienteExiste) {
						cadastrarCliente(cliDTO);
					} else {
						Long idClienteOrigem = buscarPeloIdDoClientePelo(cliDTO.getDocumento());
						cliDTO.setId(idClienteOrigem);
						alterarCliente(cliDTO);
					}
				} else {
					throw new IllegalArgumentException("Houve um erro na sincronização");
				}
			}

			if (clientesRemovidos != null) {
				removerClientesApi(clientesRemovidos);
			}
		} else {
			excluirTodosClientes();
		}
	}

	public void removerClientesApi(List<ClienteDTO> clientesRemovidos) {
		List<Cliente> clientes = listarClientes();
		for (ClienteDTO cliDTO : clientesRemovidos) {
			Long idCliente = buscarPeloIdDoClientePelo(cliDTO.getDocumento());
			cliDTO.setId(idCliente);
			if (validarClienteExistente(cliDTO)) {
				excluirCliente(cliDTO.getId());
			}
		}
	}

	public void excluirTodosClientes() {
		clienteRepository.excluirTodosClientes();
	}

	public boolean cadastrarClienteComId(ClienteDTO clienteDTO)
			throws IllegalArgumentException, IllegalAccessException {
		validarCliente(clienteDTO);
		if (validarClienteExistente(clienteDTO)) {
			throw new RuntimeException("Documento existente!");
		}
		Cliente cliente = new Cliente(clienteDTO);
		return clienteRepository.inserirComId(cliente);
	}

}

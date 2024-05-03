package com.wmw.frontend.integrador.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.wmw.frontend.integrador.dto.ClienteDTO;
import com.wmw.frontend.integrador.model.cliente.Cliente;

import totalcross.util.Vector;

public class UtilCliente {
	private static final int[] pesoDV1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoDV2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	public static Cliente clienteParaAtualizar;
	private static List<Cliente> clientesDeletados = new ArrayList<Cliente>();
	public static List<ClienteDTO> clientesApi;

	public static int calcularDigitoCPF(String cpf, int indice, int pesoInicial) {
		// TODO Auto-generated method stub
		int soma = 0;

		int peso = pesoInicial;

		for (int i = 0; i < indice; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
		}

		int mod = soma % 11;

		return mod < 2 ? 0 : 11 - mod;
	}

	public static int calcularDigitoCNPJ(String cnpj, int peso) {
		int[] pesos = pesoDV1;
		if (peso == 13) { // Se for o segundo dígito verificador
			pesos = pesoDV2;
		}

		int soma = 0;
		for (int i = 0; i < peso; i++) {
			int digito = Character.getNumericValue(cnpj.charAt(i));
			soma += digito * pesos[i];
		}
		int resto = soma % 11;
		return resto < 2 ? 0 : 11 - resto;

	}

	public static void validarCamposVazios(ClienteDTO clienteDTO)
			throws IllegalArgumentException, IllegalAccessException {
		Field[] campos = clienteDTO.getClass().getDeclaredFields();
		for (Field campo : campos) {
			campo.setAccessible(true);
			Object campoAtual = campo.get(clienteDTO);
			if (!campo.getName().equals("id") && !campo.getName().equals("email")) {
				if (campoAtual == null || campoAtual.toString().isEmpty()) {
					throw new IllegalArgumentException("Campo " + campo.getName() + " está vazio!");
				}
			}
		}
	}

	public static List<Cliente> getClientesDeletados() {
		return clientesDeletados;
	}

	public static void addClientesDeletados(Cliente clienteDeletado) {
		clientesDeletados.add(clienteDeletado);
	}

	public void setClienteParaAtualizar(Cliente cliente) {
		UtilCliente.clienteParaAtualizar = cliente;
	}

	public static void validarTelefone(ClienteDTO clienteDTO) {
		String telefone = clienteDTO.getTelefone();
		String regex = ".*[a-zA-Z].*";
		if (telefone.equals("") || telefone.matches(regex)) {
			throw new RuntimeException("Telefone inválido!");
		}
	}

	public static void clearClientesDeletados() {
		clientesDeletados.clear();
	}

	public static boolean validarClienteExistenteRemovido(ClienteDTO clienteDTO) {
		List<Cliente> clientesRemovidos = getClientesDeletados();
		for (Cliente cliRemovidos : clientesRemovidos) {
			if (cliRemovidos.getDocumento().equals(clienteDTO.getDocumento())) {
				return true;
			}
		}

		return false;
	}

	public static void deletarClienteRemovido(ClienteDTO clienteDTO) {
		List<Cliente> clientesRemovidos = getClientesDeletados();
		if (clienteDTO != null) {
			clientesRemovidos.remove(new Cliente(clienteDTO));
		}
	}
	
	public static void validarEmail(ClienteDTO clienteDTO) {
		String email = clienteDTO.getEmail();
		if(email.length() > 0 && !email.contains("@")) {
			throw new RuntimeException("Email não está formatado na forma correta!");
		}
	}
	



}

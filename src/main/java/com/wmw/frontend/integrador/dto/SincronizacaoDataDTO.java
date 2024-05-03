package com.wmw.frontend.integrador.dto;

import java.util.List;

public class SincronizacaoDataDTO {
	private List<ClienteDTO> clientesDTO;
	
	private List<ClienteDTO> clientesDeletadosDTO;

	public List<ClienteDTO> getClientesDTO() {
		return clientesDTO;
	}	
	
	public SincronizacaoDataDTO(List<ClienteDTO> clientesDTO, List<ClienteDTO> clientesDeletadosDTO) {
		this.clientesDTO = clientesDTO;
		this.clientesDeletadosDTO = clientesDeletadosDTO;
	}

	public void setClientesDTO(List<ClienteDTO> clientesDTO) {
		this.clientesDTO = clientesDTO;
	}

	public List<ClienteDTO> getClientesDeletadosDTO() {
		return clientesDeletadosDTO;
	}

	public void setClientesDeletadosDTO(List<ClienteDTO> clientesDeletadosDTO) {
		this.clientesDeletadosDTO = clientesDeletadosDTO;
	}
}

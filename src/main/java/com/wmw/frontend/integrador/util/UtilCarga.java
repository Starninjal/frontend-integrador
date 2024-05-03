package com.wmw.frontend.integrador.util;

import java.util.Arrays;
import java.util.List;

import com.wmw.frontend.integrador.dto.ClienteDTO;
import com.wmw.frontend.integrador.model.cliente.Cliente;
import com.wmw.frontend.integrador.model.cliente.TipoPessoa;
import com.wmw.frontend.integrador.repository.ClienteRepository;

public class UtilCarga {
	
	public void inserirUsuarios() {
		ClienteRepository clienteRepository = new ClienteRepository();
		List<ClienteDTO> novosClientes = Arrays.asList(
			    new ClienteDTO(3L, "Novo Cliente 1", TipoPessoa.FISICA, "173.348.000-52", "(xx) xxxxxxxxx", "novocliente1@gmail.com"),
			    new ClienteDTO(4L, "Novo Cliente 2", TipoPessoa.FISICA, "656.866.110-41", "(xx) xxxxxxxxx", "novocliente2@gmail.com"),
			    new ClienteDTO(5L, "Novo Cliente 3", TipoPessoa.FISICA, "736.155.810-08", "(xx) xxxxxxxxx", "novocliente3@gmail.com"),
			    new ClienteDTO(6L, "Novo Cliente 4", TipoPessoa.FISICA, "602.671.140-60", "(xx) xxxxxxxxx", "novocliente4@gmail.com"),
			    new ClienteDTO(7L, "Novo Cliente 5", TipoPessoa.FISICA, "553.451.350-02", "(xx) xxxxxxxxx", "novocliente5@gmail.com"),
			    new ClienteDTO(8L, "Novo Cliente 6", TipoPessoa.FISICA, "954.211.780-99", "(xx) xxxxxxxxx", "novocliente6@gmail.com"),
			    new ClienteDTO(9L, "Novo Cliente 7", TipoPessoa.FISICA, "294.599.800-90", "(xx) xxxxxxxxx", "novocliente7@gmail.com"),
			    new ClienteDTO(10L, "Novo Cliente 8", TipoPessoa.FISICA, "194.339.620-52", "(xx) xxxxxxxxx", "novocliente8@gmail.com"),
			    new ClienteDTO(11L, "Novo Cliente 9", TipoPessoa.FISICA, "167.659.390-08", "(xx) xxxxxxxxx", "novocliente9@gmail.com"),
			    new ClienteDTO(12L, "Novo Cliente 10", TipoPessoa.FISICA, "657.205.510-85", "(xx) xxxxxxxxx", "novocliente10@gmail.com"),
			    new ClienteDTO(13L, "Novo Cliente 11", TipoPessoa.FISICA, "390.684.740-30", "(xx) xxxxxxxxx", "novocliente11@gmail.com"),
			    new ClienteDTO(14L, "Novo Cliente 12", TipoPessoa.FISICA, "614.290.190-93", "(xx) xxxxxxxxx", "novocliente12@gmail.com"),
			    new ClienteDTO(15L, "Novo Cliente 13", TipoPessoa.FISICA, "035.834.310-06", "(xx) xxxxxxxxx", "novocliente13@gmail.com")
			);
		
		for(ClienteDTO cliDTO: novosClientes) {
			clienteRepository.inserirComId(new Cliente(cliDTO));
		}
	}
}

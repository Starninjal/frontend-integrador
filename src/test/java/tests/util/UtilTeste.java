package tests.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.wmw.frontend.integrador.dto.ClienteDTO;
import com.wmw.frontend.integrador.model.cliente.Cliente;
import com.wmw.frontend.integrador.model.cliente.TipoPessoa;
import com.wmw.frontend.integrador.service.ClienteService;

public class UtilTeste {

	private static ClienteService clienteService = new ClienteService();

	public static void cadastrarClientes() throws IllegalArgumentException, IllegalAccessException {
		List<ClienteDTO> clientes = Arrays.asList(
			    new ClienteDTO(1l, "João Gomes", TipoPessoa.FISICA, "044.772.889-05", "(48) 996136359", "gmailtop@gmail.com"),
			    new ClienteDTO(2l, "Maria Silva", TipoPessoa.JURIDICA, "50.199.526/0001-10", "(48) 987654321", "mariasilva@gmail.com"),
			    new ClienteDTO(12l, "José Souza", TipoPessoa.FISICA, "912.221.300-76", "(48) 123456789", "josesouza@gmail.com"));
		for(ClienteDTO cli: clientes) {
			clienteService.cadastrarClienteComId(cli);
		}
	}
	
	public static void removerTodosClientes() {
		clienteService.excluirTodosClientes();
	}
}

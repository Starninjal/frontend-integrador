package tests;


import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.wmw.frontend.integrador.dto.ClienteDTO;
import com.wmw.frontend.integrador.model.cliente.Cliente;
import com.wmw.frontend.integrador.model.cliente.TipoPessoa;
import com.wmw.frontend.integrador.service.ClienteService;

import tests.util.UtilTeste;


public class IntegradorApplicationTests {
	
	private ClienteService clienteService;
	
	public IntegradorApplicationTests() {
		clienteService = new ClienteService();
	}
	
	@Test
	public void testDeveListarUmClienteExistenteDeAcordoComOIdentificador() throws IllegalArgumentException, IllegalAccessException {
		UtilTeste.removerTodosClientes();
		ClienteDTO clienteDTO = new ClienteDTO(1l, "Cliente 01", TipoPessoa.FISICA, "136.213.859-24", "(48) 996136359", "cliente01@gmail.com");
		clienteService.cadastrarClienteComId(clienteDTO);
		Cliente clienteCadastrado = new Cliente(clienteDTO);
		Cliente clienteBuscado = clienteService.buscarClientePor(1l);
		Assert.assertEquals(clienteCadastrado, clienteBuscado);
	}
	
	
	@Test
	public void testDeveExcluirTodosOsClientesDoBancoLocal() {
		UtilTeste.removerTodosClientes();
		List<Cliente> clientes = clienteService.listarClientes();
		System.out.println(clientes);
		Assert.assertTrue(clientes.isEmpty());
	}
	
	@Test
	public void testDeveListarTodosOsClientes() throws IllegalArgumentException, IllegalAccessException {
		UtilTeste.removerTodosClientes();
		UtilTeste.cadastrarClientes();
		List<Cliente> clientesExistentes = clienteService.listarClientes();
		Assert.assertFalse(clientesExistentes.isEmpty());
	}
	
	@Test
	public void testDeveInformarQueOCPFInformadoExisteDentroDoBancoDeDados() throws IllegalArgumentException, IllegalAccessException {
		UtilTeste.removerTodosClientes();
		UtilTeste.cadastrarClientes();
		ClienteDTO clienteDTO = new ClienteDTO(1l, "João Gomes", TipoPessoa.FISICA, "044.772.889-05", "(48) 996136359", "gmailtop@gmail.com");
		assertThrows(RuntimeException.class, () -> clienteService.cadastrarCliente(clienteDTO));
	}
	
	@Test
	public void testDeveInformarQueOCNPJInformadoExisteDentroDoBancoDeDados() throws IllegalArgumentException, IllegalAccessException {
		UtilTeste.removerTodosClientes();
		UtilTeste.cadastrarClientes();
		ClienteDTO clienteDTO = new ClienteDTO(1l, "João Gomes", TipoPessoa.JURIDICA, "50.199.526/0001-10", "(48) 996136359", "gmailtop@gmail.com");
		assertThrows(RuntimeException.class, () -> clienteService.cadastrarCliente(clienteDTO));
	}
	
	@Test
	public void testDeveInformarQueoCPFEInvalido() {
		UtilTeste.removerTodosClientes();
		ClienteDTO clienteDTO = new ClienteDTO(1l, "João Gomes", TipoPessoa.FISICA, "044.772.889-04", "(48) 996136359", "gmailtop@gmail.com");
		assertThrows(NullPointerException.class, () -> clienteService.cadastrarCliente(clienteDTO));
	}
	
	@Test
	public void testDeveInformarQueoCNPJEInvalido() {
		UtilTeste.removerTodosClientes();
		ClienteDTO clienteDTO = new ClienteDTO(1l, "João Gomes", TipoPessoa.JURIDICA, "50.129.529/0001-10", "(48) 996136359", "gmailtop@gmail.com");
		assertThrows(NullPointerException.class, () -> clienteService.cadastrarCliente(clienteDTO));
	}

	@Test
	public void testDeveCadastrarClienteDevidoAoCPFSerValido() throws IllegalArgumentException, IllegalAccessException {
		UtilTeste.removerTodosClientes();
		ClienteDTO clienteDTO = new ClienteDTO(1l, "João Gomes", TipoPessoa.FISICA, "777.437.860-05", "(48) 996136359", "gmailtop@gmail.com");
		Assert.assertTrue(clienteService.cadastrarClienteComId(clienteDTO));
	}
	
	@Test
	public void testDeveCadastrarClienteDevidoAoCNPJSerValido() throws IllegalArgumentException, IllegalAccessException {
		UtilTeste.removerTodosClientes();
		ClienteDTO clienteDTO = new ClienteDTO(1l, "João Gomes", TipoPessoa.JURIDICA, "52.664.393/0001-31", "(48) 996136359", "gmailtop@gmail.com");
		Assert.assertTrue(clienteService.cadastrarClienteComId(clienteDTO));
	}
	
	@Test
	public void testInformarQueoClienteNaoFoiIdentificadoDevidoAoIdentificadorSerInformadoIncorretamente() {
		assertThrows(NullPointerException.class, () -> clienteService.buscarClientePor(10l));
	}
	
	@Test
	public void testDeveAlterarApenasOsDadosDeContatoDoCliente() throws IllegalArgumentException, IllegalAccessException {
		UtilTeste.removerTodosClientes();
		UtilTeste.cadastrarClientes();
		Cliente clienteAtual = clienteService.buscarClientePor(1l);
		Cliente clienteAlterado = clienteAtual;
		clienteAlterado.setTelefone("(48) 734637473");
		clienteAlterado.setEmail("clientealterado@gmail.com");
		clienteService.alterarCliente(ClienteDTO.fromCliente(clienteAlterado));
		
		System.out.println("Detalhes do cliente atual:");
		System.out.println(clienteAtual);
		System.out.println();

		System.out.println("Detalhes do cliente alterado:");
		System.out.println(clienteAlterado);
	}
	
	@Test
	public void testDeveExcluirUmClienteDeAcordoComIdentificador() throws IllegalArgumentException, IllegalAccessException {
		UtilTeste.removerTodosClientes();
		UtilTeste.cadastrarClientes();
		long idExcluir = 1l;
		clienteService.excluirCliente(idExcluir);
		List<Cliente> clientes = clienteService.listarClientes();
		Cliente cliente = clientes.stream().filter(c -> c.getId() == idExcluir).findFirst().orElse(null);
		assertNull(cliente);
	}
	
	@Test
	public void testDeveSincronizarOBancoLocalComDadosFicticiosDaApi() throws IllegalArgumentException, IllegalAccessException {
		UtilTeste.removerTodosClientes();
		UtilTeste.cadastrarClientes();
		
		List<Cliente> clientesAntesDaSincronizacao = clienteService.listarClientes();
		
		
		System.out.println("\n Clientes Antes: " + clientesAntesDaSincronizacao + "\n\n");
		
		
		List<ClienteDTO> clientesVindosDaApi = Arrays.asList(
			    new ClienteDTO("João Gomes", TipoPessoa.FISICA, "044.772.889-05", "(48) 996136359", "gmailtop@gmail.com"),
			    new ClienteDTO("Maria Silva", TipoPessoa.JURIDICA, "50.199.526/0001-10", "(48) 987654321", "mariasilva@gmail.com"),
			    new ClienteDTO("José Souza", TipoPessoa.FISICA, "912.221.300-76", "(48) 123456789", "josesouza@gmail.com"));
		
		List<ClienteDTO> clientesRemovidosVindosDaApi = Arrays.asList(
			    new ClienteDTO("João Ferreira", TipoPessoa.FISICA, "781.392.830-02", "(48) 996136359", "gmailtop@gmail.com"),
			    new ClienteDTO("Maria Antônia", TipoPessoa.JURIDICA, "50.199.526/0001-10", "(48) 987654321", "mariasilva@gmail.com"),
			    new ClienteDTO("José Ricardo", TipoPessoa.FISICA, "965.353.900-04", "(48) 123456789", "josesouza@gmail.com"));
		
		clienteService.sincronizarClientesApi(clientesVindosDaApi, clientesRemovidosVindosDaApi);
		
		List<Cliente> clientesDepoisDaSincronizacao = clienteService.listarClientes();
		
		clientesDepoisDaSincronizacao.forEach(c -> System.out.println("\n\n Clientes Depois: " + c + "\n\n"));
		
	}
	
	@Test
	public void testDeveNaoCadastrarUmClienteDevidoAosCamposObrigatoriosEstaremNulos() throws IllegalArgumentException, IllegalAccessException {
		UtilTeste.removerTodosClientes();
		ClienteDTO clienteDTO = new ClienteDTO(1l, "João Gomes", TipoPessoa.FISICA, "777.437.860-05", "", "gmailtop@gmail.com");
		assertThrows(IllegalArgumentException.class, () -> clienteService.cadastrarClienteComId(clienteDTO));
	}
	
	
	
}
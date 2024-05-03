	package com.wmw.frontend.integrador.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wmw.frontend.integrador.model.cliente.Cliente;
import com.wmw.frontend.integrador.model.cliente.TipoPessoa;
import com.wmw.frontend.integrador.util.DatabaseManager;

import totalcross.sql.Connection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;

public class ClienteRepository {
	private Connection conn;

	public ClienteRepository() {
		conn = DatabaseManager.getInstance().getConnection();
	}

	public boolean inserir(Cliente cliente) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"INSERT INTO cliente(nome, tipopessoa, documento, telefone, email) VALUES(?, ?, ?, ?, ?)");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getTipoPessoa().toString());
			ps.setString(3, cliente.getDocumento());
			ps.setString(4, cliente.getTelefone());
			ps.setString(5, cliente.getEmail());
			return ps.executeUpdate() == 0;
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao cadastrar o cliente. Motivo: " + e.getMessage());
		} finally {
			DatabaseManager.getInstance().fechar(ps);
		}
	}
	
	public boolean inserirComId(Cliente cliente) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"INSERT INTO cliente(id, nome, tipopessoa, documento, telefone, email) VALUES(?, ?, ?, ?, ?, ?)");
			ps.setLong(1, cliente.getId());
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getTipoPessoa().toString());
			ps.setString(4, cliente.getDocumento());
			ps.setString(5, cliente.getTelefone());
			ps.setString(6, cliente.getEmail());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Ocorreu um erro ao cadastrar o cliente. Motivo: " + e.getMessage());
		} finally {
			DatabaseManager.getInstance().fechar(ps);
		}
	}

	public List<Cliente> listarClientes() {
		// TODO Auto-generated method stub
		List<Cliente> clientes = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT id, nome, tipoPessoa, documento, telefone, email FROM Cliente");
			rs = ps.executeQuery();
			while (rs.next()) {
				clientes.add(extrairClienteDo(rs));
			}
			return clientes;
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao listar os clientes. Motivo: " + e.getMessage());
		} finally {
			DatabaseManager.getInstance().fechar(ps);
			DatabaseManager.getInstance().fechar(rs);
		}
	}

	public Cliente extrairClienteDo(ResultSet rs) {
		try {
			Long id = rs.getLong("id");
			String nome = rs.getString("nome");
			String tipoPessoa = rs.getString("tipoPessoa");
			String documento = rs.getString("documento");
			String telefone = rs.getString("telefone");
			String email = rs.getString("email");
			return new Cliente(id, nome, TipoPessoa.valueOf(tipoPessoa), documento, telefone, email);
		} catch (SQLException e) {
			throw new RuntimeException("Ocorreu um erro ao extrair o cliente do rs. Motivo: " + e.getMessage());
		}

	}

	public void excluirClientePor(Long id) {
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("DELETE FROM Cliente WHERE id = ?");
			ps.setLong(1, id);
			boolean isExclusaoOk = ps.executeUpdate() == 1;
			if (isExclusaoOk) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao excluir o cliente. Motivo: " + e.getMessage());
		} finally {
			DatabaseManager.getInstance().setarAutoCommitDa(conn, true);
			DatabaseManager.getInstance().fechar(ps);
		}
	}

	public Cliente buscarClientePor(Long id) {
		Cliente clienteBuscado = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT id, nome, tipoPessoa, documento, telefone, email FROM Cliente WHERE ID = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				clienteBuscado = extrairClienteDo(rs);
			}
			return clienteBuscado;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Ocorreu um erro ao buscar encontrar o cliente pelo id. Motivo: " + e.getMessage());
		} finally {
			DatabaseManager.getInstance().fechar(ps);
			DatabaseManager.getInstance().fechar(rs);
		}
	}
	
	public Long buscarIdClientePelo(String documento) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT id FROM Cliente WHERE documento = ?");
			ps.setString(1, documento);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getLong("id");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Ocorreu um erro ao buscar encontrar o cliente pelo id. Motivo: " + e.getMessage());
		} 
		return null;
	}

	public boolean alterarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("UPDATE Cliente SET nome = ?, tipoPessoa = ?, documento = ?, telefone = ?, email = ? WHERE id = ?");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getTipoPessoa().toString());
			ps.setString(3, cliente.getDocumento());
			ps.setString(4, cliente.getTelefone());
			ps.setString(5, cliente.getEmail());
			ps.setLong(6, cliente.getId());
			boolean isAtualizacaoOk = ps.executeUpdate() == 1;
			if(isAtualizacaoOk) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
				return false;
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao alterar o cliente. Motivo: " + e.getMessage());
		} finally {
			DatabaseManager.getInstance().setarAutoCommitDa(conn, true);
			DatabaseManager.getInstance().fechar(ps);
		}
	}
	
	public void excluirTodosClientes() {
 		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM Cliente");
			conn.setAutoCommit(false);
			boolean isExclusaoOk = ps.executeUpdate() > 0;
			if(isExclusaoOk) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao excluir os clientes. Motivo:" + e.getMessage());
		} finally {
			DatabaseManager.getInstance().setarAutoCommitDa(conn, true);
			DatabaseManager.getInstance().fechar(ps);
		}
	}

}

package br.com.sistema.contacorrente.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;

import br.com.sistema.contacorrente.factory.ConnectionFactory;
import br.com.sistema.contacorrente.models.Contas;
import br.com.sistema.contacorrente.models.Movimentacoes;

public class ContaCorrenteDAO {
	public void saveContas (Contas conta) {
		String sql = "INSERT INTO contas(cod, cliente, saldo, limite, tipo_conta, dia_vencimento) VALUES (?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, conta.getCod());
			pstm.setString(2, conta.getNomeCliente());
			pstm.setDouble(3, conta.getSaldo());
			pstm.setDouble(4, conta.getLimite());
			pstm.setInt(5, conta.getTipoConta());
			pstm.setDate(6, (Date) conta.getVencimento());
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveMovimentacoes (Movimentacoes movimento) {
		String sql = "INSERT INTO movimentacoes(cod, valor_do_movimento, tipo_movimento, data_movimentacao) VALUES (?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, movimento.getCod());
			pstm.setDouble(2, movimento.getValorMovimento());
			pstm.setInt(3, movimento.getTipoMovimento());
			pstm.setDate(4, (Date) movimento.getDataMovimentacao());
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Contas> getContas(){
		String sql = "SELECT * FROM contas";
		List<Contas> contas = new ArrayList<Contas>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Contas ce = new Contas();
				ce.setId(rset.getLong("id"));
				ce.setCod(rset.getInt("cod"));
				ce.setNomeCliente(rset.getString("cliente"));
				ce.setSaldo(rset.getDouble("saldo"));
				ce.setLimite(rset.getDouble("limite"));
				ce.setTipoConta(rset.getInt("tipo_conta"));
				ce.setVencimento(rset.getDate("dia_vencimento"));
				
				contas.add(ce);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset != null) {
					rset.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return contas;
	}
	
	public List<Movimentacoes> getMovimentacoes(){
		String sql = "SELECT * FROM movimentacoes";
		List<Movimentacoes> movimentacoes = new ArrayList<Movimentacoes>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Movimentacoes m = new Movimentacoes();
				m.setId(rset.getLong("id"));
				m.setCod(rset.getInt("cod"));
				m.setValorMovimento(rset.getDouble("valor_do_movimento"));
				m.setTipoMovimento(rset.getInt("tipo_movimento"));
				m.setDataMovimentacao(rset.getDate("data_movimentacao"));
				
				movimentacoes.add(m);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset != null) {
					rset.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return movimentacoes;
	}
	
	public void update(Contas conta) {
		String sql = "UPDATE contas SET cliente = ?, saldo = ?, limite = ?, tipo_conta = ?, dia_vencimento = ? " + "WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, conta.getNomeCliente());
			pstm.setDouble(2, conta.getSaldo());
			pstm.setDouble(3, conta.getLimite());
			pstm.setInt(4, conta.getTipoConta());
			pstm.setDate(5, (Date) conta.getVencimento());
			pstm.setLong(6, conta.getId());
			pstm.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteById(long id) {
		String sql = "DELETE FROM contas WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setLong(1, id);
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null){
					pstm.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}	
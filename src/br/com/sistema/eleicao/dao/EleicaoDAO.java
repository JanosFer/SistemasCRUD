package br.com.sistema.eleicao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;

import br.com.sistema.eleicao.factory.ConnectionFactory;
import br.com.sistema.eleicao.models.CadastroEleitor;
import br.com.sistema.eleicao.models.Votacao;

public class EleicaoDAO {
	public void saveEleitor (CadastroEleitor eleitor) {
		String sql = "INSERT INTO cadastro_eleitor(num_eleitor, eleitor, secao, zona) VALUES (?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setLong(1, eleitor.getNumEleitor());
			pstm.setString(2, eleitor.getNomeEleitor());
			pstm.setInt(3, eleitor.getSecao());
			pstm.setInt(4, eleitor.getZona());
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
	
	public void saveVotacao (Votacao votacao) {
		String sql = "INSERT INTO votacao(secao, zona, num_eleitor, candidato) VALUES (?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, votacao.getSecao());
			pstm.setInt(2, votacao.getZona());
			pstm.setLong(3, votacao.getNumEleitor());
			pstm.setInt(4, votacao.getNumCandidato());
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
	
	public List<CadastroEleitor> getEleitor(){
		String sql = "SELECT * FROM cadastro_eleitor";
		List<CadastroEleitor> eleitores = new ArrayList<CadastroEleitor>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				CadastroEleitor ce = new CadastroEleitor();
				ce.setId(rset.getLong("id"));
				ce.setNumEleitor(rset.getLong("num_eleitor"));
				ce.setNomeEleitor(rset.getString("eleitor"));
				ce.setSecao(rset.getInt("secao"));
				ce.setZona(rset.getInt("zona"));
				
				eleitores.add(ce);
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
		return eleitores;
	}
	
	public List<Votacao> getVotacao(){
		String sql = "SELECT * FROM votacao";
		List<Votacao> votacao = new ArrayList<Votacao>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Votacao v = new Votacao();
				v.setId(rset.getLong("id"));
				v.setSecao(rset.getInt("secao"));
				v.setZona(rset.getInt("zona"));
				v.setNumEleitor(rset.getLong("num_eleitor"));
				v.setNumCandidato(rset.getInt("candidato"));
				
				votacao.add(v);
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
		return votacao;
	}
	
	public void update(CadastroEleitor eleitor) {
		String sql = "UPDATE cadastro_eleitor SET eleitor = ?, secao = ?, zona = ? " + "WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, eleitor.getNomeEleitor());
			pstm.setInt(2, eleitor.getSecao());
			pstm.setInt(3, eleitor.getZona());
			pstm.setLong(4, eleitor.getId());
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
}	
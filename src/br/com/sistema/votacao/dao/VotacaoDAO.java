package br.com.sistema.votacao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;

import br.com.sistema.votacao.factory.ConnectionFactory;
import br.com.sistema.votacao.models.Votacao;

public class VotacaoDAO {
	public void save (Votacao votacao) {
		String sql = "INSERT INTO votacao(secao, num_candidato) VALUES (?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, votacao.getNumSecao());
			pstm.setInt(2, votacao.getNumCandidato());
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
	
	public List<Votacao> getVotacao(){
		String sql = "SELECT * FROM votacao";
		List<Votacao> votacoes = new ArrayList<Votacao>();
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
				v.setNumSecao(rset.getInt("secao"));
				v.setNumCandidato(rset.getInt("num_candidato"));
				
				votacoes.add(v);
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
		return votacoes;
	}
}	
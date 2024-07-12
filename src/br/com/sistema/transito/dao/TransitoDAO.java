package br.com.sistema.transito.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;

import br.com.sistema.transito.factory.ConnectionFactory;
import br.com.sistema.transito.models.Transito;

public class TransitoDAO {
	public void save(Transito transito) {
		String sql = "INSERT INTO transito(cidade, acidentes) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, transito.getNomeCidade());
			pstm.setInt(2, transito.getQtdAcidentes());
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
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Transito> getTransito(){
		String sql = "SELECT * FROM transito";
		List<Transito> estatisticas = new ArrayList<Transito>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Transito transito = new Transito();
				transito.setId(rset.getLong("id"));
				transito.setNomeCidade(rset.getString("cidade"));
				transito.setQtdAcidentes(rset.getInt("acidentes"));
				
				estatisticas.add(transito);
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
		return estatisticas;
	}
	
	public void update(Transito transito) {
		String sql = "UPDATE transito SET cidade = ?, acidentes = ? " + "WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, transito.getNomeCidade());
			pstm.setInt(2, transito.getQtdAcidentes());
			pstm.setLong(3, transito.getId());
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
		String sql = "DELETE FROM transito WHERE id = ?";
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
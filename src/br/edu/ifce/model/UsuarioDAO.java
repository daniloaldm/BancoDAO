package br.edu.ifce.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifce.bancodedados.dao.Conexao;
import br.edu.ifce.model.Usuario;

public class UsuarioDAO {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	public UsuarioDAO() {
		//conn = new Conexao().conecta();
		//
		conn = Conexao.getInstance().conecta();
	}


	public List<Usuario> lerUsuarios(){

		
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try
		{
			stmt = conn.createStatement();

			String query = "SELECT * FROM usuario";

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				
				Usuario usuario = new Usuario();
				
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				System.out.println(usuario.getNome());
				
				listaUsuarios.add(usuario);

				
			}
			System.out.println("Consulta efetuada. ");

			stmt.close();
			rs.close();
			//conn.close();
			
			System.out.println("Conexão fechada. ");
		} catch ( SQLException ex ) {

			System.err.println( "SQLException: " + ex.getMessage());
		} 
		
		return listaUsuarios;
	}
	
	
	public void inserirUsuario(int id, String usuario, String email){
		
		try {
			
		
			String query = "insert into usuario (id_usuario, nome, email) values (?, ?, ?) ";

			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			pStmt.setString(2, usuario);
			pStmt.setString(3, email);
			
			pStmt.executeUpdate();

			System.out.println("Registro inserido com sucesso!");

			pStmt.close();
			conn.close();
			
		} catch (SQLException ex ) {
			ex.printStackTrace();
		}
		
	}
	
	public void deletarUsuario(int id){
		/*
		try {
			
		
			String query = "delete from usuario where id_usuario = " + id;
			
			System.out.println(query);

			stmt = conn.createStatement();
			
			stmt.executeUpdate(query);

			System.out.println("Registro apagado com sucesso!");

			stmt.close();
			conn.close();
			
		} catch (SQLException ex ) {
			ex.printStackTrace();
		}
		*/
		
		try {
			
			
			String query = "delete from usuario where id_usuario = ?";

			PreparedStatement pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			
			pStmt.executeUpdate();

			System.out.println("Registro apagado com sucesso!");

			pStmt.close();
			conn.close();
			
		} catch (SQLException ex ) {
			ex.printStackTrace();
		}
		
	}
	
	public void atualizarUsuario(int id, String nome, String email, String senha){
		try {
			
			String query = "update usuario set nome = '" + nome + "', email = '" + email + "', senha = md5('" + senha + 
					"') where id_usuario = " + id;
			
			System.out.println(query);

			stmt = conn.createStatement();
			
			stmt.executeUpdate(query);

			System.out.println("Registro atualizado com sucesso!");

			stmt.close();
			//conn.close();
			
		} catch (SQLException ex ) {
			ex.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		UsuarioDAO dao = new UsuarioDAO();
		
		//dao.inserirUsuario(1, "Danilo", "danilo@gmail.com");
		//dao.inserirUsuario(2, "Carlos", "carlos@gmail.com");
		//dao.inserirUsuario(3, "Diego", "diego@gmail.com");
		
		dao.lerUsuarios();
		//dao.deletarUsuario(2);
		System.out.println("========");
		
		
		dao.atualizarUsuario(1, "danilo alexandrino", "danilo@gmail.com", "123456");
	}

}



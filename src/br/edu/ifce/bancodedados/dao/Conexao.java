package br.edu.ifce.bancodedados.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {

	final private String URL = "jdbc:postgresql://localhost:5432/danilo";  
	final private String USUARIO = "postgres";  
	final private String SENHA = "postgres";  
	final private String DRIVER = "org.postgresql.Driver";  
	
//	private static int contadorObjetos = 0;
	
	Connection con;
	
	private static Conexao conexao;
	
/*	public Conexao() {
		contadorObjetos++;
	}
*/	
	public static Conexao getInstance(){
		
		if(conexao == null){
			conexao = new Conexao();
		}
		return conexao;
		
	}


	public Connection conecta() {  
		try {  
			Class.forName(DRIVER);  
			
			con = DriverManager.getConnection(URL, USUARIO, SENHA);
			
			System.out.println("Conexão realizada com sucesso!");
			
		} catch (ClassNotFoundException cnfe) {  
			JOptionPane.showMessageDialog(null, "não encontrado");  
			System.out.println("Driver não encontrado!!");  
			cnfe.printStackTrace();  
		}  catch (SQLException se) {  
			System.out.println("Não foi possivel conectar!");  
			se.printStackTrace();  
		} 
		
		return con;  
	}
	
	
	
	public static void main(String[] args) {
		
/*		
		Conexao novaConexao = new Conexao();
		Conexao conexao2 = new Conexao();
		Conexao conexao3 = new Conexao();
		System.out.println(contadorObjetos);
*/		
	
		
//		new Conexao().conecta();
//		new Conexao().conecta();
		
		Conexao.getInstance().conecta();
//		Conexao.getInstance().conecta();
//		Conexao.getInstance().conecta();
//		Conexao.getInstance().conecta();
		
	}
	
}  


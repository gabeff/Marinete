package br.com.marineteapp.business;


import br.com.marineteapp.bean.Usuario;
import br.com.marineteapp.dao.UsuarioDAO;

public class UsuarioBusiness {
	
	private UsuarioDAO usuarioDao;
	
	public Usuario logar(Usuario usuario){
		usuarioDao = new UsuarioDAO();
		return usuarioDao.logar(usuario);
	}
	
	public String issueToken(Usuario usuario){
		usuarioDao = new UsuarioDAO();
		return usuarioDao.issueToken(usuario);
	}
	
	public String cadastrar(Usuario usuario){
		usuarioDao = new UsuarioDAO();
		return usuarioDao.cadastrar(usuario);
	}
	
	public Usuario getUsuarioByToken(String token){
		usuarioDao = new UsuarioDAO();
		return usuarioDao.getUsuarioByToken(token);
	}

}

package com.marinete.business;


import com.marinete.bean.Usuario;
import com.marinete.dao.UsuarioDAO;

public class UsuarioBusiness {
	
	private UsuarioDAO usuarioDao;
	
	public String login(Usuario usuario){
		usuarioDao = new UsuarioDAO();
		return usuarioDao.login(usuario);
	}
	
	public String cadastrar(Usuario usuario){
		usuarioDao = new UsuarioDAO();
		return usuarioDao.cadastrar(usuario);
	}

}

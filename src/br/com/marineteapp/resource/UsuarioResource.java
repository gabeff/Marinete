package br.com.marineteapp.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONException;

import br.com.marineteapp.bean.Usuario;
import br.com.marineteapp.business.UsuarioBusiness;

@Path("/usuario")
public class UsuarioResource {
	
	private UsuarioBusiness usuarioBusiness;

	@Path("/logar")
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String logar(Usuario usuario) throws JSONException {
		usuarioBusiness = new UsuarioBusiness();
		return usuarioBusiness.logar(usuario);
	}
	
	@Path("/cadastrar")
	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String cadastrar(Usuario usuario) throws JSONException {
		usuarioBusiness = new UsuarioBusiness();
		return usuarioBusiness.cadastrar(usuario);
	}

}

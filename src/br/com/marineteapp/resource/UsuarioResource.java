package br.com.marineteapp.resource;

import java.util.EmptyStackException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.marineteapp.bean.Usuario;
import br.com.marineteapp.business.UsuarioBusiness;

@Path("/usuario")
public class UsuarioResource {

	private UsuarioBusiness usuarioBusiness;

	@Path("/logar")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response authenticateUser(Usuario usuario) {

		try {

			// Authenticate the user using the credentials provided
			logar(usuario);
			
			// Issue a token for the user
            String token = issueToken(usuario);       
            
			// Return the token on the response
			return Response.ok(token).header("response", "1").build();

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).header("response", "0").build();
		}
	}

	private void logar(Usuario u) {
		usuarioBusiness = new UsuarioBusiness();
		Usuario usuario = usuarioBusiness.logar(u);
		if (usuario == null) {
			throw new EmptyStackException();
		}
	}
	
	private String issueToken(Usuario u) {
		usuarioBusiness = new UsuarioBusiness();
		String token = usuarioBusiness.issueToken(u);
		if (token == null || token.isEmpty()) {
			throw new EmptyStackException();
		}
		return token;
	}

	@Path("/cadastrar")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrar(Usuario usuario) {
		usuarioBusiness = new UsuarioBusiness();
		String response = usuarioBusiness.cadastrar(usuario);
		if (response.equals("1")) {
			return Response.ok(response).header("response", "1").build();
		}
		return Response.ok(response).header("response", "0").build();
	}

}

package br.com.marineteapp.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.marineteapp.bean.Marinete;
import br.com.marineteapp.business.MarineteBusiness;
import br.com.marineteapp.provider.Secured;

@Path("/marinete")
public class MarineteResource {

	private MarineteBusiness marineteBusiness;

	@GET
	@Path("/listar")
	@Produces("application/json")
	@Secured
	public Response listarMarinetes() {
		marineteBusiness = new MarineteBusiness();
		List<Marinete> marinetes = marineteBusiness.listarMarinetes();
		if (marinetes != null) {
			return Response.ok(new Gson().toJson(marinetes)).header("response", "1").build(); 
		}
		return Response.status(Response.Status.NO_CONTENT).header("response", "0").build(); 
	}

}

package br.com.marineteapp.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
	public String listarMarinetes() {
		marineteBusiness = new MarineteBusiness();
		List<Marinete> marinetes = marineteBusiness.listarMarinetes();
		return new Gson().toJson(marinetes);
	}

}

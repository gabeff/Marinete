package com.marinete.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/apelidoservice")
public class ApelidoService {
	
	@GET
	  @Produces("application/json")
	  public Response teste() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		String nome = "Teste";
		String apelido = "teste sucedido";
		jsonObject.put("Nome", nome); 
		jsonObject.put("Apelido", apelido);

		String result = "JSON retornado pelo Web Service: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	  }

	  @Path("{nome}/{idade}")
	  @GET
	  @Produces("application/json")
	  public Response descobrirApelido(@PathParam("nome") String nome, @PathParam("idade") int idade) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		String apelido;
		if (nome.equals("CH")) {
			apelido = "Burra Cega";
		} else if (nome.equals("Saulo")) {
			apelido = "Pedófilo";
		} else {
			apelido = "Sem apelido";
		}
		jsonObject.put("Nome", nome); 
		jsonObject.put("Apelido", apelido);
		jsonObject.put("Idade", idade);

		String result = "JSON retornado pelo Web Service: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	  }

}

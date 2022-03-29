package com.devidea.rest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.devidea.business.PaisBusiness;
import com.devidea.enumeration.Permissao;
import com.devidea.model.Politica;
import com.devidea.util.DateDeserializer;
import com.devidea.util.WebUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.devidea.business.UsuarioBusiness;
import com.devidea.model.Usuario;
// IMPORTS_RESOURCE


import net.sf.jasperreports.engine.JRException;

@Path("/pais")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class PaisResource {

	@Context
	private HttpServletRequest httpServletResquest;
	
	@EJB
	private PaisBusiness paisBusiness;
	
		@EJB
	private UsuarioBusiness usuarioBusiness;

// VARIABLES_RESOURCE

	
	private Gson gson = new Gson();
	private final String frontEndResponse = "*";
	
	public PaisResource() {
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Date.class, new DateDeserializer());
		gson = gb.excludeFieldsWithoutExposeAnnotation().setDateFormat("dd/MM/yyyy").create();
	}
	
	@POST
	@Path("/getById")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(Integer id, @Context HttpHeaders headers){
		if (WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, usuarioBusiness) == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		Politica entidade = paisBusiness.findById(id);
		
		return Response.ok(gson.toJson(entidade)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpHeaders headers){
		if (WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, usuarioBusiness) == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		List<Politica> list = paisBusiness.findAll();
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getAllFast")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFast(@Context HttpHeaders headers){
		if (WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, usuarioBusiness) == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		List<Politica> list = paisBusiness.findAllFast();
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getAllPaginated")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(Integer page, @Context HttpHeaders headers){
		if (WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, usuarioBusiness) == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		List<Politica> list = paisBusiness.findAll(page);
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getByExample")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByExample(String json, @Context HttpHeaders headers){
		if (WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, usuarioBusiness) == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		
		Politica example = gson.fromJson(json, Politica.class);
		List<Politica> list = paisBusiness.findByExample(example,example.getListPage(),example.getListPageSize());
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/create") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String json, @Context HttpHeaders headers){	
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness);
		if (usuario == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		Politica entidade = gson.fromJson(json, Politica.class);
		entidade.setDataCadastro(new Date());
		entidade.setUsuarioCadastro(usuario);
		
		try {
			entidade = paisBusiness.create(entidade);			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return Response.ok(entidade).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/update") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(String json, @Context HttpHeaders headers){	
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness);
		if (usuario == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		Politica entidade = gson.fromJson(json, Politica.class);
		entidade.setDataAtualizacao(new Date());
		entidade.setUsuarioAtualizacao(usuario);
				
		try {
			entidade = paisBusiness.update(entidade);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return Response.ok(entidade).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}

	@POST
	@Path("/listagem")
	@Produces("application/pdf")
	public Response listagem(String json, @Context HttpHeaders headers){
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness);
		if (usuario == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}

		Politica example = gson.fromJson(json, Politica.class);
		List<Politica> list = paisBusiness.findByExample(example);

		byte[] bytesReport = null;
		try {
			bytesReport = paisBusiness.listagemPDF(this.httpServletResquest, usuario, list);
			
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return Response.ok(bytesReport).header("Access-Control-Allow-Origin", frontEndResponse)
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept")
				.header("Content-Disposition", "filename=\"" + "paisList" + ".pdf\"").build();
	}

	// METHODS_RESOURCE
}

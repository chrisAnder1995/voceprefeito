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

import com.devidea.business.BairroBusiness;
import com.devidea.business.UsuarioBusiness;
import com.devidea.enumeration.Permissao;
import com.devidea.model.Bairro;
import com.devidea.model.Usuario;
// IMPORTS_RESOURCE
import com.devidea.util.DateDeserializer;
import com.devidea.util.WebUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.jasperreports.engine.JRException;

@Path("/bairro")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class BairroResource {

	@Context
	private HttpServletRequest httpServletResquest;
	
	@EJB
	private BairroBusiness bairroBusiness;
	
		@EJB
	private UsuarioBusiness usuarioBusiness;

// VARIABLES_RESOURCE

	
	private Gson gson = new Gson();
	private final String frontEndResponse = "*";
	
	public BairroResource() {
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Date.class, new DateDeserializer());
		gson = gb.excludeFieldsWithoutExposeAnnotation().setDateFormat("dd/MM/yyyy").create();
	}
	
	@POST
	@Path("/getById")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(Integer id, @Context HttpHeaders headers){
		if (WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_TOTAL, usuarioBusiness) == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		Bairro entidade = bairroBusiness.findById(id);
		
		return Response.ok(gson.toJson(entidade)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpHeaders headers){
		if (WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_TOTAL, usuarioBusiness) == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		List<Bairro> list = bairroBusiness.findAll();
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getAllPublic")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPublic (@Context HttpHeaders headers){
		
		List<Bairro> list = bairroBusiness.findAll();
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}

	@POST
	@Path("/getByExamplePublic")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByExamplePublic (String json,@Context HttpHeaders headers){
		
		Bairro example = gson.fromJson(json, Bairro.class);
		List<Bairro> list = bairroBusiness.findByExample(example);
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getAllFast")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFast(@Context HttpHeaders headers){
		
		List<Bairro> list = bairroBusiness.findAllFast();
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getAllPaginated")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(Integer page, @Context HttpHeaders headers){
		if (WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_TOTAL, usuarioBusiness) == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		List<Bairro> list = bairroBusiness.findAll(page);
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getByExample")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByExample(String json, @Context HttpHeaders headers){
		Bairro example = gson.fromJson(json, Bairro.class);
		List<Bairro> list = bairroBusiness.findByExample(example,example.getListPage(),example.getListPageSize());
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/create") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String json, @Context HttpHeaders headers){	
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_TOTAL, usuarioBusiness);
		Bairro entidade = gson.fromJson(json, Bairro.class);
		entidade.setDataCadastro(new Date());
		entidade.setUsuarioCadastro(usuario);
		
		try {
			entidade = bairroBusiness.create(entidade);			
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
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_TOTAL, usuarioBusiness);
		Bairro entidade = gson.fromJson(json, Bairro.class);
		entidade.setDataAtualizacao(new Date());
		entidade.setUsuarioAtualizacao(usuario);
				
		try {
			entidade = bairroBusiness.update(entidade);
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
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_TOTAL, usuarioBusiness);
		if (usuario == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}

		Bairro example = gson.fromJson(json, Bairro.class);
		List<Bairro> list = bairroBusiness.findByExample(example);

		byte[] bytesReport = null;
		try {
			bytesReport = bairroBusiness.listagemPDF(this.httpServletResquest, usuario, list);
			
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return Response.ok(bytesReport).header("Access-Control-Allow-Origin", frontEndResponse)
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept")
				.header("Content-Disposition", "filename=\"" + "bairroList" + ".pdf\"").build();
	}

	// METHODS_RESOURCE
}

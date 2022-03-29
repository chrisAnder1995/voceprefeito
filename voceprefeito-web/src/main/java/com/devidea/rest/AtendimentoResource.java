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

import com.devidea.business.AtendimentoBusiness;
import com.devidea.enumeration.Permissao;
import com.devidea.model.Atendimento;
import com.devidea.model.Bairro;
import com.devidea.model.ListagemPojo;
import com.devidea.util.DateDeserializer;
import com.devidea.util.PDFUtil;
import com.devidea.util.WebUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.devidea.business.UsuarioBusiness;
import com.devidea.model.Usuario;
// IMPORTS_RESOURCE


import net.sf.jasperreports.engine.JRException;

@Path("/atendimento")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class AtendimentoResource {

	@Context
	private HttpServletRequest httpServletResquest;
	
	@EJB
	private AtendimentoBusiness atendimentoBusiness;
	
	@EJB
	private UsuarioBusiness usuarioBusiness;

// VARIABLES_RESOURCE

	
	private Gson gson = new Gson();
	private final String frontEndResponse = "*";
	
	public AtendimentoResource() {
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
		Atendimento entidade = atendimentoBusiness.findById(id);
		
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
		List<Atendimento> list = atendimentoBusiness.findAll();
		
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
		List<Atendimento> list = atendimentoBusiness.findAllFast();
		
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
		List<Atendimento> list = atendimentoBusiness.findAll(page);
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getByExample")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByExample(String json, @Context HttpHeaders headers){
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness);
		if (usuario == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		
		Atendimento example = gson.fromJson(json, Atendimento.class);
		List<Atendimento> list = atendimentoBusiness.findByExample(example,example.getListPage(),example.getListPageSize());
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/create") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String json, @Context HttpHeaders headers) throws IOException{	
		Atendimento entidade = gson.fromJson(json, Atendimento.class);
		entidade.setDataCadastro(new Date());
		Usuario user = usuarioBusiness.create(entidade.getUsuario());
		entidade.setUsuario(user);
		try {
			entidade = atendimentoBusiness.create(entidade);			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return Response.ok(gson.toJson(entidade)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/update") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(String json, @Context HttpHeaders headers) {	
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness);
		if (usuario == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		Atendimento entidade = gson.fromJson(json, Atendimento.class);
	
		entidade.setDataAtualizacao(new Date());
		entidade.setUsuarioAtualizacao(usuario);
				
		try {
			entidade = atendimentoBusiness.update(entidade);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return Response.ok(gson.toJson(entidade)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/listagem")
	@Produces("application/json")
	public Response listagem(String json, @Context HttpHeaders headers){	
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness);
		if (usuario == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		
		Atendimento example = gson.fromJson(json, Atendimento.class);
		List<Atendimento> list = atendimentoBusiness.findByExample(example, null, null);

		byte[] bytesReport = null;
		try {
			bytesReport = atendimentoBusiness.listagemPDF(this.httpServletResquest, usuario, list);
			
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return Response.ok(bytesReport).
				type("application/pdf").
				header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").
				header("Content-Disposition", "filename=\"" + PDFUtil.LISTAGEM_NAME + ".pdf\"").
				build();
	}
	
	@POST
	@Path("/atendimentoPDF")
	@Produces("application/pdf")
	public Response atendimentoPDF(Integer id, @Context HttpHeaders headers) {
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness);
		if (usuario == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		byte[] bytesReport = null;
		try {
			bytesReport = atendimentoBusiness.generatePDF(id, this.httpServletResquest);
			
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return Response.ok(bytesReport).
				type("application/pdf").
				header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").
				header("Content-Disposition", "filename=\"" + PDFUtil.ATENDIMENTO_NAME + ".pdf\"").build();
	}
	
	@POST
	@Path("auxilioLegalPDF")
	@Produces("application/pdf")
	public Response auxilioLegalPDF(Integer id, @Context HttpHeaders headers) {
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness);
		if (usuario == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		byte[] bytesReport = null;
		try {
			bytesReport = atendimentoBusiness.auxilioLegalPDF(id, this.httpServletResquest);
			
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return Response.ok(bytesReport).
				type("application/pdf").
				header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").
				header("Content-Disposition", "filename=\"" + PDFUtil.AUXILIO_LEGAL_NAME + ".pdf\"").build();
	}
//
//	@POST
//	@Path("/listagem")
//	@Produces("application/pdf")
//	public Response listagem(String json, @Context HttpHeaders headers){
//		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness);
//		if (usuario == null) {
//			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
//		}
//
//		Atendimento example = gson.fromJson(json, Atendimento.class);
//		List<Atendimento> list = atendimentoBusiness.findByExample(example);
//
//		byte[] bytesReport = null;
//		try {
//			bytesReport = atendimentoBusiness.listagemPDF(this.httpServletResquest, usuario, list);
//			
//		} catch (JRException | IOException e) {
//			e.printStackTrace();
//		}
//		return Response.ok(bytesReport).header("Access-Control-Allow-Origin", frontEndResponse)
//				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept")
//				.header("Content-Disposition", "filename=\"" + "atendimentoList" + ".pdf\"").build();
//	}

	// METHODS_RESOURCE
}

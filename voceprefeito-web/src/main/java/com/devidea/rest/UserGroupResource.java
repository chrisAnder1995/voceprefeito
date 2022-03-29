package com.devidea.rest;

import java.io.IOException;
import java.util.ArrayList;
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

import com.devidea.business.UserGroupBusiness;
import com.devidea.business.UsuarioBusiness;
import com.devidea.enumeration.Permissao;
import com.devidea.model.UserGroup;
import com.devidea.model.Usuario;
import com.devidea.util.WebUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
// IMPORTS_RESOURCE

import net.sf.jasperreports.engine.JRException;

@Path("/userGroup")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class UserGroupResource {

	@Context
	private HttpServletRequest httpServletResquest;
	
	@EJB
	private UserGroupBusiness userGroupBusiness;
	
	@EJB
	private UsuarioBusiness userBusiness;
	
	// VARIABLES_RESOURCE
	
	private Gson gson = new Gson();
	private final String frontEndResponse = "*";
	
	public UserGroupResource() {
		GsonBuilder gb = new GsonBuilder();
		gson = gb.excludeFieldsWithoutExposeAnnotation().setDateFormat("MM/dd/yyyy").create();
	}
	
	@POST
	@Path("/getById")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(Integer id, @Context HttpHeaders headers){
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, userBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		UserGroup UsuarioGroup = userGroupBusiness.findById(id);
		
		return Response.ok(gson.toJson(UsuarioGroup)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpHeaders headers){
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, userBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		List<UserGroup> list = userGroupBusiness.findAll();
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getAllFast")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFast(@Context HttpHeaders headers){
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, userBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		
		List<UserGroup> list = userGroupBusiness.findAllFast();
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getAllPaginated")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(Integer page, @Context HttpHeaders headers){
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, userBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		List<UserGroup> list = userGroupBusiness.findAll(page);
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getByExample")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByExample(String json, @Context HttpHeaders headers){
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, userBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		
		UserGroup example = gson.fromJson(json, UserGroup.class);
		List<UserGroup> list = userGroupBusiness.findByExample(example,example.getListPage(),example.getListPageSize());
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/create") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String json, @Context HttpHeaders headers){	
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, userBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		UserGroup usuarioGroup = gson.fromJson(json, UserGroup.class);
		usuarioGroup.setDataCadastro(new Date());
		usuarioGroup.setUsuarioCadastro(user);
		
		try {
			usuarioGroup = userGroupBusiness.create(usuarioGroup);			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return Response.ok(gson.toJson(usuarioGroup)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/update") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(String json, @Context HttpHeaders headers){	
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, userBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		
		UserGroup usuarioGroup = gson.fromJson(json, UserGroup.class);
		usuarioGroup.setDataAtualizacao(new Date());
		usuarioGroup.setUsuarioAtualizacao(user);
				
		try {
			usuarioGroup = userGroupBusiness.update(usuarioGroup);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return Response.ok(gson.toJson(usuarioGroup)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}

	@POST
	@Path("/listagem")
	@Produces("application/pdf")
	public Response listagem(String json, @Context HttpHeaders headers){
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, userBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}

		UserGroup example = gson.fromJson(json, UserGroup.class);
		List<UserGroup> list = userGroupBusiness.findByExample(example);

		byte[] bytesReport = null;
		try {
			bytesReport = userGroupBusiness.listagemPDF(this.httpServletResquest, user, list);
			
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return Response.ok(gson.toJson(bytesReport)).header("Access-Control-Allow-Origin", frontEndResponse)
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept")
				.header("Content-Disposition", "filename=\"" + "UsuarioGroups" + ".pdf\"").build();
	}
	
	

	// METHODS_RESOURCE
}

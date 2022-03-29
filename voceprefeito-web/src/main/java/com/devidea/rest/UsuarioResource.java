package com.devidea.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.devidea.business.PoliticaBusiness;
import com.devidea.business.RegiaoBusiness;
import com.devidea.business.UsuarioBusiness;
import com.devidea.enumeration.Permissao;
import com.devidea.model.Politica;
import com.devidea.model.Regiao;
import com.devidea.model.Usuario;
import com.devidea.util.GeneralUtil;
import com.devidea.util.PhotoUtil;
import com.devidea.util.WebUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class UsuarioResource {
	
	@Context
	private HttpServletRequest httpServletResquest;
	
	@EJB
	private UsuarioBusiness usuarioBusiness;
	
	@EJB
	private RegiaoBusiness regiaoBusiness;
	
	@EJB
	private PoliticaBusiness politicaBusiness;
	
	private Gson gson = new Gson();
	private final String frontEndResponse = "*";
	
	String[] versions = {"1.0.0"};
	
	public UsuarioResource() {
		GsonBuilder gb = new GsonBuilder();
		gb.excludeFieldsWithoutExposeAnnotation();
		gson = gb.excludeFieldsWithoutExposeAnnotation().setDateFormat("dd/MM/yyyy").create();
	}
	
	@POST
	@Path("/verifyVersion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyVersion(String versionNumber) {
		boolean acceptable = false;
		
		for (String version : versions) {
			if (version.equals(versionNumber)) {
				acceptable = true;
			}
		}
		
		return Response.ok(gson.toJson(acceptable)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	
	@POST
	@Path("/getById")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(Integer id, @Context HttpHeaders headers){
		if (WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness) == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		Usuario usuario = usuarioBusiness.findById(id);
		
		return Response.ok(gson.toJson(usuario)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpHeaders headers){
		if (WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness) == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		List<Usuario> list = usuarioBusiness.findAll();
		
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
		List<Usuario> list = usuarioBusiness.findAllFast();
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/consultarUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(String json,@Context HttpHeaders headers){
		
		Usuario usuario = gson.fromJson(json, Usuario.class);
		List<Usuario> list = usuarioBusiness.consultarUsuario(usuario);
		
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
		
		Usuario example = gson.fromJson(json, Usuario.class);
		List<Usuario> list = usuarioBusiness.findByExample(example,example.getListPage(),example.getListPageSize());
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	
	
	@POST
	@Path("/create") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String json, @Context HttpHeaders headers){	
		Usuario usuarioAtt = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness);
		if (usuarioAtt == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		Usuario usuario = gson.fromJson(json, Usuario.class);
		usuario.setDataCadastro(new Date());
		usuario.setUsuarioCadastro(usuarioAtt);
		
		try {
			usuario.setEmail(usuario.getEmail().toLowerCase());
			usuario.setSenha(usuario.getSenha().toUpperCase());
			usuario = usuarioBusiness.create(usuario);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return Response.ok(gson.toJson(usuario)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/update") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(String json, @Context HttpHeaders headers){	
		Usuario usuarioAtt = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ATENDENTE, usuarioBusiness);
		if (usuarioAtt == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		Usuario usuario = gson.fromJson(json, Usuario.class);
		usuario.setDataAtualizacao(new Date());
		
		if (usuario.getId().intValue() != usuarioAtt.getId().intValue()) {
			usuario.setUsuarioAtualizacao(usuarioAtt);
		}
		
		try {
			usuario = usuarioBusiness.update(usuario);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return Response.ok(gson.toJson(usuario)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/login") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String json, @Context HttpHeaders headers){
		Usuario usuario = gson.fromJson(json, Usuario.class);
		
		String email = usuario.getEmail().toLowerCase();
		String password = usuario.getSenha().toUpperCase();
		
		usuario = this.usuarioBusiness.login(email,password);
		
		if (usuario == null){
			usuario = new Usuario();
		} else {
			usuario.setJwt(WebUtils.issueToken(email));
			try {
				usuario.getImagem().setBase64(PhotoUtil.getEncodedPhoto(usuario.getImagem().getNome()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return Response.ok(gson.toJson(usuario)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/getToken") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getToken(String json, @Context HttpHeaders headers){
		Usuario usuario = gson.fromJson(json, Usuario.class);
		
		String email = usuario.getEmail().toLowerCase();

		String jwt = WebUtils.issueToken(email);
		
		return Response.ok(gson.toJson(jwt)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@GET
	@Path("/inicializar")
	@Produces("application/json")
	public Response inicializar(){
		String email = "felipedantas89@gmail.com.br";
		String password = "abcd1234";
		String cpf = "10662411471";
		String firstName = "DevIdea";
		String lastName = ""; 
		
		List<Usuario> usuarios = usuarioBusiness.findAll();
		
		Usuario usuario = new Usuario();
		
		
		 for(int i = 1; i < 15; i++){
			 Regiao regiao = new Regiao();
			 regiao.setAtivo(true);
			 regiao.setNome(i + "ª RPP");
			 regiaoBusiness.create(regiao);
		 }
		 
			 Politica politica1 = new Politica();
			 politica1.setAtivo(true);
			 politica1.setNome("Assistência Social");
			 politicaBusiness.create(politica1);
			 
			 Politica politica2 = new Politica();
			 politica2.setAtivo(true);
			 politica2.setNome("Cultuta,Esporte e Educação");
			 politicaBusiness.create(politica2);
			 
			 Politica politica3 = new Politica();
			 politica3.setAtivo(true);
			 politica3.setNome("Habitação");
			 politicaBusiness.create(politica3);
			 
			 Politica politica4 = new Politica();
			 politica4.setAtivo(true);
			 politica4.setNome("Saúde");
			 politicaBusiness.create(politica4);
			 
			 Politica politica5 = new Politica();
			 politica5.setAtivo(true);
			 politica5.setNome("Infraestrutura");
			 politicaBusiness.create(politica5);
			 
		if (usuarios.size() == 0) {	
			password = GeneralUtil.generateHash(password);
			
			usuario = new Usuario();
			usuario.setAtivo(true);
		//	usuario.setDateAUD(new Date());
			usuario.setEmail(email);
			usuario.setPermissao(Permissao.ACESSO_TOTAL);
			usuario.setSenha(password.toUpperCase());
			usuario.setNome(firstName);
			usuario.setSobrenome(lastName);
			usuario.setCpf(cpf);
			
			
			try {
				usuario = usuarioBusiness.create(usuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		File precadastrosFile = new File(PhotoUtil.getDataPath()+ File.separator + "precadastros.txt");
		
		if (precadastrosFile.exists()) {
			try {
				String preCadastros = new String(Files.readAllBytes(precadastrosFile.toPath()));
				
				String[] preCadastroList = preCadastros.split("\\n");
				
				for (String linha : preCadastroList) {
					if (!linha.equals("")) {
						String[] preCadastroSplit = linha.split("\\|");

//						try {
//						    Class<?> model = Class.forName("br.com.devidea." + preCadastroSplit[0]);
//						    Class<?> business = Class.forName("br.com.devidea." + preCadastroSplit[0] + "Business");
//						    
//						    model.
//						 } catch (ClassNotFoundException e) {
//						        e.printStackTrace();
//						}
						
						usuarioBusiness.preCadastro(preCadastroSplit[0],preCadastroSplit[1],preCadastroSplit[2]);
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return Response.ok(usuario).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
}

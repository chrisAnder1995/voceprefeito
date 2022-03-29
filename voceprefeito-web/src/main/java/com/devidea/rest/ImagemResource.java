package com.devidea.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.devidea.business.ImagemBusiness;
import com.devidea.business.UsuarioBusiness;
import com.devidea.enumeration.Permissao;
import com.devidea.model.Imagem;
import com.devidea.model.Usuario;
import com.devidea.util.PhotoUtil;
import com.devidea.util.WebUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Path("/imagem")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class ImagemResource {
	
	@Context
	private HttpServletRequest httpServletResquest;
	
	@EJB
	private ImagemBusiness imageBusiness;
	
	@EJB
	private UsuarioBusiness usuarioBusiness;
	
	private Gson gson = new Gson();
	private final String frontEndResponse = "*";
	
	public ImagemResource() {
		GsonBuilder gb = new GsonBuilder();
		gb.excludeFieldsWithoutExposeAnnotation();
		gson = gb.excludeFieldsWithoutExposeAnnotation().setDateFormat("dd/MM/yyyy").create();
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
		
		return Response.ok(usuario).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@GET
	@Path("/getByIdSmallGET/{id}")
	@Produces("image/jpeg")
	public Response getByIdSmallGET(@PathParam("id") Integer id) throws IOException{
		//System.out.println((new Date()).toString());
		Imagem imageObj = imageBusiness.findById(id);	
		File file;
		
		if (imageObj.getNome() == null || imageObj.getNome().equals("")){
			String base64 = imageObj.getBase64();
			byte[] imageByte = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			
			BufferedImage image = null;
			image = ImageIO.read(bis);
			bis.close();
			
			try {
				image = PhotoUtil.resizeImage(image, 300, 300);
			} catch(Exception e) {
				System.out.println("Imagem n�o encontrada");
			}
			
			
			// write the image to a file
			file = new File("image.png");
			ImageIO.write(image, "png", file);
		} else {
			//file = new File(PhotoUtil.getPhotoPath()+File.separator+imageObj.getNome());
			
			// write the image to a file
			file = new File(PhotoUtil.getPhotoPath()+File.separator+"image.png");
			BufferedImage image = PhotoUtil.resizeImage(PhotoUtil.getPhotoPath()+File.separator+imageObj.getNome(), 300, 300);
			ImageIO.write(image, "png", file);
		}

		ResponseBuilder response = Response.ok((Object) gson.toJson( file));
		response.header("Content-Disposition",
				"attachment; filename=image_from_server.png");

		
		return response.build();
	}
	
	@POST
	@Path("/getByIdSmall")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByIdSmall(Integer id, @Context HttpHeaders headers){	
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, usuarioBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		if (id != null && id > 0) {
			Imagem image = imageBusiness.findById(id);
			
			try {
				image.setBase64(PhotoUtil.resizeBase64(image.getNome(),100));
			} catch(Exception e) {
				System.out.println("Imagem n�o encontrada: " + image.getNome());
			}
			
			return Response.ok(gson.toJson(image)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
		} else {
			String logo = this.httpServletResquest.getSession().getServletContext().getRealPath("/") + 
							File.separator + "assets" +	File.separator + "img" + File.separator + "logo.jpg"; 
			
			String base64;
			try {
				base64 = PhotoUtil.getBase64FullPath(logo);
			} catch (IOException e) {
				base64 = "";
				e.printStackTrace();
			}
			
			Imagem image = new Imagem();
			image.setBase64(base64);
			
			return Response.ok(gson.toJson(image)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
		}
	}
	
	@POST
	@Path("/getByIdMedium")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByIdMedium(Integer id, @Context HttpHeaders headers){	
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, usuarioBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		if (id != null && id > 0) {
			Imagem image = imageBusiness.findById(id);
			
			try {
				image.setBase64(PhotoUtil.resizeBase64(image.getNome(),250));
			} catch(Exception e) {
				System.out.println("Imagem n�o encontrada: " + image.getNome());
			}
			
			return Response.ok(gson.toJson(image)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
		} else {
			String logo = this.httpServletResquest.getSession().getServletContext().getRealPath("/") + 
							File.separator + "assets" +	File.separator + "img" + File.separator + "logo.jpg"; 
			
			String base64;
			try {
				base64 = PhotoUtil.getBase64FullPath(logo);
			} catch (IOException e) {
				base64 = "";
				e.printStackTrace();
			}
			
			Imagem image = new Imagem();
			image.setBase64(base64);
			
			return Response.ok(image).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
		}
	}
	
	@POST
	@Path("/getByIdBig")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByIdBig(Integer id, @Context HttpHeaders headers){	
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, usuarioBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		if (id != null && id > 0) {
			Imagem image = imageBusiness.findById(id);
			
			try {
				image.setBase64(PhotoUtil.resizeBase64(image.getNome(),500));
			} catch(Exception e) {
				System.out.println("Imagem n�o encontrada: " + image.getNome());
			}
			
			return Response.ok(gson.toJson(image)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
		} else {
			String logo = this.httpServletResquest.getSession().getServletContext().getRealPath("/") + 
							File.separator + "assets" +	File.separator + "img" + File.separator + "logo.jpg"; 
			
			String base64;
			try {
				base64 = PhotoUtil.getBase64FullPath(logo);
			} catch (IOException e) {
				base64 = "";
				e.printStackTrace();
			}
			
			Imagem image = new Imagem();
			image.setBase64(base64);
			
			return Response.ok(image).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
		}
	}
	
	@POST
	@Path("/file")
	@Produces("image/jpeg")
	public Response file(Integer id, @Context HttpHeaders headers){
		Usuario user = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, usuarioBusiness);
		if (user == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		Imagem imageObj = imageBusiness.findById(id);	
		File file;
					
		file = new File(PhotoUtil.getPhotoPath()+File.separator+imageObj.getNome());
		byte[] fileContent;
		try {
			fileContent = Files.readAllBytes(file.toPath());
			
			ResponseBuilder response = Response.ok(fileContent);
			response.header("Content-Disposition",
					"attachment; filename=file."+imageObj.getTipo());
			
			return response.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
}

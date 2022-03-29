package com.devidea.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import javax.ws.rs.core.Response.ResponseBuilder;

import com.devidea.business.LocalFileBusiness;
import com.devidea.business.UsuarioBusiness;
import com.devidea.enumeration.Permissao;
import com.devidea.model.LocalFile;
import com.devidea.model.Usuario;
import com.devidea.util.PhotoUtil;
import com.devidea.util.WebUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Path("/localFile")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class LocalFileResource {
	
	@Context
	private HttpServletRequest httpServletResquest;
	
	@EJB
	private LocalFileBusiness localFileBusiness;
	
	@EJB
	private UsuarioBusiness usuarioBusiness;
	
	private Gson gson = new Gson();
	private final String frontEndResponse = "*";
	
	public LocalFileResource() {
		GsonBuilder gb = new GsonBuilder();
		gson = gb.setDateFormat("dd/MM/yyyy").create();
	}
	
	@POST
	@Path("/getById")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(Integer id){
		if (id != null && id > 0) {
			LocalFile image = localFileBusiness.findById(id);
			LocalFile newLocalFile = gson.fromJson((gson.toJson(image)), LocalFile.class);
			
			newLocalFile.setBase64(PhotoUtil.resizeBase64(newLocalFile.getNome(),1000));
			
			return Response.ok(gson.toJson(newLocalFile)).header("Access-Control-Allow-Origin", frontEndResponse).
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
			
			LocalFile image = new LocalFile();
			image.setBase64(base64);
			
			return Response.ok(gson.toJson(image)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
		}
	}
	
	@POST
	@Path("/file")
	@Produces("image/jpeg")
	public Response file(Integer id, @Context HttpHeaders headers){
		Usuario usuario = WebUtils.validAccess(headers.getRequestHeaders().getFirst("authorization"), Permissao.ACESSO_SUSPEITA, usuarioBusiness);
		if (usuario == null) {
			return Response.serverError().status(Response.Status.UNAUTHORIZED).build();
		}
		LocalFile imageObj = localFileBusiness.findById(id);	
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
	
	@POST
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(){
		List<LocalFile> list = localFileBusiness.findAll();
		
		return Response.ok(gson.toJson(list)).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/create") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String json){	
		LocalFile image = gson.fromJson(json, LocalFile.class);
		
		Integer result = 0;
		
		try {
			image = localFileBusiness.create(image);
			result = image.getId();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return Response.ok(result).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
	
	@POST
	@Path("/update") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(String json){	
		LocalFile image = gson.fromJson(json, LocalFile.class);
		boolean result = false;
		
		try {
			image = localFileBusiness.update(image);
			result = true;
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return Response.ok(result).header("Access-Control-Allow-Origin", frontEndResponse).
				header("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-XSRF-TOKEN, Content-Type, Accept").build();
	}
}

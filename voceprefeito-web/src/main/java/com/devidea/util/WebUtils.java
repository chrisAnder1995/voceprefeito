package com.devidea.util;

import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;

import com.devidea.business.UsuarioBusiness;
import com.devidea.enumeration.Permissao;
import com.devidea.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Stateless
public class WebUtils {
	
	static final long EXPIRATION_TIME = 1_800_000; // A cada 600_000 � 10 minutos
	static final String SECRET = "2cda62efac2f25d2c227e82915a5671547cf54abd90bc2c0e958a8918ba05f15"; // senha secreta
	static final String PREFIX = "Bearer ";
	
	@EJB
	private UsuarioBusiness usuarioBusiness;

    @SuppressWarnings("deprecation")
	public static String issueToken(String login) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	            .signWith(signatureAlgorithm, signingKey)
                .compact();
		
        return jwtToken;
    }
    
    public static String decompactToken(String token) {
    	token = token.replaceAll(PREFIX, "");
    	Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(token).getBody();
    	
    	String login = claims.getSubject(); 
    	
    	return login;
    }
    
    public static Usuario validAccess(String token, Permissao permissao, UsuarioBusiness usuarioBusiness) {
    	try {
			String login = WebUtils.decompactToken(token);
			Usuario usuario = usuarioBusiness.findByEmail(login);
			
			if (usuario == null || usuario.getId() == null) {
				return null;
			}
	
			if (usuario.getPermissao() == Permissao.ACESSO_SUSPEITA && (permissao == Permissao.ATENDENTE || permissao == Permissao.ASSISTENTE_SOCIAL || permissao == Permissao.ADVOGADA || permissao == Permissao.ACESSO_TOTAL)) {
				return null;
			}
			if (usuario.getPermissao() == Permissao.ATENDENTE && (permissao == Permissao.ASSISTENTE_SOCIAL || permissao == Permissao.ADVOGADA || permissao == Permissao.ACESSO_TOTAL)) {
				return null;
			}
			if (usuario.getPermissao() == Permissao.ASSISTENTE_SOCIAL && (permissao == Permissao.ADVOGADA || permissao == Permissao.ACESSO_TOTAL)) {
				return null;
			}
			if (usuario.getPermissao() == Permissao.ADVOGADA && (permissao == Permissao.ACESSO_TOTAL)) {
				return null;
			}
	    	
	    	return usuario;
    	} catch (Exception ex) {
    		return null;
    	}
    }
    
}

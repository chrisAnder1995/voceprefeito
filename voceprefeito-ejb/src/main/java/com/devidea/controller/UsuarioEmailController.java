package com.devidea.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;

import org.apache.commons.mail.EmailException;

import com.devidea.business.UsuarioEmailBusiness;
import com.devidea.model.UsuarioEmail;
import com.devidea.util.MailUtil;

@Stateless
public class UsuarioEmailController implements UsuarioEmailBusiness {
	
	@Override
	public void sendMail(UsuarioEmail entity) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		
		String subjectMail = "Suporte ao Mulher Forte";
		StringBuilder emailBody = new StringBuilder();
		emailBody.append("E-mail registrado com sucesso! \n")
		.append("Nome: ").append(entity.getNome()).append("\n")
		.append("E-mail: ").append(entity.getEmail()).append("\n")
		.append("Assunto: ").append(entity.getAssunto()).append("\n")
		.append("Descrição: ").append(entity.getDescricao()).append("\n")
		.append("ON ").append(sdf.format(today));
		
		//String url = "http://localhost:8080/devyachts/service/signInRequest/confirm/" + entity.getUserEmail() + "/" + entity.getHashCode();
//		String url = "https://devidea.com.br/devyachts/service/signInRequest/confirm/" + entity.getNome();
		
		String emailHtml = 
				"<div style=\"max-width: 500px; border: 1px solid #aaa; margin: auto; padding: 20px;\">\n" + 
				"        <p\n" + 
				"            style=\"color:#444444 !important;font-size:28px;text-align:center;line-height:28px;font-family:Arial,sans-serif;margin-top:0px;margin-bottom:30px;padding-top:0px;padding-bottom:0px;font-weight:bold;\">\n" + 
				"            Mulher Forte\n" + 
				"        </p>\n" + 
				"        <p\n" + 
				"            style=\"color:#606060;font-size:14px;line-height:19px;font-family:Arial,sans-serif;margin-top:0;margin-bottom:20px;padding-top:0;padding-bottom:0;font-weight:normal;text-align:left\">\n" + 
				"            <strong>Nome:   </strong>\n"  + entity.getNome() + 
				"        </p>\n" + 
				"        <p\n" + 
				"            style=\"color:#606060;font-size:14px;line-height:19px;font-family:Arial,sans-serif;margin-top:0;margin-bottom:20px;padding-top:0;padding-bottom:0;font-weight:normal;text-align:left\">\n" + 
				"            <strong>E-mail:  </strong>\n"  + entity.getEmail() + 
				"        </p>\n" + 
				"        <p\n" + 
				"            style=\"color:#606060;font-size:14px;line-height:19px;font-family:Arial,sans-serif;margin-top:0;margin-bottom:20px;padding-top:0;padding-bottom:0;font-weight:normal;text-align:left\">\n" + 
				"            <strong>Assunto:  </strong>\n" + entity.getAssunto()  +
				"        </p>\n" + 
				"        <p\n" + 
				"            style=\"color:#606060;font-size:14px;line-height:19px;font-family:Arial,sans-serif;margin-top:0;margin-bottom:20px;padding-top:0;padding-bottom:0;font-weight:normal;text-align:left\">\n" + 
				"            <strong>Descrição: </strong>\n" + entity.getDescricao()  +
				"        </p>\n" + 
				
//				"        <p\n" + 
//				"            style=\"color:#444444;font-family:'Arimo',Helvetica,Arial,sans-serif;font-weight:400;line-height:20px;font-size:13px\">\n" + 
//				"           Seu e-mail foi registado com sucesso" + 
//				"            logo mais entraremos em contato com você!\n" + 
//				"            Obrigado por nos contatar! \n" + 
//				"        </p>\n" + 
//				"\n" + 

				"        <p align=\"center\"\n" + 
				"            <em>Suporte Mulher Forte</em></p>\n" + 
				"        <p></p>\n" + 
				"\n" + 
				"    </div>";
		
		try {
			MailUtil.sendEmail("felipedantas89@gmail.com", subjectMail, emailHtml, entity);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

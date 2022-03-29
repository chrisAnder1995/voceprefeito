package com.devidea.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import com.devidea.model.UsuarioEmail;

public class MailUtil {
	
//	public  static final String EMAIL = "chrisandervp95@gmail.com";
//	//private static final String PASS = "abcd1234";
//	private static final String SERVER_NAME = "smtp.gmail.com";
//	private static final int SERVER_PORT = 465;
//	private static final String SERVER_USERNAME = "chrisandervp95@gmail.com";
//	private static final String SERVER_PASSTaskRD = "capc030195";
	
	public  static final String EMAIL = "suporte.mulher@devidea.com.br";
	//private static final String PASS = "abcd1234";
	private static final String SERVER_NAME = "email-smtp.us-east-1.amazonaws.com";
	private static final int SERVER_PORT = 465;
	private static final String SERVER_USERNAME = "AKIA44OZFXTUMQSDKEF4";
	private static final String SERVER_PASSTaskRD = "BCXZ7xCfhfxwLLB5ub2mQh9OD/60aGwa5+TcdmYQJiv2";
	
	public static void sendEmail12(String from, String subject, String bodyText) throws EmailException {
		
//		Email email = new SimpleEmail();
//		email.setHostName("smtp.gmail.com");
//		email.setSslSmtpPort("465");
//		email.setAuthenticator(new DefaultAuthenticator("chrisandervp95@gmail.com", "capc030195"));
//		email.setSSLOnConnect(true);
//		email.setFrom(from);
//		email.setSubject("TestMail");
//		email.setMsg("This is a test mail ... :-)");
//		email.addTo(EMAIL);
		
    	Email email = new HtmlEmail();
    	email.setHostName(SERVER_NAME);
    	email.setSmtpPort(SERVER_PORT);
    	email.setAuthenticator(new DefaultAuthenticator(SERVER_USERNAME, SERVER_PASSTaskRD));
    	email.setSSLOnConnect(true);
    	//email.setTLS(true);
    	email.setSubject(subject);
    	//email.setMsg(bodyText);
    	email.setContent(bodyText, "text/html; charset=utf-8");
    	email.setFrom(from);
    	email.addTo(EMAIL);
    	
    	email.send();
    	
    }
	
	public static void sendEmail(String to, String subject, String bodyText) throws EmailException {
    	Email email = new HtmlEmail();
    	email.setHostName(SERVER_NAME);
    	email.setSmtpPort(SERVER_PORT);
    	email.setAuthenticator(new DefaultAuthenticator(SERVER_USERNAME, SERVER_PASSTaskRD));
    	email.setSSLOnConnect(true);
    	//email.setTLS(true);
    	email.setSubject(subject);
    	//email.setMsg(bodyText);
    	email.setContent(bodyText, "text/html; charset=utf-8");
    	email.setFrom(EMAIL);
    	email.addTo(to);
    	
    	
    	email.send();
    }

	public static void sendEmail(String to , String subject, String bodyText, UsuarioEmail entity) throws EmailException {
		// TODO Auto-generated method stub
		Email email = new HtmlEmail();
    	email.setHostName(SERVER_NAME);
    	email.setSmtpPort(SERVER_PORT);
    	email.setAuthenticator(new DefaultAuthenticator(SERVER_USERNAME, SERVER_PASSTaskRD));
    	email.setSSLOnConnect(true);
    	//email.setTLS(true);
    	email.setSubject(subject);
    	//email.setMsg(bodyText);
    	email.setContent(bodyText, "text/html; charset=utf-8");
    	email.setFrom(EMAIL);
    	email.addTo(to);
    	email.addReplyTo(entity.getEmail(), entity.getNome());
    	
    	email.send();
		
	}
    
    
//    public static void sendEmailWithCC(String to, String from, String[] ccEmails, String subject, String bodyText) throws EmailException {
//    	Email email = new SimpleEmail();
//    	email.setHostName("smtp.gmail.com");
//    	email.setSmtpPort(465);
//    	email.setAuthenticator(new DefaultAuthenticator(EMAIL, PASS));
//    	email.setSSLOnConnect(true);
//    	email.setSubject(subject);
//    	email.setMsg(bodyText);
//    	email.setFrom(from);
//    	email.addTo(to);
//    	if(ccEmails != null && ccEmails.length > 0) {
//    		email.addCc(ccEmails);
//    	}
//    	email.send();
//    }
    
//	public static MimeMessage createEmail(String to, String from, String subject, String bodyText) throws AddressException, MessagingException {
//		Properties props = new Properties();
//		Session session = Session.getDefaultInstance(props, null);
//		
//		MimeMessage email = new MimeMessage(session);
//		email.setFrom(new InternetAddress(from));
//		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
//		email.setSubject(subject);
//		email.setText(bodyText);
//		return email;
//	}
//	
//	public static Message createMessageWithEmail(MimeMessage emailContent) throws IOException, MessagingException {
//		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//		emailContent.writeTo(buffer);
//		byte[] bytes = buffer.toByteArray();
//		String encodeEmail = Base64.encodeBase64URLSafeString(bytes);
//		
//		Message message = new Message();
//		message.setRaw(encodeEmail);
//		return message;
//	}
//	
//	public static Message sendMessage(Gmail service, String userId, MimeMessage emailContent) throws IOException, MessagingException {
//		Message message = MailUtil.createMessageWithEmail(emailContent);
//		message = service.users().messages().send(userId, message).execute();
//		System.out.println("Message id: " + message.getId());
//        System.out.println(message.toPrettyString());
//		return message;
//	}
//	
//	private static Credential getCredentials(String realPath, final NetHttpTransport HTTP_TRANSPORT) throws IOException {
//        // Load client secrets.
//        InputStream in = new FileInputStream(new File(realPath + File.separator + CREDENTIALS_FILE_PATH));
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//        // Build flow and trigger user authorization request.
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//        		HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, Arrays.asList(GmailScopes.GMAIL_READONLY, GmailScopes.GMAIL_SEND, GmailScopes.GMAIL_COMPOSE))
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//                .setAccessType("offline")
//                .build();
//        
//        LocalServerReceiver receier = new LocalServerReceiver.Builder().setPort(8888).build();
//        return new AuthorizationCodeInstalledApp(flow, receier).authorize("user");
//    }
//	
//	public static Message sendEmail(HttpServletRequest servletRequest, String to, String from, String subject, String bodyText) throws GeneralSecurityException, IOException, AddressException, MessagingException {
//		// Find the server path
//		String realPath = servletRequest.getSession().getServletContext().getRealPath("/");
//		realPath = realPath.replace("web.war", "ejb.jar");
//		
//		// Build a new authorized API client service.
//		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//		Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(realPath, HTTP_TRANSPORT))
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//		
//		MimeMessage emailContent = createEmail(to, from, subject, bodyText);
//		Message message = createMessageWithEmail(emailContent);
//		message = sendMessage(service, "me", emailContent);
//		return message;
//	}
}

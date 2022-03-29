package com.devidea.business;

import javax.ejb.Local;

import com.devidea.model.UsuarioEmail;

@Local
public interface UsuarioEmailBusiness {

	void sendMail(UsuarioEmail entity);
}

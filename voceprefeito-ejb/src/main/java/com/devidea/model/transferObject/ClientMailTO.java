package com.devidea.model.transferObject;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClientMailTO {
	@Expose
	private String name;
	@Expose
	private String email;
	@Expose
	private String subject;
	@Expose
	private String message;
}

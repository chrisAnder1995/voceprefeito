package com.devidea.model.transferObject;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignInTO {
	@Expose
	private String name;
	@Expose
	private String lastName;
	@Expose
	private String email;
}

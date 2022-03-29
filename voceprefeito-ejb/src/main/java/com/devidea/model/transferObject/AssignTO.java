package com.devidea.model.transferObject;

import java.util.Date;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssignTO {

	@Expose
	private Integer idWorkOrder;
	@Expose
	private Integer idUserAssigned;
	@Expose
	private Integer idUserManager;
	@Expose
	private Date orderDate;
	@Expose
	private String textDescriptionEdited;
}

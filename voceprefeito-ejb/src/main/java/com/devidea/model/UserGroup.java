package com.devidea.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="user_group")
@SequenceGenerator(name = "seq_user_group", sequenceName = "seq_user_group", allocationSize = 1)
public class UserGroup extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 2691413916387841395L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_user_group")
    @Expose
    private Integer id;

    @Column(name="description", nullable=false)
    @Expose
    private String description;

    @Column(name="total_access", nullable=true)
    @Expose
    private Boolean totalAccess;

    
}

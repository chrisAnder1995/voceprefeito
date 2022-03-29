package com.devidea.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="local_file")
@SequenceGenerator(name = "seq_local_file", sequenceName = "seq_local_file", allocationSize = 1)
public class LocalFile extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 23292175786830334L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_local_file")
    @Expose
    private Integer id;

    @Column(name="nome", nullable=false)
    @Expose
    private String nome;

    @Column(name="tipo", nullable=false)
    @Expose
    private String tipo;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name="data", nullable=false)
    @Expose
    private Date data;
    
    @Transient
    @Expose
    private String base64;
}

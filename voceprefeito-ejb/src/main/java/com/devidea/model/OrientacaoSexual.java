package com.devidea.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="orientacaosexual")
@SequenceGenerator(name = "seq_orientacaosexual", sequenceName = "seq_orientacaosexual", allocationSize = 1)
public class OrientacaoSexual extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_orientacaosexual")
    @Expose
    private Integer id;

    @Column(name="nome", nullable=false)
    @Expose
    private String nome;
}

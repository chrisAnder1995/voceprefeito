
package com.devidea.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.devidea.enumeration.ViolenciaCategoria;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="violenciatipo")
@SequenceGenerator(name = "seq_violenciatipo", sequenceName = "seq_violenciatipo", allocationSize = 1)
public class ViolenciaTipo extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_violenciatipo")
    @Expose
    private Integer id;

    @Column(name="nome", nullable=false)
    @Expose
    private String nome;

    @Column(name="pena")
    @Expose
    private String pena;

    @Column(name="artigo")
    @Expose
    private String artigo;

    @Column(name="lei", columnDefinition="TEXT")
    @Expose
    private String lei;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="violencia_categoria")
    private ViolenciaCategoria violenciaCategoria;
}

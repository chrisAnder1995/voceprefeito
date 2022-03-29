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

import com.devidea.enumeration.Permissao;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="user_email")
@SequenceGenerator(name = "seq_user_email", sequenceName = "seq_user_email", allocationSize = 1)
public class UsuarioEmail extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 2691413916387841395L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_user_email")
    @Expose
    private Integer id;

    @Column(name="nome", nullable=false)
    @Expose
    private String nome;

    @Column(name="sobrenome",nullable=false)
    @Expose
    private String sobrenome;

    @Column(name="email", nullable=false)
    @Expose
    private String email;

    @Column(name="assunto", nullable=false)
    @Expose
    private String assunto;
    
    @Column(name="descricao", nullable=false)
    @Expose
    private String descricao;

    public String getFullName(){
        String fullName = this.nome;

        if (sobrenome != null && !sobrenome.equals("")){
            fullName += " " + this.sobrenome;
        }

        return fullName;
    }
}
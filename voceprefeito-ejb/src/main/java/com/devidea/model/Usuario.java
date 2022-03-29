package com.devidea.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.devidea.enumeration.Escolaridade;
import com.devidea.enumeration.Permissao;
import com.devidea.enumeration.Sexo;
import com.devidea.enumeration.Trabalho;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="local_user")
@SequenceGenerator(name = "seq_local_user", sequenceName = "seq_local_user", allocationSize = 1)
public class Usuario extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 2691413916387841395L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_local_user")
    @Expose
    private Integer id;

    @Column(name="nome")
    @Expose
    private String nome;

    @Column(name="sobrenome")
    @Expose
    private String sobrenome;

    @Column(name="email")
    @Expose
    private String email;
    
    @Column(name="cpf")
    @Expose
    private String cpf;

    @Column(name="senha")
    @Expose
    private String senha;
    
    @Column(name="data_nascimento")
    @Expose
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="sexo")
    private Sexo sexo;
    
    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="escolaridade")
    private Escolaridade escolaridade;
    
    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="trabalho")
    private Trabalho trabalho;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="permissao")
    private Permissao permissao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="imagem_id", referencedColumnName="id")
    @Expose
    private Imagem imagem;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_group_id", referencedColumnName="id")
    @Expose
    private UserGroup userGroup;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="regiao_id", referencedColumnName="id")
    @Expose
    private Regiao regiao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bairro_id", referencedColumnName="id")
    @Expose
    private Bairro bairro;
    
    @Column(name="avaliacao")
    @Expose
    private BigDecimal avaliacao;
    
    @Column(name="opcional")
    @Expose
    private Boolean opcional;
    
    @Transient
    @Expose
    private String jwt;
    
    public String getFullName(){
        String fullName = this.nome;

        if (sobrenome != null && !sobrenome.equals("")){
            fullName += " " + this.sobrenome;
        }

        return fullName;
    }
}
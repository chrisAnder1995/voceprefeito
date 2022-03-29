package com.devidea.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.devidea.enumeration.Turno;
import com.devidea.enumeration.ViolenciaCategoria;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="ocorrencia")
@SequenceGenerator(name = "seq_ocorrencia", sequenceName = "seq_ocorrencia", allocationSize = 1)
public class Ocorrencia extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_ocorrencia")
    @Expose
    private Integer id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name="data")
    @Expose
    private Date data;

    @Enumerated(EnumType.STRING)
    @Expose
    @Column(name="turno")
    private Turno turno;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="local_ocorrencia_id", referencedColumnName="id", nullable=true)
    @Expose
    private LocalOcorrencia localOcorrencia;

    @Column(name="endereco")
    @Expose
    private String endereco;

    @Column(name="numero")
    @Expose
    private String numero;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bairro_id", referencedColumnName="id", nullable=true)
    @Expose
    private Bairro bairro;
    
    @Transient
    @Expose
    private List<ViolenciaTipo> violencias;
    
    public String getEndereco() {
    	if (endereco != null) {
    		return endereco;
    	} else {
    		return "";
    	}
    }
    
    public String getNumero() {
    	if (numero != null) {
    		return numero;
    	} else {
    		return "";
    	}
    }
    
    public String getViolenciasString() {
    	String violencias = "";
		
		if (getViolencias() != null) {
			for (ViolenciaTipo violencia : getViolencias()) {
				if (!violencias.equals("")) {
					violencias += ", ";
				}
				violencias += violencia.getNome();
			}
		}
		
		return violencias;
    }
    
    public String getViolenciasFisicas() {
    	String violencias = "";
		
		if (getViolencias() != null) {
			for (ViolenciaTipo violencia : getViolencias()) {
				if (violencia.getViolenciaCategoria() == ViolenciaCategoria.FISICA) {
					if (!violencias.equals("")) {
						violencias += ", ";
					}
					violencias += violencia.getNome();
				}
			}
		}
		
		return violencias;
    }
    
    public String getViolenciasPsicologicas() {
    	String violencias = "";
		
		if (getViolencias() != null) {
			for (ViolenciaTipo violencia : getViolencias()) {
				if (violencia.getViolenciaCategoria() == ViolenciaCategoria.PSICOLOGICA) {
					if (!violencias.equals("")) {
						violencias += ", ";
					}
					violencias += violencia.getNome();
				}
			}
		}
		
		return violencias;
    }
    
    public String getViolenciasPatrimoniais() {
    	String violencias = "";
		
		if (getViolencias() != null) {
			for (ViolenciaTipo violencia : getViolencias()) {
				if (violencia.getViolenciaCategoria() == ViolenciaCategoria.PATRIMONIAL) {
					if (!violencias.equals("")) {
						violencias += ", ";
					}
					violencias += violencia.getNome();
				}
			}
		}
		
		return violencias;
    }
    
    public String getViolenciasSexuais() {
    	String violencias = "";
		
		if (getViolencias() != null) {
			for (ViolenciaTipo violencia : getViolencias()) {
				if (violencia.getViolenciaCategoria() == ViolenciaCategoria.SEXUAL) {
					if (!violencias.equals("")) {
						violencias += ", ";
					}
					violencias += violencia.getNome();
				}
			}
		}
		
		return violencias;
    }
    
    public String getViolenciasMorais() {
    	String violencias = "";
		
		if (getViolencias() != null) {
			for (ViolenciaTipo violencia : getViolencias()) {
				if (violencia.getViolenciaCategoria() == ViolenciaCategoria.MORAL) {
					if (!violencias.equals("")) {
						violencias += ", ";
					}
					violencias += violencia.getNome();
				}
			}
		}
		
		return violencias;
    }
}

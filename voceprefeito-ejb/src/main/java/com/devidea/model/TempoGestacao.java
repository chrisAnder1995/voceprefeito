package com.devidea.model;

import com.devidea.enumeration.*;
import com.devidea.model.jsonAdapter.*;

import java.io.Serializable;
import java.util.List;
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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="tempo_gestacao")
@SequenceGenerator(name = "seq_tempo_gestacao", sequenceName = "seq_tempo_gestacao", allocationSize = 1)
public class TempoGestacao extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_tempo_gestacao")
    @Expose
    private Integer id;

    @Column(name="nome", nullable=false)
    @Expose
    private String nome;

// JAVA_INSERT

// METHODS_INSERT
}

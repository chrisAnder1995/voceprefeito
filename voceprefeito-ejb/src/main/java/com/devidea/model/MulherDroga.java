package com.devidea.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="mulherdroga")
@SequenceGenerator(name = "seq_mulherdroga", sequenceName = "seq_mulherdroga", allocationSize = 1)
public class MulherDroga extends GenericEntity implements Serializable {

	private static final long serialVersionUID = 8442135833769385295L;

    @Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_mulherdroga")
    @Expose
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="mulher_id", referencedColumnName="id", nullable=false)
    @Expose
    private Mulher mulher;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="droga_id", referencedColumnName="id", nullable=false)
    @Expose
    private Droga droga;
}

package org.graf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Zutat", uniqueConstraints = {@UniqueConstraint(name="uq_zutat_zutat", columnNames={"zutat"})})
public class Zutat extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="zutat")
	private String zutat;
	
	public Zutat() {
		
	}
	
	public Zutat(String zutat){
		this.zutat = zutat;
	}
	
	public String getZutat() {
		return zutat;
	}
	
	public void setZutat(String zutat) {
		this.zutat = zutat;
	}

	@Override
	public String toString() {
		return "Zutat [zutat=" + zutat + "]";
	}
	
	

}

package org.sample.test.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the SAMPLE database table.
 * 
 */
@Entity
@Table(name="SAMPLE")
@Getter @Setter @EqualsAndHashCode @ToString
public class Sample implements Serializable {

	private static final long serialVersionUID = 5494883031538992132L;

	@Id
	@Column(name="ID")
	private String id;
}
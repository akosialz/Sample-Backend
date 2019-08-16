package org.sample.test.dao.criteria;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode @ToString
public class SampleCriteria implements Serializable {

	private static final long serialVersionUID = -8067122806821133765L;

	private String id;
}

package org.sample.test.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonInclude;

@Getter @Setter @EqualsAndHashCode @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SampleVO implements Serializable {
	
	private static final long serialVersionUID = -1228724646400282215L;

	@ApiModelProperty(value = "Id of the user")
	private String Id;
}


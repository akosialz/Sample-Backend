package org.sample.test.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonInclude;

@Getter @Setter @EqualsAndHashCode @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse implements Serializable {
	
	private static final long serialVersionUID = -228359251612212310L;

	@ApiModelProperty(value = "Http status code")
	private Integer status;
	
	@ApiModelProperty(value = "List of error responses")
	private List<ErrorVO> error;
	
	@ApiModelProperty(value = "Response payload")
	private Object data;
	
	public GenericResponse() {
		data = new HashMap<String, Object>();
	}
}

package org.sample.test.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ErrorVO
 */
@Getter @Setter @EqualsAndHashCode @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorVO implements Serializable {
	
	private static final long serialVersionUID = -5158089345856788786L;

	@ApiModelProperty(value = "Error Code")
	private String code;
	
	@ApiModelProperty(value = "Detailed information of the error")
	private SourceVO source;
	
	@ApiModelProperty(value = "Error message displayed on front-end")
	private String title;
}


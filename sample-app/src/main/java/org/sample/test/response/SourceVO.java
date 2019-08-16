package org.sample.test.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonInclude;

@ApiModel(value = "Details information of the error")
@Getter @Setter @EqualsAndHashCode @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceVO implements Serializable {

	private static final long serialVersionUID = 1842926991682480306L;

	@ApiModelProperty(value = "Error description")
	private String pointer;
	
	@ApiModelProperty(value = "Error message to be displayed on the UI")
	private String title;
}

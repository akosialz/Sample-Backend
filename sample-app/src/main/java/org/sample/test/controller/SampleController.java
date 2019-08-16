package org.sample.test.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.sample.test.response.GenericResponse;
import org.sample.test.response.SampleVO;
import org.sample.test.service.impl.SampleServiceImpl;
import org.sample.test.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("Sample")
@CrossOrigin
@RestController
public class SampleController {

	private final static Logger LOGGER = Logger.getLogger(SampleController.class);
	
	@Autowired 
	private SampleServiceImpl sampleServiceImpl;
	
	@ApiOperation(value = "Get NSPay information", response = SampleVO.class)
	@RequestMapping(value = "/getNspayInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GenericResponse> getSample(
			@RequestHeader(name = "id", required = true, defaultValue = "") 
				@ApiParam(value = "Used to denote the Id") String nricID) {
		GenericResponse nspayRes = null;
		try {
			Object sample = sampleServiceImpl.sample();
			nspayRes = Utility.processData(sample);
		} catch(Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			nspayRes = Utility.processError(
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return new ResponseEntity<GenericResponse>(nspayRes, HttpStatus.OK);
	}
}

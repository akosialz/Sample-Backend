package org.sample.test.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sample.test.response.ErrorVO;
import org.sample.test.response.GenericResponse;
import org.sample.test.response.SourceVO;
import org.springframework.http.HttpStatus;

public abstract class Utility {
	
	protected static Map<String, String> messages;
	
	protected static void setMessages(Map<String, String> messages) {
		// THESE VALUES WILL BE POPULATED DURING STARTUP (MethodInvokingBean)
		Utility.messages = messages;
	}

	public static GenericResponse processData(Object data) {
		// FILL RESPONSE DETAILS
		GenericResponse response = new GenericResponse();
		if (null != data) {
			response.setData(data);
			response.setStatus(HttpStatus.OK.value());
		} else {
			response.setStatus(HttpStatus.NO_CONTENT.value());
		}
		return response;
	}
	
	public static GenericResponse processError(HttpStatus status, Throwable error) {
		status = null != status ? status : HttpStatus.INTERNAL_SERVER_ERROR;
		// FILL ERROR DETAILS
		ErrorVO errorVO = new ErrorVO();
		errorVO.setCode(status.toString());
		errorVO.setSource(Utility.getErrorSource(status, error));
		List<ErrorVO> errorVOList = new ArrayList<ErrorVO>(0);
		errorVOList.add(errorVO);
		// FILL RESPONSE DETAILS
		GenericResponse response = new GenericResponse();
		response.setError(errorVOList);
		response.setStatus(status.value());
		return response;
	}
	
	protected static SourceVO getErrorSource(HttpStatus status, Throwable error) {
		SourceVO sourceVO = new SourceVO();
		sourceVO.setTitle(messages.get(status.value()));
		return sourceVO;
	}
}

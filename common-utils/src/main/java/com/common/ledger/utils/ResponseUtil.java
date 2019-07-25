/**
 * 
 */
package com.common.ledger.utils;

import static com.common.ledger.utils.ResponseUtil.RESPONSEMAP_CODE_KEY;
import static com.common.ledger.utils.ResponseUtil.RESPONSEMAP_STATUS_KEY;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

public class ResponseUtil {
	
	public static final String RESPONSEMAP_STATUS_KEY = "status";
	public static final String RESPONSEMAP_MESSAGE_KEY = "message";
	public static final String RESPONSEMAP_CODE_KEY = "code";
	public static final String RESPONSEMAP_DATA_KEY = "data";
	public static final String RESPONSEMAP_TIMESTAMP_KEY = "timestamp";
	public static final String RESPONSEMAP_PATH_KEY = "path";
	public static final String RESPONSEMAP_ERROR_KEY = "error";
	public static final String RESPONSEMAP_EXCEPTION_KEY = "exception";
	public static final String RESPONSEMAP_ERRORCODE_KEY = "errorCode";
	public static final String RESPONSEMAP_HTTPSTATUS_KEY = "httpStatus";
	
	private ResponseUtil() {
		// Prevent Instantiation
	}
	/**
	 * 
	 * @param code
	 * @param success
	 * @return
	 */
	public static Map<String, String> getResponse(HttpStatus code , boolean success) {
		 Map<String,String> responseMap = new HashMap<>(5);
		 responseMap.put(RESPONSEMAP_TIMESTAMP_KEY, Calendar.getInstance().getTime().toString());
		 responseMap.put(RESPONSEMAP_CODE_KEY, code.toString());
		 if(success) {
			 responseMap.put(RESPONSEMAP_MESSAGE_KEY, "The request completed successfully.");
		 }
		 else {
			 responseMap.put(RESPONSEMAP_MESSAGE_KEY, "The request failed.");
		 }
		 
		 return responseMap;
	}

	/**
	 * 
	 * @param statusCode
	 * @return
	 */
	public static Map<String, String> getResponse(HttpStatus statusCode) {
		
		return (HttpStatus.OK.equals(statusCode)) ? getResponse(statusCode,true): getResponse(statusCode,false);
	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	public static ResponseEntity<Map<String, Object>> handleResponse(LedgerResponse response) {
		Assert.notNull(response, "response is required.");

		Assert.notNull(response.getResponseMap().get(RESPONSEMAP_STATUS_KEY),
				String.format("%s is required in Response", RESPONSEMAP_STATUS_KEY));

		Assert.notNull(response.getResponseMap().get(RESPONSEMAP_CODE_KEY),
				String.format("%s is required in Response", RESPONSEMAP_CODE_KEY));

		return new ResponseEntity<>(response.getResponseMap(), response.getHttpStatus());
	}
	
	
	
}

/**
 * 
 */
package com.common.ledger.utils;

import static com.common.ledger.utils.ResponseUtil.RESPONSEMAP_CODE_KEY;
import static com.common.ledger.utils.ResponseUtil.RESPONSEMAP_DATA_KEY;
import static com.common.ledger.utils.ResponseUtil.RESPONSEMAP_ERRORCODE_KEY;
import static com.common.ledger.utils.ResponseUtil.RESPONSEMAP_MESSAGE_KEY;
import static com.common.ledger.utils.ResponseUtil.RESPONSEMAP_PATH_KEY;
import static com.common.ledger.utils.ResponseUtil.RESPONSEMAP_STATUS_KEY;
import static com.common.ledger.utils.ResponseUtil.RESPONSEMAP_TIMESTAMP_KEY;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class LedgerResponse implements Serializable {

	private static final long serialVersionUID = 8218105114689710526L;

	private transient Map<String, Object> responseMap;
	HttpStatus httpStatus;
	
	/**
	 * 
	 */
	public LedgerResponse() {
		responseMap = new HashMap<>(5);
	}
	/**
	 * 
	 * @param code
	 * @param message
	 * @param result
	 */
	public LedgerResponse(HttpStatus code , String message , Object result) {
		responseMap = new HashMap<>(5);
		setHttpStatus(code);
		setMessage(message);
		if(result != null) {
			setData(result);
		}
	}
	/**
	 * 
	 * @param code
	 * @param message
	 */
	public LedgerResponse(HttpStatus code , String message) {
		this(code,message,null);
	}
	/**
	 * 
	 * @return
	 */
	public Map<String, Object> getResponseMap(){
		return responseMap;
	}
	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		responseMap.put(RESPONSEMAP_CODE_KEY,code);
	}
	/**
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		responseMap.put(RESPONSEMAP_MESSAGE_KEY,message);
	}
	/**
	 * 
	 * @return
	 */
	public String getMessage() {
		if(responseMap.get(RESPONSEMAP_MESSAGE_KEY) != null) {
			return responseMap.get(RESPONSEMAP_MESSAGE_KEY).toString();
		}			
		return null;
	}
	/**
	 * 
	 * @param authData
	 */
	public void setData(Object authData) {
		responseMap.put(RESPONSEMAP_DATA_KEY,authData);
	}
	/**
	 * 
	 * @param time
	 */
	public void setTimestamp(String time) {
		responseMap.put(RESPONSEMAP_TIMESTAMP_KEY,time);
		
	}
	/**
	 * 
	 * @param requestURI
	 */
	public void setPath(String requestURI) {
		responseMap.put(RESPONSEMAP_PATH_KEY,requestURI);
	}
	/**
	 * 
	 * @param errorCode
	 */
	public void setErrorCode(String errorCode) {
		responseMap.put(RESPONSEMAP_ERRORCODE_KEY,errorCode);
	}

	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		responseMap.put(RESPONSEMAP_STATUS_KEY,httpStatus.getReasonPhrase());
		responseMap.put(RESPONSEMAP_CODE_KEY,httpStatus.value());
	}

	

}

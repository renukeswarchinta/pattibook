package com.ledger.owner.workerservice.web;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.common.ledger.utils.LedgerResponse;
import com.common.ledger.utils.ResponseUtil;
import com.ledger.owner.workerservice.service.WorkerService;

@RestController
public class WorkerController {

	WorkerService workerService;
	
	/**
	 * 
	 * @param wName param value expecting with worker name
	 * @param wId param value expecting with worker id
	 * @return
	 */
	@ResponseBody ResponseEntity<Map<String, Object>> getWorkerDetails(@RequestParam(name = "workerName", required=false) String wName,
			@RequestParam(name = "workerId", required=false) String wId) {
		
		LedgerResponse ledgerResponse = null;
		
		if(StringUtils.isBlank(wName) && StringUtils.isBlank(wId)) {
			ledgerResponse =  new LedgerResponse(HttpStatus.BAD_REQUEST, "Param value could not be blank!");
		} else {
			if(StringUtils.isNotBlank(wName)) {
				ledgerResponse = workerService.getWorkerDetailsByName(wName);
			} else if(StringUtils.isNotBlank(wId)) {
				ledgerResponse = workerService.getWorkerDetailsById(wId);
			}
		}
		
		return ResponseUtil.handleResponse(ledgerResponse);
		
	}

}

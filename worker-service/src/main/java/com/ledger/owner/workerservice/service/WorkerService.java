package com.ledger.owner.workerservice.service;

import com.common.ledger.utils.LedgerResponse;

public interface WorkerService {

	LedgerResponse getWorkerDetailsByName(String name);
	
	LedgerResponse getWorkerDetailsById(String id);
}

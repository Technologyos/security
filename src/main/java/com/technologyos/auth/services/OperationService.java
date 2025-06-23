package com.technologyos.auth.services;

import com.technologyos.auth.dtos.operation.OperationRequest;
import com.technologyos.auth.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OperationService {
   Page<Operation> findAll(Pageable pageable);

   Operation findOperationById(Long operationId);

   Operation createOperation(OperationRequest operationRequest);

   Operation updateOperation(OperationRequest operationRequest, Long operationId);

   Operation disableById(Long operationId);

   Operation enableById(Long operationId);

   List<Operation> findByStatusId(Long statusId);
}

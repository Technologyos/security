package com.technologyos.auth.services.impl;

import com.technologyos.auth.dtos.operation.OperationRequest;
import com.technologyos.auth.dtos.status.StatusEnum;
import com.technologyos.auth.entities.Operation;
import com.technologyos.auth.exceptions.ObjectNotFoundException;
import com.technologyos.auth.repositories.OperationRepository;
import com.technologyos.auth.services.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {
   private final OperationRepository operationRepository;

   @Override
   public Page<Operation> findAll(Pageable pageable) {
      return operationRepository.findAll(pageable);
   }

   @Override
   public Operation findOperationById(Long operationId) {
      return operationRepository.findById(operationId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "operation not found by operationId " + operationId, HttpStatus.NOT_FOUND));
   }

   @Override
   public Operation createOperation(OperationRequest operationRequest) {
      Operation operation = new Operation();
      operation.setName(operationRequest.getName());
      operation.setPath(operationRequest.getPath());
      operation.setHttpMethod(operationRequest.getHttpMethod());
      operation.setPermitAll(operationRequest.getPermitAll());
      operation.setModuleId(operationRequest.getModuleId());
      operation.setStatusId(StatusEnum.ENABLED.getCode());
      return operationRepository.save(operation);
   }

   @Override
   public Operation updateOperation(OperationRequest operationRequest, Long operationId) {
      Operation currentOperation = this.findOperationById(operationId);
      currentOperation.setName(operationRequest.getName());
      currentOperation.setPath(operationRequest.getPath());
      currentOperation.setHttpMethod(operationRequest.getHttpMethod());
      currentOperation.setPermitAll(operationRequest.getPermitAll());
      currentOperation.setModuleId(operationRequest.getModuleId());
      currentOperation.setStatusId(operationRequest.getStatusId());
      return operationRepository.save(currentOperation);
   }

   @Override
   public Operation disableById(Long operationId) {
      Operation currentOperation = this.findOperationById(operationId);
      currentOperation.setStatusId(StatusEnum.DISABLED.getCode());
      return operationRepository.save(currentOperation);
   }

   @Override
   public Operation enableById(Long operationId) {
      Operation currentOperation = this.findOperationById(operationId);
      currentOperation.setStatusId(StatusEnum.ENABLED.getCode());
      return operationRepository.save(currentOperation);
   }

   @Override
   public List<Operation> findByStatusId(Long statusId) {
      return operationRepository.findByStatusId(statusId);
   }
}

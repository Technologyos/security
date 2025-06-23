package com.technologyos.auth.controllers;

import com.technologyos.auth.dtos.operation.OperationRequest;
import com.technologyos.auth.dtos.status.StatusEnum;
import com.technologyos.auth.services.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "operation resources", description = "This APi serve all functionality for management operation")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/operations")
public class OperationController {
   private final OperationService operationService;

   @Operation(summary = "get operations")
   @GetMapping()
   public ResponseEntity<?> operations(Pageable pageable){
      log.info("Get: operations");
      return ResponseEntity.ok(this.operationService.findAll(pageable));
   }

   @Operation(summary = "get a operation given a operation id")
   @GetMapping("/{operationId}")
   public ResponseEntity<?> operationById(@PathVariable(name = "operationId") Long operationId){
      log.info("Get: operation {}", operationId);
      return ResponseEntity.ok(this.operationService.findOperationById(operationId));
   }

   @Operation(summary = "create a operation")
   @PostMapping()
   public ResponseEntity<?> createOperation(@RequestBody OperationRequest operationRequest,
                                            HttpServletRequest request){
      log.info("create: operation {}", operationRequest.getName());
      com.technologyos.auth.entities.Operation newOperation = this.operationService.createOperation(operationRequest);

      String baseUrl = request.getRequestURI();
      URI newLocation = URI.create(baseUrl + "/"+ newOperation.getOperationId());

      return ResponseEntity.created(newLocation).body(newOperation);
   }

   @Operation(summary = "update a operation by operation id")
   @PutMapping("/{operationId}")
   public ResponseEntity<?> updateOperation(@PathVariable(name = "operationId") Long operationId,
                                            @RequestBody OperationRequest operationRequest){
      log.info("updating: operation {}", operationId);
      return ResponseEntity.ok(this.operationService.updateOperation(operationRequest, operationId));
   }

   @Operation(summary = "enabled operation")
   @PutMapping("/{operationId}/enabled")
   public ResponseEntity<?> enableOperation(@PathVariable(name = "operationId") Long operationId){
      log.info("enabled operation {}", operationId);
      return ResponseEntity.ok(operationService.enableById(operationId));
   }

   @Operation(summary = "disabled a operation")
   @PutMapping("/{operationId}/disabled")
   public ResponseEntity<?> disableOperation(@PathVariable(name = "operationId") Long operationId){
      log.info("disable operation {}", operationId);
      return ResponseEntity.ok(operationService.disableById(operationId));
   }

   @Operation(summary = "get operations by status")
   @GetMapping("/filter")
   public ResponseEntity<?> findByStatusId(@RequestParam(name = "status") StatusEnum status){
      return ResponseEntity.ok(this.operationService.findByStatusId(status.getCode()));
   }
}

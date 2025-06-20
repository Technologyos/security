package com.technologyos.auth.controllers;

import com.technologyos.auth.dtos.module.ModuleRequest;
import com.technologyos.auth.dtos.status.StatusEnum;
import com.technologyos.auth.entities.Module;
import com.technologyos.auth.services.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "module resources", description = "This APi serve all functionality for management modules")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/modules")
public class ModuleController {
   private final ModuleService moduleService;

   @Operation(summary = "get modules")
   @GetMapping()
   public ResponseEntity<?> modules(Pageable pageable){
      log.info("Get: module");
      return ResponseEntity.ok(this.moduleService.findAll(pageable));
   }

   @Operation(summary = "get a module given a module id")
   @GetMapping("/{moduleId}")
   public ResponseEntity<?> moduleById(@PathVariable(name = "moduleId") Long moduleId){
      log.info("Get: module {}", moduleId);
      return ResponseEntity.ok(this.moduleService.findModuleById(moduleId));
   }

   @Operation(summary = "create a module")
   @PostMapping()
   public ResponseEntity<?> createModule(@RequestBody ModuleRequest moduleRequest, HttpServletRequest request){
      log.info("create: module {}", moduleRequest.getName());
      Module newModule = this.moduleService.createModule(moduleRequest);

      String baseUrl = request.getRequestURI();
      URI newLocation = URI.create(baseUrl + "/"+ newModule.getModuleId());

      return ResponseEntity.created(newLocation).body(newModule);
   }

   @Operation(summary = "update a module by module id")
   @PutMapping("/{moduleId}")
   public ResponseEntity<?> updateModule(@PathVariable(name = "moduleId") Long moduleId,
                                         @RequestBody ModuleRequest moduleRequest){
      log.info("updating: module {}", moduleId);
      return ResponseEntity.ok(this.moduleService.updateModuleById(moduleId, moduleRequest));
   }

   @Operation(summary = "enabled module")
   @PutMapping("/{moduleId}/enabled")
   public ResponseEntity<?> enableModule(@PathVariable(name = "moduleId") Long moduleId){
      log.info("enabled module {}", moduleId);
      return ResponseEntity.ok(moduleService.enableById(moduleId));
   }

   @Operation(summary = "disabled module")
   @PutMapping("/{moduleId}/disabled")
   public ResponseEntity<?> disableModule(@PathVariable(name = "moduleId") Long moduleId){
      log.info("disable module {}", moduleId);
      return ResponseEntity.ok(moduleService.disableById(moduleId));
   }

   @Operation(summary = "get modules by status")
   @GetMapping("/filter")
   public ResponseEntity<?> findByStatusId(@RequestParam(name = "status") StatusEnum status){
      return ResponseEntity.ok(this.moduleService.findByStatusId(status.getCode()));
   }
}

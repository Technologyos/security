package com.technologyos.auth.services.impl;

import com.technologyos.auth.dtos.module.ModuleRequest;
import com.technologyos.auth.dtos.status.StatusEnum;
import com.technologyos.auth.entities.Module;
import com.technologyos.auth.exceptions.ObjectNotFoundException;
import com.technologyos.auth.repositories.ModuleRepository;
import com.technologyos.auth.services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {
   private final ModuleRepository moduleRepository;

   @Override
   public Page<Module> findAll(Pageable pageable) {
      return moduleRepository.findAll(pageable);
   }

   @Override
   public Module findModuleById(Long moduleId) {
      return moduleRepository.findById(moduleId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "module not found by moduleId " + moduleId, HttpStatus.NOT_FOUND));
   }

   @Override
   public Module createModule(ModuleRequest saveClinic) {
      Module module = new Module();
      module.setName(saveClinic.getName());
      module.setBasePath(saveClinic.getBasePath());
      module.setStatusId(StatusEnum.ENABLED.getCode());
      return moduleRepository.save(module);
   }

   @Override
   public Module updateModuleById(Long moduleId, ModuleRequest saveModule) {
      Module module = this.findModuleById(moduleId);
      module.setName(saveModule.getName());
      module.setBasePath(saveModule.getBasePath());
      module.setStatusId(StatusEnum.ENABLED.getCode());
      return moduleRepository.save(module);
   }

   @Override
   public Module disableById(Long moduleId) {
      Module currentModule = this.findModuleById(moduleId);
      currentModule.setStatusId(StatusEnum.DISABLED.getCode());
      return moduleRepository.save(currentModule);
   }

   @Override
   public Module enableById(Long moduleId) {
      Module currentModule = this.findModuleById(moduleId);
      currentModule.setStatusId(StatusEnum.ENABLED.getCode());
      return moduleRepository.save(currentModule);
   }

   @Override
   public List<Module> findByStatusId(Long statusId) {
      return moduleRepository.findByStatusId(statusId);
   }
}

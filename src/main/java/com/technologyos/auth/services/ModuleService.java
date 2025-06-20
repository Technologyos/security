package com.technologyos.auth.services;

import com.technologyos.auth.dtos.module.ModuleRequest;
import com.technologyos.auth.entities.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModuleService {
   Page<Module> findAll(Pageable pageable);

   Module findModuleById(Long moduleId);

   Module createModule(ModuleRequest saveModule);

   Module updateModuleById(Long moduleId, ModuleRequest saveModule);

   Module disableById(Long moduleId);

   Module enableById(Long moduleId);

   List<Module> findByStatusId(Long statusId);
}

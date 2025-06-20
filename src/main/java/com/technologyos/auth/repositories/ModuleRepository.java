package com.technologyos.auth.repositories;

import com.technologyos.auth.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {
   List<Module> findByStatusId(Long statusId);
}

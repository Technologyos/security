package com.technologyos.auth.services.impl;

import com.technologyos.auth.dtos.status.StatusRequest;
import com.technologyos.auth.entities.Status;
import com.technologyos.auth.exceptions.ObjectNotFoundException;
import com.technologyos.auth.repositories.StatusRepository;
import com.technologyos.auth.services.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {
   private final StatusRepository statusRepository;

   @Override
   public Page<Status> findAll(Pageable pageable) {
      return statusRepository.findAll(pageable);
   }

   @Override
   public Status findStatusById(Long statusId) {
      return statusRepository.findById(statusId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
         "status not found by statusId " + statusId, HttpStatus.NOT_FOUND));
   }

   @Override
   public Status createStatus(StatusRequest statusRequest) {
      Status status = new Status();
      status.setName(statusRequest.getName());
      return this.statusRepository.save(status);
   }

   @Override
   public Status updateStatus(StatusRequest statusRequest, Long statusId) {
      Status currentStatus = this.findStatusById(statusId);
      currentStatus.setName(statusRequest.getName());
      return this.statusRepository.save(currentStatus);
   }
}

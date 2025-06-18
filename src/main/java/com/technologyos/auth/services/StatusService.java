package com.technologyos.auth.services;

import com.technologyos.auth.dtos.status.StatusRequest;
import com.technologyos.auth.entities.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StatusService {
   Page<Status> findAll(Pageable pageable);

   Status findStatusById(Long statusId);

   Status createStatus(StatusRequest statusRequest);

   Status updateStatus(StatusRequest statusRequest, Long statusId);
}

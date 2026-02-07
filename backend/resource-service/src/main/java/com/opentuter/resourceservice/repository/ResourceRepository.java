package com.opentuter.resourceservice.repository;


import com.opentuter.resourceservice.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResourceRepository extends JpaRepository<Resource, UUID> {

    List<Resource> findByModule_Id(UUID moduleId);
}



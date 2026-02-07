package com.opentuter.resourceservice.repository;



import com.opentuter.resourceservice.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {
}


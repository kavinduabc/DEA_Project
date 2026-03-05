package com.opentuter.assignmentservice.repository;

import com.opentuter.assignmentservice.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for {@link Assignment} entities.
 *
 * <p>
 * Provides standard CRUD operations inherited from {@link JpaRepository},
 * as well as custom query methods for the Assignment domain.
 * </p>
 */
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {

    /**
     * Finds all assignments associated with a specific classroom.
     *
     * @param classroomId the UUID of the classroom to filter by
     * @return a list of assignments in the specified classroom; empty list if none
     *         found
     */
    List<Assignment> findByClassroomId(UUID classroomId);
}

package com.opentuter.assignmentservice.repository;

import com.opentuter.assignmentservice.model.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for {@link AssignmentSubmission} entities.
 *
 * <p>
 * Provides standard CRUD operations inherited from {@link JpaRepository},
 * as well as custom query methods for the Submission domain.
 * </p>
 */
@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, UUID> {

    /**
     * Finds all submissions for a specific assignment.
     *
     * @param assignmentId the UUID of the assignment to filter by
     * @return a list of submissions for the specified assignment; empty list if
     *         none found
     */
    List<AssignmentSubmission> findByAssignmentId(UUID assignmentId);

    /**
     * Finds all submissions made by a specific student.
     *
     * @param studentId the UUID of the student to filter by
     * @return a list of submissions made by the specified student; empty list if
     *         none found
     */
    List<AssignmentSubmission> findByStudentId(UUID studentId);
}

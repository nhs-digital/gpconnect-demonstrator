package uk.gov.hscic.patient.problems.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.gov.hscic.patient.problems.model.ProblemEntity;

public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {
	
    List<ProblemEntity> findBynhsNumber(String nhsNumber);
    List<ProblemEntity> findBynhsNumberAndStartDateAfter(String nhsNumber, Date endDate);
    List<ProblemEntity> findBynhsNumberAndStartDateBefore(String nhsNumber, Date startDate);
    List<ProblemEntity> findBynhsNumberAndStartDateAfterAndStartDateBefore(String nhsNumber, Date startDate, Date endDate);

}

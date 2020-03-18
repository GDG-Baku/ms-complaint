package az.gdg.mscomplaint.repository;

import az.gdg.mscomplaint.model.entity.ComplaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<ComplaintEntity, Integer> {

}


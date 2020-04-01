package az.gdg.mscomplaint.repository;

import az.gdg.mscomplaint.repository.entity.ComplaintStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintStatusRepository extends CrudRepository<ComplaintStatusEntity, Integer> {

}


package az.gdg.mscomplaint.repository;

import az.gdg.mscomplaint.model.entity.ComplaintStatus;
import az.gdg.mscomplaint.model.entity.ComplaintType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintStatusRepository extends CrudRepository<ComplaintStatus, Integer>{

}


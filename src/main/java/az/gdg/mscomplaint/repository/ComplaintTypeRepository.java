package az.gdg.mscomplaint.repository;

import az.gdg.mscomplaint.model.entity.Complaint;
import az.gdg.mscomplaint.model.entity.ComplaintType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintTypeRepository extends CrudRepository<ComplaintType, Integer>{

}


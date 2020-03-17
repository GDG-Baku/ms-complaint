package az.gdg.mscomplaint.repository;

import az.gdg.mscomplaint.model.entity.Complaint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends CrudRepository<Complaint, Integer>{

}


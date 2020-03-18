package az.gdg.mscomplaint.repository;

import az.gdg.mscomplaint.model.entity.ComplaintTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintTypeRepository extends CrudRepository<ComplaintTypeEntity, Integer>{

}


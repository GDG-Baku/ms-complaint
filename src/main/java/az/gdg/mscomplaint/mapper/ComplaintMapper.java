package az.gdg.mscomplaint.mapper;

import az.gdg.mscomplaint.model.dto.ComplaintDTO;
import az.gdg.mscomplaint.repository.entity.ComplaintEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComplaintMapper {

    ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "typeId", source = "typeId.id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "surname", source = "surname"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "phone", source = "phone"),
            @Mapping(target = "message", source = "message"),
            @Mapping(target = "statusId", source = "statusId.id"),
    })
    ComplaintDTO entityToDTO(ComplaintEntity complaintEntity);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "surname", source = "surname"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "phone", source = "phone"),
            @Mapping(target = "message", source = "message"),
            @Mapping(target = "statusId", ignore = true),
            @Mapping(target = "typeId", ignore = true),
    })
    ComplaintEntity dtoToEntity(ComplaintDTO complaintDTO);

    List<ComplaintDTO> entityListToDtoList(List<ComplaintEntity> complaintEntity);
}

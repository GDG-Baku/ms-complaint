package az.gdg.mscomplaint.service.impl;

import az.gdg.mscomplaint.mapper.ComplaintMapper;
import az.gdg.mscomplaint.model.ComplaintRequest;
import az.gdg.mscomplaint.model.dto.ComplaintDTO;
import az.gdg.mscomplaint.model.entity.ComplaintEntity;
import az.gdg.mscomplaint.repository.ComplaintRepository;
import az.gdg.mscomplaint.repository.ComplaintStatusRepository;
import az.gdg.mscomplaint.repository.ComplaintTypeRepository;
import az.gdg.mscomplaint.service.ComplaintService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintTypeRepository complaintTypeRepository;
    private final ComplaintStatusRepository complaintStatusRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                ComplaintTypeRepository complaintTypeRepository,
                                ComplaintStatusRepository complaintStatusRepository){
        this.complaintRepository = complaintRepository;
        this.complaintTypeRepository = complaintTypeRepository;
        this.complaintStatusRepository = complaintStatusRepository;
    }

    @Override
    public List<ComplaintDTO> getAllComplaints() {
        List<ComplaintEntity> complaints = complaintRepository.findAll();
        return ComplaintMapper.INSTANCE.entityListToDtoList(complaints);
    }

    @Override
    public void createComplaint(ComplaintRequest complaintRequest) {
        ComplaintEntity complaintEntity = new ComplaintEntity();

        complaintEntity.setTypeId(complaintTypeRepository.findById(
                        complaintRequest.getTypeId()).get());
        complaintEntity.setName(complaintRequest.getName());
        complaintEntity.setSurname(complaintRequest.getSurname());
        complaintEntity.setEmail(complaintRequest.getEmail());
        complaintEntity.setPhone(complaintRequest.getPhone());
        complaintEntity.setMessage(complaintRequest.getMessage());
        complaintEntity.setStatusId(complaintStatusRepository.findById(1).get());
        complaintRepository.save(complaintEntity);
    }

    @Override
    public void updateComplaint(ComplaintDTO complaintDTO) {
        ComplaintEntity complaintEntity = ComplaintMapper.INSTANCE.dtoToEntity(complaintDTO);
        complaintEntity.setTypeId(complaintTypeRepository.findById(
                complaintDTO.getTypeId()).get());
        complaintEntity.setStatusId(complaintStatusRepository.findById(
                complaintDTO.getTypeId()).get());
        complaintRepository.save(complaintEntity);
    }

    @Override
    public void deleteComplaint(int id) {
        complaintRepository.deleteById(id);
    }
}

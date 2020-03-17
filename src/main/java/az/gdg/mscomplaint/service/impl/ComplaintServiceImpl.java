package az.gdg.mscomplaint.service.impl;

import az.gdg.mscomplaint.mapper.ComplaintMapper;
import az.gdg.mscomplaint.model.ComplaintRequest;
import az.gdg.mscomplaint.model.dto.ComplaintDTO;
import az.gdg.mscomplaint.model.entity.Complaint;
import az.gdg.mscomplaint.repository.ComplaintRepository;
import az.gdg.mscomplaint.repository.ComplaintStatusRepository;
import az.gdg.mscomplaint.repository.ComplaintTypeRepository;
import az.gdg.mscomplaint.service.ComplaintService;
import org.apache.commons.collections4.IterableUtils;
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
        Iterable<Complaint> complaints = complaintRepository.findAll();
        return ComplaintMapper.INSTANCE.complaintListToDTO(IterableUtils.toList(complaints));
    }

    @Override
    public void createComplaint(ComplaintRequest complaintRequest) {
        Complaint complaint = new Complaint();

        complaint.setTypeId(complaintTypeRepository.findById(
                        complaintRequest.getTypeId()).get());
        complaint.setName(complaintRequest.getName());
        complaint.setSurname(complaintRequest.getSurname());
        complaint.setEmail(complaintRequest.getEmail());
        complaint.setPhone(complaintRequest.getPhone());
        complaint.setMessage(complaintRequest.getMessage());
        complaint.setStatusId(complaintStatusRepository.findById(1).get());
        complaintRepository.save(complaint);
    }

    @Override
    public void updateComplaint(ComplaintDTO complaintDTO) {
        Complaint complaint = ComplaintMapper.INSTANCE.DtoToComplaint(complaintDTO);
        complaint.setTypeId(complaintTypeRepository.findById(
                complaintDTO.getTypeId()).get());
        complaint.setStatusId(complaintStatusRepository.findById(
                complaintDTO.getTypeId()).get());
        complaintRepository.save(complaint);
    }

    @Override
    public void deleteComplaint(int id) {
        complaintRepository.deleteById(id);
    }
}

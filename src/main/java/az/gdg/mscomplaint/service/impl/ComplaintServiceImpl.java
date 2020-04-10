package az.gdg.mscomplaint.service.impl;

import az.gdg.mscomplaint.exception.NotFoundException;
import az.gdg.mscomplaint.mapper.ComplaintMapper;
import az.gdg.mscomplaint.model.ComplaintRequest;
import az.gdg.mscomplaint.model.dto.ComplaintDTO;
import az.gdg.mscomplaint.model.dto.MailDTO;
import az.gdg.mscomplaint.repository.ComplaintRepository;
import az.gdg.mscomplaint.repository.ComplaintStatusRepository;
import az.gdg.mscomplaint.repository.ComplaintTypeRepository;
import az.gdg.mscomplaint.repository.entity.ComplaintEntity;
import az.gdg.mscomplaint.service.ComplaintService;
import az.gdg.mscomplaint.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private static final Logger logger = LoggerFactory.getLogger(ComplaintServiceImpl.class);
    private final ComplaintRepository complaintRepository;
    private final ComplaintTypeRepository complaintTypeRepository;
    private final ComplaintStatusRepository complaintStatusRepository;
    private final MailService mailService;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                ComplaintTypeRepository complaintTypeRepository,
                                ComplaintStatusRepository complaintStatusRepository,
                                MailService mailService) {
        this.complaintRepository = complaintRepository;
        this.complaintTypeRepository = complaintTypeRepository;
        this.complaintStatusRepository = complaintStatusRepository;
        this.mailService = mailService;
    }

    @Override
    public List<ComplaintDTO> getAllComplaints() {
        logger.info("ActionLog.getAllComplaints.start");
        List<ComplaintEntity> complaints = complaintRepository.findAll();
        return ComplaintMapper.INSTANCE.entityListToDtoList(complaints);
    }

    @Override
    public void createComplaint(ComplaintRequest complaintRequest) {
        logger.info("ActionLog.createComplaint.start");
        ComplaintEntity complaintEntity = new ComplaintEntity();

        complaintEntity.setTypeId(complaintTypeRepository.findById(
                complaintRequest.getTypeId()).orElseThrow(() -> new NotFoundException("Type is not found")));
        complaintEntity.setName(complaintRequest.getName());
        complaintEntity.setSurname(complaintRequest.getSurname());
        complaintEntity.setEmail(complaintRequest.getEmail());
        complaintEntity.setPhone(complaintRequest.getPhone());
        complaintEntity.setMessage(complaintRequest.getMessage());
        complaintEntity.setStatusId(complaintStatusRepository.findById(1).orElseThrow(()
                -> new NotFoundException("Status is not found")));
        complaintRepository.save(complaintEntity);
        String mailBody = "Complaint type: " + complaintEntity.getTypeId().getType() + "<br>" +
                "Name: " + complaintEntity.getName() + "<br>" +
                "Surname: " + complaintEntity.getSurname() + "<br>" +
                "E-mail: " + complaintEntity.getEmail() + "<br>" +
                "Phone: " + complaintEntity.getPhone() + "<br>" +
                "Message: " + complaintEntity.getMessage();
        MailDTO mailDTO = MailDTO.builder()
                .mailTo(Collections.singletonList("gdg.rubber.duck@gmail.com"))
                .mailSubject("Complaint mail #" + complaintEntity.getId())
                .mailBody(mailBody).build();
        mailService.sendToQueue(mailDTO);

        MailDTO mailDtoForCustomer = MailDTO.builder()
                .mailTo(Collections.singletonList(complaintEntity.getEmail()))
                .mailSubject("Your Complaint has accepted")
                .mailBody("Your Complaint has been accepted." +
                        "We'll deal with it as soon as possible")
                .build();
        mailService.sendToQueue(mailDtoForCustomer);
        logger.info("ActionLog.createComplaint.success");
    }

    @Override
    public void updateComplaint(ComplaintDTO complaintDTO) {
        logger.info("ActionLog.updateComplaint.start");
        ComplaintEntity complaintEntity = ComplaintMapper.INSTANCE.dtoToEntity(complaintDTO);
        complaintEntity.setTypeId(complaintTypeRepository.findById(
                complaintDTO.getTypeId()).orElseThrow(() -> new NotFoundException("Type is not found")));
        complaintEntity.setStatusId(complaintStatusRepository.findById(
                complaintDTO.getStatusId()).orElseThrow(() -> new NotFoundException("Status is not found")));
        complaintRepository.save(complaintEntity);
        logger.info("ActionLog.updateComplaint.success");
    }

    @Override
    public void deleteComplaint(int id) {
        logger.info("ActionLog.deleteComplaint.start");
        complaintRepository.deleteById(id);
        logger.info("ActionLog.deleteComplaint.end");
    }
}

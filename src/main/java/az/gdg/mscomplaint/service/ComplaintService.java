package az.gdg.mscomplaint.service;

import az.gdg.mscomplaint.model.ComplaintRequest;
import az.gdg.mscomplaint.model.dto.ComplaintDTO;

import java.util.List;

public interface ComplaintService {

    List<ComplaintDTO> getAllComplaints();

    void createComplaint(ComplaintRequest complaintRequest);

    void updateComplaint(ComplaintDTO complaintDTO);

}

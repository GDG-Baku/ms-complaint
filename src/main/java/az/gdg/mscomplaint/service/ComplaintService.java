package az.gdg.mscomplaint.service;

import az.gdg.mscomplaint.model.ComplaintRequest;
import az.gdg.mscomplaint.model.dto.ComplaintDTO;

import java.util.List;

public interface ComplaintService {

    public List<ComplaintDTO> getAllComplaints();

    public void createComplaint(ComplaintRequest complaintRequest);

    public void updateComplaint(ComplaintDTO complaintDTO);

    public void deleteComplaint(int id);

}

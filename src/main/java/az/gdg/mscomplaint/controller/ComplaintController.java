package az.gdg.mscomplaint.controller;

import az.gdg.mscomplaint.EmailChannel;
import az.gdg.mscomplaint.model.ComplaintRequest;
import az.gdg.mscomplaint.model.dto.ComplaintDTO;
import az.gdg.mscomplaint.model.entity.EmailDetails;
import az.gdg.mscomplaint.service.ComplaintService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/complaint")
@RestController
public class ComplaintController {

    private static final Logger logger = LoggerFactory.getLogger(ComplaintController.class);
    private final ComplaintService complaintService;
    private MessageChannel email;

    public ComplaintController(ComplaintService complaintService, EmailChannel emailChannel) {
        this.complaintService = complaintService;
        email = emailChannel.emailOutput();
    }

    @ApiOperation("get all complaints")
    @GetMapping
    public List<ComplaintDTO> getAllComplaints() {
        logger.debug("Get all complaints");
        return complaintService.getAllComplaints();
    }

    @ApiOperation("create complaint")
    @PostMapping
    public void createComplaint(@RequestBody ComplaintRequest complaintRequest) {
        logger.debug("Create complaint start");
        complaintService.createComplaint(complaintRequest);
        logger.debug("Create complaint end");
    }

    @ApiOperation("update complaint")
    @PutMapping
    public void updateComplaint(@RequestBody ComplaintDTO complaintDTO) {
        logger.debug("Update complaint start");
        complaintService.updateComplaint(complaintDTO);
        logger.debug("Update complaint end");
    }

    @ApiOperation("delete complaint by id")
    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable("id") int id) {
        logger.debug("Delete complaint of complaintId {} start", id);
        complaintService.deleteComplaint(id);
        logger.debug("Delete complaint of complaintId {} start", id);
    }
}

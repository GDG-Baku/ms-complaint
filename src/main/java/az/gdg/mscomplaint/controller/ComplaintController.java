package az.gdg.mscomplaint.controller;

import az.gdg.mscomplaint.model.ComplaintRequest;
import az.gdg.mscomplaint.model.dto.ComplaintDTO;
import az.gdg.mscomplaint.service.ComplaintService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/complaint")
@RestController
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService){
     this.complaintService = complaintService;
    }

    @ApiOperation("get all complaints")
    @GetMapping
    public List<ComplaintDTO> getAllComplaints(){
        return complaintService.getAllComplaints();
    }

    @ApiOperation("create complaint")
    @PostMapping
    public void createComplaint(@RequestBody ComplaintRequest complaintRequest){
        complaintService.createComplaint(complaintRequest);
    }

    @ApiOperation("update complaint")
    @PutMapping
    public void updateComplaint(@RequestBody ComplaintDTO complaintDTO){
        complaintService.updateComplaint(complaintDTO);
    }

    @ApiOperation("delete complaint by id")
    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable("id") int id){
        complaintService.deleteComplaint(id);
    }

}

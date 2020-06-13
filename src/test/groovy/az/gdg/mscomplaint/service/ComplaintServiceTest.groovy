package az.gdg.mscomplaint.service

import az.gdg.mscomplaint.exception.NotFoundException
import az.gdg.mscomplaint.exception.NotValidTokenException
import az.gdg.mscomplaint.exception.UnauthorizedAccessException
import az.gdg.mscomplaint.mapper.ComplaintMapper
import az.gdg.mscomplaint.model.ComplaintRequest
import az.gdg.mscomplaint.model.dto.ComplaintDTO
import az.gdg.mscomplaint.repository.ComplaintRepository
import az.gdg.mscomplaint.repository.ComplaintStatusRepository
import az.gdg.mscomplaint.repository.ComplaintTypeRepository
import az.gdg.mscomplaint.repository.entity.ComplaintEntity
import az.gdg.mscomplaint.repository.entity.ComplaintStatusEntity
import az.gdg.mscomplaint.repository.entity.ComplaintTypeEntity
import az.gdg.mscomplaint.security.UserAuthentication
import az.gdg.mscomplaint.service.impl.ComplaintServiceImpl
import az.gdg.mscomplaint.service.impl.MailServiceImpl
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Specification
import spock.lang.Title

@Title("Testing for Complaint Service")
class ComplaintServiceTest extends Specification {
    
    ComplaintRepository complaintRepository
    ComplaintTypeRepository complaintTypeRepository
    ComplaintStatusRepository complaintStatusRepository
    ComplaintServiceImpl complaintServiceImpl
    MailServiceImpl mailServiceImpl
    
    def setup() {
        complaintRepository = Mock()
        mailServiceImpl = Mock()
        complaintTypeRepository = Mock()
        complaintStatusRepository = Mock()
        complaintServiceImpl = new ComplaintServiceImpl(complaintRepository, complaintTypeRepository, complaintStatusRepository, mailServiceImpl)
    }
    
    def "should use the repository to fetch all complaints"() {
        given:
            def complaint1 = new ComplaintEntity()
            def complaint2 = new ComplaintEntity()
            def complaint3 = new ComplaintEntity()
            def complaints = [complaint1, complaint2, complaint3]
            def complaintDTOs = ComplaintMapper.INSTANCE.entityListToDtoList(complaints)
            def userAuthentication = new UserAuthentication("1", true, "ROLE_ADMIN")
            SecurityContextHolder.getContext().setAuthentication(userAuthentication)
        when:
            def res = complaintServiceImpl.getAllComplaints()
        
        then:
            1 * complaintRepository.findAll() >> complaints
            res == complaintDTOs
    }
    
    def "should throw UnauthorizedAccessException if it's not admin"() {
        given:
            def userAuthentication = new UserAuthentication("1", true, "ROLE_USER")
            SecurityContextHolder.getContext().setAuthentication(userAuthentication)
        when:
            complaintServiceImpl.getAllComplaints()
        
        then:
            thrown(UnauthorizedAccessException)
    }
    
    def "should use the repository to create complaint"() {
        given:
            def type = new ComplaintTypeEntity()
            type.setId(1)
            def status = new ComplaintStatusEntity()
            status.setId(1)
            def complaintRequest = new ComplaintRequest()
            complaintRequest.setTypeId(1)
            def complaintEntity = new ComplaintEntity()
            complaintEntity.setTypeId(type)
            complaintEntity.setStatusId(status)
            def userAuthentication = new UserAuthentication("1", true, "ROLE_ADMIN")
            SecurityContextHolder.getContext().setAuthentication(userAuthentication)
        
        when:
            complaintServiceImpl.createComplaint(complaintRequest)
        
        then:
            1 * complaintTypeRepository.findById(complaintRequest.getTypeId()) >> Optional.of(type)
            1 * complaintStatusRepository.findById(1) >> Optional.of(status)
            1 * complaintRepository.save(complaintEntity)
            2 * mailServiceImpl.sendToQueue(_)
    }
    
    def "should throw NotFoundException if type is not found"() {
        given:
            def type = Optional.empty()
            def complaintRequest = new ComplaintRequest()
            complaintRequest.setTypeId(25)
        
        when:
            complaintServiceImpl.createComplaint(complaintRequest)
        
        then:
            1 * complaintTypeRepository.findById(complaintRequest.getTypeId()) >> type
            thrown(NotFoundException)
    }
    
    def "should use the repository to update complaint"() {
        given:
            def type = new ComplaintTypeEntity()
            type.setId(1)
            def status = new ComplaintStatusEntity()
            status.setId(1)
            def complaintDTO = new ComplaintDTO()
            complaintDTO.setStatusId(3)
            complaintDTO.setTypeId(type.getId())
            def complaintEntity = ComplaintMapper.INSTANCE.dtoToEntity(complaintDTO)
            complaintEntity.setTypeId(type)
            complaintEntity.setStatusId(status)
            def userAuthentication = new UserAuthentication("1", true, "ROLE_ADMIN")
            SecurityContextHolder.getContext().setAuthentication(userAuthentication)
        
        when:
            complaintServiceImpl.updateComplaint(complaintDTO)
        
        then:
            1 * complaintTypeRepository.findById(complaintDTO.getTypeId()) >> Optional.of(type)
            1 * complaintStatusRepository.findById(complaintDTO.getStatusId()) >> Optional.of(status)
            1 * complaintRepository.save(complaintEntity)
    }
    
    def "should get authenticated object"() {
        given:
            def userAuthentication = new UserAuthentication("1", true, "ROLE_ADMIN")
            SecurityContextHolder.getContext().setAuthentication(userAuthentication)
        when:
            complaintServiceImpl.getAuthenticatedObject()
        then:
            notThrown(NotValidTokenException)
        
    }
    
    def "should throw NotValidTokenException if user is not authenticated"() {
        given:
            SecurityContextHolder.getContext().setAuthentication(null)
        when:
            complaintServiceImpl.getAuthenticatedObject()
        then:
            thrown(NotValidTokenException)
        
    }
    
}
package az.gdg.mscomplaint.service

import az.gdg.mscomplaint.model.entity.ComplaintEntity
import az.gdg.mscomplaint.model.entity.ComplaintStatusEntity
import az.gdg.mscomplaint.model.entity.ComplaintTypeEntity
import az.gdg.mscomplaint.repository.ComplaintRepository
import az.gdg.mscomplaint.repository.ComplaintStatusRepository
import az.gdg.mscomplaint.repository.ComplaintTypeRepository
import az.gdg.mscomplaint.service.impl.ComplaintServiceImpl
import spock.lang.Specification
import spock.lang.Title

@Title("Testing for Complaint Service")
class ComplaintServiceTest extends Specification {

    ComplaintRepository complaintRepository
    ComplaintTypeRepository complaintTypeRepository
    ComplaintStatusRepository complaintStatusRepository
    ComplaintServiceImpl complaintServiceImpl

    def setup() {
        complaintRepository = Mock()
        complaintTypeRepository = Mock()
        complaintStatusRepository = Mock()
        complaintServiceImpl = new ComplaintServiceImpl(complaintRepository, complaintTypeRepository, complaintStatusRepository)
    }

    def "should use the repository to fetch all complaints"() {
        given:
        def complaint1 = new ComplaintEntity(id: 1, name: "Ali", surname: "Huseynov",
                email: "alihsoff@gmail.com", phone: "+994503493571", message: "Some message1")
        def complaint2 = new ComplaintEntity(id: 1, name: "Ramal", surname: "Huseyn",
                email: "test1@gmail.com", phone: "+994553493581", message: "Some message2")
        def type = new ComplaintTypeEntity(id: 1, type: "Şikayət")
        type.setComplaintEntityList([complaint1, complaint2])
        def status = new ComplaintStatusEntity(id: 1, status: "Qəbul olundu!")
        status.setComplaintEntityList([complaint1, complaint2])
        complaint1.setTypeId(type)
        complaint2.setStatusId(status)

        when:
        def res = complaintServiceImpl.getAllComplaints()

        then:
        1 * complaintRepository.findAll() >> [complaint1, complaint2]
        res.size() == 2
    }

}

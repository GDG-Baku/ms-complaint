package az.gdg.mscomplaint.service;


import az.gdg.mscomplaint.model.dto.MailDTO;

public interface MailService {

    void sendToQueue(MailDTO mailDTO);
}

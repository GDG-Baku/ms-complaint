package az.gdg.mscomplaint.service.impl;

import az.gdg.mscomplaint.model.dto.MailDTO;
import az.gdg.mscomplaint.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final Source source;

    @Override
    public void sendToQueue(MailDTO mailDTO) {
        source.output().send(MessageBuilder.withPayload(mailDTO).build());
    }
}

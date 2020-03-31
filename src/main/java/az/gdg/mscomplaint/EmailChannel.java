package az.gdg.mscomplaint;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EmailChannel {
    @Output("emailOutput")
    MessageChannel emailOutput();
}

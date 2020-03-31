package az.gdg.mscomplaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(EmailChannel.class)
public class MsComplaintApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsComplaintApplication.class, args);
    }

}

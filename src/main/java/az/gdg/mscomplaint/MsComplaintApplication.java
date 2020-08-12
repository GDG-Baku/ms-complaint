package az.gdg.mscomplaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsComplaintApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsComplaintApplication.class, args);
    }

}

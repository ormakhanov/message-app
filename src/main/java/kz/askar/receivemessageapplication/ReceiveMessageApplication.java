package kz.askar.receivemessageapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableKafka
public class ReceiveMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiveMessageApplication.class, args);
    }

}

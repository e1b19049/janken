package oit.is.z1204.first.janken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class JankenApplication {

  public static void main(String[] args) {
    SpringApplication.run(JankenApplication.class, args);
  }

}

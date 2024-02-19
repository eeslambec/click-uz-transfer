package uz.pdp.clickuztransactionsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "uz.pdp")
public class ClickUzTransactionsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClickUzTransactionsServiceApplication.class, args);
    }
}

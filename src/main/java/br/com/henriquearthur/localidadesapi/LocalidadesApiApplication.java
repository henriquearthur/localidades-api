package br.com.henriquearthur.localidadesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LocalidadesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalidadesApiApplication.class, args);
    }

}

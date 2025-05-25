package co.edu.uniquindio.proyecto_bd2_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "[http://localhost:4200]")
public class ProyectoBd2BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoBd2BackendApplication.class, args);
    }

}

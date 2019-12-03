package Allie;

import org.springframework.boot.SpringApplication;

import java.sql.SQLException;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws SQLException {

        SpringApplication.run(Main.class, args);
    }
}

package com.example.springbootmultipledatasource;

import lombok.Cleanup;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringbootMultipleDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMultipleDatasourceApplication.class, args);
	}

}

package uns.edu.pe.agendaVirtual2023;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class AgendaVirtual2023Application {

	public static void main(String[] args) {
		SpringApplication.run(AgendaVirtual2023Application.class, args);
	}
	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}
}

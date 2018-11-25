package trabalho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("trabalho.*")
public class JuliusApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuliusApplication.class, args);
	}
}

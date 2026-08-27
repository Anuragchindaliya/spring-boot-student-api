package in.anurag.crudSpingBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication()
public class CrudSpingBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpingBootDemoApplication.class, args);
		System.out.println("anurag inside");
	}

}

package demo.botkit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"koredotai.botkit.sdk","demo.botkit.app"})
public class DemoBotkitAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBotkitAppApplication.class, args);
	}
}

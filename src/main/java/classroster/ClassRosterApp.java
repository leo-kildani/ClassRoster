package classroster;

import classroster.controller.ClassRosterCommandLineController;
import classroster.controller.ClassRosterGUIController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ClassRosterApp {
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ClassRosterApp.class);
		builder.headless(false);
		ConfigurableApplicationContext ctx = builder.run();
	}

	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(ClassRosterGUIController controller) {
		return runner -> controller.run();
	}
}

/*
AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
		appContext.scan("classroster");
		appContext.refresh();

		ClassRosterController controller = appContext.getBean("classRosterController", ClassRosterController.class);
		controller.run();
		appContext.close();
 */

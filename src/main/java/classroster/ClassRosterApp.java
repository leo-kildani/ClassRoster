package classroster;

import classroster.controller.ClassRosterController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ClassRosterApp {
	public static void main(String[] args) {
		SpringApplication.run(ClassRosterApp.class, args);
	}

	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(ClassRosterController controller){
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

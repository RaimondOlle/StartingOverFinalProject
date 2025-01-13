package com.raimond.apcminiclient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class apcMiniApplication extends Application {
	private ConfigurableApplicationContext applicationContext;

	@Override
	public void start(Stage stage) {
		applicationContext.publishEvent(new StageReadyEvent(stage));
//		stage.heightProperty().addListener((observable, oldValue, newValue) -> {
//			double heightDiff = newValue.doubleValue() - oldValue.doubleValue();
//
//		})
	}

	@Override
	public void init() {
		applicationContext = new SpringApplicationBuilder(ApcminiClientApplication.class).run();
	}

	@Override
	public void stop() {
		applicationContext.close();
		Platform.exit();
	}

	static class StageReadyEvent extends ApplicationEvent {
		public StageReadyEvent(Stage stage) {
			super(stage);
		}

		public Stage getStage() {
			return (Stage) getSource();
		}
	}
}

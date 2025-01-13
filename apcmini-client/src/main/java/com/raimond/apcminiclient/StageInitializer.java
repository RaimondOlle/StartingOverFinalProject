package com.raimond.apcminiclient;

import com.raimond.apcminiclient.apcMiniApplication.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
	@Value("classpath:/APCmini.fxml")
	private Resource apcResource;
	private String applicationTitle;
	private String springApplicationTitle;
//	private ApplicationContext applicationContext;

	public StageInitializer(@Value("${spring.application.client.title}") String applicationTitle/*, ApplicationContext applicationContext*/) {
		this.applicationTitle = applicationTitle;
//		this.applicationContext = applicationContext;
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(apcResource.getURL());
//			fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
			Parent parent = fxmlLoader.load();

			Stage stage = event.getStage();
			stage.setScene(new Scene(parent, 1280, 720));
			stage.setTitle(applicationTitle);
			stage.show();

			Image image = new Image("/icons/appIcon.png");
			stage.getIcons().add(image);

			APCminiController apcMiniController = fxmlLoader.getController();
			apcMiniController.setUpPage();
		} catch (IOException e) {
			// TODO: deal with exception correctly
			throw new RuntimeException(e);
		}
	}
}

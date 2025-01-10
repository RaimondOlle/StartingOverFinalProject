package com.raimond.apcminiclient;

import com.raimond.apcminiclient.apcMiniApplication.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j2
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
	@Value("classpath:/APCmini.fxml")
	private Resource apcResource;
	private String applicationTitle;
//	private ApplicationContext applicationContext;

	public StageInitializer(@Value("${client.streamdeck.title}") String applicationTitle/*, ApplicationContext applicationContext*/) {
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

			APCminiController apcMiniController = fxmlLoader.getController();
			apcMiniController.setBackgroundColor();
		} catch (IOException e) {
//			throw new RuntimeException(e);
			log.debug(e);
			log.trace(e);
			log.error(e);
			log.info(e);
			log.info("probably a problem with fxml loader/file");
		}
	}
}

package com.raimond.apcminiclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class APCminiController implements Initializable {

	@FXML
	private Pane background;

	@FXML
	private Paint primaryColor,secondaryColor,complimentary1,complimentary2;

	@FXML
	private TreeView<String> actionManager = new TreeView<>();

	@FXML
	private ChoiceBox<String> programs = new ChoiceBox<>();
	private String[] app = {"APCmini Streamdeck", "APCmini Wavelink"};

	void setUpPage() {
		setBackgroundColor();
	}

	void setBackgroundColor() {
		setColorValues();
		background.setBackground(new Background(new BackgroundFill(primaryColor, null, null)));
	}

	private void setColorValues(){
		primaryColor = Color.valueOf("22092C");
		secondaryColor = Color.valueOf("872341");
		complimentary1 = Color.valueOf("BE3144");
		complimentary2 = Color.valueOf("F05941");
	}

	public void goToWebsite() {
		System.out.println("goToWebsite");
	}

	public void goToSettings() {
		System.out.println("goToSettings");
	}

	public void goToGithub() {
		System.out.println("goToGithub");
	}

	public void manageActions() {
		System.out.println("manageActions");
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		programs.getItems().addAll(app);
		programs.getSelectionModel().select(0);
	}

}

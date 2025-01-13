package com.raimond.apcminiclient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeView;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

	@FXML
	private TreeView<String> contextActions = new TreeView<>();

	@FXML
	private Button settings, manageActions, homeWebPage, goToGitHub;

	@FXML
	private Button button56, button57, button58, button59, button60, button61, button62, button63,
			button48, button49, button50, button51, button52, button53, button54, button55,
			button40, button41, button42, button43, button44, button45, button46, button47,
			button32, button33, button34, button35, button36, button37, button38, button39,
			button24, button25, button26, button27, button28, button29, button30, button31,
			button16, button17, button18, button19, button20, button21, button22, button23,
			button8, button9, button10, button11, button12, button13, button14, button15,
			button0, button1, button2, button3, button4, button5, button6, button7;

	void setBackgroundColor() {
		setColorValues();
		background.setBackground(new Background(new BackgroundFill(primaryColor, null, null)));
		contextActions.setBackground(new Background(new BackgroundFill(secondaryColor, null, null)));
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

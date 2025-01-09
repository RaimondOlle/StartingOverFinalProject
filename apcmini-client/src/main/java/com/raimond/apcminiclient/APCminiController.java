package com.raimond.apcminiclient;

import javafx.fxml.FXML;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.springframework.stereotype.Component;

@Component
public class APCminiController {

	@FXML
	private Pane background;

	@FXML
	private Paint primaryColor,secondaryColor,complimentary1,complimentary2;

	void setBackgroundColor() {
		setColorValues();
		background.setBackground(new Background(new BackgroundFill(primaryColor, null, null)));
	}

	private void setColorValues(){
		primaryColor = Color.valueOf("22092C");
		secondaryColor = Color.valueOf("872341");
		complimentary1 = Color.valueOf("BE3144");
		complimentary2 = Color.valueOf("BE3144");
	}
}

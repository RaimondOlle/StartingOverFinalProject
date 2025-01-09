package com.raimond.apcminiclient;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApcminiClientApplication {

	public static void main(String[] args) {
		Application.launch(apcMiniApplication.class,args);
	}

}

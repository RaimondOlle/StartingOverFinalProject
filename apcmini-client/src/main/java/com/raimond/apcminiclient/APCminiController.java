package com.raimond.apcminiclient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
	private TextField searchBox;

	@FXML
	private Paint primaryColor,secondaryColor,complimentary1,complimentary2;

	@FXML
	private ChoiceBox<String> programs = new ChoiceBox<>();
	private String[] app = {"APCmini Streamdeck", "APCmini Wavelink"};

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

	@FXML
	private TreeView<String> actionManager = new TreeView<>();

	@FXML
	private final TreeItem<String> actionManagerRoot = new TreeItem<>("Root ActionManager");

	@FXML
	private final TreeItem<String> soundboard = new TreeItem<>("Soundboard");
	@FXML
	private final TreeItem<String> system = new TreeItem<>("System");
	@FXML
	private final TreeItem<String> multiAction = new TreeItem<>("Multi Action");
	@FXML
	private final TreeItem<String> spotify = new TreeItem<>("Spotify");

	@FXML
	private final TreeItem<String> playAudio = new TreeItem<>("Play Audio");
	@FXML
	private final TreeItem<String> stopAudio = new TreeItem<>("Stop Audio");

	@FXML
	private final TreeItem<String> website = new TreeItem<>("Website");
	@FXML
	private final TreeItem<String> hotkeySwitch = new TreeItem<>("Hotkey Switch");
	@FXML
	private final TreeItem<String> hotkey = new TreeItem<>("Hotkey");
	@FXML
	private final TreeItem<String> open = new TreeItem<>("Open");
	@FXML
	private final TreeItem<String> close = new TreeItem<>("Close");
	@FXML
	private final TreeItem<String> text = new TreeItem<>("Text");
	@FXML
	private final TreeItem<String> multimedia = new TreeItem<>("Multimedia");

	@FXML
	private final TreeItem<String> multiActionAction = new TreeItem<>("Multi Action");
	@FXML
	private final TreeItem<String> multiActionSwitch = new TreeItem<>("Multi Action Switch");
	@FXML
	private final TreeItem<String> randomAction = new TreeItem<>("Random Action");

	@FXML
	private final TreeItem<String> playPause = new TreeItem<>("Play/Pause");
	@FXML
	private final TreeItem<String> previousSong = new TreeItem<>("Previous Song");
	@FXML
	private final TreeItem<String> nextSong = new TreeItem<>("Next Song");
	@FXML
	private final TreeItem<String> changePlaybackDevice = new TreeItem<>("Change Playback Device");
	@FXML
	private final TreeItem<String> likedSongToggle = new TreeItem<>("Liked Song Toggle");
	@FXML
	private final TreeItem<String> playURI = new TreeItem<>("Play URI");
	@FXML
	private final TreeItem<String> playlistStart = new TreeItem<>("Playlist Start");
	@FXML
	private final TreeItem<String> saveSongToPlaylist = new TreeItem<>("Save Song To Playlist");
	@FXML
	private final TreeItem<String> removeSongFromPlaylist = new TreeItem<>("Remove Song From Playlist");
	@FXML
	private final TreeItem<String> repeatMode = new TreeItem<>("Repeat Mode");
	@FXML
	private final TreeItem<String> shuffleMode = new TreeItem<>("Shuffle Mode");
	@FXML
	private final TreeItem<String> songInfo = new TreeItem<>("Song Info");

	void setUpPage() {
		setupTreeView();
		setBackgroundColor();
	}

	void setBackgroundColor() {
		setColorValues();
		background.setBackground(new Background(new BackgroundFill(primaryColor, null, null)));
		contextActions.setBackground(new Background(new BackgroundFill(secondaryColor, null, null)));
//		searchBox.setBackground(new Background(new BackgroundFill(complimentary1, null, null)));
//		programs.setBackground(new Background(new BackgroundFill(complimentary1, null, null)));
	}

	private void setColorValues(){
		primaryColor = Color.valueOf("22092C");
		secondaryColor = Color.valueOf("872341");
		complimentary1 = Color.valueOf("8E1616");
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

	private void setupTreeView() {
		actionManager.setRoot(actionManagerRoot);
		actionManager.setShowRoot(false);
		actionManager.setBackground(new Background(new BackgroundFill(secondaryColor, null, null)));
		actionManager.setOpacity(100);
		actionManager.getStylesheets().add(getClass().getResource("/styles/TreeviewStyle.css").toExternalForm());

		actionManagerRoot.getChildren().addAll(soundboard, system, multiAction, spotify);

		soundboard.getChildren().addAll(playAudio, stopAudio);

		system.getChildren().addAll(website, hotkeySwitch, hotkey, open, close, text, multimedia);

		multiAction.getChildren().addAll(multiActionAction, multiActionSwitch, randomAction);

		spotify.getChildren().addAll(playPause, previousSong, nextSong, changePlaybackDevice, likedSongToggle, playURI,
				playlistStart, saveSongToPlaylist, removeSongFromPlaylist, repeatMode, shuffleMode, songInfo);
	}
}

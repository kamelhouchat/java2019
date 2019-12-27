package GameClass;

import java.util.ArrayList;

import javax.management.remote.SubjectDelegationPermission;

import GameClass.Input;
import GameClass.Settings;

//import javax.swing.text.Element;
//import javax.swing.text.html.ImageView;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{

	Group root;
	private Scene scene;
	private Pane playfieldLayer = new Pane();
	private AnimationTimer gameLoop;
	
	private Image background_image = new Image(getClass().getResource("/images/background.jpg").toExternalForm(), Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT, true , true);
	private ImageView background = new ImageView(background_image);
	private Image castle_image;
	private Image onager_image;
	private Image pikeman_image;
	private Image knight_image;
	
	private Input input;
	private Text scoreMessage = new Text();
	
	private ArrayList<Castle> castles = new ArrayList<Castle>();
	
	
	public void start(Stage primaryStage) {

		root = new Group(background);
		//root.getChildren().addAll(background); 
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT + Settings.STATUS_BAR_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		//root.getChildren().add(playfieldLayer);
		playfieldLayer = new Pane();
		root.getChildren().add(playfieldLayer);
		
		
		

	}

	private void loadGame() {
		castle_image = new Image(getClass().getResource("/images/carre.jpg").toExternalForm(), 50, 50, true, true); 
		onager_image = new Image(getClass().getResource("/images/carre.jpg").toExternalForm(), 50, 50, true, true);
		pikeman_image = new Image(getClass().getResource("/images/carre.jpg").toExternalForm(), 50, 50, true, true);
		knight_image = new Image(getClass().getResource("/images/carre.jpg").toExternalForm(), 50, 50, true, true);

		input = new Input(scene);
		input.addListeners();

		createCastles();
		createStatusBar();
		
		scene.setOnMousePressed(e -> {
			player.setX(e.getX() - (player.getWidth() / 2));
			player.setY(e.getY() - (player.getHeight() / 2));
		});
		
		scene.setOnMouseClicked(EventHandler<>);
	}
	
	public void createStatusBar() {
		HBox statusBar = new HBox();
		scoreMessage.setText("Score : 0          Life : "/* + player.getHealth()*/);
		statusBar.getChildren().addAll(scoreMessage);
		statusBar.getStyleClass().add("statusBar");
		statusBar.relocate(0, Settings.SCENE_HEIGHT);
		statusBar.setPrefSize(Settings.SCENE_WIDTH, Settings.STATUS_BAR_HEIGHT);
		root.getChildren().add(statusBar);
	}
	
	private void createCastles() {
		double x, y;
		for(int i = 0; i < 5; i++ ) {
			switch(i) {
			case (0):
				x = Settings.SCENE_WIDTH * 0.05;
				y = Settings.SCENE_HEIGHT * 0.05;
			case (1):
				x = Settings.SCENE_WIDTH * 0.05;
				y = Settings.SCENE_HEIGHT * 0.95;
			case (2):
				x = Settings.SCENE_WIDTH * 0.95;
				y = Settings.SCENE_HEIGHT * 0.05;
			case (3):
				x = Settings.SCENE_WIDTH * 0.95;
				y = Settings.SCENE_HEIGHT * 0.95;
			case (4):
				x = (Settings.SCENE_WIDTH - castle_image.getWidth()) / 2.0;
				y = (Settings.SCENE_HEIGHT - castle_image.getHeight()) / 2.0;
			default :
				
			
			
			}
			castles.add()
		}
		
		player = new Player(playfieldLayer, playerImage, x, y, Settings.PLAYER_HEALTH, Settings.PLAYER_DAMAGE,
				Settings.PLAYER_SPEED, input);
		
		player.getView().setOnMousePressed(e -> {
			System.out.println("Click on player");
			e.consume();
		});
		
		player.getView().setOnContextMenuRequested(e -> {
			ContextMenu contextMenu = new ContextMenu();
			MenuItem low = new MenuItem("Slow");
			MenuItem medium= new MenuItem("Regular");
			MenuItem high= new MenuItem("Fast");
			low.setOnAction(evt -> player.setFireFrequencyLow());
			medium.setOnAction(evt -> player.setFireFrequencyMedium());
			high.setOnAction(evt -> player.setFireFrequencyHigh());
			contextMenu.getItems().addAll(low, medium, high);
			contextMenu.show(player.getView(), e.getScreenX(), e.getScreenY());
		});
	}
	
	public static void main(String[] args) {
		launch(args);		
	}

}

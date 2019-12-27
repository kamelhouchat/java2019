package GameClass;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//import javax.swing.text.Element;
//import javax.swing.text.html.ImageView;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
	
	Random rnd = new Random();
	
	private Image background_image = new Image(getClass().getResource("/images/background.jpg").toExternalForm(), Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT, true , true);
	private ImageView background = new ImageView(background_image);
	private Image castle_imageS;
	private Image castle_imageO;
	private Image castle_imageN;
	private Image castle_imageE;
	
	private Input input;
	
	private Text infoMessage = new Text();

	private ArrayList<Castle> castles = new ArrayList<Castle>();
	
	public void start(Stage primaryStage) {

		root = new Group();  //
		root.getChildren().addAll(background); 
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT + Settings.STATUS_BAR_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		//root.getChildren().add(playfieldLayer);
		playfieldLayer = new Pane();
		root.getChildren().add(playfieldLayer);
		
		loadGame();
		
		new AnimationTimer() {
			public void handle(long now) {
				
			}
		};

	}

	private void loadGame() {
		castle_imageS = new Image(getClass().getResource("/images/carreS.jpg").toExternalForm(), 50, 50, true, true);
		castle_imageO = new Image(getClass().getResource("/images/carreO.jpg").toExternalForm(), 50, 50, true, true);
		castle_imageN = new Image(getClass().getResource("/images/carreN.jpg").toExternalForm(), 50, 50, true, true);
		castle_imageE = new Image(getClass().getResource("/images/carreE.jpg").toExternalForm(), 50, 50, true, true);

		input = new Input(scene);
		input.addListeners();

		createCastles();
		createStatusBar();
		
		/*scene.setOnMousePressed(e -> {
			player.setX(e.getX() - (player.getWidth() / 2));
			player.setY(e.getY() - (player.getHeight() / 2));
		});
		
		scene.setOnMouseClicked(EventHandler<>);*/
	}
	
	public void createStatusBar() {
		HBox statusBar = new HBox();
		infoMessage.setText("                                       -- Dukes of the Realm --");
		statusBar.getChildren().addAll(infoMessage);
		statusBar.getStyleClass().add("statusBar");
		statusBar.relocate(0, Settings.SCENE_HEIGHT);
		statusBar.setPrefSize(Settings.SCENE_WIDTH, Settings.STATUS_BAR_HEIGHT);
		root.getChildren().add(statusBar);
	}
	
	private void createCastles() {
		boolean not_found = true;
		boolean taken = true ;
		boolean mine = true ;
		int generated_taken ;
		double generated_x ; 
		double generated_y ;
		
		for(int i = 0; i < Settings.TOTAL_PLAYERS; i++) {
			not_found = true ;
			do {
				generated_x = ThreadLocalRandom.current().nextDouble(Settings.SCENE_WIDTH * 0.05,
						Settings.SCENE_WIDTH * 0.95 - castle_imageS.getWidth());
				
				generated_y = ThreadLocalRandom.current().nextDouble(( Settings.SCENE_HEIGHT - Settings.STATUS_BAR_HEIGHT ) * 0.05,
						( Settings.SCENE_HEIGHT - Settings.STATUS_BAR_HEIGHT ) * 0.95 - castle_imageS.getHeight());
				for(int j = 0; j < castles.size(); j++) {
					double current_caslte_x = castles.get(j).getX(); 
					double current_caslte_y = castles.get(j).getY();
					if ( Math.abs(current_caslte_x - generated_x) > 100 || Math.abs(current_caslte_y - generated_y) > 100)
						not_found = false;
				}
				if (i == 0) break;  
			}while(not_found);
			
			Door door = new Door();
			
			switch(door.getDirection()) {
			case('S'):
				castles.add( new Castle(playfieldLayer, castle_imageS, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 1, 1, 1, mine));
				break;
			case('O'):
				castles.add( new Castle(playfieldLayer, castle_imageO, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 1, 1, 1, mine));
				break;
			case('N'):
				castles.add( new Castle(playfieldLayer, castle_imageN, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 1, 1, 1, mine));
				break;
			case('E'):
				castles.add( new Castle(playfieldLayer, castle_imageE, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 1, 1, 1, mine));
				break;
			}
			
			mine = false ;
			generated_taken = rnd.nextInt(2);
			switch (generated_taken) {
				case 0 :
					taken = true ;
					break ;
				case 1 : 
					taken = false ;
					break ;
				default:
					break;
			}
		}
		
		/*for(int i = 0; i < 5; i++ ) {
			switch(i) {
			case (0):
				x = Settings.SCENE_WIDTH * 0.05;
				y = ( Settings.SCENE_HEIGHT - Settings.STATUS_BAR_HEIGHT ) * 0.05;
				break;
			case (1):
				x = Settings.SCENE_WIDTH * 0.05;
				y = ( Settings.SCENE_HEIGHT - Settings.STATUS_BAR_HEIGHT ) * 0.95;
				break;
			case (2):
				x = Settings.SCENE_WIDTH * 0.95 - castle_imageS.getWidth();
				y = ( Settings.SCENE_HEIGHT - Settings.STATUS_BAR_HEIGHT ) * 0.05;
				break;
			case (3):
				x = Settings.SCENE_WIDTH * 0.95 - castle_imageS.getWidth();
				y = ( Settings.SCENE_HEIGHT - Settings.STATUS_BAR_HEIGHT ) * 0.95;
				break;
			case (4):
				x = (Settings.SCENE_WIDTH - castle_imageS.getWidth()) / 2.0;
				y = ( ( Settings.SCENE_HEIGHT - Settings.STATUS_BAR_HEIGHT ) ) / 2.0;
				break;
			default :
				break;
			}
		
			
			Door door = new Door();
			
			switch(door.getDirection()) {
			case('S'):
				castles.add( new Castle(playfieldLayer, castle_imageS, x, y, Integer.toString(i), 0, 1, taken, door, 1, 1, 1, my));
				break;
			case('O'):
				castles.add( new Castle(playfieldLayer, castle_imageO, x, y, Integer.toString(i), 0, 1, taken, door, 1, 1, 1, my));
				break;
			case('N'):
				castles.add( new Castle(playfieldLayer, castle_imageN, x, y, Integer.toString(i), 0, 1, taken, door, 1, 1, 1, my));
				break;
			case('E'):
				castles.add( new Castle(playfieldLayer, castle_imageE, x, y, Integer.toString(i), 0, 1, taken, door, 1, 1, 1, my));
				break;
			}
			
			my = false ;
			generated_taken = rnd.nextInt(2);
			switch (generated_taken) {
				case 0 :
					taken = true ;
					break ;
				case 1 : 
					taken = false ;
					break ;
			}
		}*/
		
		for (Castle castle : castles) {
			castle.getView().setOnMousePressed(e -> {
				showStatusInStatusBar(castle);
				e.consume();
			});	
		}
		
		
		/*player.getView().setOnContextMenuRequested(e -> {
			ContextMenu contextMenu = new ContextMenu();
			MenuItem low = new MenuItem("Slow");
			MenuItem medium= new MenuItem("Regular");
			MenuItem high= new MenuItem("Fast");
			low.setOnAction(evt -> player.setFireFrequencyLow());
			medium.setOnAction(evt -> player.setFireFrequencyMedium());
			high.setOnAction(evt -> player.setFireFrequencyHigh());
			contextMenu.getItems().addAll(low, medium, high);
			contextMenu.show(player.getView(), e.getScreenX(), e.getScreenY());
		});*/
	}
	
	private void showStatusInStatusBar(Castle castle) {
		infoMessage.setText("Duke : "+castle.getDuke()+" | Level : "+castle.getLevel()+" | Treasure : "+castle.getTreasure());
	}
	
	public static void main(String[] args) {
		launch(args);		
	}

}

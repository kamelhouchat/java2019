package GameClass;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//import javax.swing.text.Element;
//import javax.swing.text.html.ImageView;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application{

	Group root;
	private Scene scene;
	private Pane playfieldLayer = new Pane();
	Random rnd = new Random();
	
	private Image background_image = new Image(getClass().getResource("/images/background.jpg").toExternalForm(), Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT, true , true);
	private ImageView background = new ImageView(background_image);
	//https://stackoverflow.com/questions/12630296/resizing-images-to-fit-the-parent-node
	//background.fitWidthProperty().bind(scene.widthProperty());
	private Image castle_imageS;
	private Image castle_imageO;
	private Image castle_imageN;
	private Image castle_imageE;
	private Image my_castle_S;
	private Image my_castle_E;
	private Image my_castle_N;
	private Image my_castle_O;
	private Image neutral_castle_S;
	private Image neutral_castle_E;
	private Image neutral_castle_N;
	private Image neutral_castle_O;
	
	private Input input;
	
	private Text infoMessage = new Text();

	private ArrayList<Castle> castles = new ArrayList<Castle>();
	
	private ArrayList<Castle> pressed_now = new ArrayList<Castle>();
	
	public void start(Stage primaryStage) {
		
		root = new Group();  //
		root.getChildren().addAll(background); 
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT + Settings.STATUS_BAR_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		background.fitWidthProperty().bind(primaryStage.widthProperty());
		background.fitHeightProperty().bind(primaryStage.heightProperty());
		
		//root.getChildren().add(playfieldLayer);
		playfieldLayer = new Pane();
		root.getChildren().add(playfieldLayer);
		
		if (Settings.SCENE_WIDTH > 9)
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
		my_castle_S = new Image(getClass().getResource("/images/my_castleS.jpg").toExternalForm(), 50, 50, true, true);
		my_castle_E = new Image(getClass().getResource("/images/my_castleE.jpg").toExternalForm(), 50, 50, true, true);
		my_castle_N = new Image(getClass().getResource("/images/my_castleN.jpg").toExternalForm(), 50, 50, true, true);
		my_castle_O = new Image(getClass().getResource("/images/my_castleO.jpg").toExternalForm(), 50, 50, true, true);
		neutral_castle_S = new Image(getClass().getResource("/images/neutre_castleS.jpg").toExternalForm(), 50, 50, true, true);
		neutral_castle_E = new Image(getClass().getResource("/images/neutre_castleE.jpg").toExternalForm(), 50, 50, true, true);
		neutral_castle_N = new Image(getClass().getResource("/images/neutre_castleN.jpg").toExternalForm(), 50, 50, true, true);
		neutral_castle_O = new Image(getClass().getResource("/images/neutre_castleO.jpg").toExternalForm(), 50, 50, true, true);

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
		infoMessage.setText("-- Dukes of the Realm --");
		statusBar.setAlignment(Pos.CENTER);
		statusBar.getChildren().addAll(infoMessage);
		statusBar.getStyleClass().add("statusBar");
		statusBar.relocate(0, Settings.SCENE_HEIGHT);
		statusBar.setPrefSize(Settings.SCENE_WIDTH, Settings.STATUS_BAR_HEIGHT);
		root.getChildren().add(statusBar);
	}
	
	private void createCastles() {
		boolean not_found = true;
		boolean taken = true ;
		boolean my = true ;
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
				if (i == 0) {
					not_found = false;
				}
				else {
					for(int j = 0; j < castles.size(); j++) {
						double current_caslte_x = castles.get(j).getX(); 
						double current_caslte_y = castles.get(j).getY();
						if ( Math.sqrt(Math.pow(Math.abs(current_caslte_x - generated_x), 2) + Math.pow((Math.abs(current_caslte_y - generated_y)), 2) ) < Settings.MIN_DISTANCE_2_CASTLES)
							break;
						else
							if(j == castles.size()-1)
								not_found = false;
					}	
				}  
			}while(not_found);
			
			Door door = new Door();
			
			switch(door.getDirection()) {
			case('S'):
				if (my)
					castles.add( new Castle(playfieldLayer, my_castle_S, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));
				else if (!taken)
					castles.add( new Castle(playfieldLayer, neutral_castle_S, generated_x, generated_y, "N", 0, 1, taken, door, 3, 3, 3, my));
				else
					castles.add( new Castle(playfieldLayer, castle_imageS, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));
				break;
			case('O'):
				if (my)
					castles.add( new Castle(playfieldLayer, my_castle_O, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));
				else if (!taken)
					castles.add( new Castle(playfieldLayer, neutral_castle_O, generated_x, generated_y, "N", 0, 1, taken, door, 3, 3, 3, my));
				else
					castles.add( new Castle(playfieldLayer, castle_imageO, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));
				break;
			case('N'):
				if (my)
					castles.add( new Castle(playfieldLayer, my_castle_N, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));					
				else if (!taken)
					castles.add( new Castle(playfieldLayer, neutral_castle_N, generated_x, generated_y, "N", 0, 1, taken, door, 3, 3, 3, my));
				else
					castles.add( new Castle(playfieldLayer, castle_imageN, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));
				break;
			case('E'):
				if (my)
					castles.add( new Castle(playfieldLayer, my_castle_E, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));					
				else if (!taken)
					castles.add( new Castle(playfieldLayer, neutral_castle_E, generated_x, generated_y, "N", 0, 1, taken, door, 3, 3, 3, my));
				else
					castles.add( new Castle(playfieldLayer, castle_imageE, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));
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
			if (i == 0) taken = true ;
			if (i == 1) taken = false ;
		}

		for (Castle castle : castles) {
			castle.getView().setOnMousePressed(e -> {				
				if (pressed_now.size() == 1) {
					if (!castle.isMy()) {
						display(pressed_now.get(0), castle);
						pressed_now.clear();
					}
					else {
						pressed_now.clear();
						pressed_now.add(castle);
					}
				}
				else if (castle.isMy()) {
					pressed_now.clear();
					pressed_now.add(castle);
				}
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
	
	public void display(Castle castle, Castle to_attack) {
		
		Stage popupwindow=new Stage();
		      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Attack !");
		popupwindow.setResizable(false);
		
		Button close = new Button("Close !");
		Button attack = new Button("Attack !");
		
		Label onager_label = new Label("   Onager :  "+castle.getNb_onager_target()+"  ");
		Button more_onager = new Button("+");
		Button less_onager = new Button("-");
		
		Label pikeman_label = new Label("  Pikeman : "+castle.getNb_pikeman_target()+"  ");
		Button more_pikeman = new Button("+");
		Button less_pikeman = new Button("-");
		
		Label knight_label = new Label("   Knight :   "+castle.getNb_knight_target()+"  ");
		Button more_knight = new Button("+");
		Button less_knight = new Button("-");
		     
		HBox onager_line = new HBox();
		HBox pikeman_line = new HBox();
		HBox knight_line = new HBox();
		HBox button_line = new HBox();
		
		onager_line.getChildren().addAll(less_onager,onager_label,more_onager);
		pikeman_line.getChildren().addAll(less_pikeman,pikeman_label,more_pikeman);
		knight_line.getChildren().addAll(less_knight,knight_label,more_knight);
		button_line.getChildren().addAll(close,attack);
		
		onager_line.setAlignment(Pos.CENTER);
		pikeman_line.setAlignment(Pos.CENTER);
		knight_line.setAlignment(Pos.CENTER);
		button_line.setAlignment(Pos.CENTER);
		
		close.setOnAction(e -> {
			castle.setNb_knight_target(0);
			castle.setNb_onager_target(0);
			castle.setNb_pikeman_target(0);
			popupwindow.close();
		});
		less_onager.setOnAction(e -> {
			if (castle.getNb_onager_target() > 0) {
				castle.setNb_onager_target(castle.getNb_onager_target()-1);
				onager_label.setText("   Onager :  "+castle.getNb_onager_target()+"  ");
			}
		});
		more_onager.setOnAction(e -> {
			if (castle.getNb_onager_target()+1 <= castle.getOnagers_list().size()) {
				castle.setNb_onager_target(castle.getNb_onager_target()+1);
				onager_label.setText("   Onager :  "+castle.getNb_onager_target()+"  ");
			}
		});
		less_pikeman.setOnAction(e -> {
			if (castle.getNb_pikeman_target() > 0) {
				castle.setNb_pikeman_target(castle.getNb_pikeman_target()-1);
				pikeman_label.setText("  Pikeman : "+castle.getNb_pikeman_target()+"  ");
			}
		});
		more_pikeman.setOnAction(e -> {
			if (castle.getNb_pikeman_target()+1 <= castle.getPikeman_list().size()) {
				castle.setNb_pikeman_target(castle.getNb_pikeman_target()+1);
				pikeman_label.setText("  Pikeman : "+castle.getNb_pikeman_target()+"  ");
			}
		});
		less_knight.setOnAction(e -> {
			if (castle.getNb_knight_target() > 0) {
				castle.setNb_knight_target(castle.getNb_knight_target()-1);
				knight_label.setText("   Knight :   "+castle.getNb_knight_target()+"  ");
			}
		});
		more_knight.setOnAction(e -> {
			if (castle.getNb_knight_target()+1 <= castle.getKnight_list().size()) {
				castle.setNb_knight_target(castle.getNb_knight_target()+1);
				knight_label.setText("   Knight :   "+castle.getNb_knight_target()+"  ");
			}
		});
		attack.setOnAction(e -> {
			castle.setTarget(to_attack);
			popupwindow.close();
		});
		
		
		VBox layout= new VBox();      
		layout.getChildren().addAll(onager_line, pikeman_line, knight_line, button_line);
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene1= new Scene(layout, 150, 150);
		      
		popupwindow.setScene(scene1);      
		popupwindow.showAndWait();
	}
	
	private void showStatusInStatusBar(Castle castle) {
		infoMessage.setText("Duke : "+castle.getDuke()+" | Level : "+castle.getLevel()+" | Returned : "+castle.getReturned()+" | O : "+castle.getOnagers_list().size()
				+" <-> K : "+castle.getKnight_list().size()+" <-> P : "+castle.getPikeman_list().size()+" | Treasure : "+castle.getTreasure());
	}
	
	public static void main(String[] args) {
		launch(args);		
	}

}

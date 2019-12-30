package GameClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//import javax.swing.text.Element;
//import javax.swing.text.html.ImageView;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
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
	private AnimationTimer gameLoop;
	
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
	private ArrayList<Castle> last_pressed = new ArrayList<Castle>();
	
	public void start(Stage primaryStage) {
		
		root = new Group();  //
		root.getChildren().addAll(background); 
		root.setOnMouseClicked(e -> {

			boolean cast = false;
			for(Castle castle : castles) {
				if (e.getSceneX() >= castle.getX() && e.getSceneX() <= castle.getX()+castle.getWidth() && e.getSceneY() >= castle.getY() && 
						e.getSceneY() <= castle.getY()+castle.getHeight() ) 
					cast = true ;
			}
			if (! cast) {
				pressed_now.clear();
			}
		});
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
		
		if (Settings.SCENE_WIDTH < 100) {   // CALCULE A FAIRE
			Stage error = new Stage();
			error.setResizable(false);
			error.initOwner(primaryStage);
			error.initModality(Modality.WINDOW_MODAL);
			error.setOnCloseRequest(e -> primaryStage.close());
			error.setTitle("Error !");
			Label error_label = new Label("The screen size is not sufficient to contain "+Settings.TOTAL_PLAYERS+" castles");
			Button ok_but_err = new Button("Ok");
			VBox err_vbox = new VBox();
			err_vbox.getChildren().addAll(error_label,ok_but_err);
			err_vbox.setAlignment(Pos.CENTER);
			Scene error_scene = new Scene(err_vbox,300,100);
			error.setScene(error_scene);
			ok_but_err.setOnAction(e -> {
				error.close();
				primaryStage.close();
			});
			error.showAndWait();
		}
		
		loadGame();
		
		gameLoop = new AnimationTimer() {
			private long lastUpdate = 0 ;
			private boolean isPaused = false ;
			
			public void handle(long now) {
				
				check_paused(input, now);
				
				if (!isPaused) {
					if (now - lastUpdate >= Settings.TIME_PER_LAP) {
						
						try {
							processInput(input, now);
						} catch (ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
						
						//Update Returned
						castles.forEach(castle -> castle.update_returned());
						
						//Update Treasure
						castles.forEach(castle -> castle.update_treasure());
						
						//Update Status bar
						if (last_pressed.size() > 0)
							showStatusInStatusBar(last_pressed.get(0));	
						
						//Castle Order
						for (Castle castle : castles) {
							if (!castle.getProduction_queue().isEmpty()) {
								if ( castle.getProduction_queue().get(0).check_end_of_production_and_decrementing() ) {
									if ( castle.getProduction_queue().get(0).is_level()) {
										castle.increment_level();
									}
									else {
										if (castle.getProduction_queue().get(0).getType_soldier() == 'O'){
											castle.getOnagers_list().add(new Onager(playfieldLayer, castle.onager_image, castle.getX()-100, castle.getY()-100));
											//castle.getOnagers_list().get(castle.getOnagers_list().size()-1).removeFromLayer();
										}
										if (castle.getProduction_queue().get(0).getType_soldier() == 'P'){
											castle.getPikeman_list().add(new Pikeman(playfieldLayer, castle.pikeman_image, castle.getX()+100, castle.getY()+100));
											//castle.getPikeman_list().get(castle.getPikeman_list().size()-1).removeFromLayer();
										}
										if (castle.getProduction_queue().get(0).getType_soldier() == 'K'){
											castle.getKnight_list().add(new Knight(playfieldLayer, castle.knight_image, castle.getX()+50, castle.getY()+50));
											//castle.getKnight_list().get(castle.getKnight_list().size()-1).removeFromLayer();
										}
									}
									castle.getProduction_queue().remove(0);
								}
							}
						}
						
						
				        lastUpdate = now ;
				    }
				}
			
			}
			
			private void processInput(Input input, long now) throws IOException, ClassNotFoundException {
				if (input.isExit()) {
					Platform.exit();
					System.exit(0);
				}
				else if (input.isIncreaseLevel()) {
					if (pressed_now.size() > 0) 
						IncreaseLevel(pressed_now.get(0));
				}
				else if (input.isProductOnager()) {
					if (pressed_now.size() > 0)
						ProductOnager(pressed_now.get(0));
				}
				else if (input.isProductPikeman()) {
					if (pressed_now.size() > 0)
						ProductPikeman(pressed_now.get(0));
				}
				else if (input.isProductKight()) {
					if (pressed_now.size() > 0)
						ProductKnight(pressed_now.get(0));
				}
				else if (input.isSave()) {
					//To code......
					FileOutputStream fout = new FileOutputStream("file.txt"); 
					Save oot = new Save(fout); 
			        //Castle c = castles.get(0);
			        Door d = new Door();
			        
			        
			        //illustrating annotateClass(Class<?> cl) method 
			        //oot.annotateClass(Character.class); 
			          
			        //Write the specified object to the ObjectOutputStream 
			        oot.writeObject(d); 
			          
			        //flushing the stream 
			        oot.flush(); 
			          
			        //closing the stream 
			        oot.close(); 
			        
					Platform.exit();
					System.exit(0);
				}
				
				else if (input.isLoad()) {
					//To code......
					ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("file.txt"));

			        //Castle c = (Castle) objectInputStream.readObject();
			        Door d = (Door) objectInputStream.readObject();
			        objectInputStream.close();

			        System.out.println(d.getDirection());
					Platform.exit();
					System.exit(0);
				}
			}
			
			private void check_paused(Input input, long now) {
				if (input.isPause()) {
					if (isPaused) {
						isPaused = false ;
						//HideCenter();
					}
					else {
						isPaused = true ;
						//ShowCenter("Paused", false);
					}
				}
			}
			
			
		};
		
		gameLoop.start();
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
		int generated_treasure;
		int generated_soldier_o;
		int generated_soldier_p;
		int generated_soldier_k;
		int generated_level;
		
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
			generated_treasure = rnd.nextInt(6000 - 1000 + 1) + 1000 ;
			generated_soldier_o = rnd.nextInt(40 - 20 + 1) + 20 ; 
			generated_soldier_p = rnd.nextInt(40 - 20 + 1) + 20 ;
			generated_soldier_k = rnd.nextInt(40 - 20 + 1) + 20 ;
			generated_level = rnd.nextInt(13 - 8 + 1) + 8 ;
			
			switch(door.getDirection()) {
			case('S'):
				if (my)
					castles.add( new Castle(playfieldLayer, my_castle_S, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));
				else if (!taken)
					castles.add( new Castle(playfieldLayer, neutral_castle_S, generated_x, generated_y, "N", generated_treasure, generated_level, taken, door, generated_soldier_o, generated_soldier_p, generated_soldier_k, my));
				else
					castles.add( new Castle(playfieldLayer, castle_imageS, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));
				break;
			case('O'):
				if (my)
					castles.add( new Castle(playfieldLayer, my_castle_O, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));
				else if (!taken)
					castles.add( new Castle(playfieldLayer, neutral_castle_O, generated_x, generated_y, "N", generated_treasure, generated_level, taken, door, generated_soldier_o, generated_soldier_p, generated_soldier_k, my));
				else
					castles.add( new Castle(playfieldLayer, castle_imageO, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));
				break;
			case('N'):
				if (my)
					castles.add( new Castle(playfieldLayer, my_castle_N, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));					
				else if (!taken)
					castles.add( new Castle(playfieldLayer, neutral_castle_N, generated_x, generated_y, "N", generated_treasure, generated_level, taken, door, generated_soldier_o, generated_soldier_p, generated_soldier_k, my));
				else
					castles.add( new Castle(playfieldLayer, castle_imageN, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));
				break;
			case('E'):
				if (my)
					castles.add( new Castle(playfieldLayer, my_castle_E, generated_x, generated_y, Integer.toString(i), 0, 1, taken, door, 3, 3, 3, my));					
				else if (!taken)
					castles.add( new Castle(playfieldLayer, neutral_castle_E, generated_x, generated_y, "N", generated_treasure, generated_level, taken, door, generated_soldier_o, generated_soldier_p, generated_soldier_k, my));
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
			if (i == 0) {
				taken = true ;
				//my = true;
			}
			if (i == 1) taken = false ;
		}

		for (Castle castle : castles) {
			castle.getView().setOnMousePressed(e -> {	
				last_pressed.clear();
				last_pressed.add(castle);
				if (pressed_now.size() == 1) {
					if (!castle.isMy()) {
						display(pressed_now.get(0), castle);
						pressed_now.clear();
					}
					else {
						if (castle.equals(pressed_now.get(0))){
							pressed_now.clear();
							pressed_now.add(castle);
						}
						else {
							display(pressed_now.get(0), castle);
							pressed_now.clear();
						}
					}
				}
				else if (castle.isMy()) {
					pressed_now.clear();
					pressed_now.add(castle);
				}
				showStatusInStatusBar(castle);
				e.consume();
			});
			
			
			castle.getView().setOnContextMenuRequested(e -> {
				pressed_now.clear();
				if (castle.isMy()){
					ContextMenu contextMenu = new ContextMenu();
					MenuItem increase = new MenuItem("Increase the level");
					for (Production_unit prod : castle.getProduction_queue()) {
						if (prod.is_level()) 
							increase.setDisable(true);
					}
					SeparatorMenuItem space = new SeparatorMenuItem();
					//space.setDisable(true);
					MenuItem product_onager = new MenuItem("Product Onager");
					MenuItem product_pikeman = new MenuItem("Product Pikeman");
					MenuItem product_knight = new MenuItem("Product Knight");
					SeparatorMenuItem space_2 = new SeparatorMenuItem();
					//space_2.setDisable(true);
					MenuItem remove_last_item = new MenuItem("Remove the last item from the queue");
					MenuItem cancel_queue = new MenuItem("Cancel queue");
					
					
					increase.setOnAction(evt -> {
						IncreaseLevel(castle);
					});
					product_onager.setOnAction(evt -> ProductOnager(castle));
					product_pikeman.setOnAction(evt -> ProductPikeman(castle));
					product_knight.setOnAction(evt -> ProductKnight(castle));
					remove_last_item.setOnAction(env -> {
						
					});
					cancel_queue.setOnAction(env -> {
						
					});
					
					contextMenu.getItems().addAll(increase, space, product_onager, product_pikeman, product_knight, space_2, remove_last_item, cancel_queue);
					contextMenu.show(castle.getView(), e.getScreenX(), e.getScreenY());
				}
			});
		}
	}
	
	public void IncreaseLevel(Castle castle) {
		if (castle.isMy()) {
			if (castle.getTreasure() < 1000*(castle.getLevel()+1)) {
				ShowOnStatusBar("You do not have enough money");
				last_pressed.clear();
			}
			else {
				castle.setTreasure(castle.getTreasure() - 1000*(castle.getLevel()+1) );
				castle.getProduction_queue().add(new Production_unit(castle.getLevel()+1, 100+50*castle.getLevel()+1));
			}
		}
	}
	
	public void ProductOnager(Castle castle) {
		if (castle.isMy()) {
			if (castle.getTreasure() < Settings.ONAGER_PRODUCT_COST) {
				ShowOnStatusBar("You do not have enough money");
				last_pressed.clear();
			}
			else {
				castle.setTreasure(castle.getTreasure() - Settings.ONAGER_PRODUCT_COST);
				castle.getProduction_queue().add(new Production_unit('O', Settings.ONAGER_PRODUCT_TIME));
			}
		}
	}
	
	public void ProductPikeman(Castle castle) {
		if (castle.isMy()) {
			if (castle.getTreasure() < Settings.PIKEMAN_PRODUCT_COST) {
				ShowOnStatusBar("You do not have enough money");
				last_pressed.clear();
			}
			else {
				castle.setTreasure(castle.getTreasure() - Settings.PIKEMAN_PRODUCT_COST);
				castle.getProduction_queue().add(new Production_unit('P', Settings.PIKEMAN_PRODUCT_TIME));
			}
		}
	}
	
	public void ProductKnight(Castle castle) {
		if (castle.isMy()) {
			if (castle.getTreasure() < Settings.KNIGHT_PRODUCT_COST) {
				ShowOnStatusBar("You do not have enough money");
				last_pressed.clear();
			}
			else {
				castle.setTreasure(castle.getTreasure() - Settings.KNIGHT_PRODUCT_COST);
				castle.getProduction_queue().add(new Production_unit('K', Settings.KNIGHT_PRODUCT_TIME));
			}
		}
	}
	
	public void RemoveLastItemProd(Castle castle) {
		if (castle.isMy() && !castle.getProduction_queue().isEmpty())
			castle.getProduction_queue().remove(castle.getProduction_queue().size()-1);
	}
	
	public void CancelProduction(Castle castle) {
		if (castle.isMy())
			castle.getProduction_queue().clear();
	}
	
	public void display(Castle castle, Castle to_attack) {
		
		Stage popupwindow=new Stage();
		      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Order !");
		popupwindow.setResizable(false);
		
		Button close = new Button("Close !");
		Button okbut = new Button("Order !");
		
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
		button_line.getChildren().addAll(close,okbut);
		
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
		okbut.setOnAction(e -> {
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
		infoMessage.setText("Duke : "+castle.getDuke()+"  |  Level : "+castle.getLevel()+"  |  Returned : "+castle.getReturned()+"F/T  |  O : "+castle.getOnagers_list().size()
				+" <-> K : "+castle.getKnight_list().size()+" <-> P : "+castle.getPikeman_list().size()+"\n"
						+ "                                              Treasure : "+castle.getTreasure());
		
	}
	
	private void ShowCenter(String texte, boolean stop_game_loop) {
		HBox hbox = new HBox();
		hbox.setPrefSize(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		hbox.getStyleClass().add("message");
		Text message = new Text();
		message.getStyleClass().add("message");
		message.setText(texte);
		hbox.getChildren().add(message);
		root.getChildren().add(hbox);
		if (stop_game_loop)
			gameLoop.stop();
	}

	private void HideCenter() {
		root.getChildren().remove(root.getChildren().size()-1);
	}

	private void ShowOnStatusBar(String texte) {
		infoMessage.setText(texte);
	}
	
	public static void main(String[] args) {
		launch(args);		
	}

}

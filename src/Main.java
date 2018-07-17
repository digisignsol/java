import java.io.File;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

	static File fbrd = new File("F:\\Common\\git\\Simple\\src\\images\\chessboard.png");
	static ImageView image = new ImageView(fbrd.toURI().toString());
	static File fk = new File("F:\\Common\\git\\Simple\\src\\images\\Knight.png");
	static ImageView imageK = new ImageView(fk.toURI().toString());
    static File f = new File("F:\\Common\\git\\Simple\\src\\images\\KnightT.png");
    static ImageView  imageT =new ImageView(f.toURI().toString());;
    boolean FirstMove = true; 
    int Counter;
    StackPane root;
    Bpo n;
    
	@Override
    public void start(Stage stage) {

        root = new StackPane();
        Scene s = new Scene(root, 800, 800, Color.WHITE);

        //final Canvas canvas = new Canvas(800, 800);
        // I changed this and am going to commit 
        
        imageK.setScaleX(0.45);
        imageK.setScaleY(0.45);
        imageT.setScaleX(0.45);
        imageT.setScaleY(0.45);
        
        imageK.setTranslateX(-295);
    	imageK.setTranslateY(-290);
    	
    	imageK.setVisible(false);
    	
    	imageT.setTranslateX(-295);
    	imageT.setTranslateY(-290);
    	
        root.getChildren().addAll(image, imageK );
        stage.setScene(s);
        stage.show();
    	

        // Listener for MouseClick
        image.setOnMouseClicked(e -> {
        	
        	double x = e.getX()-23;      	
        	x -= x%84;
        	
        	double y = e.getY()-23; 
        	y -= y%84;       	
        	
        	
        	if (FirstMove) {
        		FirstMove = false;
        		int kx = (int) x/84;
        		int ky = (int) y/84;
        		movek( kx, ky);
        		Marker.init();
        		n = new Bpo(kx,ky);
        		Counter = 1;
        		FirstMove = false;
        	}
        	else {
        		if (n!= null) {
	        		Marker.mark(n);
	        		movet(n.x, n.y);
	        		n = n.getNextPos();
	        		if(n!=null) {
	        			//System.out.println("(" + n.x + ","+  n.y +")" );
	        			movek(n.x, n.y);
	        		}
	        		else {
	        			Alert alert = new Alert(AlertType.CONFIRMATION, "Startover? ", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
	        			alert.showAndWait();

	        			if (alert.getResult() == ButtonType.YES) {
	        			    FirstMove = true;
	        			    Counter = 1;
	        			    root.getChildren().clear();
	        			    imageK.setVisible(false);
	        			    root.getChildren().addAll(image, imageK);
	        			}
	        		}
        		}
        	}
        });        
    }
	
    void movek(int a, int b){
    	double x = a*84, y = b*84;
    	imageK.setTranslateX(x-295);
    	imageK.setTranslateY(y-290);
    	imageK.toFront();
    	imageK.setVisible(true);
    }
    void movet(int a, int b){
    	double x = a*84, y = b*84;
    	ImageView im= new ImageView();
    	im.setImage(imageT.getImage());
    	im.setScaleX(0.45);
    	im.setScaleY(0.45);
    	im.setTranslateX(x-295);
    	im.setTranslateY(y-290);
    	Text tx = new Text();
    	tx.setTranslateX(x-295);
    	tx.setTranslateY(y-290);
    	tx.setStyle("-fx-font-size: 32px;");
    	tx.setStyle("-fx-font-size: 32px;");
    	tx.setFill(Color.RED);
    	
    	tx.setText(String.valueOf(Counter++));   	
    	
    	root.getChildren().add(im);
    	root.getChildren().add(tx);
    	
    }

    public static void main(String[] args) {
        launch(args);
    }
}
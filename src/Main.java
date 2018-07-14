import java.io.File;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    int count = 1;
    double x, y;
    ImageView imageK, imageT;
    StackPane root;
    
	@Override
    public void start(Stage stage) {

        root = new StackPane();
        Scene s = new Scene(root, 800, 800, Color.WHITE);

        //final Canvas canvas = new Canvas(800, 800);
         
        File f = new File("F:\\Common\\git\\Simple\\src\\images\\chessboard.png");
        ImageView image = new ImageView(f.toURI().toString());
        f = new File("F:\\Common\\git\\Simple\\src\\images\\Knight.png");
        imageK = new ImageView(f.toURI().toString());
        f = new File("F:\\Common\\git\\Simple\\src\\images\\KnightT.png");
        imageT = new ImageView(f.toURI().toString());
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
        	x = e.getX()-23;      	
        	x -= x%84;
        	
        	y = e.getY()-23; 
        	y -= y%84;       	
        	
        	move((int) x/84,(int)y/84);
        	
        	System.out.println("(" + x/84 + ","+  y/84 +")" );        	
        });

        
    }
    void move(int a, int b){
    	
    	if( a<0 || (a>7) || b<0 || b> 7) {
    		System.out.println("outside bounds");
    		return;
    	}
    	imageK.setVisible(true);
    	System.out.println(" " +  imageK.getX() + ":" + imageK.getLayoutX());
    	
    	ImageView im= new ImageView();
    	im.setImage(imageT.getImage());
    	im.setScaleX(0.45);
    	im.setScaleY(0.45);
    	im.setTranslateX(x-295);
    	im.setTranslateY(y-290);
    	root.getChildren().add(im);

    	double x = a*84, y = b*84;
    	imageK.setTranslateX(x-295);
    	imageK.setTranslateY(y-290);
    	imageK.toFront();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
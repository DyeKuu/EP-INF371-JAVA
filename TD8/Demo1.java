import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.transform.Translate; 

public class Demo1 extends Application {
    Label l1 = new Label("label 1");
    Label l2 = new Label("label 2");
    Label l3 = new Label("label 3");
    
    Button b(String c) {
	Button b = new Button(c);
	int size = 90;
	b.setMaxSize(size, size);
	b.setMinSize(size, size);
	return b;
    }

    int r = 0;
   
     Button b(String c, ImageView iv) {
	Button b = new Button(c);
	int size = 90;
	b.setMaxSize(size, size);
	b.setMinSize(size, size);
	b.setOnAction(value -> { r += 90; iv.setRotate(r); });
	return b;
    }

       public void start(Stage stage) {
        stage.show();
        stage.setTitle("Des Trucs divers");
        stage.setWidth(600.);
        stage.setHeight(300.);
        //display = new Label("0.");
	Label line0 = new Label("des Boutons:");
	Button b3 = b("bye");
	b3.setOnAction(value -> {stage.close();});

	HBox line1 = new HBox(2, b("1"),  b("2"), b3);
        Label line2 = new Label("des Labels:");
	HBox line3 = new HBox(2, l1, l2, l3);
        HBox line4 = new HBox(2, b("0"), b("8"), b("9"), b("+"));
        HBox line5 = new HBox(2, b("="), b("C"), b("("), b(")"));

	Label line6a = new Label(" ");
	Label line6 = new Label("des Check-boxes:");

	VBox cb = new VBox(2, new CheckBox("a"), new CheckBox("b"));
        VBox vbox = new VBox(2, line0, line1, line2, line3, line6a,line6, cb);

	vbox.getChildren().add(new Label("des images !"));

	Image mon_image = new Image("b3.jpg");
	ImageView iv = new ImageView(mon_image);

	iv.setFitWidth(200);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);


	
	vbox.getChildren().add(iv);

	Translate gauche = new Translate(-20, 0);
	Translate droite = new Translate(20, 0);
	Translate up = new Translate(0, -20);
	Translate down = new Translate(0, 20);
	
	
	HBox lineAct = new HBox(2, b("a",iv),  b("b",iv), b("c",iv));
	vbox.getChildren().add(lineAct);
	String cssLayout = "-fx-border-color: red;\n" +
	    "-fx-border-insets: 5;\n" +
	    "-fx-border-width: 3;\n" +
	    "-fx-border-style: dashed;\n" +
	    "-fx-background-color:POWDERBLUE";
	vbox.setStyle(cssLayout);
	
        Scene scene = new Scene(vbox);
	scene.setOnKeyTyped(e -> {
		if (e.getCharacter().equals("j")) {
		    r += 45; iv.setRotate(r); }
		else if (e.getCharacter().equals("k")) {
		    r -= 45; iv.setRotate(r);
		}
		else if (e.getCharacter().equals("s")) 
		     iv.getTransforms().addAll(gauche);
		else if (e.getCharacter().equals("d")) 
		    iv.getTransforms().addAll(droite);
		else if (e.getCharacter().equals("e")) 
		    iv.getTransforms().addAll(up);
		else if (e.getCharacter().equals("x")) 
		     iv.getTransforms().addAll(down);
		
	    });
		    
	    //handlekey(e));
        stage.setScene(scene);
    }

	public static void main(String[] args) {
        launch(args);
    }
}

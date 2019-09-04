import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class GraphicsCalculator extends Application {
    Label result = new Label("Surprise");
    Label exprelabel = new Label();
    String expre = "";
    boolean isNum = true;
    String currentNumber = "";
    Tokenizer test = new Tokenizer();
    boolean duplicatedEqual = false;
    boolean resultdominant = true;
    boolean duplicatedOp = false;
    int bigwordsize = 30;
    int smallwordsize =20;
    String name = "789+456-123*0.C/()$=";

    @Override
    public void start(Stage stage) {
        stage.show();

        stage.setTitle("Surprise");
        stage.setHeight(480);
        stage.setWidth(400);
        setDominantHBox(bigwordsize, smallwordsize);
        HBox hbresult = new HBox();
        hbresult.getChildren().add(result);
        HBox hbexpre = new HBox();
        hbexpre.getChildren().add(exprelabel);
        
        VBox vb = new VBox(5,hbexpre,hbresult);
        
        int divide = 4;
        for (int j = 0;j<name.length();j += divide){
            HBox but = new HBox(5);
            String nameStri = name.substring(j, j+divide);
            for (int i = 0; i<nameStri.length(); i++){
                char c = nameStri.charAt(i);
                Button button = b(c);
                button.setOnAction( value -> {
                    update(c);
                    setDominantHBox(bigwordsize, smallwordsize);
                });
                but.getChildren().add(button);                
            }
            vb.getChildren().add(but);
        }

        Scene scene = new Scene(vb);
        scene.setOnKeyTyped(e  -> handlekey(e));
        stage.setScene(scene);
    }

    public Button b(char c){
        Button button = new Button(""+c);
        int size = 60;
        button.setMinSize(size, size);
        button.setMaxSize(size, size);
        return button;
    }

    public void update(char c){
        if (c == 'C'){
            expre = "";
            currentNumber = "0";
            isNum = false;
            duplicatedEqual = false;
            resultdominant = true;
            exprelabel.setText(expre);
            result.setText(currentNumber);
            return;
        }
        expre += c;
        if (((c >= '0') && (c <= '9')) || (c == '.') || (c == '$')) {
            if ((c >= '0') && (c <= '9')) {
                if (!isNum) currentNumber = "";
                currentNumber += c;
                isNum = true;
                duplicatedEqual = false;
                resultdominant = false;
            }
            if((c == '.') || (c == '$')){
                duplicatedOp = true;
            }
            else{
                duplicatedOp = false;
            }
        }
        else {
            isNum = false;
            if (c == '='){
                duplicatedOp = false;
                resultdominant = true;
                if (duplicatedEqual) expre = expre.substring(0, expre.length()-1);
                test.readString(expre);
                result.setText(Double.toString(test.calc.getResult()));
                duplicatedEqual = true;
            }
            else{
                if (duplicatedOp) {
                    expre = expre.substring(0, expre.length()-2);
                    expre += c;
                }
                if (c == '(' || c == ')') duplicatedOp = false;
                else duplicatedOp = true;
                duplicatedEqual = false;
                resultdominant = false;
            }
        }
        exprelabel.setText(expre);
    }
    
    public void setDominantHBox(int a, int b){
        if (!resultdominant){
            a = a + b;
            b = a - b;
            a = a - b;
        }
        result.setFont(new Font("Arial", a));
        exprelabel.setFont(new Font("Arial", b));
    }
    public void handlekey(KeyEvent e){
        if(e.getCharacter().getBytes()[0] == '\r'){
            update('=');
            //System.out.println("yes");
        }
        if(e.getCharacter().getBytes()[0] == '\b'){
            if(isNum) expre = expre.substring(0, expre.length()-1);
            exprelabel.setText(expre);
        }
        if (name.contains(e.getCharacter())){
            update(e.getCharacter().charAt(0));
        }
        setDominantHBox(bigwordsize, smallwordsize);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
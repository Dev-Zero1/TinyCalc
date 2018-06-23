import javafx.application.Application;
import javafx.stage.Stage;
import java.util.*;
import javafx.scene.Scene;
import java.lang.IllegalArgumentException;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.beans.*;
 

public class calcApp extends Application{

//---------------------------------------------------------------------------//     
                                //START MAIN
//---------------------------------------------------------------------------//     
    @Override
    public void start(Stage primaryStage) {
 	
		calcGUI calc = new calcGUI();
	    Scene scene  = new Scene(calc.layout ,250,175);  			//add the layout to the scene
		
        primaryStage.setTitle("TinyCalc 2.0");         				//add a title
        primaryStage.setScene(scene);                       		//add the scene to the stage
        primaryStage.show();  
    }
        
    public static void main(String [] args) {
      //  System.out.println("HelloWorld!!!");
		
		//launch the GUI app 
        launch(args);
    }
	
	public calcApp(){
		
	}
//---------------------------------------------------------------------------//     
                                //START MAIN
//---------------------------------------------------------------------------//     

}
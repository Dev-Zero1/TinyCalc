/*
	Layout borderpane diagram below
-------------------------------------------------------------------------
																		|
-------------------------------------------------------------------------
	*****container VBox (vertical) with 6 HBox Rows(horizontal)			|
						-------------------------						|
						-         Answer        -						|
						-------------------------						|
						-	0		1		2	-						|
						-------------------------						|
						-	3		4		5	-						|
						-------------------------						|
						-	6		7		8	-						|
						-------------------------						|
						-	9		CLR	   ENTER-						|	
						-------------------------						|
						-	+    -	 x   /   %	-						|
						-------------------------						|
																		|
																		|
-------------------------------------------------------------------------
																		|
-------------------------------------------------------------------------
*/


import javafx.geometry.Pos;
import java.util.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.beans.*;
import java.lang.IllegalArgumentException;



public class calcGUI{   

	final int 	MAX =	10;
	Button 		num 	[]; //0-9
	Button 		sign 	[]; //Plus, minus multiply divide and modulus
	HBox 		row 	[]; //rows of buttons for calculator
	VBox 		container;  //my vertical box to store each row, "contains" elements.
	TextField 	Answer;		//text field to display result
	BorderPane 	layout;		//final layout pane for the stage.
	Button 		clear;
	Button 		submit; 
	
	double 		value1	=  0; //right side double value	
	String 		RHS		= ""; //right side as a string
	
	double		value2 	=  0; //left side double value
	String	 	LHS		= ""; //left side as a string
	
	String 		symbol 	= ""; // + - x / %
	
	double 		result 	=  0; //result as a double
	String 		res 	= ""; //result as a string
	//whether the symbol button has been pressed yet.... need to use an event to detect
	//whethehr the button was pushed (similar to the #0-9 buttons.)
	boolean 	hasSymbol = false;
//--------------------------------------------------------------------------//
		//function: calcGUI() - default constructor for new calcGUI() objects. 
		//
//--------------------------------------------------------------------------//	

	//default constructor no parameters.
	public calcGUI() 
	{
		_default(); //initialize all elements to not-null states.
		_inplace(); //place all components on the layout.
		setListeners();	
	}
//--------------------------------------------------------------------------//
		//function: _default() - no args, no return. 
		//Initializes each element with an object/value. Fresh state/constructor.
//--------------------------------------------------------------------------//	
	
    public void setListeners(){
		 
		setNumListeners(0);
		setSignListeners();
		clear.setOnAction (e ->{ Answer.setText("");  });
		submit.setOnAction(e ->{ calcAnswer();  });
	}
	
	public void calcAnswer(){
		result = 0;
		value1 = Double.parseDouble(LHS);
		value2 = Double.parseDouble(RHS);
		try{
			if((symbol.charAt(0) == '/' && (value1 ==0 || value2 ==0))) 
				throw new IllegalArgumentException();
			
			switch(symbol.charAt(0)){
				case '+': result = value1 + value2; break;
				case '-': result = value1 - value2; break;
				case 'x': result = value1 * value2; break;
				case '/': result = value1 / value2; break;
				case '%': result = value1 % value2; break;
				default: throw new IllegalArgumentException();
			}
		}catch(IllegalArgumentException e){
			Answer.setText("INVALID_NaN");
		}
		
		//valid answers here! Nothing went wrong above.
		
		
		 Answer.setText(String.valueOf(result));
		 resetVals();
	}
	public void resetVals(){
		hasSymbol = false;
		LHS 	= "";
		RHS 	= "";
		value1 	=  0;
		value2 	=  0;
		result 	=  0;	
		res		="";
	}
	
	public void setNumListeners(final int x){
		
			
		//if the symbol is set, modify the right value instead.
		if(x <10){
		num[x].setOnAction(e -> { 	
			if(hasSymbol){ 	
				RHS+=String.valueOf(x);
				res = LHS +" " +symbol+ " " + RHS;	
				Answer.setText(res);
			}else{ 	
				LHS+=String.valueOf(x);
				res = LHS; 
				Answer.setText(res);
			}	
		});
		setNumListeners(x+1);
		}
		
	}
	public void setSignListeners(){
		sign[0].setOnAction(e ->{  	hasSymbol =true; /*AND*/ symbol = "+";});
		sign[1].setOnAction(e ->{  	hasSymbol =true; /*AND*/ symbol = "-";});
		sign[2].setOnAction(e ->{  	hasSymbol =true; /*AND*/ symbol = "x";});
		sign[3].setOnAction(e ->{  	hasSymbol =true; /*AND*/ symbol = "/";});
		sign[4].setOnAction(e ->{  	hasSymbol =true; /*AND*/ symbol = "%";});
	}
//--------------------------------------------------------------------------//
		//function: _default() - no args, no return. 
		//Initializes each element with an object/value. Fresh state/constructor.
//--------------------------------------------------------------------------//	
//6 HBoxes, 10 numbers, 5 signs.
	public void _default(){
		//init textField to a blank element
		Answer 		= new TextField("");
		layout		= new BorderPane();  //one layout borderpane for final layout scene.
		container 	= new  VBox(); 	//one vertical box to hold all elements
		
		num 	= new Button 	[MAX];//initialize to a size of 10 num buttons
		sign 	= new Button 	[5]; //initialize to a size of 5 sign buttons
		row 	= new HBox 		[6];//initialize  to a size of 6 Horizontal layout boxes.
		clear 	= new Button("CLR");
		submit 	= new Button("ENTER");

		//init all buttons
		for (int i = 0; i<MAX;i++){
			num[i] = new Button(""+i);
			
			if(i<6) //while looping & i < 6 max, create rows.
			row[i] = new HBox();
		
			if(i < 5){//while looping & i < 5 max, create signs using a switch for each loop.
				switch(i){
				case 0: sign[i]= new Button("+"); break;	// 	+ plus	
				case 1: sign[i]= new Button("-"); break;	// 	- minus	
				case 2: sign[i]= new Button("x"); break;	// 	x multiply
				case 3: sign[i]= new Button("/"); break;	// 	/ divide
				case 4: sign[i]= new Button("%"); break;	//	% modulus
				}	
			}
		}	 
	}
//--------------------------------------------------------------------------//
		//function: _inplace() - no args, no return. Places elements onto layout.
//--------------------------------------------------------------------------//
	public void _inplace() 
	{
	
	//since the elements were initialized the _default function, place them here.
	
	row[0].getChildren().add(Answer);					//top row with text box.	
	row[1].getChildren().addAll(num[0],num[1],num[2]);  //making the # rows
	row[2].getChildren().addAll(num[3],num[4],num[5]);	
	row[3].getChildren().addAll(num[6],num[7],num[8]);
	row[4].getChildren().addAll(num[9],clear, submit);
	
	//last row has the signs/symbols...
	row[5].getChildren().addAll(sign[0],sign[1],sign[2],sign[3],sign[4]);
    
	//add the rows one by one to the VBox container so they stack.
	container.getChildren().addAll(row[0],row[1],row[2],row[3],row[4],row[5]);	
	
	
	//borderpane uses a different syntax to place nodes than those above.
	layout.setCenter(container);  
	container.setAlignment(Pos.CENTER);	
	
	}
}
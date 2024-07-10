import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Guess extends Application{
	private int number = 1 + (int)(100*Math.random());
	private int attempts = 6;
	
	@Override
	public void start(Stage primaryStage) {
		
		Text header = new Text("Number Guessing Game");
		header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
		
		Text txt = new Text("A number is chosen between 1 to 100. "
				+ "\nGuess the number within 6 trials.");
		txt.setFont(Font.font("Segoe UI", FontWeight.BOLD, 25));
		
		
		TextField tf = new TextField();
		tf.setPromptText("Guess the number: ");
		tf.setPrefSize(200, 40);
		
		Label feedback = new Label();
		feedback.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
		feedback.setTextFill(Color.RED);
		
		Button guessBt = new Button("Guess");
		guessBt.setPrefSize(100, 40);
		guessBt.setStyle("-fx-background-color: #4CAF50;-fx-text-fill: white;");
		
		Button resetBt = new Button("Reset");
		resetBt.setPrefSize(100, 40);
		resetBt.setStyle("-fx-background-color: #eb4034;-fx-text-fill: white;");
		resetBt.setOnAction(e -> {
			number = 1 + (int)(100 * Math.random());
		    attempts = 6;
		    feedback.setText(""); // Clear any previous feedback
		    guessBt.setDisable(false); // Enable the guess button
		    tf.clear(); // Clear the text field
		});
		ButtonHoverRed(resetBt);
		
		tf.setOnAction(e -> processGuess(tf, feedback, guessBt));
		ButtonHoverGreen(guessBt);
		
		guessBt.setOnAction(e -> processGuess(tf, feedback, guessBt));
		ButtonHoverGreen(guessBt);
		
		VBox vb = new VBox(20);
		vb.setStyle("-fx-background-color:#ffffff;");
		
		HBox h1 = new HBox();
		h1.getChildren().add(header);
		h1.setAlignment(Pos.CENTER);
		h1.setStyle("-fx-background-color:#00b509; -fx-padding: 20px;");
		
		HBox h2 = new HBox();
		h2.getChildren().add(txt);
		h2.setAlignment(Pos.CENTER);
		h2.setPadding(new Insets(20));
		
		HBox h3 = new HBox(20);
		h3.getChildren().addAll(tf, guessBt, resetBt);
		h3.setAlignment(Pos.CENTER);
		h3.setPadding(new Insets(20));
		
		HBox h4 = new HBox();
		h4.getChildren().add(feedback);
		h4.setAlignment(Pos.BASELINE_CENTER);
		
		
		vb.getChildren().addAll(h1, h2, h3, h4);
		
		BorderPane bp = new BorderPane();
		bp.setCenter(vb);
		bp.setPadding(new Insets(10));
		Scene scene = new Scene(bp, 800, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Number Guessing Game");
		primaryStage.show();
	}

	private void processGuess(TextField tf, Label feedback, Button guessBt) {
		try {
			int guess = Integer.parseInt(tf.getText());
			
			
			if(guess < 1 || guess > 100) {
				feedback.setText("Please enter a number between 1 and 100");
			}
			else {
				attempts--;
				
				if (number > guess && attempts > 0) {
					feedback.setText("The number is greater than " + guess);
					feedback.setTextFill(Color.ORANGE);
				}
				else if (number < guess && attempts > 0) {
					feedback.setText("The number is less than " + guess);
					feedback.setTextFill(Color.ORANGE);
				}
				else if (number == guess){
					feedback.setText("Congratulations! You guessed the number!");
					feedback.setTextFill(Color.GREEN);
					guessBt.setDisable(true);
				}
			
				if(attempts == 0 && number != guess) {
					feedback.setText("You have used up all attempts");
					feedback.setText("The number was " + number);
					guessBt.setDisable(true);
				}
			}
			
		} catch(NumberFormatException ex) {
			feedback.setText("Please enter a valid number");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void ButtonHoverGreen(Button button) {
		button.setOnMouseEntered(e ->button.setStyle("-fx-background-color: #45a049;"));
        button.setOnMouseExited(e ->button.setStyle("-fx-background-color: #4CAF50;-fx-text-fill: white;"));
	}
	private void ButtonHoverRed(Button button) {
		button.setOnMouseEntered(e ->button.setStyle("-fx-background-color: #9c271f;"));
        button.setOnMouseExited(e ->button.setStyle("-fx-background-color: #eb4034;-fx-text-fill: white;"));
	}
}

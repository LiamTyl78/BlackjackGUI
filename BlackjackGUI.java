
/**
 * Blackjack GUI created by
 * @author Liam Tyler
 * @version 1.0
 */

import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BlackjackGUI extends Application {
    private static Stage guide = new Stage();
    BlackjackController control = new BlackjackController();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage s) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Blackjack.fxml"));
        //System.out.println("Game window initilized");
        Parent root2 = FXMLLoader.load(getClass().getResource("Guide.fxml"));
        //System.out.println("Guide panel initilized");
        Scene scene = new Scene(root);
        Scene scene2 = new Scene(root2);

        s.setTitle("Blackjack");
        s.setScene(scene);
        s.setResizable(false);
        s.show();

        guide.setScene(scene2);
        guide.setTitle("Game guide");
        guide.initStyle(StageStyle.UTILITY);
        guide.setResizable(false);

    }

    /**
     * Changes the picture of the cards utilizing the Imageview entered in as a
     * parameter
     * 
     * @param card  card to grap the suit and value from
     * @param image
     * @return the image object with the correct texture applied to it so it can be
     *         applied to the image texture of the card in the GridPane
     */
    public static ImageView cardskin(Card card, ImageView image) {
        String imageurl = "Card_Skins/" + card.GetValue() + card.GetSuitAsString() + ".png";
        // System.out.println(imageurl);
        Image card_image = new Image(imageurl);
        image.setImage(card_image);
        try {
        } catch (Exception e) {
            error(e);
        }
        return image;
    }

    public static void ShowGuide() {
        guide.show();
    }

    public static void error(Exception e) {
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle("Fatal Error");
        error.setHeaderText("An error has inturupted the program");
        error.setContentText(e.toString());
        error.show();
        
    }

    public static void Alert(String title, String header, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static int playagainprompt(int won, int lost) {
        Alert playagain = new Alert(AlertType.NONE);
        ButtonType Yes = new ButtonType("Yes");
        ButtonType No = new ButtonType("No");
        
        playagain.getButtonTypes().setAll(Yes, No);
        playagain.setHeaderText("Would you like to play again?");
        Optional<ButtonType> result = playagain.showAndWait();
        //playagain.setGraphic();
        

        if (result.get() == Yes) {
            return 1;
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("End Game Stats");
            alert.setHeaderText("You won " + won + " times and lost " + lost + " times.");
            alert.showAndWait();
            return 0;
        }
    }
}

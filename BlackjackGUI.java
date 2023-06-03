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


public class BlackjackGUI extends Application{
    private static Stage guide = new Stage();
    BlackjackController control = new BlackjackController();
    
    
    public static void main(String[] args) {
        launch(args);
        //BlackjackGUI b = new BlackjackGUI();
        //Blackjack p = new Blackjack();
        //p.Play();
    }
    
    public void start(Stage s) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Blackjack.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("Guide.fxml"));
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
        
        //control.PlayButtonPressed(null);
        


    }
    
    public static ImageView cardskin(Card card, ImageView image) {
        String imageurl = "Card_Skins/" + card.GetValue() + card.GetSuitAsString() + ".png";
        //System.out.println(imageurl);
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
        error.setHeaderText("An error has inturupted to program");
        error.setContentText(e.toString());
        error.show();
    }

    public static void Alert(String title,String header, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static int playagainprompt(){
        Alert playagain = new Alert(AlertType.NONE);
        ButtonType Yes = new ButtonType("Yes");
        ButtonType No = new ButtonType("No");
        
        playagain.getButtonTypes().setAll(Yes,No);
        playagain.setHeaderText("Would you like to play again?");
        Optional<ButtonType> result = playagain.showAndWait();

        if(result.get() == Yes){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    
    
    

}

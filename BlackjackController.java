import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BlackjackController {
    @FXML
    private AnchorPane GameScreen;
    @FXML
    private Label HandValueLabel;
    @FXML
    private Label DealerValueLabel;
    @FXML
    private Button GuideButton;
    @FXML
    private AnchorPane MainMenu;
    @FXML
    private Button PlayButton;
    @FXML
    private Button HitButton;
    @FXML
    private ImageView CardFive = new ImageView();
    @FXML
    private ImageView CardFour = new ImageView();
    @FXML
    private ImageView CardOne = new ImageView();
    @FXML
    private ImageView CardSix = new ImageView();
    @FXML
    private ImageView CardThree = new ImageView();
    @FXML
    private ImageView CardTwo = new ImageView();
    @FXML
    private ImageView CardZero = new ImageView();

    private ArrayList<ImageView> PlayerCards = new ArrayList<>();
    private ArrayList<Card> current_cards = new ArrayList<>();
    private int CardNum = 0;
    private int PlayerHand;
    private int DealerHand;
    private boolean initilized = false;
    private boolean playing = true;
    private Deck deck = new Deck();

    @FXML
    void GuideButtonPressed(ActionEvent event) {
        BlackjackGUI.ShowGuide();
    }
    
    @FXML
    void PlayButtonPressed(ActionEvent event) {
        GameScreen();
        deck.Shuffle();
        Deal(1); 
        DealerValueLabel.setText("Dealer face up card is a " + current_cards.get(current_cards.size() - 1).toString() + ".");
        Deal(1);
        //System.out.println(current_cards);
        DealerHand = CardSum();
        current_cards.clear();
        Deal(0); 
        Deal(0);
        HandValueLabel.setText("Hand value: " + CardSum());
        
    }
    
    @FXML
    void HitButtonPressed(ActionEvent event) {
        if (playing) {
            if(CardSum() < 21){
                Deal(0);
                HandValueLabel.setText("Hand value: " + CardSum());
            }
            if(CardSum() > 21){
                BlackjackGUI.Alert("Game Over", "You Busted!", null);
                playing = false;
                if(BlackjackGUI.playagainprompt() == 1){
                    playagain();
                }
            }
            if(CardSum() == 21){
                BlackjackGUI.Alert("You Won!", "You got blackjack!", null);
                playing = false;
                if(BlackjackGUI.playagainprompt() == 1){
                    playagain();
                }
            }
        }
        else{
            
        }
    }
    
    @FXML
    void StandButtonPressed(ActionEvent event) {
        if(playing){
            PlayerHand = CardSum();
            playing = false;
            current_cards.clear();
            //System.out.println(DealerHand);
            while (DealerHand < 17) {
                Deal(1);
                DealerHand = DealerHand + current_cards.get(current_cards.size() - 1).GetValue();
                //System.out.println(DealerHand);
                DealerValueLabel.setText("Dealer Hand Value: " + DealerHand);
            }
            //System.out.println(current_cards);
            if(DealerHand == 21){
                BlackjackGUI.Alert("You Lost!", "You Lost! Dealer got blackjack! ", null);
            }
            else if(PlayerHand > DealerHand){
                BlackjackGUI.Alert("You Won!", "You Won! You got a hand value of: " + PlayerHand + " while the dealer had a hand value of " + DealerHand, null);
            }
            else if(DealerHand > 21){
                BlackjackGUI.Alert("You Won!", "You Won! The dealer busted and you didnt.",null);
            }
            else if(PlayerHand < DealerHand){
                BlackjackGUI.Alert("You Lost!", "You Lost! You got a hand value of: " + PlayerHand + " while the dealer had a hand value of: " + DealerHand, null);
            }
            else if(PlayerHand == DealerHand){
                BlackjackGUI.Alert(null, "You and the dealer both tied at: " + DealerHand, null);
            }
            if(BlackjackGUI.playagainprompt() == 1){
                playagain();
            }
            
        }
    }
    
    //----------------------------------------------------------------------------------------------------
    
    private void GameScreen() {
        if(MainMenu.isVisible()){
            MainMenu.setVisible(false);
            GameScreen.setVisible(true);
        }
    }
    public void Deal(int mode) {
        current_cards.add(deck.DealCard());
        // System.out.println(initilized);
        // System.out.println(CardNum);
        if(mode == 0){
            if(initilized == false){
                PlayerCards.add(CardZero);
                PlayerCards.add(CardOne);
                PlayerCards.add(CardTwo);
                PlayerCards.add(CardThree);
                PlayerCards.add(CardFour);
                PlayerCards.add(CardFive);
                PlayerCards.add(CardSix);
                initilized = true;
                // System.out.println(PlayerCards);
            }
            try {
                BlackjackGUI.cardskin(current_cards.get(current_cards.size() - 1),PlayerCards.get(CardNum));   
            } catch (Exception e) {
                BlackjackGUI.error(e);
            }
            CardNum++;
        }
    }


    public int CardSum() {
        int sum = 0;
        for (Card card : current_cards) {
            int added_val = card.GetValue();
            if (added_val >= 11) {
                added_val = 10;
            }
            sum += added_val;
        }
        return sum;
    }

    public void playagain(){
        initilized = false;
        current_cards.clear();
        for (ImageView imageView : PlayerCards) {
            imageView.setImage(null);
        }
        CardNum = 0;
        deck.Shuffle();
        Deal(1); 
        DealerValueLabel.setText("Dealer face up card is a " + current_cards.get(current_cards.size() - 1).toString() + ".");
        Deal(1);
        DealerHand = CardSum();
        current_cards.clear();
        //System.out.println(current_cards);
        System.out.println(DealerHand);
        Deal(0); 
        Deal(0);
        HandValueLabel.setText("Hand value: " + CardSum());
        playing = true;
    }

}

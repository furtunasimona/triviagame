package trivia;

import trivia.properties.Category;

import java.util.HashMap;
import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {
   LinkedList<Player> players = new LinkedList<>();

   HashMap<String,LinkedList<String>> questions = new HashMap<>();

   Player currentPlayer;
   boolean isGettingOutOfPenaltyBox;

   public GameBetter() {
      for (Category category : Category.values()){
         LinkedList qList = createQuestions(category);
         questions.put(category.name(),qList);
      }
   }

   public boolean isPlayable() {
      return (players.size() >= 2);
   }

   public boolean add(String playerName) {
      if(players.stream().anyMatch(player-> player.getName().equals(playerName))){
         return false;
      }
      else{
         Player player = new Player(playerName);
         players.addLast(player);
         System.out.println(playerName + " was added");
         System.out.println("They are player number " + players.size());
         return true;
      }
   }

   public void roll(int roll) {
      if(currentPlayer == null){
         currentPlayer = players.get(0);
      }
      System.out.println(currentPlayer + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer.isInPenaltyBox()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(currentPlayer + " is getting out of the penalty box");
            currentPlayer.changePenaltyBoxStatus(false);
            playerNextRound(roll);
         } else {
            System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox=false;
         }
      } else {
         playerNextRound(roll);
      }

   }

   private Category currentCategory() {
      switch(currentPlayer.getPlace() % 4){
         case 0:
            return Category.Pop;
         case 1:
            return Category.Science;
         case 2:
            return Category.Sports;
         default:
            return Category.Rock;
      }
   }

   public boolean wasCorrectlyAnswered() {
      boolean isWinner=true;
      if (isGettingOutOfPenaltyBox || !currentPlayer.isInPenaltyBox()) {
            System.out.println("Answer was correct!!!!");
            currentPlayer.increasePurse();
            System.out.println(currentPlayer.showPurse());
            isWinner=!currentPlayer.won();
         }
      if (currentPlayer == players.getLast())
      {
         currentPlayer = players.getFirst();
      }
      else {
         currentPlayer = players.get(players.indexOf(currentPlayer)+1);
      }
      return isWinner;
   }


   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer + " was sent to the penalty box");
      currentPlayer.changePenaltyBoxStatus(true);
      if (currentPlayer == players.getLast()) {
         currentPlayer = players.getFirst();
      }
      else {
         currentPlayer = players.get(players.indexOf(currentPlayer)+1);
      }
      return true;
   }


   private void playerNextRound(int roll){
      currentPlayer.upPlace(roll);
      if (currentPlayer.getPlace() > 11)
      {
         currentPlayer.downPlace(12);
      }

      System.out.println(currentPlayer.showPlace());
      System.out.println("The category is " + currentCategory());
      System.out.println(questions.get(currentCategory().name()).removeFirst());
   }

   public LinkedList<String> createQuestions(Category category){
      LinkedList<String> q = new LinkedList<>();
      for(int i=0;i<50;i++){
         q.add(category.name() + " Question " + i);
      }
      return q;
   }
}

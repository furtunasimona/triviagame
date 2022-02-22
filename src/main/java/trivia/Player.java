package trivia;

public class Player {

    private String name;
    private int purse;
    private int place;
    private Boolean penaltyBox;
    private Boolean winner;

    public Boolean isInPenaltyBox() {
        return penaltyBox;
    }

    public void changePenaltyBoxStatus(boolean status){
        this.penaltyBox = status;
    }

    public int getPurse() {
        return purse;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Player(String name) {
        this.name = name;
        this.place = 0;
        this.purse = 0;
        this.penaltyBox = false;
        this.winner = false;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.purse = 0;
        this.place = 0;
    }

    public void upPlace(int val){
        this.place += val;
    }

    public void downPlace(int val){
        this.place -= val;
    }

    public String showPlace(){
        return this.name + "'s new location is " + String.valueOf(this.place);
    }

    public void increasePurse(){
        this.purse++;
        if(this.purse == 6) this.winner = true;
    }

    public String showPurse(){
        return this.name + " now has " + String.valueOf(this.purse) + " Gold Coins.";
    }

    public Boolean won(){
        return this.winner;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

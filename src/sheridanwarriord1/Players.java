import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Player {
    private String name;
    private List<String> hand;
    private int score;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public List<String> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }

    public void addToScore(int points) {
        score += points;
    }

    public void addToHand(String card) {
        hand.add(card);
    }

    public void removeFromHand(String card) {
        hand.remove(card);
    }

    public int getHandSize() {
        return hand.size();
    }

    public boolean hasCard(String card) {
        return hand.contains(card);
    }

    public void displayHand() {
        System.out.print(name + "'s hand: ");
        for (String card : hand) {
            System.out.print(card + " ");
        }
        System.out.println();
    }
}
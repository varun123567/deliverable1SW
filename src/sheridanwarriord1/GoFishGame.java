import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class GoFishGame {
    private List<Player> players;
    private List<String> deck;

    public GoFishGame() {
        this.players = new ArrayList<>();
        this.deck = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void start() {
        initializeDeck();
        dealCards();

        while (!isGameOver()) {
            for (Player player : players) {
                if (isGameOver()) {
                    break;
                }

                Player opponent = getOpponent(player);
                playerTurn(player, opponent);
            }
        }

        displayResults();
    }

    private void initializeDeck() {
        deck.clear();
        // Add a standard deck of cards (52 cards)
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
    }

    private void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                drawCard(player);
            }
        }
    }

    private void drawCard(Player player) {
        if (deck.isEmpty()) {
            return;
        }

        int randomIndex = (int) (Math.random() * deck.size());
        String card = deck.get(randomIndex);
        deck.remove(randomIndex);
        player.addToHand(card);
    }

    private boolean isGameOver() {
        for (Player player : players) {
            if (player.getHandSize() == 0) {
                return true;
            }
        }
        return false;
    }

    private Player getOpponent(Player currentPlayer) {
        for (Player player : players) {
            if (player != currentPlayer) {
                return player;
            }
        }
        return null;
    }

    private void playerTurn(Player player, Player opponent) {
        System.out.println("--------------------------------");
        System.out.println(player.getName() + "'s turn:");
        player.displayHand();

        if (player.getHandSize() == 0) {
            drawCard(player);
            System.out.println("No cards in hand. Drew a card from the deck.");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ask " + opponent.getName() + " for a card (enter rank): ");
        String rank = scanner.nextLine().toUpperCase();

        if (opponent.hasCard(rank)) {
            System.out.println(opponent.getName() + " has the card!");

            List<String> matchingCards = new ArrayList<>();
            for (String card : opponent.getHand()) {
                if (card.startsWith(rank)) {
                    matchingCards.add(card);
                }
            }
            for (String card : matchingCards) {
                opponent.removeFromHand(card);
                player.addToHand(card);
            }

            player.addToScore(matchingCards.size());
        } else {
            System.out.println(opponent.getName() + " does not have the card.");
            drawCard(player);
        }
    }

    private void displayResults() {
        System.out.println("--------------------------------");
        System.out.println("Game over!");

        for (Player player : players) {
            System.out.println(player.getName() + "'s score: " + player.getScore());
        }

        Player winner = getWinner();
        if (winner != null) {
            System.out.println("The winner is: " + winner.getName());
        } else {
            System.out.println("It's a tie!");
        }
    }

    private Player getWinner() {
        Player winner = null;
        int maxScore = -1;

        for (Player player : players) {
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
                winner = player;
            } else if (player.getScore() == maxScore) {
                winner = null; // It's a tie
            }
        }

        return winner;
    }
}

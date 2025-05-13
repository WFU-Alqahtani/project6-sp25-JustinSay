import java.util.*;
import java.io.*;

public class lab6 {

    public static LinkedList initialize_deck() {

        LinkedList deck = new LinkedList();

        // populate linked list with a single deck of cards
        for (Card.suites s : Card.suites.values()) {
            for(Card.ranks r : Card.ranks.values()) {
                if (r != Card.ranks.NULL && s != Card.suites.NULL) {
                    Card newCard = new Card(s, r);
                    //newCard.print_card();
                    deck.add_at_tail(newCard);
                }
            }
        }

        return deck;
    }

    private static void play_blind_mans_bluff(LinkedList player1, LinkedList computer, LinkedList deck) {
        System.out.println("\nStarting Blind mans Bluff \n");
        Scanner input = new Scanner (System.in);
        int wins = 0;
        int losses = 0;
        int consecutive_Losses = 0;


        for (int i = 0; i < 5; i ++){
            Card opponentCard = computer.remove_from_head();
            opponentCard.print_card();

            System.out.println("\nIs your card higher or lower?: (Type 'higher' or 'lower') ");
            String guess = input.nextLine().trim().toLowerCase();

            Card playerCard = player1.remove_from_head();
            System.out.println("Your card is: ");
            playerCard.print_card();

            boolean correctGuess = false;

            int compare = compare_cards(playerCard, opponentCard);
            if (guess.equals("higher") && compare > 0) correctGuess = true;
            else if (guess.equals("lower") && compare < 0)correctGuess = true;

            if(correctGuess){
                System.out.println("\n You won this round!");
                wins++;
                consecutive_Losses = 0;
            }
            else{
                System.out.println("\n You lost this round.");
                losses++;
                consecutive_Losses++;
            }
            if (consecutive_Losses == 3){
                rage_quit (player1, computer, deck);
                return;
            }
        }
        System.out.println("\nGame over! Wins: " + wins + " Losses: " + losses);
    }

    public static void main(String[] args) {

        // create a deck (in order)
        LinkedList deck = initialize_deck();
        deck.print();
        deck.sanity_check(); // because we can all use one

        // shuffle the deck (random order)
        deck.shuffle(512);
        deck.print();
        deck.sanity_check(); // because we can all use one

        // cards for player 1 (hand)
        LinkedList player1 = new LinkedList();
        // cards for player 2 (hand)
        LinkedList computer = new LinkedList();

        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }

        // let the games begin!
        play_blind_mans_bluff(player1, computer, deck);
    }

    public static int compare_cards(Card a, Card b){
        //compare the ranks
        int rankComparison = a.getRank().ordinal() - b.getRank().ordinal();
        if (rankComparison != 0){
            return rankComparison;
        }
        return a.getSuit().ordinal() - b.getSuit().ordinal();
    }

    public static void rage_quit(LinkedList player1, LinkedList computer, LinkedList deck){
        System.out.println("\n RAGE QUIT! Resetting game...");

        //returns all the cards
        while (player1.size() > 0){
            deck.add_at_tail(player1.remove_from_head());
        }
        while (computer.size() > 0){
            deck.add_at_tail(computer.remove_from_head());
        }

        deck.shuffle(512);
        for (int i = 0; i < 5; i++){
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }
        play_blind_mans_bluff(player1, computer, deck);
    }
}

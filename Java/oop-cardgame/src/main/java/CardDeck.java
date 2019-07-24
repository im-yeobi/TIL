import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

public class CardDeck {
    private Stack<Card> cards = new Stack<>();

    private CardDeck() {
        init();
        shuffle();
    }

    public static CardDeck getInstance() {
        return new CardDeck();
    }

    public Card pop() {
        final int empty = 0;

        if (cards.size() <= empty) {
            throw new EmptyStackException();
        }

        return cards.pop();
    }

    private void init() {
        for (Card.CARD_TYPE cardType : Card.CARD_TYPE.values()) {
            for (Card.CARD_VALUE cardValue : Card.CARD_VALUE.values()) {
                cards.push(Card.of(cardType, cardValue));
            }
        }
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    private int size() {
        return cards.size();
    }

}

public class Card {
    private final int CARD_TYPE_SIZE = 4;    // 카드 종류 : heart, spade, clover, diamond
    private final int CARD_VALUE_SIZE = 13;  // A, 2~10, J, Q, K 총 13개

    public enum CARD_TYPE {
        HEART(1, "♥")
        , SPADE(2, "♠")
        , CLOVER(3, "♣")
        , DIAMOND(4, "◆");

        private int value;
        private String shape;

        CARD_TYPE(int value, String shape) {
            this.value = value;
            this.shape = shape;
        }
    }

    public enum CARD_VALUE {
        ACE(1, "A")
        , TWO(2, "2")
        , THREE(3, "3")
        , FOUR(4, "4")
        , FIVE(5, "5")
        , SIX(6, "6")
        , SEVEN(7, "7")
        , EIGHT(8, "8")
        , NINE(9, "9")
        , TEN(10, "10")
        , JACK(10, "J")
        , QUEEN(10, "Q")
        , KING(10, "K");

        private int value;
        private String name;

        CARD_VALUE(int value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    private CARD_TYPE cardType;
    private CARD_VALUE cardValue;

    private Card(CARD_TYPE cardType, CARD_VALUE cardValue) {
        this.cardType = cardType;
        this.cardValue = cardValue;
    }

    // 정적 팩토리 메서드 이용하여 인스턴스 생성
    public static Card of(CARD_TYPE cardType, CARD_VALUE cardValue) {
        return new Card(cardType, cardValue);
    }

}

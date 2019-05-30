package ch7_oop_2.extend;

public class DeckTest {
    public static void main(String[] args) {
        Deck d = new Deck();    // 카드 한 벌
        Card c = d.pick();      // 카드 하나 뽑기
        System.out.println(c);  // 뽑은 카드 출력

        d.shuffle();    // 섞기
        c = d.pick(0);  // 0번째 (첫 번째) 카드 뽑기, 스택 써도 될 거 같다.
        System.out.println(c);
    }
}

class Deck {
    final int CARD_NUM = 52;    // 카드의 개수
    Card cardArr[] = new Card[CARD_NUM];    // Card 객체 포함

    // default constructor
    Deck() {
        int i = 0;

        for (int k = Card.KIND_MAX; k > 0; k--) {
            for (int n = 0; n < Card.NUM_MAX; n++) {
                cardArr[i++] = new Card(k, n+1);    // 총 52개
            }
        }
    }

    Card pick(int index) {
        return cardArr[index];
    }

    Card pick() {
        int index = (int) (Math.random() * CARD_NUM);   // 랜덤하게 카드 하나 선택
        return pick(index);
    }

    void shuffle() {
        for (int i = 0; i < cardArr.length; i++) {
            int r = (int)(Math.random() * CARD_NUM);

            Card temp = cardArr[i];
            cardArr[i] = cardArr[r];
            cardArr[r] = temp;
        }
    }

}

class Card {
    static final int KIND_MAX = 4;  // 카드 종류
    static final int NUM_MAX = 13;  // 카드 숫자 최대

    static final int SPADE = 4;     // SPADE 인덱스
    static final int DIAMOND = 3;   // DIAMOND 인덱스
    static final int HEART = 2;     // HEART 인덱스
    static final int CLOVER = 1;    // CLOVER 인덱스
    int kind;   // 보유 카드 종류
    int number; // 보유 카드 숫자

    Card() {
        this(SPADE, 1);
    }
    Card(int kind, int number) {
        this.kind = kind;
        this.number = number;
    }

    @Override
    public String toString() {
        String[] kinds = { "", "CLOVER", "HEART", "DIAMOND", "SPADE" };
        String numbers = "0123456789XJQK";  // 숫자 10은 X로 표현

        return "kind : " + kinds[this.kind] + ", number : " + numbers.charAt(this.number);
    }
}

package ch6_oop_1.Initialization_block;

public class StaticBlockTest {
    static int[] arr = new int[10];

    static {
        // static 초기화 블럭
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 10) + 1; // Math.Random() 0.0 >= && < 1.0
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr[" + i + "] : " + arr[i]);
        }
    }
}

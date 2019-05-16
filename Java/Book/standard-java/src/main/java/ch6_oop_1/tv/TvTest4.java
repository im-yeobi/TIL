package ch6_oop_1.tv;

public class TvTest4 {
    public static void main(String[] args) {
        Tv[] tvArr = new Tv[3];     // 길이 3인 tv 객체 배열

        // tv 객체 배열 초기화
        // foreach 문을 사용하면 NullPointerException 발생 => 인스턴스 생성되지 않은 참조변수를 사용하려 했기 때문
        // 클래스는 객체를 생성하기 위한 틀
        for (int i = 0; i < tvArr.length; i++) {
            tvArr[i] = new Tv();
            tvArr[i].channel = i + 10;
        }

        for (int i = 0; i < tvArr.length; i++) {
            tvArr[i].channelUp();
            System.out.printf("tvArr[%d].channel = %d\n", i, tvArr[i].channel);
        }
    }
}

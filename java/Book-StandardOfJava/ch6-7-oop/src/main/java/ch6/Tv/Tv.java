package ch6.Tv;

public class Tv {
    // 멤버변수
    String color;
    boolean power;
    int channel;

    // 메서드
    void power() { power = !power; }
    void channelUp() { ++channel; }
    void channelDown() { --channel; }
}

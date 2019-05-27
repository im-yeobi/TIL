package ch7_oop_2.extend;

class Tv {
    boolean power;
    int channel;

    void power()        { power = !power; }
    void channelUp()    { ++channel; }
    void channelDown()  { --channel; }
}

class CaptionTv extends Tv {
    boolean caption;
    void displayCaption(String text) {
        if (caption) {  // 캠션 ture 일 때만 text 보여준다.
            System.out.println(text);
        }
    }
}

class CaptionTvTest {
    public static void main(String[] args) {
        CaptionTv ctv = new CaptionTv();
        ctv.channel = 10;
        ctv.channelUp();
        System.out.println(ctv.channel);

        ctv.displayCaption("Hello, World");  // caption = false
        ctv.caption = true;
        ctv.displayCaption("Hello, World");
    }
}

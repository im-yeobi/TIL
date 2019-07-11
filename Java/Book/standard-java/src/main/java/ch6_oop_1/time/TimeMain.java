package ch6_oop_1.time;

public class TimeMain {
    public static void main(String[] args) {
        Time time = new Time(10, 5, 24);

        System.out.println("현재시간 : " + time.getHour() + "시 " + time.getMin() + "분 " + time.getSecond() + "초");

        int minute = 0;
        System.out.println(minute + "분 더하기");
        time.addMinute(minute);
        System.out.println("현재시간 : " + time.getHour() + "시 " + time.getMin() + "분 " + time.getSecond() + "초");

        minute = 100;
        System.out.println(minute + "분 더하기");
        time.addMinute(minute);
        System.out.println("현재시간 : " + time.getHour() + "시 " + time.getMin() + "분 " + time.getSecond() + "초");

        int second = 0;
        System.out.println(second + "초 더하기");
        time.addSecond(second);
        System.out.println("현재시간 : " + time.getHour() + "시 " + time.getMin() + "분 " + time.getSecond() + "초");

        second = 7632;
        System.out.println(second + "초 더하기");
        time.addSecond(second);
        System.out.println("현재시간 : " + time.getHour() + "시 " + time.getMin() + "분 " + time.getSecond() + "초");

    }
}

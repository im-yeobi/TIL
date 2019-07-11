package ch6_oop_1.time;

public class Time {

    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour()    {   return hour;    }
    public int getMin()     {   return minute;  }
    public int getSecond()  {   return second;  }

    public void setHour(int hour) {
        if (hour < 0 || hour > 23) {
            return;
        }
        this.hour = hour;
    }

    public void setMinute(int minute) {
        if (minute < 0 || minute > 59) {
            return;
        }
        this.minute = minute;
    }

    public void setSecound(int second) {
        if (second < 0 || second > 59) {
            return;
        }
        this.second = second;
    }

    // 시간의 차와 같은 시간 관련 메서드 추가
    public void addMinute(int minute) {
        if (minute == 0) {    // 더해야 할 time 0인 경우
            System.out.println(minute + "분 더하는 것은 의미없다");
            return;
        }

        final int timeValue = 60;

        this.hour += calcHour(minute, timeValue);
        this.minute += minute % timeValue;
    }

    public void addSecond(int second) {
        if (second == 0) {
            System.out.println(second + "초 더하는 것은 의미없다");
            return;
        }

        final int timeValue = 60;

        this.hour += calcHour(second, timeValue);
        this.minute += (second / timeValue) % timeValue;
        this.second += (second % timeValue);
    }

    // Max 값이 시간이기 때문에 시간에 대한 공통 함수 가능.
    // minute은 time에 분인지, 초인지 구분이 없기에 공통으로 묶을 수 없다.
    private int calcHour(int time, final int timeValue) {
        int hour = time / timeValue;

        if (hour == 0) {
            return time;
        }

        if (hour > 0) {
            hour = calcHour(hour, timeValue);
        }

        return hour;
    }

}



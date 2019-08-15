package ch7_oop_2.interface_sample1;

interface Parseable {
    // 구문 분석작업을 수행한다.
    public abstract void parse(String fileName);
}

class ParserManager {
    // 리턴타입이 Parseable 인터페이스이다. 인스턴스를 리턴한다.
    // **리턴타입이 인터페이스라는 것은 메서드가 해당 인터페이스를 구현한 클래스의 인스턴스를 반환한다는 것을 의미한다.
    public static Parseable getParser(String type) {
        if (type.equals("XML")) {
            return new XMLParser();
        } else {
            Parseable p = new HTMLParser();
            return p;
        }
    }
}

class XMLParser implements Parseable {
    @Override
    public void parse(String fileName) {
        System.out.println(fileName + "-XML parsing completed.");
    }
}

class HTMLParser implements Parseable {
    @Override
    public void parse(String fileName) {
        System.out.println(fileName + "-HTML parsing completed.");
    }
}

public class ParserTest {
    public static void main(String[] args) {
        Parseable parser = ParserManager.getParser("XML");
        parser.parse("document.xml");

        parser = ParserManager.getParser("HTML");
        parser.parse("document2.html");
    }
}

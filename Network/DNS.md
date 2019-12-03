## IP (Internet Protocol)

인터넷상의 모든 컴퓨터는 숫자를 사용하여 서로를 찾고 통신한다. (e.g. 192.168.0.5) 

웹 브라우저를 열고 웹 사이트로 이동할 때는 긴 숫자를 입력할 필요가 없다. 그 대신 example.com과 같은 도메인 이름을 입력해도 원하는 웹 사이트로 갈 수 있다.

## DNS (Domain Name System)

DNS 서비스는 www.example.com과 같이 사람이 읽을 수 있는 이름을 192.0.2.1과 같은 숫자 IP 주소로 변환하여 컴퓨터가 서로 통신할 수 있도록 한다. 인터넷의 DNS시스템은 이름과 숫자 간의 매핑을 관리하여 마치 전화번호부와 같은 기능을 한다. DNS 서버는 이름에 대한 요청을 IP주소로 변환하여 최종 사용자가 도메인 이름을 웹 브라우저에 입력할 때 해당 사용자를 어떤 서버에 연결할 것인지를 제어한다. 이 요청을 `쿼리`라고 부른다.

### DNS 트래픽이 웹 애플리케이션에 라우팅 되는 과정

웹사이트 또는 애플리케이션으로 라우팅하는 방법

[](https://www.notion.so/defde77b15ef4643bb1a509c33f5b9bd#dfaecb60eb724c6e9da3a0a3a67cf335)

1. 사용자가 웹 브라우저를 열어 주소 표시줄에 www.exaple.com을 입력하고 Enter 키를 누른다.
2. www.example.com에 대한 요청을 일반적으로 케이블 인터넷 공급업체(KT, SK, LG 등), DSL 광대역 공급업체 또는 기업 네트워크 같은 인터넷 서비스 제공업체(ISP)가 관리하는 DNS 해석기로 라우팅됩니다.
3. ISP의 DNS 해석기는 www.example.com에 대한 요청을 DNS 루트 이름 서버에 전달한다.
4. ISP의 DNS 해석기는 www.[example.com](http://example.com)에 대한 요청을 이번에는 .com 도메인의 TLD 이름 서버 중 하나에 다시 전달한다. .com 도메인의 이름 서버는 example.com 도메인과 연결된 4개의 Amazon Route 53 이름 서버의 이름을 사용하여 요청에 응답한다.
5. ISP의 DNS 해석기는 Amazon Route 53 이름 서버 하나를 선택해 www.example.com에 대한 요청을 해당 이름 서버에 전달한다.
6. Amazon Route 53 이름 서버는 [example.com](http://example.com) 호스팅 영역에서 [www.example.com](http://www.example.com) 레코드를 찾아 웹 서버의 IP 주소 192.0.2.44 등 연관된 값을 받고 이 IP 주소를 DNS 해석기로 변환한다.
7. ISP의 DNS 해석기가 마침내 사용자에게 필요한 IP 주소를 확보하게 된다. 해석기는 이 값을 웹 브라우저로 반환한다. 또한 DNS 해석기는 다음에 누군가가 [example.com](http://example.com)을 탐색할 때 좀 더 빠르게 응답할 수 있도록 사용자가 지정하는 일정 기간 동안 example.com의 IP 주소를 캐시(저장)한다.
8. 웹 브라우저는 DNS 해석기로부터 얻은 IP 주소로 www.example.com에 대한 요청을 전송한다. 여기가 콘텐츠가 있는 곳으로, 예를 들어 웹 사이트 엔드 포인트로 구성된 Amazon S3 버킷 또는 Amazon EC2 인스턴스에서 실행되는 웹 서버이다.
9. 192.0.2.44에 있는 웹 서버 또는 그 밖의 리소스는 www.example.com의 웹 페이지를 웹 브라우저로 반환하고, 웹 브라우저는 이 페이지를 표시한다.

[](https://www.notion.so/defde77b15ef4643bb1a509c33f5b9bd#1a396cdf533e46858d345b31c3480b44)

- [https://www.naver.com/index.html](https://www.naver.com/index.html) — URL이라 부른다.
- [www.naver.com](http://www.naver.com) — Host Name이라 부른다.
- .com — Top-level Domain Name이라 부른다.
- .naver.com — Second-level Domain Name이라 부른다.

1. PC 브라우저에서 www.naver.com을 입력한다. 그러면 PC는 미리 설정되어 있는 DNS(단말에 설정되어 있는 이 DNS를 Local DNS라 부른다.)에게 www.naver.com이라는 hostname에 대한 IP 주소를 물어본다.
2. Local DNS에서는 www.naver.com에 대한 IP 주소가 있을 수도 없을 수도 있다. 만약 있다면 Local DNS가 바로 PC에 IP 주소를 주고 끝날 것이다. 본 설명에서는 Local DNS에 www.naver.com에 대한 IP 주소가 없다고 가정한다.
3. Local DNS는 이제 [www.naver.com](http://www.naver.com)에 대한 IP 주소를 찾아내기 위해 다른 DNS 서버들과 통신(DNS 메시지)을 시작한다. 먼저 Root DNS 서버에게 www.naver.com에 대한 IP 주소를 물어본다. 이를 위해 각 Local DNS 서버에는 Root DNS 서버의 정보(IP 주소)가 미리 설정되어 있어야 한다.
4. Root DNS 서버는 전세계에 13대가 구축되어 있다(2011년 기준, 현재는 달라졌을 수 있음). 미국에 10대, 일본/네덜란드/노르웨이에 각 1대씩. 우리나라의 경우 Root DNS 서버가 존재하지 않지만 Root DNS 서버에 대한 미러 서버를 3대 운용하고 있다고 한다.
5. Root DNS 서버는 www.naver.com의 IP 주소를 모른다. 그래서 Local DNS 서버에게 "난 www.naver.com에 대한 IP 주소를 몰라. 나 말고 내가 알려주는 다른 DNS 서버에게 물어봐" 라고 응답한다.
6. 이 다른 DNS 서버는 com 도메인을 관리하는 DNS 서버이다.
7. 이제 Local DNS 서버는 com 도메인을 관리하는 DNS 서버에게 www.naver.com의 IP 주소를 물어본다.
8. 역시 com 도메인을 관리하는 DNS 서버에도 해당 정보가 없다. 그래서 DNS 서버는 Local DNS 서버에게 "난 [www.naver.com](http://www.naver.com)에 대한 IP 주소를 몰라. 나 말고 내가 알려주는 다른 DNS 서버에게 물어봐"라고 응답한다. 이 다른 DNS 서버는 www.naver.com 도메인을 관리하는 DNS 서버이다.
9. 이제 Local DNS 서버는 [naver.com](http://naver.com) 도메인을 관리하는 DNS 서버에게 다시 [www.naver.com](http://www.naver.com) 도메인의 IP 주소를 물어본다.
10. [naver.com](http://naver.com) 도메인을 관리하는 DNS 서버에는 [www.naver.com](http://www.naver.com) 호스트네임에 대한 IP 주소가 있다. 그래서 Local DNS 서버에게 www.naver.com에 대한 IP 주소 222.122.195.6 을 응답 해 준다.
11. 이를 수신한 Local DNS는 www.naver.com에 대한 IP 주소를 캐싱하고(이후 다른 요청이 들어오면 바로 응답 해 줄 수 있도록) 그 IP 주소 정보를 단말(PC)에 전달해준다.

Local DNS 서버는 `Recursive query`를 하고 있고

Root, com, [naver.com](http://naver.com) DNS는 `Iterative query`를 하고 있다.

## References

- [https://aws.amazon.com/ko/route53/what-is-dns/](https://aws.amazon.com/ko/route53/what-is-dns/)
- [https://www.netmanias.com/ko/post/blog/5353/dns/dns-basic-operation](https://www.netmanias.com/ko/post/blog/5353/dns/dns-basic-operation)

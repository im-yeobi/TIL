# .pem 파일을 이용한 EC2 인스턴스 SSH 접속

## .pem 파일 권한 문제로 인한 접속 오류
```
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@         WARNING: UNPROTECTED PRIVATE KEY FILE!          @
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
Permissions 0644 for './ec2-key.pem' are too open.
It is required that your private key files are NOT accessible by others.
This private key will be ignored.
Load key "./ec2-key.pem": bad permissions
```

.pem 파일의 권한 문제로 인해 접속 오류가 발생한다. private key 파일인 .pem은 다른 사용자의 접근을 허용해서는 안된다는 경고 메시지를 띄운다.

```
// user에게만 읽기, 쓰기 권한 부여
$ chmod 600 ec2-key.pem

// 결과
-rw-------@ 1 [user]  staff  1696  1  7 17:24 ec2-key.pem
```

chmod 명령으로 파일의 권한을 변경한 뒤, SSH 재시도를 하면 정상적으로 접속이 된다.
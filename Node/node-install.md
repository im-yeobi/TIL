## Mac에 Node 설치하기


NVM(Node Version Manager) 설치
```shell
$ brew install nvm
```

```shell
Please note that upstream has asked us to make explicit managing nvm via Homebrew is unsupported by them and you should check any problems against the standard nvm install method prior to reporting.

You should create NVM's working directory if it doesn't exist:

mkdir ~/.nvm

Add the following to ~/.zshrc or your desired shell configuration file:

export NVM_DIR="$HOME/.nvm" [ -s "/usr/local/opt/nvm/nvm.sh" ] && . "/usr/local/opt/nvm/nvm.sh" # This loads nvm [ -s "/usr/local/opt/nvm/etc/bash_completion.d/nvm" ] && . "/usr/local/opt/nvm/etc/bash_completion.d/nvm" # This loads nvm bash_completion

You can set $NVM_DIR to any location, but leaving it unchanged from /usr/local/opt/nvm will destroy any nvm-installed Node installations upon upgrade/reinstall.

Type `nvm help` for further information.

Bash completion has been installed to: /usr/local/etc/bash_completion.d
```

```shell
$ mkdir ~/.nvm

# ~/.zshrc 파일에 추가
export NVM_DIR="$HOME/.nvm"
[ -s "/usr/local/opt/nvm/nvm.sh" ] && . "/usr/local/opt/nvm/nvm.sh" # This loads nvm
[ -s "/usr/local/opt/nvm/etc/bash_completion.d/nvm" ] && . "/usr/local/opt/nvm/etc/bash_completion.d/nvm"
```

설치할 수 있는 Node 버전 목록 보기

```shell
$ nvm ls-remote
```

설치된 Node 확인

```shell
$ nvm ls
```

Node 버전 변경

```shell
$ nvm use [version]
```

NPM 버전 확인

```shell
$ npm --version
```

Node 버전 확인

```shell
$ node --version
```
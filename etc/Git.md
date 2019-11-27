## 자주 쓰는 Git 명령어 정리

GUI에 의존하지 않고, CLI에 익숙해지자 !



### Clone

원격 저장소로부터 복제하기

```shell
git clone https://github.com/name/repository
```



### Log

커밋 이력 한 줄씩 보기

```shell
git log --oneline
```

커밋 이력 한 줄씩 그래프 형식으로 보기

```shell
git log --oneline --graph
```

커밋 이력 설명 포함하여 그래프 형식으로 보기

```shell
git log --graph
```



### Status

파일의 상태 확인하기 (untracked, unmodified, modified, staged)

```shell
git status
```



### Diff

staging area에 있는 파일과 unstaged에 있는 파일을 비교하여 변경 부분 보기

```shell
git diff
// staging area에 있는 파일의 변경 부분 보기
// Git v1.6.1부터 --staged 옵션 지원, 이전에는 --cached
git diff --staged
```



### Stage

staging area 추가하기

```shell
git add [파일경로/파일명]
```

add 취소하기

```shell
// 전체 취소
git reset
// 특정 파일 취소
git reset [파일명]
```



### Reset

변경사항은 stage 상태로 유지하고 커밋 되돌리기 (soft)

```shell
git reset --soft [commit id]
```

변경사항 unstage 상태로 유지하고 커밋 되돌리기 (mixed)

--mixed 옵션은 default 설정이다

```shell
git reset [commit id]
```

변경사항와 커밋 모두 되돌리기 (hard)

```shell
git reset --hard [commit id]
```



### Stash

stash 저장

```shell
git stash
// -u 옵션 사용해서 untracked 파일도 함께 저장
git stash -u
// stash 설명 추가
git stash save "설명"
```

stash 목록 보기

```shell
git stash list
```

stash 적용하기

```shell
// 가장 최근의 stash 적용
git stash apply
// 특정 stash 적용
git stash apply [stash 이름]
```

stash 삭제하기

```shell
// 가장 최근의 stash 삭제
git stash drop 
// 특정 stash 삭제
git stash drop [stash 이름]
// 모든 stash 삭제
git stash clear
```



### Branch

branch 목록 보기

```shell
git branch
```

branch 생성 & 체크아웃

```shell
git checkout -b [branch 이름]
```

branch 이름 변경하기

```shell
git branch -m [new branch 이름]
```

로컬 branch 삭제하기

```shell
// --delete 옵션
git branch -d [branch 이름]
// --delete --force 옵션
git branch -D [branch 이름]
```

원격 branch 삭제하기

```shell
git push [remote 이름] --delete [branch 이름]
git push [remote 이름] :[branch 이름]
```



### Fetch 

모든 원격 저장소로 부터 가져오기

```shell
git fetch --all
```



### Remote

원격 저장소 목록 보기

```shell
git remote -v
```



### Merge

특정 branch의 최신 커밋까지 병합하기

```shell
git merge [branch 이름]
```

특정 branch의 지정한 커밋까지 병합하기

```shell
git merge [branch 이름] [commit id]
```

옵션

```shell
--ff : fast-forward 가능한 경우(충돌 없을 때) merge 커밋 없이 병합 => default
--no-ff : no fast-forward. merge 커밋 생성
--no-commit : 자동 병합 커밋을 생성하지 않음. 커밋 메시지 직접 입력
```




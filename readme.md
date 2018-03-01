sparse Checkout 이 가능하도록 설정한다.
git config core.sparseCheckout true

clone 할 로컬 저장소를 만든다.
git init my-proj
cd my-proj

remote 를 추가한다.
git remote add -f origin <REMOTE_URL>

checkout 하기 원하는 파일이나 폴더를 .git/info/sparse-checkout 파일에 기술하면 된다. 폴더일 경우 자동으로 하위 폴더가 포함된다.
echo "script/sys-script" >> .git/info/sparse-checkout
echo "script/user-script/user1" >> .git/info/sparse-checkout

이제 pull 로 원격 저장소에서 파일을 가져오면 sparse-checkout 에 기술한 경로의 파일만 가져온다.
git pull origin master

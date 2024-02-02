## Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и ослы), а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека).
```
echo "dogs, cats, hamsters" > pets
echo "horses, camels, donkeys" > packs
cat pets packs > human_friends
ll
cat human_friends 
mv human_friends big_friends
ll
cat big_friends 
```
## Создать директорию, переместить файл туда.
```
mkdir test
mv big_friends test/
```
## Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория
```
sudo apt-get update
sudo apt-get install mysql-server
```
## Установить и удалить deb-пакет с помощью dpkg
```
sudo dpkg -i google-chrome-stable_current_amd64.deb 
sudo dpkg -r google-chrome-stable 
```
## Выложить историю команд в терминале ubuntu
```
  254  echo "dogs, cats, hamsters" > pets
  255  echo "horses, camels, donkeys" > packs
  256  cat pets packs > human_friends
  257  ll
  258  cat human_friends 
  259  mv human_friends big_friends
  260  ll
  261  cat big_friends 
  262  cd test
  263  mkdir test
  264  mv big_friends test/
  265  cd test/
  266  ll
  267  cd ..
  268  clear
  269  sudo apt get update
  270  sudo apt-get update
  271  sudo apt-get install mysql-server
  272  cd Downloads/
  273  sudo dpkg -i google-chrome-stable_current_amd64.deb 
  274  sudo dpkg -r google-chrome-stable 
  275  history
```

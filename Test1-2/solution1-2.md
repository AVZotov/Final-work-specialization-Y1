## Creating 2 new files and fill them with animals
```
echo "dogs, cats, hamsters" > pet_animals
```
## Review file content
```
cat pet_animals
echo "horses, camels, donkeys" > pack_animals
cat pack_animals
```
## Join 2 files in one file human_friends
```
cat "pet_animals" "pack_animals" > "human_friends"
```
## Review file content
```
cat human_friends
```
## Creating new directory and move file into it directory
```
mkdir testwork
ls
mv "human_friends" testwork/
cd testwork/
ls
```

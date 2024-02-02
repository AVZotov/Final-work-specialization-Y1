## В подключенном MySQL репозитории создать базу данных “Друзья человека”
```
DROP DATABASE IF EXISTS human_friends;
CREATE DATABASE human_friends;
USE human_friends;
```
## Создать таблицы с иерархией из диаграммы в БД
## Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения
```
CREATE TABLE animals(
	id INT AUTO_INCREMENT PRIMARY KEY,
    animal_type VARCHAR(45)
);
INSERT INTO animals (animal_type)
VALUES ('pet_animals'),('pack_animals');

CREATE TABLE pets(
	id INT AUTO_INCREMENT PRIMARY KEY,
    animal_type_id INT DEFAULT 1,
    animal_kind VARCHAR(45) NOT NULL,
    FOREIGN KEY (animal_type_id) REFERENCES animals (id)
    ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO pets (animal_kind)
	VALUES ('dogs'), ('cats'), ('hamsters');
    
CREATE TABLE paks(
	id INT AUTO_INCREMENT PRIMARY KEY,
    animal_type_id INT DEFAULT 2,
    animal_kind VARCHAR(45) NOT NULL,
    FOREIGN KEY (animal_type_id) REFERENCES animals (id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO paks (animal_kind)
	VALUES ('horses'), ('camels'), ('donkeys');
    
CREATE TABLE dogs(
	id INT AUTO_INCREMENT PRIMARY KEY,
    animal_kind_id INT DEFAULT 1,
    dog_name VARCHAR(45),
    commands VARCHAR(200),
    birthday DATE,
	FOREIGN KEY (animal_kind_id) REFERENCES pets (id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO dogs(dog_name, commands, birthday)
	VALUES
		('dog1', 'up, down', '2023-11-09'),
        ('dog2', 'bark, seat', '2022-12-05'),
        ('dog3', 'go back, attack', '2020-07-25');

CREATE TABLE cats(
	id INT AUTO_INCREMENT PRIMARY KEY,
    animal_kind_id INT DEFAULT 2,
    cat_name VARCHAR(45),
    commands VARCHAR(200),
    birthday DATE,
	FOREIGN KEY (animal_kind_id) REFERENCES pets (id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO cats(cat_name, commands, birthday)
	VALUES
		('cat1', 'up, down', '2019-01-23'),
        ('cat2', 'eat, seat', '2022-12-05'),
        ('cat3', 'go back, sleep', '2008-09-11');
        
CREATE TABLE hamsters(
	id INT AUTO_INCREMENT PRIMARY KEY,
    animal_kind_id INT DEFAULT 3,
    hamster_name VARCHAR(45),
    commands VARCHAR(200),
    birthday DATE,
	FOREIGN KEY (animal_kind_id) REFERENCES pets (id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO hamsters(hamster_name, commands, birthday)
	VALUES
		('hamster1', 'up, down', '2020-01-23'),
        ('hamster2', 'eat, seat', '2023-11-11'),
        ('hamster3', 'go back, sleep', '2008-11-15');
        
CREATE TABLE horses(
	id INT AUTO_INCREMENT PRIMARY KEY,
    animal_kind_id INT DEFAULT 1,
    horse_name VARCHAR(45),
    commands VARCHAR(200),
    birthday DATE,
	FOREIGN KEY (animal_kind_id) REFERENCES paks (id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO horses(horse_name, commands, birthday)
	VALUES
		('horse1', 'up, down', '2010-01-23'),
        ('horse2', 'eat, seat', '2020-09-13'),
        ('horse3', 'go back, sleep', '2008-11-15');
        
	CREATE TABLE camels(
	id INT AUTO_INCREMENT PRIMARY KEY,
    animal_kind_id INT DEFAULT 2,
    camel_name VARCHAR(45),
    commands VARCHAR(200),
    birthday DATE,
	FOREIGN KEY (animal_kind_id) REFERENCES paks (id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO camels(camel_name, commands, birthday)
	VALUES
		('camel1', 'up, down', '2020-09-13'),
        ('camel2', 'eat, seat', '2019-03-19'),
        ('camel3', 'go back, sleep', '2016-01-07');
        
CREATE TABLE donkeys(
	id INT AUTO_INCREMENT PRIMARY KEY,
    animal_kind_id INT DEFAULT 3,
    donkey_name VARCHAR(45),
    commands VARCHAR(200),
    birthday DATE,
	FOREIGN KEY (animal_kind_id) REFERENCES paks (id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO donkeys(donkey_name, commands, birthday)
	VALUES
		('donkey1', 'up, down', '2021-03-13'),
        ('donkey2', 'eat, seat', '2010-01-23'),
        ('donkey3', 'go back, sleep', '2016-01-07');
```
## Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
```
SET SQL_SAFE_UPDATES = 0;
DELETE FROM camels;
SET SQL_SAFE_UPDATES = 1;

CREATE TABLE horses_donkeys
	SELECT * FROM horses
    UNION SELECT * FROM donkeys;
```

## Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице
```
DROP TEMPORARY TABLE IF EXISTS all_friends;
CREATE TEMPORARY TABLE all_friends
SELECT id, animal_kind_id, commands, birthday, dog_name AS animal_name FROM dogs
	UNION SELECT id, animal_kind_id, commands, birthday, cat_name AS animal_name FROM cats
	UNION SELECT id, animal_kind_id, commands, birthday, hamster_name AS animal_name FROM hamsters
	UNION SELECT id, animal_kind_id, commands, birthday, horse_name AS animal_name FROM horses
	UNION SELECT id, animal_kind_id, commands, birthday, camel_name AS animal_name FROM camels
	UNION SELECT id, animal_kind_id, commands, birthday, donkey_name AS animal_name FROM donkeys;

CREATE TABLE child_animals
	SELECT id, animal_kind_id, commands, birthday, animal_name, TIMESTAMPDIFF(MONTH, birthday, CURDATE()) 
		AS birthday_months
	FROM all_friends
    WHERE birthday BETWEEN ADDDATE(CURDATE(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
```
## Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.
```
SELECT dogs.dog_name, dogs.commands, dogs.birthday, pets.animal_kind, animals.animal_type
FROM dogs
LEFT JOIN pets ON pets.id = dogs.animal_kind_id
LEFT JOIN animals ON animals.id=pets.animal_type_id
UNION
SELECT cats.cat_name, cats.commands, cats.birthday, pets.animal_kind, animals.animal_type
FROM cats
LEFT JOIN pets ON pets.id = cats.animal_kind_id
LEFT JOIN animals ON animals.id=pets.animal_type_id
UNION
SELECT hamsters.hamster_name, hamsters.commands, hamsters.birthday, pets.animal_kind, animals.animal_type
FROM hamsters
LEFT JOIN pets ON pets.id = hamsters.animal_kind_id
LEFT JOIN animals ON animals.id=pets.animal_type_id
UNION
SELECT horses.horse_name, horses.commands, horses.birthday, paks.animal_kind, animals.animal_type
FROM horses
LEFT JOIN paks ON paks.id = horses.animal_kind_id
LEFT JOIN animals ON animals.id=paks.animal_type_id
UNION
SELECT camels.camel_name, camels.commands, camels.birthday, paks.animal_kind, animals.animal_type
FROM camels
LEFT JOIN paks ON paks.id = camels.animal_kind_id
LEFT JOIN animals ON animals.id=paks.animal_type_id
UNION
SELECT donkeys.donkey_name, donkeys.commands, donkeys.birthday, paks.animal_kind, animals.animal_type
FROM donkeys
LEFT JOIN paks ON paks.id = donkeys.animal_kind_id
LEFT JOIN animals ON animals.id=paks.animal_type_id;
```

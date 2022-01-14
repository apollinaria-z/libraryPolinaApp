INSERT INTO libuser (firstname,surname,middlename,
                    passportid,dateOfBirth,address,email)
             VALUES('Joe','Black','Sergeevich','MP1234567','1985-01-12',
                    'Minsk, Nezavisimosti 10-2-3', 'joe@gmail.com'),
                   ('Tom','Petrovich','White','MP3334455','1988-11-06',
                    'Brest, Socetskaya 1-45', 'tom@gmail.com'),
                   ('Jerry','Konstantinovich','Green','MP1119900','1990-05-23',
                    'Minsk, Kosmonavtov 35-52', 'jerry.green@gmail.com'),
                   ('Peter','Maksimovich','Brown','MP4536819','1980-07-09',
                    'Grodno, Centralnaya 26-34', 'brown@gmail.com');

INSERT INTO author (firstname, surname, imagePath)
VALUES ('Adatha', 'Christie','/users/somepath'),
        ('Joanne', 'Rowling', '/users/anotherpath');

INSERT INTO book (nameRu,nameOrigin, cost, imagePath,dayPrice,
                    publicationYear, pages)
VALUES ('Гарри Поттер', 'Harry Potter', 45,'/sdfdsf/sdas',
        2,'2001', 300),
       ('Шерлок Холмс', 'Sherlock Holmes', 61,'/sdfdsf/sdas',
        1,'1934', 330);

INSERT INTO genre (name) values ('ROMANCE'),('ADVENTURE'),('CLASSIC');

insert into book_genre (book_id,genre_id) values(1,2),
(1,4), (1,5),(2,1);

SELECT b.id, b.nameRu, b.nameOrigin,b.cost, b.imagePath,
       b.dayPrice, b.publicationYear, b.pages
FROM ((book AS b LEFT JOIN book_author as ba ON ba.book_id=b.id)
    LEFT JOIN author as a ON ba.author_id=a.id)
WHERE (b.nameRu = 'Гарри Поттер' OR b.nameOrigin = 'Harry Potter') AND a.surname='Rowling';

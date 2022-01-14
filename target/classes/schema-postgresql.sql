CREATE TABLE IF NOT EXISTS libuser
(
    id          SERIAL PRIMARY KEY,
    firstname   VARCHAR(50) NOT NULL,
    surname     VARCHAR(50) NOT NULL,
    middlename  VARCHAR(50) NOT NULL,
    passportid  VARCHAR(50) NOT NULL UNIQUE,
    dateOfBirth DATE        NOT NULL,
    address     VARCHAR(50) NOT NULL,
    email       varchar(50) not null
);
CREATE TABLE IF NOT EXISTS author
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    surname   VARCHAR(50) NOT NULL,
    imagePath VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS book
(
    id              SERIAL PRIMARY KEY,
    nameRu          VARCHAR(50) NOT NULL,
    nameOrigin      VARCHAR(50) NOT NULL,
    cost            INTEGER     NOT NULL,
    dayPrice        INTEGER     NOT NULL,
    publicationYear INTEGER     NOT NULL,
    pages           INTEGER     NOT NULL
);
CREATE TABLE IF NOT EXISTS book_image
(
    book_id   INTEGER references book (id) on update cascade on delete cascade,
    imagePath VARCHAR(50) not null
);


CREATE TABLE IF NOT EXISTS genre
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS book_genre
(
    genre_id INTEGER references genre (id) on update cascade on delete cascade,
    book_id  INTEGER references book (id) on update cascade on delete cascade
);

CREATE TABLE IF NOT EXISTS bookState
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    imagePath  VARCHAR(50),
    damageCost INTEGER     NOT NULL
);

CREATE TABLE IF NOT EXISTS bookItem
(
    id               SERIAL PRIMARY KEY,
    book_id          INTEGER references book (id) on update cascade on delete cascade,
    registrationDate DATE NOT NULL
);
CREATE TABLE IF NOT EXISTS bookItem_bookState
(
    bookItem_id  INTEGER references bookItem (id) on update cascade on delete cascade,
    bookState_id INTEGER references bookState (id) on update cascade on delete cascade
);

CREATE TABLE IF NOT EXISTS book_author
(
    book_id   INTEGER references book (id) on update cascade on delete cascade,
    author_id INTEGER references author (id) on update cascade on delete cascade
);

CREATE TABLE IF NOT EXISTS liborder
(
    id          SERIAL PRIMARY KEY,
    bookItem_id INTEGER references bookItem (id) on update cascade on delete cascade,
    dateIn      DATE    NOT NULL,
    dateOut     DATE    NOT NULL,
    preCost     INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS bill
(
    id        SERIAL PRIMARY KEY,
    order_id  INTEGER references liborder (id) on update cascade on delete cascade,
    date      DATE    NOT NULL,
    totalCost INTEGER NOT NULL,
    isPaid    BOOLEAN
);

CREATE TABLE IF NOT EXISTS libuser_order_bill
(
    libuser_id INTEGER references libuser (id) on update cascade on delete cascade,
    order_id     INTEGER references liborder (id) on update cascade on delete cascade,
    bill_id      INTEGER references bill (id) on update cascade on delete cascade
);





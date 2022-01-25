CREATE TABLE book
(
    id  SERIAL PRIMARY KEY NOT NULL,
    russianname character varying(100) NOT NULL,
    originalname character varying(100),
    count integer NOT NULL,
    registrationdate date NOT NULL,
    countpages smallint,
    year date
)
CREATE TABLE bookcopy
(
    id  SERIAL PRIMARY KEY NOT NULL,
    bookid integer NOT NULL,
    price numeric NOT NULL,
    isavailable boolean,
    priceforday numeric NOT NULL,
    isdamaged boolean DEFAULT false,
    rating numeric,
    CONSTRAINT bookcopy_bookid_fkey FOREIGN KEY (bookid)
        REFERENCES public.book (id)

)
CREATE TABLE authors
(
    id  SERIAL PRIMARY KEY NOT NULL,
    authorname character varying(100) NOT NULL,
    path character varying(200),
    CONSTRAINT authorname UNIQUE (authorname)
)
CREATE TABLE author_book
(
    book_id integer NOT NULL,
    author_id integer NOT NULL,
    CONSTRAINT "FK_author_id" FOREIGN KEY (author_id)
        REFERENCES authors (id),
    CONSTRAINT "FK_book_id" FOREIGN KEY (book_id)
        REFERENCES book (id)
)

CREATE TABLE bookpicture
(
    id  SERIAL PRIMARY KEY NOT NULL,
    bookpicturepath character varying(400) NOT NULL,
    book_id integer NOT NULL ,
    CONSTRAINT "imageFK" FOREIGN KEY (book_id)
        REFERENCES public.book (id)
)

CREATE TABLE damaged_book
(
    book_id integer NOT NULL ,
    damaged_id integer NOT NULL
)
CREATE TABLE damagedbook_photo
(
    id  SERIAL PRIMARY KEY NOT NULL,
    path character varying(50) ,
    CONSTRAINT damagedbook_photo_path_key UNIQUE (path)
)
CREATE TABLE genres
(
    id SERIAL PRIMARY KEY NOT NULL,
    genrename character varying
)
CREATE TABLE genre_book
(
    book_id integer NOT NULL,
    genre_id integer NOT NULL,
    CONSTRAINT "FK_book_id" FOREIGN KEY (book_id)
        REFERENCES book (id),
    CONSTRAINT "FK_genre_id" FOREIGN KEY (genre_id)
        REFERENCES genres (id)
)
CREATE TABLE reader
(
    id SERIAL PRIMARY KEY NOT NULL,
    firstname character varying(100)  NOT NULL ,
    lastname character varying(100)  NOT NULL,
    passportnumber character varying(60)  NOT NULL,
    address character varying(100),
    email character varying(60)  NOT NULL,
    date date  NOT NULL,
    patronymic character varying,
    photopath character varying,
    CONSTRAINT reader_email_key UNIQUE (email),
    CONSTRAINT reader_passportnumber_key UNIQUE (passportnumber)
)

CREATE TABLE orders
(
    id  SERIAL PRIMARY KEY NOT NULL,
    readerid integer NOT NULL,
    fine numeric,
    date date NOT NULL,
    price numeric NOT NULL,
    copy_id integer NOT NULL,
    CONSTRAINT orders_readerid_fkey FOREIGN KEY (readerid)
        REFERENCES reader (id)
)

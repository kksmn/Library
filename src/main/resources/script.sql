CREATE TABLE book
(
    id integer NOT NULL ,
    russianname character varying(100) NOT NULL,
    originalname character varying(100),
    count integer NOT NULL,
    registrationdate date NOT NULL,
    countpages smallint,
    year date,
    CONSTRAINT book_pkey PRIMARY KEY (id)
)
CREATE TABLE bookcopy
(
    id integer NOT NULL,
    bookid integer NOT NULL,
    price numeric NOT NULL,
    isavailable boolean,
    priceforday numeric NOT NULL,
    isdamaged boolean DEFAULT false,
    rating numeric,
    CONSTRAINT bookcopy_pkey PRIMARY KEY (id),
    CONSTRAINT bookcopy_bookid_fkey FOREIGN KEY (bookid)
        REFERENCES public.book (id)
)
CREATE TABLE authors
(
    id integer NOT NULL,
    authorname character varying(100),
    path character varying(200),
    CONSTRAINT authors_pkey PRIMARY KEY (id),
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
    id integer NOT NULL,
    bookpicturepath character varying(400) NOT NULL,
    book_id integer NOT NULL ,
    CONSTRAINT bookpicture_pkey PRIMARY KEY (id),
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
    id integer NOT NULL,
    path character varying(50) ,
    CONSTRAINT damagedbook_photo_pkey PRIMARY KEY (id),
    CONSTRAINT damagedbook_photo_path_key UNIQUE (path)
)
CREATE TABLE genres
(
    id integer NOT NULL,
    genrename character varying NOT NULL,
    CONSTRAINT genres_pkey PRIMARY KEY (id)
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
    id integer NOT NULL,
    firstname character varying(100)  NOT NULL ,
    lastname character varying(100)  NOT NULL,
    passportnumber character varying(60)  NOT NULL,
    address character varying(100),
    email character varying(60)  NOT NULL,
    date date  NOT NULL,
    patronymic character varying,
    photopath character varying,
    CONSTRAINT reader_pkey PRIMARY KEY (id),
    CONSTRAINT reader_email_key UNIQUE (email),
    CONSTRAINT reader_passportnumber_key UNIQUE (passportnumber)
)

CREATE TABLE orders
(
    id integer NOT NULL,
    readerid integer NOT NULL,
    fine numeric,
    date date NOT NULL,
    price numeric NOT NULL,
    copy_id integer NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT orders_readerid_fkey FOREIGN KEY (readerid)
        REFERENCES reader (id)
)

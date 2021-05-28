/*
 * MIT License
 *
 * Copyright (c) 2020-2021 Rostyslav Koval
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

create table if not exists author
(
    author_id bigserial not null
        constraint author_pkey
            primary key,
    name varchar(255) not null
);

alter table author owner to bhmxtxhwdecvdi;

create table if not exists book_file
(
    book_id bigint not null
        constraint book_file_pkey
            primary key,
    file bytea
);

alter table book_file owner to bhmxtxhwdecvdi;

create table if not exists book_photo
(
    book_id bigint not null
        constraint book_photo_pkey
            primary key,
    file bytea
);

alter table book_photo owner to bhmxtxhwdecvdi;

create table if not exists book
(
    book_id bigserial not null
        constraint book_pkey
            primary key,
    file_id bigint
        constraint fk6sem5nj8s5ct4h0onvr7c5lh4
            references book_file,
    photo_id bigint
        constraint fkn32edtat1i56il76733um7wac
            references book_photo,
    status boolean,
    text varchar(4000) not null,
    title varchar(255) not null
);

alter table book owner to bhmxtxhwdecvdi;

create table if not exists book_author
(
    book_id bigint not null
        constraint fkhwgu59n9o80xv75plf9ggj7xn
            references book,
    author_id bigint not null
        constraint fkbjqhp85wjv8vpr0beygh6jsgo
            references author,
    constraint book_author_pkey
        primary key (book_id, author_id)
);

alter table book_author owner to bhmxtxhwdecvdi;

create table if not exists genre
(
    genre_id bigserial not null
        constraint genre_pkey
            primary key,
    description varchar(255) not null
);

alter table genre owner to bhmxtxhwdecvdi;

create table if not exists book_genre
(
    book_id bigint not null
        constraint fk52evq6pdc5ypanf41bij5u218
            references book,
    genre_id bigint not null
        constraint fk8l6ops8exmjrlr89hmfow4mmo
            references genre,
    constraint book_genre_pkey
        primary key (book_id, genre_id)
);

alter table book_genre owner to bhmxtxhwdecvdi;

create table if not exists recover_code
(
    recover_code_id bigserial not null
        constraint recover_code_pkey
            primary key,
    code varchar(255) not null,
    date timestamp,
    email varchar(255) not null
        constraint uk_pxp346acf1rearlgebb065ec3
            unique
);

alter table recover_code owner to bhmxtxhwdecvdi;

create table if not exists usr
(
    user_id bigserial not null
        constraint usr_pkey
            primary key,
    activated boolean,
    email varchar(255) not null
        constraint uk_g9l96r670qkidthshajdtxrqf
            unique,
    name varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null,
    verified boolean
);

alter table usr owner to bhmxtxhwdecvdi;

create table if not exists announcement
(
    announcement_id bigserial not null
        constraint announcement_pkey
            primary key,
    date timestamp,
    description varchar(255) not null,
    status boolean,
    admin_id bigint
        constraint fk7kq8upmu2jlbsmblolo5b96m8
            references usr,
    book_id bigint
        constraint fklwaudblxtdtnha891fqingncr
            references book,
    owner_id bigint
        constraint fkirrufhlynuy03figgwnyqywly
            references usr
);

alter table announcement owner to bhmxtxhwdecvdi;

create table if not exists review
(
    review_id bigserial not null
        constraint review_pkey
            primary key,
    date timestamp not null,
    grade integer
        constraint review_grade_check
            check (grade <= 10),
    status boolean,
    text varchar(255) not null,
    admin_id bigint
        constraint fkjalxvx3xwbbls9ma2wys9stug
            references usr,
    book_id bigint
        constraint fk70yrt09r4r54tcgkrwbeqenbs
            references book,
    user_id bigint
        constraint fkct5y8fsjg2mn894wyale8ihqf
            references usr
);

alter table review owner to bhmxtxhwdecvdi;

create table if not exists usr_book
(
    usr_book_id bigserial not null
        constraint usr_book_pkey
            primary key,
    is_favourite boolean,
    is_read boolean,
    book_id bigint
        constraint fk56s5aveqenc6vab82ggvnn9ow
            references book,
    user_id bigint
        constraint fk6h0fhbuw7q5gdhipnwv0xslbs
            references usr
);

alter table usr_book owner to bhmxtxhwdecvdi;

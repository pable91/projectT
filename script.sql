create table USER
(
    ID      BIGINT auto_increment
        primary key,
    POINT   INTEGER not null,
    PW      VARCHAR(255),
    USER_ID VARCHAR(255)
);

create table ARTICLE
(
    ARTICLE_ID BIGINT not null
        primary key,
    CONTENTS   VARCHAR(255),
    TITLE      VARCHAR(255),
    ID         BIGINT,
    constraint FKHI1N2W9E88N97FSBUIA60NDU1
        foreign key (ID) references USER (ID)
);

create table COMMENT
(
    COMMENT_ID BIGINT not null
        primary key,
    CONTENTS   VARCHAR(255),
    ARTICLE_ID BIGINT,
    constraint FK5YX0UPHGJC6IK6HB82KKW501Y
        foreign key (ARTICLE_ID) references ARTICLE (ARTICLE_ID)
);



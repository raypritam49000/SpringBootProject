CREATE TABLE student
(
    student_id          int not null AUTO_INCREMENT,
    first_name          varchar(50) not null,
    last_name           varchar(50) not null,
    email               varchar(50) not null,
    course              varchar(50) not null,
    registration_number varchar(50)  not null,
    primary key (student_id),
    unique key UK_email(email)
)

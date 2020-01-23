create table course (id varchar(255) not null, description varchar(255), name varchar(255), topic_id varchar(255), primary key (id))
create table topic (id varchar(255) not null, description varchar(255), name varchar(255), primary key (id))
alter table course add constraint FKokaxyfpv8p583w8yspapfb2ar foreign key (topic_id) references topic
create table course (id varchar(255) not null, description varchar(255), name varchar(255), topic_id varchar(255), primary key (id))
create table topic (id varchar(255) not null, description varchar(255), name varchar(255), primary key (id))
alter table course add constraint FKokaxyfpv8p583w8yspapfb2ar foreign key (topic_id) references topic

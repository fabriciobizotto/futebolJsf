create database futebol;
use futebol;

create table times (
	id bigint not null primary key auto_increment,
    nome varchar(255) not null,
    tecnico varchar(255) not null,
    estadio varchar(255) not null,
    escudo text not null
);
insert into times (nome,tecnico,estadio,escudo) values ('Santos','Dorival Junior', 'Vila Belmiro', 'https://s.glbimg.com/es/sde/f/equipes/2013/12/16/santos_45x45.png');

create table jogadores (
	id bigint not null primary key auto_increment,
    nome varchar(255) not null,
    posicao varchar(45) not null,
    idade int null,
    avatar text,
    time_id bigint not null references times(id)
);

create table jogos (
	id bigint not null primary key auto_increment,
    time_casa bigint not null references times(id),
    time_fora bigint not null references times(id),
    data_partida date not null
);

create table placar (
	id bigint not null primary key auto_increment,
    jogo_id bigint not null references jogos(id),
    placar_casa int null,
    placar_fora int null
);
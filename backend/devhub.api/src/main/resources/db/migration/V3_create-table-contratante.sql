create table contratantes (
    id bigint primary key auto_increment,
    nome varchar(75) not null,
    cnpj varchar(14) not null unique,
    telefone varchar(11) not null unique,
    email varchar(100) not null unique,
    contratacoes int not null default 0,
    ativo tinyint
);
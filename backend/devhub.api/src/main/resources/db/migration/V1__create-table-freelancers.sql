create table freelancers (
    id_freelancer bigint primary key auto_increment,
    nome varchar(75) not null,
    cpf varchar(14) not null unique,
    telefone varchar(11) not null unique,
    email varchar(100) not null unique,
    contratacoes int not null default 0,
    funcao varchar(100) not null,
    valor_hora decimal(7,2) not null,
    descricao text not null
);
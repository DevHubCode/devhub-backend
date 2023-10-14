create table especialidades (

    id_especialidade bigint primary key auto_increment,
    fk_freelancer bigint not null,
    descricao varchar(255) not null,
    tempo_exp varchar(45) not null
);
create table publicacao(
  id bigint primary key auto_increment,
  titulo varchar(255) not null ,
  descricao varchar(255) not null,
  role varchar(11) not null,
  id_usuario bigint,
  created_at datetime not null
);
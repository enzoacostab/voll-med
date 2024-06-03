alter table medicos add activo bit;
update medicos set activo = 1;
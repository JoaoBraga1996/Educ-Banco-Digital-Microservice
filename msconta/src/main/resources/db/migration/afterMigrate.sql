-- Inserts para a tabela 'conta'
insert into conta (agencia, cliente_id, criado_em, numero, saldo, taxa_manutecao, tipo_conta) 
values ('00001', 1, current_timestamp, '123456', 1000.00, 5.00, 'corrente');

insert into conta (agencia, cliente_id, criado_em, numero, saldo, taxa_rendimento, tipo_conta) 
values ('00003', 1, current_timestamp, '135792', 5000.00, 0.005, 'poupanca');

insert into conta 
(agencia, cliente_id, criado_em, numero, saldo, taxa_manutecao, tipo_conta) 
values 
('00123', 2, current_timestamp, '98765432-1', 5000.00, 10.00, 'corrente');


-- Inserts para a tabela 'cartao'
insert into cartao (conta_id, numero, senha, status, limite_diario, limite_diario_utilizado, tipo_cartao) 
values (1, '1234 5678 9012 3456', 'senha123', 1, 200.00, 50.00, 'debito');

insert into cartao (conta_id, numero, senha, status, limite_diario, limite_diario_utilizado, limite_credito, limite_utilizado, tipo_cartao) 
values (2, '9876 5432 1098 7654', 'senha456', 1, 1000.00, 1000.00, 1000.00, 200.00, 'credito');




delete from OPERATION;
delete from ACCOUNT;
delete from CLIENT;

insert into CLIENT (id, first_name, last_name,email) values (1, 'admin', 'admin', 'admin@sg.com');
insert into ACCOUNT (id, account_number, balance, client_id) values (1, 2500, 500, 1);
insert into OPERATION (operation_amount, operation_type, operation_date, account_id) values (300, 'DEPOSIT', CURRENT_TIMESTAMP, 1);
insert into OPERATION (operation_amount, operation_type, operation_date, account_id) values (200, 'DEPOSIT', CURRENT_TIMESTAMP ,1);


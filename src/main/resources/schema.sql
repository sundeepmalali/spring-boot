drop table stock;

create table stock (
    stock_id int,
    company_name varchar(50),
    symbol varchar(10),
    price decimal(10,2)
);

insert into stock values(1, 'VMware', 'VMW', 23.01);
insert into stock values(2, 'Facebook', 'FB', 118.01);
insert into stock values(3, 'Microsoft', 'MSFT', 223.85);
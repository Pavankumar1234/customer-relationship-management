create table crm_admin.customer(
	crmid serial primary key,
	firstname varchar(255),
	lastname varchar(255),
	email varchar(255)
);

insert into crm_admin.customer(firstname, lastname, email) values('Pavan Kumar','Lekkala','lekkala.pavan19@gmail.com');

select * from crm_admin.customer;
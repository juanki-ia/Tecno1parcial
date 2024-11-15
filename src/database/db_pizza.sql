create table users(
	id serial primary key,
	name varchar(50) not null,
	email varchar(50) not null,
	password varchar(50) not null,
	phone int not null,
	address varchar(50) not null, 
	role varchar(50) not null
);

create table states(
	id serial primary key,
	name varchar(50) not null
);

create table categories(
	id serial primary key,
	name varchar(50) not null
);

create table payment_methods(
	id serial primary key,
	name varchar(50) not null
);

create table sizes(
	id serial primary key,
	name varchar(50) not null
);

create table pizzas(
	id serial primary key,
	name varchar(50) not null,
	price decimal(10,2) not null,
	imagen_url varchar(255) not null,
	description varchar(100) not null,
	available boolean not null,
	id_size int references sizes(id),
	id_category int references categories(id)
);

create table orders(
	id serial primary key,
	total decimal(10,2) not null,
	created_at date not null,
	update_at date not null,
	id_payment_method int references payment_methods(id),
	id_user int references users(id),
	id_state int references states(id)
);

create table orders_details(
	amount int not null,
	subtotal decimal(10,2) not null,
	id_order int references orders(id),
	id_pizza int references pizzas(id),
	primary key (id_order,id_pizza)
);

select * from orders_details;
select * from orders;
select * from sizes;
select * from payment_methods;
select * from pizzas;
select * from categories;
select * from states;
select * from users;

-- INSERT INTO users(name, email, password, phone, address, role)
-- VALUES ('gino baptista', 'ginobaptista@gmail.com', '123456', '75338090', 'Av litoral', 'cliente');

-- INSERT INTO users(name, email, password, phone, address, role)
-- VALUES ('gino baptista', 'ginobaptista@gmail.com', '123456', '75338090', 'Av litoral', 'cliente');

insert into pizzas (name, price, imagen_url, description, available, id_size, id_category)
values ('peperoni', 12, 'url', 'con 4 porciones', true, 1, 4);

insert into categories(name) values('pizzas familiares');
insert into sizes(name) values('Mediana');
insert into payment_methods(name) values('Pay Pal');

insert into orders (total,id_payment_method,)

insert into states(name) values('entregado');



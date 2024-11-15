
-- ver todas las tablas:
-- SELECT table_name
-- FROM information_schema.tables
-- WHERE table_schema = 'public'
--   AND table_type = 'BASE TABLE';

-- eliminar todas las tablas:
-- DO $$ 
-- DECLARE
--     r RECORD;
-- BEGIN
--     FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public') LOOP
--         EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(r.tablename) || ' CASCADE';
--     END LOOP;
-- END $$;

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

select * from users;
select * from states;
select * from categories;
select * from sizes;
select * from payment_methods;
select * from pizzas;
select * from orders;
select * from orders_details;


create table if not exists users
(
    id BIGSERIAL,
    name VARCHAR,
    nickname VARCHAR,
    priority_area VARCHAR,
    password VARCHAR,
    role VARCHAR,
    CONSTRAINT pk_users primary key (id)
);

create table if not exists tokens
(
    id BIGSERIAL,
    access_token VARCHAR,
    logged_out BOOLEAN,
    user_id BIGINT,
    CONSTRAINT pk_tokens primary key (id),
    CONSTRAINT fk_token_user_id  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

create table if not exists sports
(
    id BIGSERIAL,
    name VARCHAR,
    CONSTRAINT pk_sports primary key (id)
);

create table if not exists users_sports
(
    id BIGSERIAL,
    level INT,
    user_id BIGINT,
    sport_id BIGINT,
    CONSTRAINT pk_users_sports primary key (id),
    CONSTRAINT fk_user_id  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_sport_id  FOREIGN KEY (sport_id) REFERENCES sports (id) ON DELETE CASCADE
);

create table if not exists achievements
(
    id BIGSERIAL,
    info VARCHAR,
    user_id BIGINT,
    CONSTRAINT pk_achievements primary key (id),
    CONSTRAINT fk_achievement_user_id  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

create table if not exists playground
(
    id BIGSERIAL,
    town VARCHAR,
    street VARCHAR,
    type VARCHAR,
    longitude REAL,
    latitude REAL,
    playground_state INT,
    current_book_state BOOLEAN,
    file_data bytea,
    CONSTRAINT pk_playground primary key (id)
);

create table if not exists playground_event
(
    id BIGSERIAL,
    start_time TIMESTAMP,
    general_collection BOOLEAN,
    playground_id BIGINT,
    CONSTRAINT pk_playground_event primary key (id),
    CONSTRAINT fk_event_playground_id  FOREIGN KEY (playground_id) REFERENCES playground (id) ON DELETE CASCADE
);

create table if not exists user_event
(
    id BIGSERIAL,
    will_come BOOLEAN,
    creator BOOLEAN,
    user_id BIGINT,
    playground_event_id BIGINT,
    CONSTRAINT pk_user_event primary key (id),
    CONSTRAINT fk_event_user_id  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_playground_event_id  FOREIGN KEY (playground_event_id) REFERENCES playground_event (id) ON DELETE CASCADE
);


-- Добавление спортивных категорий
insert into sports (name) values
                              ('Футбол'),
                              ('Баскетбол'),
                              ('Волейбол'),
                              ('Бег'),
                              ('Настольный теннис'),
                              ('Бокс');



-- Добавление площадок
insert into playground (town, street, type, longitude, latitude, playground_state, current_book_state, file_data) values
                                                                                                                      ('Нижний Новгород', 'Тверская', 'Футбол', 37.6176, 55.7558, 1, true, null),
                                                                                                                      ('Нижний Новгород', 'Невский', 'Теннис', 30.3308, 59.9343, 2, false, null),
                                                                                                                      ('Нижний Новгород', 'Ленина', 'Баскетбол', 60.5949, 56.8389, 1, true, null),
                                                                                                                      ('Нижний Новгород', 'Красный проспект', 'Футбол', 82.9214, 55.0415, 3, true, null),
                                                                                                                      ('Нижний Новгород', 'Баумана', 'Теннис', 49.1221, 55.7887, 2, false, null),
                                                                                                                      ('Нижний Новгород', 'Минина', 'Баскетбол', 45.0021, 56.2965, 1, true, null),
                                                                                                                      ('Нижний Новгород', 'Кировка', 'Хоккей', 61.4368, 55.1605, 4, true, null),
                                                                                                                      ('Нижний Новгород', 'Ленинградская', 'Волейбол', 50.1005, 53.1951, 2, true, null),
                                                                                                                      ('Нижний Новгород', 'Ленина', 'Регби', 73.4025, 54.9892, 1, true, null),
                                                                                                                      ('Нижний Новгород', 'Проспект Октября', 'Бокс', 55.9271, 54.7388, 3, false, null),
                                                                                                                      ('Нижний Новгород', 'Красная', 'Плавание', 38.9792, 45.0350, 2, true, null),
                                                                                                                      ('Нижний Новгород', 'Пролетарская', 'Гольф', 39.1190, 51.6710, 3, false, null),
                                                                                                                      ('Нижний Новгород', 'Большая Садовая', 'Легкая атлетика', 39.7170, 47.2357, 2, true, null);

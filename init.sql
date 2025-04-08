INSERT INTO "user" (login, password, role)
VALUES ('ExampleLogin', '$2a$10$oFaf6DZT6fAjCGGEq4CsCuIvfzur.7CP7fnmHE9YzcR2OMhJh0vJy', 'USER');

INSERT INTO card (user_id, name, description, views, recent_views, invites)
VALUES (1, 'Java-developer', 'some desc', 3, 1, 1);

INSERT INTO block (card_id, name, description)
VALUES (1, 'Experience', 'idk what to print here tho'),
       (1, 'Price', 'lots of'),
       (1, 'Advantages', 'more');

INSERT INTO content (block_id, name, description)
VALUES (1, 'first place of job', 'exp'),
       (1, 'second place u know', 'exp');
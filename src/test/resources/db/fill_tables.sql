INSERT INTO country(name) VALUES ('США');

INSERT INTO user(email, nickname, password, role) VALUES ('darlene.edwards15@example.com', 'Дарлин Эдвардс', '$2a$10$GCKhpgrMeb8Av6ukac2XneKBqbXElGVB2GRhdN7wyqcWKt9MWlxkK', 'ADMIN');
INSERT INTO user(email, nickname, password, role) VALUES ('gabriel.jackson91@example.com', 'Габриэль Джексон', '$2a$10$JwKOGNTLwL3rDJN8Vnr1.OoNrbKMR/g1rrNPoR/mxvOoEtrSQoVpu', 'USER');

INSERT INTO genre(name) VALUES ('драма');
INSERT INTO genre(name) VALUES ('криминал');

INSERT INTO movie(nameRussian, nameNative, yearOfRelease, description, rating, price, picturePath)
VALUES ('Побег из Шоушенка', 'The Shawshank Redemption', '1994', 'Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.',
8.89, 123.45, 'https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg');

INSERT INTO movieGenre(movieId, genreId) VALUES (1, 1);
INSERT INTO movieGenre(movieId, genreId) VALUES (1, 2);

INSERT INTO movieCountry(movieId, countryId) VALUES (1, 1);

INSERT INTO review(movieId, userId, text)
VALUES(1, 1, 'Гениальное кино! Смотришь и думаешь «Так не бывает!», но позже понимаешь, что только так и должно быть. Начинаешь заново осмысливать значение фразы, которую постоянно используешь в своей жизни, «Надежда умирает последней». Ведь если ты не надеешься, то все в твоей жизни гаснет, не остается смысла. Фильм наполнен бесконечным числом правильных афоризмов. Я уверена, что буду пересматривать его сотни раз.');
INSERT INTO review(movieId, userId, text)
VALUES(1, 2, 'Кино это является, безусловно, «со знаком качества». Что же до первого места в рейтинге, то, думаю, здесь имело место быть выставление «десяточек» от большинства зрителей вкупе с раздутыми восторженными откликами кинокритиков. Фильм атмосферный. Он драматичный. И, конечно, заслуживает того, чтобы находиться довольно высоко в мировом кинематографе.');
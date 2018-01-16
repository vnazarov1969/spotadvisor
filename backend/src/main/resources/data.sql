insert into country (id, name)
values
    ('RU','Russia'),
    ('EG', 'Egypt')
;

insert into city (country_id, name, name_short)
values
    ('RU','Saint-Petersburg','Spb'),
    ('EG', 'Dahab','Dhb')
;


insert into spot (city_id, name, name_local, latitude, longitude)
values
    (1,'Sestroretsk, Dubki','Сестроретск, Дубки',60.094171, 29.938379),
    (1,'Sestroretsk, Kurort','Сестроретск, Курорт',60.108433, 29.946107),
    (1,'Duny, TakeOff','Дюны, TakeOff',60.123663, 29.940294),
    (1, 'Solnechnoe','Солнечное',60.144395, 29.925846),
    (1, 'Solnechnoe','Солнечное',60.144395, 29.925846),
    (1, 'Zelenogorsk','Зеленогорск',60.187728, 29.694628),
    (1, 'Lahta','Лахта',59.987605, 30.158903),
    (1, 'Razliv,Saray','Разлив,Сарай',60.076571, 29.980282),
    (1, 'Razliv,Shalash','Разлив,Шалаш',60.076571, 29.980282),
    (1, 'Ladoga,Kokorevo','Ладога,Кокорево',60.053258, 31.082639),
    (1, 'Ladoga,Mayak','Ладога, Маяк',60.118278, 31.086423),
    (1, 'Bolshaya Igora, South50','Большая Ижора, South50,', 59.943693, 29.595574),
    (1, 'Sosnovy Bor','Сосновый Бор', 59.884311, 29.065932),
    (2, 'Light House','Лайт', 28.498866, 34.520065),
    (2, 'Vetratoria Ganet', 'Ветратория Ганет', 28.479131, 34.491913),
    (2, 'Vetratoria Swiss', 'Ветратория Swiss', 28.479489, 34.494865),
    (2, '5 Square', '5 Квадратов', 28.479341, 34.492911),
    (2, 'Winder Land', 'Виндер Ланд', 28.479509, 34.495124)
;

insert into users (id, user_name)
values
('vnazaroff@mail.ru', 'Vladimir Nazarov')
;

insert into stuff_type (name, size_unit )
values
('surfboard', 'l'),
('sail', 'sq.m'),
('kiteboard', 'сm x сm'),
('kite', 'sq.m'),
('ski', 'cm'),
('snowboard', 'cm')
;

insert into stuff (user_id, name, model, model_brand, model_year, model_size, stuff_type_id)
values
('vnazaroff@mail.ru','91','New Wave','Fanatic',2011,'91',1),
('vnazaroff@mail.ru','110','Fire Move', 'RRD',  2010,'110',1),
('vnazaroff@mail.ru','5.4','Pure','Gaastra', null,'5.4',2),
('vnazaroff@mail.ru','4.7','Manic','Gaastra', null,'4.7',2),
('vnazaroff@mail.ru','6.9','Remedy','Gaastra', null,'6.9',2),
('vnazaroff@mail.ru','4.2','','Ezzy', null,'4.2',2)
;

insert into workout (user_id, start_time, end_time, spot_id, fun_rate, notes, type)
values
('vnazaroff@mail.ru', CURRENT_TIMESTAMP - interval '30 hours', CURRENT_TIMESTAMP - interval '28 hours',1,null,'spot 1', 'windsurfing'),
('vnazaroff@mail.ru', CURRENT_TIMESTAMP - interval '28 hours', CURRENT_TIMESTAMP - interval '26 hours',2,2,'spot 2', 'windsurfing'),
('vnazaroff@mail.ru', CURRENT_TIMESTAMP - interval '26 hours', CURRENT_TIMESTAMP - interval '25 hours',1,1,'spot 1', 'windsurfing'),
('vnazaroff@mail.ru', CURRENT_TIMESTAMP - interval '6 hours', CURRENT_TIMESTAMP - interval '4 hours',1,1,'spot 1', 'windsurfing'),
('vnazaroff@mail.ru', CURRENT_TIMESTAMP - interval '4 hours', CURRENT_TIMESTAMP - interval '3 hours',3,3,'spot 3', 'windsurfing'),
('vnazaroff@mail.ru', CURRENT_TIMESTAMP - interval '2 hours', CURRENT_TIMESTAMP - interval '1 hours',1,null,'spot 1', 'windsurfing')
;
INSERT INTO "user" (id, name, email, password, created, last_modified, last_login, token, is_active)
VALUES ('793acf91-6a4e-497a-a34d-28ebeaf65915', 'cesar diaz', 'cesar.diaz.cannobbio@gmail.com', 'ABcd1234',
        '2023-10-06 22:00:00', '2023-10-06 22:00:00',
        '2023-10-06 22:00:00',
        'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjZXNhci5kaWF6LmNhbm5vYmJpb0BnbWFpbC5jb20iLCJpYXQiOjE2OTY5NzQ1NTIsImV4cCI6MTY5Njk3ODE1Mn0.T8SxX3o3LoFhk2IohUP3SjH84RgMJ0WS2j9jZpIx-Sc',
        1);

INSERT INTO phone (city_code, country_code, number, user_id)
VALUES (9, 56, 65319968, '793acf91-6a4e-497a-a34d-28ebeaf65915');
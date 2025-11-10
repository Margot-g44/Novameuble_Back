CREATE TABLE if not exists pictures_of_furnitures (
id BIGINT PRIMARY KEY GENERATED AlWAYS AS IDENTITY,
furniture_id BIGINT NOT NULL references furnitures(id) ON DELETE CASCADE,
url TEXT NOT NULL,
created_at TIMESTAMP DEFAULT Now()
);


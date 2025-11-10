INSERT INTO users (email, password, role, created_at, updated_at)
VALUES
  ('admin1@example.com', 'hashedpassword1', 'admin', '2025-01-01', '2025-01-01'),
  ('seller1@example.com', 'hashedpassword2', 'seller', '2025-01-03', '2025-01-03'),
  ('seller2@example.com', 'hashedpassword3', 'seller', '2025-01-04', '2025-01-04'),
  ('client1@example.com', 'hashedpassword4', 'client', '2025-01-05', '2025-01-05'),
  ('client2@example.com', 'hashedpassword5', 'client', '2025-01-06', '2025-01-06');


INSERT INTO furnitures (seller_id, name, type, material, color, price, status, created_at, updated_at)
VALUES
  ((SELECT id FROM users WHERE email = 'seller1@example.com'), 'Chaise Scandinave', 'chaise', 'bois et tissu', 'gris clair', 89.99, 'valide', '2025-01-10', '2025-01-15'),
  ((SELECT id FROM users WHERE email = 'seller1@example.com'), 'Table Basse Loft', 'table', 'bois recyclé et métal', 'noir et chêne', 149.50, 'valide', '2025-01-12', '2025-01-18'),
  ((SELECT id FROM users WHERE email = 'seller2@example.com'), 'Canapé Vintage', 'canapé', 'velours', 'vert émeraude', 599.00, 'en_attente', '2025-01-14', '2025-01-14'),
  ((SELECT id FROM users WHERE email = 'seller2@example.com'), 'Bibliothèque Moderne', 'bibliothèque', 'chêne massif', 'bois naturel', 299.99, 'valide', '2025-01-15', '2025-01-20'),
  ((SELECT id FROM users WHERE email = 'seller1@example.com'), 'Tabouret de Bar', 'tabouret', 'cuir et métal', 'marron foncé', 79.90, 'vendu', '2025-01-17', '2025-01-25');


INSERT INTO pictures_of_furnitures (furniture_id, url, created_at)
VALUES
  ((SELECT id FROM furnitures WHERE name = 'Chaise Scandinave'), 'https://example.com/photos/chaise_scandinave.jpg', '2025-01-11'),
  ((SELECT id FROM furnitures WHERE name = 'Table Basse Loft'), 'https://example.com/photos/table_basse_loft.jpg', '2025-01-13'),
  ((SELECT id FROM furnitures WHERE name = 'Canapé Vintage'), 'https://example.com/photos/canape_vintage.jpg', '2025-01-15'),
  ((SELECT id FROM furnitures WHERE name = 'Bibliothèque Moderne'), 'https://example.com/photos/bibliotheque_moderne.jpg', '2025-01-16'),
  ((SELECT id FROM furnitures WHERE name = 'Tabouret de Bar'), 'https://example.com/photos/tabouret_de_bar.jpg', '2025-01-18');


INSERT INTO carts (user_id, created_at)
VALUES
  ((SELECT id FROM users WHERE email = 'client1@example.com'), '2025-01-20'),
  ((SELECT id FROM users WHERE email = 'client2@example.com'), '2025-01-21');


INSERT INTO cart_items (cart_id, furniture_id, quantity)
VALUES
  ((SELECT id FROM carts WHERE user_id = (SELECT id FROM users WHERE email='client1@example.com')),
   (SELECT id FROM furnitures WHERE name = 'Chaise Scandinave'), 1),
  ((SELECT id FROM carts WHERE user_id = (SELECT id FROM users WHERE email='client1@example.com')),
   (SELECT id FROM furnitures WHERE name = 'Table Basse Loft'), 2),
  ((SELECT id FROM carts WHERE user_id = (SELECT id FROM users WHERE email='client2@example.com')),
   (SELECT id FROM furnitures WHERE name = 'Canapé Vintage'), 1),
  ((SELECT id FROM carts WHERE user_id = (SELECT id FROM users WHERE email='client2@example.com')),
   (SELECT id FROM furnitures WHERE name = 'Tabouret de Bar'), 3),
  ((SELECT id FROM carts WHERE user_id = (SELECT id FROM users WHERE email='client2@example.com')),
   (SELECT id FROM furnitures WHERE name = 'Bibliothèque Moderne'), 1);


INSERT INTO furniture_requests (furniture_id, seller_id, status, created_at, updated_at)
VALUES
  ((SELECT id FROM furnitures WHERE name = 'Chaise Scandinave'),
   (SELECT id FROM users WHERE email = 'seller1@example.com'), 'en_attente', '2025-01-22', '2025-01-25'),
  ((SELECT id FROM furnitures WHERE name = 'Table Basse Loft'),
   (SELECT id FROM users WHERE email = 'seller1@example.com'), 'valide', '2025-01-23', '2025-01-26'),
  ((SELECT id FROM furnitures WHERE name = 'Canapé Vintage'),
   (SELECT id FROM users WHERE email = 'seller2@example.com'), 'refuse', '2025-01-24', '2025-01-27'),
  ((SELECT id FROM furnitures WHERE name = 'Bibliothèque Moderne'),
   (SELECT id FROM users WHERE email = 'seller2@example.com'), 'en_attente', '2025-01-25', '2025-01-28'),
  ((SELECT id FROM furnitures WHERE name = 'Tabouret de Bar'),
   (SELECT id FROM users WHERE email = 'seller1@example.com'), 'vendu', '2025-01-26', '2025-01-29');


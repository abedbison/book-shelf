insert into shelf (id, max_capacity, current_capacity) values
(1, 3, 3),
(2, 2, 0),
(3, 2, 1);

INSERT INTO book (id, isbn, title, author, status) VALUES
(1, '0-7352-2462-5', 'The Witch Elm', 'Tana French', 'SHELVED'),
(2, '1-6233-6779-4', 'Let It Go', 'Peter Walsh', 'SHELVED'),
(3, '1-4002-0165-9', 'Girl, Wash Your Face', 'Rachel Hollis', 'SHELVED'),
(4, '1-5107-3240-3', 'The Maw', 'Taylor Zajonc', 'SHELVED'),
(5, '0-5992-5849-7', 'Standing in the Shadows', 'Shannon McKenna', 'NOT_SHELVED'),
(6, '0-0605-6251-X', 'The Devil in Winter', 'Lisa Kleypas', 'NOT_SHELVED'),
(7, '0-0623-1269-3', 'Multipliers', 'Liz Wiseman & Greg Mckeown', 'NOT_SHELVED'),
(8, '0-3164-3551-1', 'Winter in Paradise', 'Elin Hilderbrand', 'NOT_SHELVED'),
(9, '0-8041-7206-4', 'China Rich Girlfriend', 'Kevin Kwan', 'NOT_SHELVED'),
(10, '1-4000-6985-8', 'Cathedral of the Wild', 'Boyd Varty', 'NOT_SHELVED');

INSERT INTO shelf_book(shelf_id, book_id) VALUES 
(1, 1),
(1, 2),
(1, 3),
(3, 4);
INSERT INTO public.usr (user_id, activated, email, name, password, role, verified) VALUES (38, true, 'john_doe@ukr.net', 'John', '$2a$10$74845cRuw0bBFnEUUMRKfe7eqAr0xN3Gi6/TRWjFZ6RE5n6c.xkt6', 'MODERATOR', true);
INSERT INTO public.usr (user_id, activated, email, name, password, role, verified) VALUES (3, true, 'peep@gmail.com', 'Peep', '$2a$10$sKWhO93XL.0fLVyklg6WfeMlr9QJblmILuqShECC76N8C70oVG2Y6', 'MODERATOR', true);
INSERT INTO public.usr (user_id, activated, email, name, password, role, verified) VALUES (2, true, 'rostyslav@gmail.com', 'Rostyslav', '$2a$10$74845cRuw0bBFnEUUMRKfe7eqAr0xN3Gi6/TRWjFZ6RE5n6c.xkt6', 'USER', true);

INSERT INTO public.genre (genre_id, description) VALUES (2, 'Fantasy');
INSERT INTO public.genre (genre_id, description) VALUES (3, 'Lyrics');
INSERT INTO public.genre (genre_id, description) VALUES (4, 'Comedy');
INSERT INTO public.genre (genre_id, description) VALUES (5, 'Drama');
INSERT INTO public.genre (genre_id, description) VALUES (6, 'Detective');
INSERT INTO public.genre (genre_id, description) VALUES (7, 'Violance');
INSERT INTO public.genre (genre_id, description) VALUES (8, 'Fairy Tail');
INSERT INTO public.genre (genre_id, description) VALUES (12, 'test');
INSERT INTO public.genre (genre_id, description) VALUES (13, 'Epos');

INSERT INTO public.author (author_id, name) VALUES (3, 'Gabe Newells');
INSERT INTO public.author (author_id, name) VALUES (5, 'Marcel Proust');
INSERT INTO public.author (author_id, name) VALUES (6, 'James Joyce');
INSERT INTO public.author (author_id, name) VALUES (7, 'Miguel de Cervantes');
INSERT INTO public.author (author_id, name) VALUES (8, 'Gabriel Garcia Marquez');
INSERT INTO public.author (author_id, name) VALUES (9, 'F. Scott Fitzgerald');
INSERT INTO public.author (author_id, name) VALUES (10, 'Herman Melville');
INSERT INTO public.author (author_id, name) VALUES (11, 'Leo Tolstoy');
INSERT INTO public.author (author_id, name) VALUES (12, 'William Shakespeare');
INSERT INTO public.author (author_id, name) VALUES (13, 'Homer');

INSERT INTO public.book (book_id, file_id, photo_id, status, text, title) VALUES (23, null, null, true, 'The book is internationally famous for its innovative style and infamous for its controversial subject: the protagonist and unreliable narrator, middle aged Humbert Humbert, becomes obsessed and sexually involved with a twelve-year-old girl named Dolores Haze.', 'Lolita');
INSERT INTO public.book (book_id, file_id, photo_id, status, text, title) VALUES (13, null, null, true, 'Ulysses chronicles the passage of Leopold Bloom through Dublin during an ordinary day, June 16, 1904. The title parallels and alludes to Odysseus (Latinised into Ulysses), the hero of Homer''s Odyssey (e.g., the correspondences between Leopold Bloom and Odysseus, Molly Bloom and Penelope, and Stephen Dedalus and Telemachus). Joyce fans worldwide now celebrate June 16 as Bloomsday.', 'Ulysses ');
INSERT INTO public.book (book_id, file_id, photo_id, status, text, title) VALUES (14, null, null, true, 'Alonso Quixano, a retired country gentleman in his fifties, lives in an unnamed section of La Mancha with his niece and a housekeeper. He has become obsessed with books of chivalry, and believes their every word to be true, despite the fact that many of the events in them are clearly impossible. Quixano eventually appears to other people to have lost his mind from little sleep and food and because of so much reading.', 'Don Quixote ');
INSERT INTO public.book (book_id, file_id, photo_id, status, text, title) VALUES (22, null, null, true, 'Belonging in the immortal company of the great works of literature, Dante Alighieri''s poetic masterpiece, The Divine Comedy, is a moving human drama, an unforgettable visionary journey through the infinite torment of Hell, up the arduous slopes of Purgatory, and on to the glorious realm of Paradise — the sphere of universal harmony and eternal salvation. ', 'The Divine Comedy ');
INSERT INTO public.book (book_id, file_id, photo_id, status, text, title) VALUES (16, null, null, true, 'The novel chronicles an era that Fitzgerald himself dubbed the "Jazz Age". Following the shock and chaos of World War I, American society enjoyed unprecedented levels of prosperity during the "roaring" 1920s as the economy soared. At the same time, Prohibition, the ban on the sale and manufacture of alcohol as mandated by the Eighteenth Amendment, made millionaires out of bootleggers and led to an increase in organized crime, for example the Jewish mafia. Although Fitzgerald, like Nick Carraway in his novel, idolized the riches and glamor of the age, he was uncomfortable with the unrestrained materialism and the lack of morality that went with it, a kind of decadence.', 'The Great Gatsby ');
INSERT INTO public.book (book_id, file_id, photo_id, status, text, title) VALUES (17, null, null, true, 'First published in 1851, Melville''s masterpiece is, in Elizabeth Hardwick''s words, "the greatest novel in American literature." The saga of Captain Ahab and his monomaniacal pursuit of the white whale remains a peerless adventure story but one full of mythic grandeur, poetic majesty, and symbolic power. Filtered through the consciousness of the novel''s narrator, Ishmael, Moby-Dick draws us into a universe full of fascinating characters and stories, from the noble cannibal Queequeg to the natural history of whales, while reaching existential depths that excite debate and contemplation to this day.', 'Moby Dick ');
INSERT INTO public.book (book_id, file_id, photo_id, status, text, title) VALUES (18, null, null, true, 'Epic in scale, War and Peace delineates in graphic detail events leading up to Napoleon''s invasion of Russia, and the impact of the Napoleonic era on Tsarist society, as seen through the eyes of five Russian aristocratic families.', 'War and Peace ');
INSERT INTO public.book (book_id, file_id, photo_id, status, text, title) VALUES (20, null, null, true, 'The Odyssey is one of two major ancient Greek epic poems attributed to Homer. It is, in part, a sequel to the Iliad, the other work traditionally ascribed to Homer. The poem is fundamental to the modern Western canon. Indeed it is the second—the Iliad being the first—extant work of Western literature. It was probably composed near the end of the eighth century BC, somewhere in Ionia, the Greek-speaking coastal region of what is now Turkey. The poem mainly centers on the Greek hero Odysseus (or Ulysses, as he was known in Roman myths) and his long journey home following the fall of Troy. It takes Odysseus ten years to reach Ithaca after the ten-year Trojan War. In his absence, it is assumed he has died, and his wife Penelope and son Telemachus must deal with a group of unruly suitors, the Mnesteres or Proci, competing for Penelope''s hand in marriage. ', 'The Odyssey ');
INSERT INTO public.book (book_id, file_id, photo_id, status, text, title) VALUES (15, null, null, true, 'One of the 20th century''s enduring works, One Hundred Years of Solitude is a widely beloved and acclaimed novel known throughout the world, and the ultimate achievement in a Nobel Prize–winning career. The novel tells the story of the rise and fall of the mythical town of Macondo through the history of the Buendía family. It is a rich and brilliant chronicle of life and death, and the tragicomedy of humankind. In the noble, ridiculous, beautiful, and tawdry story of the Buendía family, one sees all of humanity, just as in the history, myths, growth, and decay of Macondo, one sees all of Latin America. Love and lust, war and revolution, riches and poverty, youth and senility — the variety of life, the endlessness of death, the search for peace and truth — these universal themes dominate the novel. Whether he is describing an affair of passion or the voracity of capitalism and the corruption of government, Gabriel García Márquez always writes with the simplicity, ease, andpurity that are the mark of a master. Alternately reverential and comical, One Hundred Years of Solitude weaves the political, personal, and spiritual to bring a new consciousness to storytelling. Translated into dozens of languages, this stunning work is no less than an accounting of the history of the human race.', 'One Hundred Years of Solitude ');
INSERT INTO public.book (book_id, file_id, photo_id, status, text, title) VALUES (19, null, null, true, 'The Tragedy of Hamlet, Prince of Denmark, or more simply Hamlet, is a tragedy by William Shakespeare, believed to have been written between 1599 and 1601. The play, set in Denmark, recounts how Prince Hamlet exacts revenge on his uncle Claudius, who has murdered Hamlet''s father, the King, and then taken the throne and married Gertrude, Hamlet''s mother. The play vividly charts the course of real and feigned madness—from overwhelming grief to seething rage—and explores themes of treachery, revenge, incest, and moral corruption.', 'Hamlet ');

INSERT INTO public.announcement (announcement_id, date, description, status, admin_id, book_id, owner_id) VALUES (9, '2021-06-04 03:00:00.000000', 'The Divine Comedy analysis with everyone who wants', true, 38, 22, 38);
INSERT INTO public.announcement (announcement_id, date, description, status, admin_id, book_id, owner_id) VALUES (8, '2021-06-19 03:00:00.000000', 'New posters with book topic are coming!', true, 38, 14, 38);
INSERT INTO public.announcement (announcement_id, date, description, status, admin_id, book_id, owner_id) VALUES (7, '2021-06-22 03:00:00.000000', 'Book open discussion', true, 38, 23, 38);

INSERT INTO public.book_author (book_id, author_id) VALUES (10, 5);
INSERT INTO public.book_author (book_id, author_id) VALUES (13, 6);
INSERT INTO public.book_author (book_id, author_id) VALUES (14, 7);
INSERT INTO public.book_author (book_id, author_id) VALUES (15, 8);
INSERT INTO public.book_author (book_id, author_id) VALUES (16, 9);
INSERT INTO public.book_author (book_id, author_id) VALUES (17, 10);
INSERT INTO public.book_author (book_id, author_id) VALUES (18, 11);
INSERT INTO public.book_author (book_id, author_id) VALUES (19, 12);
INSERT INTO public.book_author (book_id, author_id) VALUES (20, 13);
INSERT INTO public.book_author (book_id, author_id) VALUES (21, 14);

INSERT INTO public.book_genre (book_id, genre_id) VALUES (10, 2);
INSERT INTO public.book_genre (book_id, genre_id) VALUES (13, 3);
INSERT INTO public.book_genre (book_id, genre_id) VALUES (14, 4);
INSERT INTO public.book_genre (book_id, genre_id) VALUES (15, 5);
INSERT INTO public.book_genre (book_id, genre_id) VALUES (16, 6);
INSERT INTO public.book_genre (book_id, genre_id) VALUES (17, 2);
INSERT INTO public.book_genre (book_id, genre_id) VALUES (18, 3);
INSERT INTO public.book_genre (book_id, genre_id) VALUES (19, 4);
INSERT INTO public.book_genre (book_id, genre_id) VALUES (21, 6);
INSERT INTO public.book_genre (book_id, genre_id) VALUES (22, 6);

INSERT INTO public.review (review_id, date, grade, status, text, admin_id, book_id, user_id) VALUES (1, '2021-05-06 23:36:22.619000', 2, true, '<p>asdsdd</p>', 38, 10, 38);
INSERT INTO public.review (review_id, date, grade, status, text, admin_id, book_id, user_id) VALUES (2, '2021-05-06 23:37:35.725000', 5, true, '<p>dasdsasadsadasdas</p>', 38, 10, 38);
INSERT INTO public.review (review_id, date, grade, status, text, admin_id, book_id, user_id) VALUES (3, '2021-05-10 22:19:06.449000', 3, false, '<p>asdsadsadasasdasdaa</p>', 38, 10, 38);
INSERT INTO public.review (review_id, date, grade, status, text, admin_id, book_id, user_id) VALUES (4, '2021-05-19 21:23:19.349000', 4, true, '<p>Perfect book!</p>', 2, 23, 2);
INSERT INTO public.review (review_id, date, grade, status, text, admin_id, book_id, user_id) VALUES (5, '2021-05-23 17:57:46.798000', 5, false, '<p>It was interesting to read! Nice book!</p>', 3, 13, 3);

INSERT INTO public.usr_book (usr_book_id, is_favourite, is_read, book_id, user_id) VALUES (13, true, true, 10, 38);
INSERT INTO public.usr_book (usr_book_id, is_favourite, is_read, book_id, user_id) VALUES (14, true, true, 23, 38);
INSERT INTO public.usr_book (usr_book_id, is_favourite, is_read, book_id, user_id) VALUES (15, true, true, 13, 38);
INSERT INTO public.usr_book (usr_book_id, is_favourite, is_read, book_id, user_id) VALUES (17, true, true, 23, 2);
INSERT INTO public.usr_book (usr_book_id, is_favourite, is_read, book_id, user_id) VALUES (19, true, true, 21, 2);
INSERT INTO public.usr_book (usr_book_id, is_favourite, is_read, book_id, user_id) VALUES (20, true, true, 20, 2);
INSERT INTO public.usr_book (usr_book_id, is_favourite, is_read, book_id, user_id) VALUES (21, true, true, 15, 2);

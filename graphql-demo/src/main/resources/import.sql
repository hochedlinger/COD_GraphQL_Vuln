-- Create Users
INSERT INTO bloguser (id, username, password) VALUES (1, 'Michael J', 'pw123');
INSERT INTO bloguser (id, username, password) VALUES (2, 'Tobias Knödlinger', 'pw123');

-- Create BlogPosts
INSERT INTO blogpost (id, author_id, title, content) VALUES (1, 1, 'Cyber Offense and Defense', 'This is a very good course! I learned a lot about cyber security.');
INSERT INTO blogpost (id, author_id, title, content) VALUES (2, 1, 'Java', 'Java is a high-level, class-based, object-oriented programming language that is very fun to use.');
INSERT INTO blogpost (id, author_id, title, content) VALUES (3, 2, 'GraphQL security issues', 'GraphQL is a query language for APIs and a runtime for executing those queries with existing data. It is very powerful, which is why some developers may not be aware of how to secure it properly.');

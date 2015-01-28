CREATE TABLE "users" (
"username" varchar(255) NOT NULL,
"password" varchar(255) DEFAULT NULL,
PRIMARY KEY ("username")
)

CREATE TABLE "groups" (
"username" varchar(255) DEFAULT NULL,
"groupname" varchar(255) DEFAULT NULL,
PRIMARY KEY ("groupname")
)


CREATE INDEX groups_users_FK1 ON "groups"("username" ASC);

INSERT INTO "users" VALUES('bob','password1');
INSERT INTO "users" VALUES('sally','password2');
INSERT INTO "users" VALUES('tom','password3');
INSERT INTO "groups" VALUES('bob','admin');
INSERT INTO "groups" VALUES('sally','user');
INSERT INTO "groups" VALUES('tom','user');

INSERT INTO client(firstname, lastname, pesel) VALUES ('Lol', 'Beka', 98765432123)


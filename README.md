# SocialNetwork-support-mysql-mongoDB
this project support two database.
you can change database in properties.
## requirements
mongoDB:
We also need to add some rows into roles collection before assigning any role to User. Run following MongoDB insert statements:
```bash
db.roles.insertMany([
   { name: "ROLE_USER" },
   { name: "ROLE_MODERATOR" },
   { name: "ROLE_ADMIN" },
])
```
mysql:
```bash
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```
## API EndPoints

# SocialNetwork-support-mysql-mongoDB
this project support two database.
you can change database in properties.
* Mysql
* MongoDB
* Redis session
* jwt
* MapStruct

## Requirements
MongoDB:
We also need to add some rows into roles collection before assigning any role to User. Run following MongoDB insert statements:
```bash
db.roles.insertMany([
   { name: "ROLE_USER" },
   { name: "ROLE_MODERATOR" },
   { name: "ROLE_ADMIN" },
])
```
Mysql:
```bash
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```
## API EndPoints


### Signup
http://localhost:8080/api/auth/signup
```bash
body:
{
"username":"your username",
"email":"your email",
"password":"your password"
}
```
### Signin
http://localhost:8080/api/auth/signin
```bash
body:
{
"username":"your username",
"password":"your password"
}

response:
Authorization security key
```
### Send post
http://localhost:8080/api/test/sendpost
```bash

body:
{
"title":"post title",
"description":"post description"
}

header:
key:Authorization  value:Bearer security key
```
### Send comment
http://localhost:8080/api/test/sendcomment
```bash
body:
{
"postId":"post id",
"text":"comment text"
}

header:
key:Authorization  value:Bearer security key
```
### Following
http://localhost:8080/api/test/following
```bash
body:
{
    "username":"username to follow"
}

header:
key:Authorization  value:Bearer security key
```

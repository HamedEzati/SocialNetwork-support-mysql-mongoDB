# SocialNetwork-support-mysql-mongoDB
This project have multi feature that you can on/off in properties.
Example: you can change database in properties.
* Mysql
* MongoDB
* Neo4j
* Redis session
* Jwt
* Oauth2
* Oauth2(jwt access token)
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

Bearer token:
access token
```
### Send comment
http://localhost:8080/api/test/sendcomment
```bash
body:
{
"postId":"post id",
"text":"comment text"
}

Bearer token:
access token
```
### Following
http://localhost:8080/api/test/following
```bash
body:
{
    "username":"username to follow"
}

Bearer token:
access token
```
## Admin Apis
```bash
Bearer token:
access token
```
### http://localhost:8080/api/test/getusers
Show all users.

### http://localhost:8080/api/test/selectuser
```bash
body:
{
    "username":"username to show details"
}

Bearer token:
access token

response:show username details. 
```

### http://localhost:8080/api/test/getposts
Show all posts.
```bash
Bearer token:
access token
```

### http://localhost:8080/api/test/selectpost
```bash
body:
{
    "id":"id of post to show details"
}

Bearer token:
access token

response:show post details. 
```

### http://localhost:8080/api/test/getfollowings
Get followings report.
```bash
Bearer token:
access token
```

### http://localhost:8080/api/auth/signupbyadmin
Signup user by admin
```bash
body:
{
   "username":"your username",
   "email":"your email",
   "password":"your password"
}Bearer token:
access token
```

### http://localhost:8080/api/test/getfollowingpath
Get shortest following path.
```bash
body:
{
   "firstUsername":"first",
   "secondUsername":"second"
}
Bearer token:
access token
```

### http://localhost:8080/oauth/token
Get token in Oauth(jwt access token) mode.
```bash
x-www-form-urlencoded:

grant_type
username
password

Basic Auth:

Username(client id): web-app
Password(client secret): web-app-secret
```




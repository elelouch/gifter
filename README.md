# Gifter
Wishlist application.
### What can be done with this app?
- Check your friend's wishlist 
- Users can follow/unfollow another user.
- Users can perform creation/deletion operations on their gifts.
- Only followers can check the wishlist.

### Security 
The endpoints were secured via JWT, adding a filter on the FilterChain.
This filter takes the token from the Authorization header and uses the JwtService to encode/decode and
take claims.
There are only two endpoints that are public /login and /register.

### Tech
- Jwt
- Spring Boot 3.3.2
- Angular 17.3.9 (with Material)
- Java 17
- Node 20.17.0

### Not (yet) implemented functionalities
- The jwt secret was not placed outside the program.
- The admin functionalities.
- The users can't remove from their followers list another friend.

### Usage
On the /back folder. 
```console
    foo@bar:~$ ./mvnw clean install -U #might not be necessary
    foo@bar:~$ ./mvnw spring-boot:run
```

On the /front folder.
```console
    foo@bar:~$ npm i 
    foo@bar:~$ ng serve
```

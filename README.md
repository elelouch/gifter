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
- Spring Boot
- Jwt
- Angular(Material)

### Details
- The jwt secret was not placed outside the program
- The admin funcionalities are not implemented yet

### Gifter
Wishlist application.
Users can follow their friends to check which gifts they desire.
These gifts have a name, physical location and a descriptional image.

The endpoints were secured via JWT, adding a filter on the FilterChain.
This filter takes the token from the Authorization header and uses the JwtService to encode/decode and
take claims.


Tech:
    - Spring Boot
    - Jwt
    - Angular(Using material)

Details:
    - The jwt secret was not placed outside the program
    - The admin funcionalities are not implemented yet

## bricklink-api

This module provides the core interfaces for interacting with the Bricklink API.
This module serves as the foundation for other modules that build upon the core functionality.

### Usage of the BlinkAuthSigner
If you need to manually sign requests to the Bricklink API using OAuth 1.0a, you can use the `BlinkAuthSigner` class.
Here is an example of how to use the `BlinkAuthSigner` to sign a request:

```java
   BlinkAuthSigner signer = new BlinkAuthSigner(consumerKey, consumerSecret, tokenValue, tokenSecret);
   BlinkAuthSigner.SignatureBuilder signatureBuilder = signer.signatureBuilder()
     .verb(BlinkAuthSigner.Method.GET)
     .uri("colors/" + id);

   String url = signatureBuilder.buildUrl();
   String authHeader = signatureBuilder.buildAuthorizationHeader();
   // Use url and authHeader in your HTTP request
```
### The Data Transfer Objects (DTOs)
The DTOs used across the Bricklink API client can be found in the following package:
https://github.com/followsclosely/bricklink-api/tree/master/bricklink-api/src/main/java/io/github/followsclosely/bricklink/dto 
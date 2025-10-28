## bricklink-api

This module provides the core interfaces for interacting with the Bricklink API.
This module serves as the foundation for other modules that build upon the core functionality.

The DTOs used across the Bricklink API client can be found in the following package:
https://github.com/followsclosely/bricklink-api/tree/master/bricklink-api/src/main/java/io/github/followsclosely/bricklink/dto

Bricklink uses a different approach to authentication compared to most REST APIs. It employs OAuth 1.0a for secure
access.
To facilitate this, the Bricklink API client includes built-in support for OAuth 1.0a authentication.

See [OAuth 1.0a Authentication Guide](https://www.bricklink.com/v3/api.page#!/Introduction/oauth) for authentication
details.

See [BlinkAuthSigner.java](https://github.com/followsclosely/bricklink-api/blob/master/bricklink-api/src/main/java/io/github/followsclosely/bricklink/oauth/BlinkAuthSigner.java)
for an example of how to set up OAuth 1.0a authentication with the Bricklink API client.

## bricklink-spring-rest-client

This module implements the REST client interfaces defined in the bricklink-api module. It provides Spring-friendly
beans,
configuration, and integration points, making it easy to inject and use the Bricklink RestClients in a Spring
Application.

See [Bricklink API Documentation](https://www.bricklink.com/v3/api.page) for more details on available endpoints and
usage.

## bricklink-catalog-loaders

This module is responsible for loading and processing catalog data from Bricklink. It contains utilities for
importing, transforming, or managing LEGO set/catalog information.

See [Bricklink LEGO Catalog Database Download](https://www.bricklink.com/catalogDownload.asp) for more details on
available data.


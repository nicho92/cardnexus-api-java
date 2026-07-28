# cardnexus-api-java

[![Maven Central](https://img.shields.io/maven-central/v/com.github.nicho92/cardnexus-api-java.svg)](https://central.sonatype.com/artifact/com.github.nicho92/cardnexus-api-java)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-23-orange.svg)](https://openjdk.org/)
[![Build](https://img.shields.io/badge/build-Maven-red.svg)](pom.xml)

`cardnexus-api-java` is a lightweight Java SDK for the [CardNexus](https://www.cardnexus.com/) public API. It provides typed service classes and DTOs for product catalogs, expansions, prices, feeds, inventory, listings, carts, orders, account data, and marketplace operations.

The client is maintained as part of the Java API client family used by the [MtgDesktopCompanion](https://www.mtgcompanion.org) ecosystem. It follows the same conventions as [`mkm-api-java`](https://github.com/nicho92/mkm-api-java), [`cardtrader-api-java`](https://github.com/nicho92/cardtrader-api-java), [`mtgstock-api-java`](https://github.com/nicho92/mtgstock-api-java), and [`manapool-api-java`](https://github.com/nicho92/manapool-api-java).

## Table of contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Authentication](#authentication)
- [Quick start](#quick-start)
- [Available services](#available-services)
- [Configuration](#configuration)
- [Project structure](#project-structure)
- [Logging](#logging)
- [Error handling](#error-handling)
- [Building from source](#building-from-source)
- [Testing](#testing)
- [Related projects](#related-projects)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

## Features

- Typed Java models for CardNexus resources such as products, cards, sealed products, expansions, prices, inventory lines, lists, carts, orders, users, feeds, and jobs.
- Service classes for the main API domains: account, bulk jobs, cart, feeds, inventory, lists, orders, prices, and products.
- Built-in bearer-token authentication using `CARDNEXUS_API_KEY` or an explicitly configured token.
- Paginated API helpers that collect multi-page results for common list/search operations.
- Gson-based JSON serialization/deserialization with polymorphic product support.
- Caffeine-backed in-memory caches for product, expansion, and game lookups.
- Optional URL call listener hook for request/response tracing.

## Requirements

- **Java 23** or newer.
- **Maven 3.6+** to build from source.
- A valid CardNexus API key.

## Installation

### Maven

```xml
<dependency>
    <groupId>com.github.nicho92</groupId>
    <artifactId>cardnexus-api-java</artifactId>
    <version>1.4.15</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.github.nicho92:cardnexus-api-java:1.4.15'
```

The artifact is published to [Maven Central](https://central.sonatype.com/artifact/com.github.nicho92/cardnexus-api-java), so consumers normally do not need an additional repository declaration.

## Authentication

The SDK sends CardNexus credentials as a bearer token. The recommended setup is to expose the token through the `CARDNEXUS_API_KEY` environment variable:

```bash
export CARDNEXUS_API_KEY="your-cardnexus-token"
```

Then load it once before creating service instances:

```java
NexusConfig.loadTokenFromEnv();
```

You can also configure the token manually, for example when credentials come from your own secrets manager:

```java
NexusConfig.setToken(secretToken);
```

## Quick start

```java
import java.io.IOException;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;

public class Example {
    public static void main(String[] args) throws IOException {
        NexusConfig.loadTokenFromEnv();
        NexusConfig.DEFAULT_GAME_VALUE = "mtg";

        var productsService = new ProductsService();

        var request = new SearchProductRequest();
        request.setGame("mtg"); //not necessary if DEFAULT_GAME_VALUE = "mtg"
        request.setName("Liliana of the Veil");
        request.setProductTypes(EnumProductType.card);

        var products = productsService.searchProduct(request);

        products.forEach(product ->
            System.out.println(product.getId() + " - " + product.getName())
        );
    }
}
```

## Available services

| Service | Purpose |
|---|---|
| `AccountService` | Retrieve account, balance, user, payout, and vacation information. |
| `BulkService` | Submit and follow bulk/optimizer jobs. |
| `CartService` | Manage cart entries and checkout-related cart operations. |
| `FeedsService` | Download and process CardNexus feed files. |
| `InventoryService` | Search and manage inventory lines. |
| `ListsServices` | Create, update, and read marketplace/list resources. |
| `OrdersService` | Access purchase and sale order data. |
| `PricesService` | Retrieve price history and market prices. |
| `ProductsService` | List games and expansions, search products, resolve external IDs, and cache catalog data. |

## Configuration

`NexusConfig` centralizes runtime options used by the services:

| Setting | Default | Description |
|---|---:|---|
| `API_BASE_URL` | `https://public-api.cardnexus.com/v1` | Base URL used for API calls. |
| `ENV_TOKEN_KEY` | `CARDNEXUS_API_KEY` | Environment variable read by `loadTokenFromEnv()`. |
| `DIRECTORY_FEED` | user home directory | Directory used when downloading feed files. |
| `GSON_PRETTY_PRINT` | `false` | Enables pretty JSON output in the JSON service. |
| `LIMIT_LIST_RESULTS` | `200` | Default page size used by list/search helpers. |
| `CHECKSUM_MD5_FEED` | `true` | Enables feed checksum validation. |
| `DEFAULT_GAME_VALUE` | `null` | Optional application-level default game identifier, for example `mtg`. |

Create and configure `NexusConfig` before instantiating service classes, because services create their HTTP client during construction.

## Project structure

```text
src/main/java/org/api/cardnexus/adapters        Gson adapters and JSON mapping helpers
src/main/java/org/api/cardnexus/configuration  SDK configuration
src/main/java/org/api/cardnexus/listener       Request listener contracts and call metadata
src/main/java/org/api/cardnexus/model          API DTOs and domain models
src/main/java/org/api/cardnexus/model/enums    API enum values
src/main/java/org/api/cardnexus/model/requests Request builders/DTOs used by service methods
src/main/java/org/api/cardnexus/services       Service classes, one per API domain
src/main/java/org/api/cardnexus/tools          HTTP, JSON, caching, formatting, and file helpers
src/main/resources                            Runtime resources such as Log4j2 configuration
src/test/java/org/cardnexus/tests             Service and operation tests
```

## Logging

The SDK uses **Log4j2**. Add a `log4j2.xml` file on your application classpath to control logging levels and appenders. The Maven jar configuration excludes the repository's `log4j2.xml` from the packaged artifact so consuming applications can provide their own logging configuration.

## Error handling

Service methods throw `IOException` for transport failures, invalid credentials, non-successful HTTP responses, and JSON parsing errors. Non-2xx HTTP responses include the status code and response payload in the exception message when CardNexus returns one.

## Building from source

```bash
git clone https://github.com/nicho92/cardnexus-api-java.git
cd cardnexus-api-java
mvn clean install
```

## Testing

```bash
mvn test
```

Some tests call the live CardNexus API and require `CARDNEXUS_API_KEY` to be set. If you run the complete Maven lifecycle, remember that release-oriented plugins may require additional local configuration such as GPG credentials.

## Related projects

| Project | Marketplace / purpose |
|---|---|
| [mkm-api-java](https://github.com/nicho92/mkm-api-java) | Cardmarket API client |
| [cardtrader-api-java](https://github.com/nicho92/cardtrader-api-java) | CardTrader API client |
| [mtgstock-api-java](https://github.com/nicho92/mtgstock-api-java) | MTGStock API client |
| [manapool-api-java](https://github.com/nicho92/manapool-api-java) | ManaPool API client |
| [MtgDesktopCompanion](https://github.com/nicho92/MtgDesktopCompanion) | MTG collection and deck manager built with these clients |

## Contributing

Contributions are welcome:

1. Fork the repository.
2. Create a feature branch.
3. Make your changes and add tests when possible.
4. Run `mvn test`.
5. Open a pull request describing the change and any compatibility impact.

## License

Distributed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

## Author

**Nicolas Pihen** — [mtgcompanion.org](https://www.mtgcompanion.org)

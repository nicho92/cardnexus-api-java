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
- [Optional Swing GUI helpers](#optional-swing-gui-helpers)
- [Configuration](#configuration)
- [Caching Service](#caching-service)
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
- Optional Swing panels for quickly embedding product search, list management, tag/location management, marketplace listings, product images, and cart optimization workflows in desktop tools.

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
    <version>1.4.35</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.github.nicho92:cardnexus-api-java:1.4.35'
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
        NexusConfig.setDefaultGameValue("mtg");

        var productsService = new ProductsService();

        var request = new SearchProductRequest();
        request.setGame("mtg"); // not necessary if setDefaultGameValue("mtg") was called
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


## Optional Swing Components

The `org.api.cardnexus.gui` package provides ready-to-embed Swing components for desktop applications and small administration tools. These panels are optional convenience UI layers built on top of the same service classes documented above; they still require `NexusConfig` to be initialized before use.

| Component | Purpose |
|---|---|
| `NexusProductPanel` | Search products and optionally display product details, images, and marketplace listings. |
| `NexusListsPanel` | Browse, create, reload, and delete Nexus lists, then inspect list items and product images. |
| `NexusTagsAndLocationPanel` | Manage inventory tags and locations, including creation and deletion. |
| `NexusWizardPanel` | Build a cart optimization request from selected products and follow generated optimization jobs. |
| `ProductPicturePanel` | Display a scaled product image from the product image URL. |
| `MarketPlacePanel` / `MarketVariationPanel` | Inspect marketplace offers and structured market price variations. |

Minimal Swing bootstrap example:

```java
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.gui.NexusProductPanel;

public class GuiExample {
    public static void main(String[] args) throws Exception {
        NexusConfig.loadTokenFromEnv();
        NexusConfig.setDefaultGameValue("mtg");

        var frame = new JFrame("CardNexus products");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new NexusProductPanel(true, true, true));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
```

## Configuration

`NexusConfig` centralizes runtime options used by the services. Configure it through its setter methods before instantiating service classes, because services create their HTTP client during construction:

| Setting / accessor | Default | Description |
|---|---:|---|
| `API_BASE_URL` | `https://public-api.cardnexus.com/v1` | Base URL used for API calls. |
| `API_VERSION` | `0.8.0` | CardNexus API version targeted by the SDK. |
| `ENV_TOKEN_KEY` | `CARDNEXUS_API_KEY` | Environment variable read by `loadTokenFromEnv()`. |
| `REQ_DATE_PATTERN` | `yyyy-MM-dd` | Date format used by request builders. |
| `INVENTORY_CREATION_LIMIT` | `1000` | Maximum inventory lines sent in one creation request. |
| `setToken(...)` / `loadTokenFromEnv()` / `loadTokenFromFile(...)` | `null` | Bearer token used by API calls. |
| `setTempDirectory(...)` | user home directory | Temporary directory used by feed download and file helpers. |
| `setGsonPrettyPrint(...)` | `false` | Enables pretty JSON output in the JSON service. |
| `setLimitListResults(...)` | `200` | Default page size used by list/search helpers. |
| `setChecksumMd5Feed(...)` | `true` | Enables feed checksum validation. |
| `setDefaultGameValue(...)` | `null` | Optional application-level default game identifier, for example `mtg`. |
| `setFeedRententionDurationDays(...)` | `1` | Number of days to retain downloaded feed files. |
| `setListener(...)` | `null` | Optional request listener for tracing URL calls. |
| `setAcceptLanguage(...)` | `en` | use localised product information. Can use thoses values : en, fr, it, es, or de |

## Caching Service

CachingService allows you to store products in cache, and limit calling nexus-api endpoints.

```java
        CachingService.inst().cachingProducts( NexusConfig.getDefaultGameValue());
```
It will download products and prices catalog, and fill thems in cache . It will use the cache when calling getProductById.
New data files will be downloaded when previous files date > NexusConfig.getFeedRententionDurationDays() value.


## Project structure

```text
src/main/java/org/api/cardnexus/adapters        Gson adapters and JSON mapping helpers
src/main/java/org/api/cardnexus/configuration  SDK configuration
src/main/java/org/api/cardnexus/gui            Optional Swing panels and reusable GUI components
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

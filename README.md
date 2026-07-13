# cardnexus-api-java

[![Maven Central](https://img.shields.io/maven-central/v/com.github.nicho92/cardnexus-api-java.svg)](https://central.sonatype.com/artifact/com.github.nicho92/cardnexus-api-java)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-23-orange.svg)](https://openjdk.org/)

Java client for the **CardNexus** API.

This SDK is part of a series of Java clients built for the [MtgDesktopCompanion](https://www.mtgcompanion.org) ecosystem, providing access to the main trading card marketplace APIs (Cardmarket, CardTrader, MTGStock, ManaPool, and now CardNexus).

## Table of contents

- [Installation](#installation)
- [Requirements](#requirements)
- [Quick start](#quick-start)
- [Features](#features)
- [Error handling](#error-handling)
- [Contributing](#contributing)
- [License](#license)

## Installation

### Maven

```xml
<dependency>
    <groupId>com.github.nicho92</groupId>
    <artifactId>cardnexus-api-java</artifactId>
    <version>1.3.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.github.nicho92:cardnexus-api-java:1.3.0'
```

## Requirements

- Java 23 or higher
- Valid CardNexus API credentials (API key/token, as per the official CardNexus documentation)

## Quick start

<!-- TODO: adjust class/package names below to match the actual implementation -->

```java
import org.api.cardnexus.tools.CardNexusAPIConfig;
import org.api.cardnexus.services.CardService;
import org.api.cardnexus.modele.Card;

import java.util.List;

public class Example {
    public static void main(String[] args) throws Exception {
        // Initialize the API configuration once at application startup
        NexusConfig.setToken(
            System.getenv("CARDNEXUS_API_KEY")
        );

        // Use the domain-specific service classes
        var service = new ProductsService();
		
		
		var req = new SearchProductRequest();
			req.setGame("mtg");
			req.setName("Mountain");
		
		service.searchProduct(req);
		
    }
}
```

Expected environment variables:

```
CARDNEXUS_API_KEY=your-api-key
```

## Features

<!-- TODO: list the endpoints actually covered -->

- Card search
- Price / listing retrieval
- Collection management *(depending on CardNexus API availability)*

## Error handling

Network calls and parsing failures are typically surfaced as `IOException`. API-specific errors (missing credentials, invalid requests, etc.) are wrapped in dedicated exceptions from the `exceptions` package.

## Contributing

Contributions are welcome:

1. Fork the repository
2. Create a feature branch
3. Make your changes (with tests when possible)
4. Run `mvn clean test`
5. Open a pull request describing the change and any compatibility impact

## License

Distributed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

## Author

**Nicolas Pihen** — [mtgcompanion.org](https://www.mtgcompanion.org)

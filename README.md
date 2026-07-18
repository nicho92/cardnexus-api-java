# cardnexus-api-java

[![Maven Central](https://img.shields.io/maven-central/v/com.github.nicho92/cardnexus-api-java.svg)](https://central.sonatype.com/artifact/com.github.nicho92/cardnexus-api-java)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-23-orange.svg)](https://openjdk.org/)
[![Build](https://img.shields.io/badge/build-Maven-red.svg)](pom.xml)

A lightweight Java SDK for the **CardNexus API**, providing typed access to CardNexus's card, pricing, and marketplace data from Java applications.

This project is part of a family of Java API clients built around the [MtgDesktopCompanion](https://www.mtgcompanion.org) ecosystem, alongside [`mkm-api-java`](https://github.com/nicho92/mkm-api-java) (Cardmarket), [`cardtrader-api-java`](https://github.com/nicho92/cardtrader-api-java) (CardTrader), [`mtgstock-api-java`](https://github.com/nicho92/mtgstock-api-java) (MTGStock) and [`manapool-api-java`](https://github.com/nicho92/manapool-api-java) (ManaPool). All of them follow the same design conventions, so if you've used one, you'll feel at home with `cardnexus-api-java`.

## Table of contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Quick start](#quick-start)
- [Project structure](#project-structure)
- [Configuration](#configuration)
- [Error handling](#error-handling)
- [Logging](#logging)
- [Building from source](#building-from-source)
- [Testing](#testing)
- [Roadmap](#roadmap)
- [Related projects](#related-projects)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

## Requirements

- **Java 23** or higher (see `maven.compiler.source` / `maven.compiler.target` in `pom.xml`)
- **Maven** 3.6+ to build from source
- A valid CardNexus API key/credentials

## Installation

### Maven

```xml
<dependency>
    <groupId>com.github.nicho92</groupId>
    <artifactId>cardnexus-api-java</artifactId>
    <version>1.4.9</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.github.nicho92:cardnexus-api-java:1.4.9'
```

The library is published on [Maven Central](https://central.sonatype.com/artifact/com.github.nicho92/cardnexus-api-java), so no extra repository declaration is needed.

## Quick start

<!-- TODO: replace with the real package/class names from src/main -->

```java
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;

import java.util.List;

public class Example {
    public static void main(String[] args) throws Exception {

       NexusConfig.loadTokenFromEnv();

     	var service = new ProductsService();
		
		var req = new SearchProductRequest()
				.setGame("mtg")
				.setName("liliana of the veil")
				.setStrictTerms(true);
				
		var res = service.searchProduct(req);
		
		res.forEach(p->{
		    System.out.println(p.getName() + " / " + p.getId());
		});
    }
}
```

Required environment variables:

| Variable            | Description                     |
|---------------------|----------------------------------|
| `CARDNEXUS_API_KEY`  | Your CardNexus API key/token     |

## Project structure

<!-- TODO: confirm against the actual src/main tree -->

Following the same layout as the other clients in this series:

```
src/main/java/org/api/cardnexus/services    Service classes, one per API domain (cards, prices, ...)
src/main/java/org/api/cardnexus/modele      API model / DTO classes
src/main/java/org/api/cardnexus/tools       Configuration, constants, HTTP and JSON helpers
src/main/java/org/api/cardnexus/exceptions  Custom exception types
src/test/java                                Unit and integration tests
```

## Configuration

The client is configured through a singleton (`NexusConfig`), initialized once at application startup with your API credentials, then reused by all service classes. Credentials should never be hardcoded — pass them via environment variables, a `.env` file, or a secrets manager.

## Error handling

<!-- TODO: confirm the actual exception hierarchy -->

- Network and parsing failures are generally surfaced as `IOException`.
- Missing or invalid credentials typically throw a dedicated configuration exception.
- Non-2xx HTTP responses from CardNexus are wrapped in a custom API exception exposing the status code and error payload where available.

## Logging

The project uses **Log4j2** for logging. Add or adjust a `log4j2.xml` on your classpath to control log verbosity; the Maven build excludes `log4j2.xml` from the packaged jar so consuming applications can supply their own configuration.

## Building from source

```bash
git clone https://github.com/nicho92/cardnexus-api-java.git
cd cardnexus-api-java
mvn clean install
```

## Testing

```bash
mvn clean test
```

<!-- TODO: mention if tests require live CardNexus credentials / mocked responses -->

## Roadmap

<!-- TODO: fill in with actual planned work -->

- [ ] Full endpoint coverage for CardNexus
- [ ] Integration examples
- [ ] Publish Javadoc

## Related projects

| Project | Marketplace |
|---|---|
| [mkm-api-java](https://github.com/nicho92/mkm-api-java) | Cardmarket |
| [cardtrader-api-java](https://github.com/nicho92/cardtrader-api-java) | CardTrader |
| [mtgstock-api-java](https://github.com/nicho92/mtgstock-api-java) | MTGStock |
| [manapool-api-java](https://github.com/nicho92/manapool-api-java) | ManaPool |
| [MtgDesktopCompanion](https://github.com/nicho92/MtgDesktopCompanion) | Full MTG collection/deck manager built on top of these clients |

## Contributing

Contributions are welcome:

1. Fork the repository
2. Create a feature branch
3. Make your changes, with tests when possible
4. Run `mvn clean test`
5. Open a pull request describing the change and any compatibility impact

## License

Distributed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

## Author

**Nicolas Pihen** — [mtgcompanion.org](https://www.mtgcompanion.org)

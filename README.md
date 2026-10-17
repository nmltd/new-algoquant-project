# New AlgoQuant Project

A minimal Java 8 Maven application using the published **tech.nmfin:algoquant-dist:3.3.0-SNAPSHOT** combined, obfuscated JAR. It downloads AlgoQuant from GitHub Packages; it does not build AlgoQuant from source. NM Dev and the other runtime libraries are already bundled in that dependency.

## Requirements

- JDK 8 and Maven 3.9.9.
- A GitHub account and your own personal access token **(classic)** with `read:packages`.
- A valid NM license. This starter already contains the supplied license at `license/nm.lic`; replace it with your own when needed.

## Configure Maven authentication

Create or update `~/.m2/settings.xml` (Windows: `%USERPROFILE%\\.m2\\settings.xml`):

```xml
<settings>
    <servers>
        <server>
            <id>github-algoquant</id>
            <username>YOUR_GITHUB_USERNAME</username>
            <password>YOUR_GITHUB_TOKEN</password>
        </server>
    </servers>
</settings>
```

Merge the server entry into an existing settings file if you already have one. Keep your token outside the repository. Authentication must use the same server ID as the repository in `pom.xml`. For a 401/403 response, check your token and its read:packages scope. No access to the private source repository is needed.

## Build and run

From the repository root:

```bash
mvn -U clean verify exec:java
```

The example verifies and prints a 2-by-2 NM Dev matrix product, creates an AlgoQuant `StockEOD` bar, and prints:

```text
Hello AlgoQuant!
...
AlgoQuant example completed successfully.
```

It loads `license/nm.lic` explicitly before invoking licensed code. The license stays outside the application JAR.

You can also run the packaged application:

```bash
java -jar target/new-algoquant-project-0.0.1-SNAPSHOT.jar license/nm.lic
```

Keep `target/lib/` beside the application JAR. The final argument is the license path, so you can supply an absolute path when running from another directory.

## Continuous integration

GitHub Actions uses only its built-in `GITHUB_TOKEN` with `packages: read`. No NM internal secrets or private source repository access are required. CI checks public package metadata, downloads into an empty Maven cache, compiles and runs the example, and runs the packaged JAR.

The public distribution is hosted at https://maven.pkg.github.com/nmltd/maven-packages. Its artifact ID is `algoquant-dist`; Java package names are unchanged.

[AlgoQuant Javadoc](https://nmltd.github.io/algoquant/)

For runnable technical-analysis strategies and backtests, see [algoquant-examples](https://github.com/nmltd/algoquant-examples). It includes six examples with offline synthetic data, optional Yahoo downloads and optional charts.

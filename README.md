# New AlgoQuant Project

A minimal Java 8 Maven application using the published **tech.nmfin:algoquant:3.3.0-SNAPSHOT** combined, obfuscated JAR. It downloads AlgoQuant from GitHub Packages; it does not build AlgoQuant from source. NM Dev and the other runtime libraries are already bundled in that dependency.

## Requirements

- JDK 8 and Maven 3.9.9.
- A GitHub account with access to the AlgoQuant package and a personal access token **(classic)** with `read:packages`.
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

Merge the server entry into an existing settings file if you already have one. Keep your token outside the repository. Authentication must use the same server ID as the repository in `pom.xml`. For a 401/403 response, check both the token and your account's package access.

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

GitHub Actions downloads dependencies into a fresh Maven cache, compiles the example, executes its result checks, and runs the packaged JAR. It uses `NM_PACKAGES_TOKEN`, then `NM_REPOSITORIES_TOKEN`, then the built-in `GITHUB_TOKEN`. When using the built-in token, grant this repository Actions access to the AlgoQuant package in its package settings. A fork needs its own authorized package credentials.

[AlgoQuant Javadoc](https://nmltd.github.io/algoquant/)

Repository owners: configure the token under [Settings → Secrets and variables → Actions](https://github.com/nmltd/new-algoquant-project/settings/secrets/actions), or grant this repository access under the AlgoQuant package's **Package settings → Manage Actions access → Add repository**. The workflow checks metadata access before attempting the build.

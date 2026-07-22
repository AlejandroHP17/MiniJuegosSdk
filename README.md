# MiniJuegosSdk

SDK fino que define el **contrato** entre la app host [MiniJuegos](https://github.com/AlejandroHP17/MiniJuegos) y cada juego embebible (por ejemplo [Buscaminas](https://github.com/AlejandroHP17/Buscaminas)).

## Qué es

Un módulo Android Library (`:sdk`) que publica el artefacto Maven:

| Coordenada | Valor |
|------------|--------|
| Group | `pelkidev.com.mx.minijuegos` |
| Artifact | `sdk` |
| Versión | `1.0.0` |

La API pública es `GameModule`: metadatos del juego + un `@Composable Entry(onExit)`.

## Documentación

- [Estructura del proyecto](docs/ESTRUCTURA.md)
- Tutorial del ecosistema (en MiniJuegos): [TUTORIAL.md](https://github.com/AlejandroHP17/MiniJuegos/blob/main/docs/TUTORIAL.md)

## Requisitos

- JDK 17+
- Android SDK (compileSdk 36, minSdk 29)
- Credenciales de GitHub Packages para publicar / consumir

## Build local

```bash
./gradlew :sdk:assembleRelease
./gradlew :sdk:publishToMavenLocal
```

## Publicar a GitHub Packages

En CI (push a `main`) se publica automáticamente.

Manual:

```bash
export GITHUB_ACTOR=AlejandroHP17
export GITHUB_TOKEN=ghp_xxx   # write:packages
./gradlew :sdk:publishReleasePublicationToGitHubPackagesRepository
```

## Consumo desde otro proyecto

```kotlin
implementation("pelkidev.com.mx.minijuegos:sdk:1.0.0")
```

Repositorio Maven: `https://maven.pkg.github.com/AlejandroHP17/MiniJuegosSdk`

## Versionado

SemVer. Un cambio **breaking** en `GameModule` debe subir major (`2.0.0`) y actualizar host + juegos.

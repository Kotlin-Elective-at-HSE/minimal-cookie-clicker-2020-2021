# Minimal Cookie Clicker

This project demonstrates how you can create a minimal clone of
the [Cookie Clicker](https://orteil.dashnet.org/cookieclicker/) game using [KorGE](https://korge.org/).

## Running from sources

The following command will run the game using Java:

```shell
./gradlew runJvm
```

## Building and running the built version

### JVM version

The following command will build the game as a runnable jar file into the `build/libs` dir:

```shell
./gradlew packageJvmFatJar
```

You can run this jar file via `java -jar fileName.jar`.

### Web version

The following command will build the game as a set of files into the `build/distributions` dir:

```shell
./gradlew jsBrowserProductionWebpack
```

To play, open `index.html` file via a browser.

## License

The source code of this project is available under Apache 2.0 license. The resources are taken from the Cookie Clicker
game and Zombie Bird guide.



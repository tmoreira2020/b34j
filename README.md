B34J
==========
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/br.com.thiagomoreira.mercadobitcoin/B34J/badge.svg)](https://maven-badges.herokuapp.com/maven-central/br.com.thiagomoreira.mercadobitcoin/B34J)

B34J is a Java parser used to read/parse B3 (Bolsa, Balcão Brasil) files

### License

B34J is licensed under [Apache 2](http://www.apache.org/licenses/LICENSE-2.0) license.

### Getting Started

It's really simple to use the parser, just create a object of `br.com.thiagomoreira.b3.HistoricReader` class and  start to invoke its methods. That's it!


```java
import br.com.thiagomoreira.b3.HistoricReader;
import br.com.thiagomoreira.b3.model.Candlestick;

public static void main(String[] args) {
    URL url = new URL("...");

    HistoricReader reader = new HistoricReader();

    List<Candlestick> candlesticks = reader.read(url);

    System.out.println(candlesticks.size());
}
```

### Which methods are implemented?

Yep, it is a small list. Can you help me? Look here: [Contributing](#contributing).
* read(URL url)

### Maven/Gradle

B34J is available on Maven central, the artifact is as follows:

Maven:

```xml
<dependency>
    <groupId>br.com.thiagomoreira.b3</groupId>
    <artifactId>b34j</artifactId>
    <version>1.0.0</version>
</dependency>
```
Gradle:

```groovy
dependencies {
    compile(group: "br.com.thiagomoreira.b3", name: "b34j", version: "1.0.0");
}
```
### Support
B34J tracks [bugs and feature requests](https://github.com/tmoreira2020/b34j/issues) with Github's issue system. Feel free to open your [new ticket](https://github.com/tmoreira2020/b34j/issues/new)!

### Contributing

B34J is a project based on Maven to improve it you just need to fork the repository, clone it and from the command line invoke

```shell
mvn package
```
After complete your work you can send a pull request to incorporate the modifications.

Enjoy!
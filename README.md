**TicTacToe Console Game**

Design:
* App as host client.
* TicTacToe as game holder (service) orchestrating communication with outside.
* Dependency Injection approach.
* Cells as a matrix (2-dimentional array) that is deep copied at board printing time (decoupling)
* Interface for TicTacToe class only, since I dont consider necesary if class has few access methods.
* Messages extracted to a enum, enabling replacement with keys for i18n

App has:
* dependency injection pattern to enable testability testability
* 90% test coverage for internal code, 84% overall.
* mocks and fakes. 
* some javadoc, specially on code which has runtime error handling
* limit of the board at 10x10.
* build and dependency management with maven

Missing:
* move the dependency injection to some lib (Inversion of Control)
* draw check can be splitted and tested way further.
* logging strategy, like SLF4J to have better debugging knowledge.
* using some lib to get rid of boilerplate code (Lombok)


How to run:
* install maven 3 and run mvn package
* execute `java -jar ./target/tictactoe-1.0-SNAPSHOT-jar-with-dependencies.jar `

# Parboiled Grammar Tutorial

A tutorial showing how to write a grammar parser for a custom input format and how to write tests for each parsable fragment using the [parboiled] framework.

Please feel free to have a look at my blog at [www.hascode.com] for the full tutorial and more detailed information.

## Java Examples

The Java examples are build using [Apache Maven] and are executed as [JUnit] test cases.

To run the tests simply check out the project and run the following command:

    cd java-example && mvn test

This should give you a similar output:

    -------------------------------------------------------
     T E S T S
    -------------------------------------------------------
    Running it.parser.SingleLineParserTest
    Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.139 sec
    Running it.parser.CompleteDslParserTest
    Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.024 sec
    
    Results :
    
    Tests run: 5, Failures: 0, Errors: 0, Skipped: 0

## Scala Examples

The Scala examples are build using [sbt] .. to run the examples simply run the following command:

    cd scala-example && sbt run

This should produce a similar output:

    [info] Running com.hascode.parser.ParserExample 
    Got 3 tasks:
    Task: A first task
    Task: And a second task
    Task: Finally a third task
    [success] Total time: 13 s, completed Jan 19, 2014 7:15:58 PM

If you'd like to import the scala example as an Eclipse project, simply run the following command:

    cd scala-example && sbt eclipse

This generates the project files needed to import the project in Eclipse.


----

**2014 Micha Kops / hasCode.com**

   [parboiled]:https://github.com/sirthias/parboiled/wiki
   [www.hascode.com]:http://www.hascode.com/
   [sbt]:https://github.com/sbt/sbt/
   [Apache Maven]:http://maven.apache.org/
   [JUnit]:http://junit.org/


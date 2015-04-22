# Introduction #

This is a summary of the beanshell2 test infrastructure, listing open issues and mapping these to test scripts or unit tests.

# Details #

Old test-srcipts 'inherited' from Beanshell.org are executed by the test class [`OldScriptsTest.java`](http://code.google.com/p/beanshell2/source/browse/trunk/tests/junitTests/src/bsh/OldScriptsTest.java).

List of failing test scripts (revision [r54](https://code.google.com/p/beanshell2/source/detail?r=54)) is
  1. [class13.bsh](http://code.google.com/p/beanshell2/source/browse/trunk/tests/test-scripts/class13.bsh) - see [issue#7](https://code.google.com/p/beanshell2/issues/detail?id=#7)
  1. [class3.bsh](http://code.google.com/p/beanshell2/source/browse/trunk/tests/test-scripts/class3.bsh) - see [issue#8](https://code.google.com/p/beanshell2/issues/detail?id=#8)
  1. [classinterf1.bsh](http://code.google.com/p/beanshell2/source/browse/trunk/tests/test-scripts/classinterf1.bsh) - see [issue#46](https://code.google.com/p/beanshell2/issues/detail?id=#46)
  1. [commands.bsh](http://code.google.com/p/beanshell2/source/browse/trunk/tests/test-scripts/commands.bsh)
  1. [run.bsh](http://code.google.com/p/beanshell2/source/browse/trunk/tests/test-scripts/run.bsh)

List of failing unit tests (revision [r54](https://code.google.com/p/beanshell2/source/detail?r=54))
  1. [Issue\_7\_Test](http://code.google.com/p/beanshell2/source/browse/trunk/tests/junitTests/src/bsh/Issue_7_Test.java) - see [issue#7](https://code.google.com/p/beanshell2/issues/detail?id=#7)
  1. [Issue\_8\_Test](http://code.google.com/p/beanshell2/source/browse/trunk/tests/junitTests/src/bsh/Issue_8_Test.java) - see [issue#8](https://code.google.com/p/beanshell2/issues/detail?id=#8)
  1. [InterpreterConcurrencyTest](http://code.google.com/p/beanshell2/source/browse/trunk/tests/junitTests/src/bsh/InterpreterConcurrencyTest.java) - see [issue#40](https://code.google.com/p/beanshell2/issues/detail?id=#40)
  1. [ClassGeneratorTest#define\_interface\_with\_constants](http://code.google.com/p/beanshell2/source/browse/trunk/tests/junitTests/src/bsh/ClassGeneratorTest.java) - see [issue#46](https://code.google.com/p/beanshell2/issues/detail?id=#46)
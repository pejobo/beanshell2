Sometimes it comes in handy that you can call private methods and manipulate private fields of any instance. To do this in beanshell just call:

```
 setAccessibility(true);
```

Example usage on java.lang.String class:
```
 bsh % print(Capabilities.haveAccessibility());
 false
 bsh % string = "foo";
 bsh % print(string.hashCode());
 101574
 bsh % print(string.hash);
 // Error: EvalError: Cannot access field: hash, on object: foo : at Line: 1 : in file: <unknown file> : string .hash 

 bsh % setAccessibility(true);
 bsh % print(string.hash);
 101574
 bsh % print(Capabilities.haveAccessibility());
 true
```


**You'll also need this when [extending classes](http://code.google.com/p/beanshell2/wiki/DefiningClasses) to access inherited protected methods and fields.**

Up to version **2.1.5** the accessibility mode was transparently switched on with the first class definition in your script:
```
 2.1b5 - http://code.google.com/p/beanshell2
 bsh % print(Capabilities.haveAccessibility());
 false
 bsh % class Foo { };
 bsh % print(Capabilities.haveAccessibility());
 true
```

Starting with version **2.1.6** the accessibility mode must always switched on explicitly:
```
 2.1.6 - http://code.google.com/p/beanshell2
 bsh % print(Capabilities.haveAccessibility());
 false
 bsh % class Foo { };
 bsh % print(Capabilities.haveAccessibility());
 false
```

Make sure you read the hints about running with a SecurityManager.
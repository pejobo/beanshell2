_Please consider the beanshell ClosureSupport in favour of java class definitions - for implementing interfaces or extending abstract classes in beanshell the definition of classes is not necessary!_

Beanshell has (limited) support for defining classes in your script:
```
   public class Foo {
      public void hello() {
         print("Hello world from " + this + ", my class is " + this.getClass() + ", my class loader is " + this.getClass().getClassLoader());
      }
   }
   new Foo().hello();
```

This yields:
```
  Hello world from Foo@acb8af4, my class is class Foo, my class loader is bsh.classpath.DiscreteFilesClassLoader@3c75339ffor files: {Foo=bsh.classpath.BshClassPath$GeneratedClassSource@390f9eb5}
```

Please be aware that creating classes puts stress on the garbage collector (depending on your JDK version classes are treated different from normal heap usage)!

What are the limitations?
  * Nested class definitions (inner classes) should be considered broken
  * Accessing of protected inherited methods and fields only work with _[accessibility](http://code.google.com/p/beanshell2/wiki/Accessibility)_ switched on<br>(up to <b>v2.1b5</b> accessibilty was transparently switched on when the first class definition was encountered)<br>
<ul><li>Constructor chaining is buggy</li></ul>

Despite the change with <b>v2.1.6</b> (accessibilty must be switched on explicitly) and some minor bugfixes in this area the support of classes should be the same as with the last public release <b>v2.0b4</b> from <a href='http://www.beanshell.org/download.html'>beanshell.org</a>.<br>
<br>
Feel free to file the <a href='http://code.google.com/p/beanshell2/issues/list'>issue</a> you encounter, at least this will help others to know exactly which parts are broken.
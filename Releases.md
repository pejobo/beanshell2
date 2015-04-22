Version 2.1 is "bug-fix-only" release, lastest jar is **[v2.1b4](http://code.google.com/p/beanshell2/downloads/detail?name=bsh-2.1b4.jar)**.

The code hasn't changed much since the last last release on [Beanshell.org](http://www.beanshell.org), which was **2.0b4** in june 2005.

This version should be 100% compatible to 2.0b4 with one exception: The [support for parsing of java files through the class loader has been dropped](http://code.google.com/p/beanshell2/issues/detail?id=17). It was considered more harmful than helpful. Please leave a comment at the corresponding [issue-tracker item](http://code.google.com/p/beanshell2/issues/detail?id=17) if you rely on this - we could possibly add a switch for this.

One of the most notable changes isthe drop of java 1.4 support and the support of the java 1.5 [vararg](http://code.google.com/p/beanshell2/issues/detail?id=13) feature - it is now possible calling vararg methods with java5 from beanshell2. Also it's now possible to [use long string literals](http://code.google.com/p/beanshell2/issues/detail?id=2):
```
    String xml =  """
<books>
    <book>
        <title>Beanshell2</title>
        ...
    </book>
    ...
</books>""";
```

Also there is now build-in [jsr-233](http://www.jcp.org/en/jsr/detail?id=223) support.

This version resides in the [v2.1 SVN branch](http://code.google.com/p/beanshell2/source/browse/#svn%2Fbranches%2Fv2.1).

See the list of [bug fixes](http://code.google.com/p/beanshell2/issues/list?can=1&q=label%3Av2.1), [enhancements](http://code.google.com/p/beanshell2/issues/list?can=1&q=status%3AFixed+Type%3DEnhancement) or see [all changes here](http://code.google.com/p/beanshell2/issues/list?can=1&q=status%3AFixed+milestone%3D2.1b0).

[SVN-trunk](http://code.google.com/p/beanshell2/source/browse/#svn%2Ftrunk) is the upcoming v2.2 release. One target for this release is the support of [project coin](http://openjdk.java.net/projects/coin/)  feature set (i.e. java 7 syntax support).

A possible long time target could be the compilation to class file, utilizing the new Java 7 dynamic call site support.

Feel free to add comments!
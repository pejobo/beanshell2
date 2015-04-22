Up to version **2.1.5** BeanShell needed the Permission `java.lang.reflect.ReflectPermission("suppressAccessChecks")`.

Since version **2.1.6** this is only needed in [accessibilty](http://code.google.com/p/beanshell2/wiki/Accessibility) mode.


**You should not grant this permission if you do not trust the scripts which are executed!**

But be aware, without _accessibility_ there are some restrictions when defining classes: _Unlike in Java you cannot access protected methods or fields of your super class_.
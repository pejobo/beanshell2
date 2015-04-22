# v2.1.8, 20th February 2014 #

Hot-Fix build for bugs introduced with [issue#88](https://code.google.com/p/beanshell2/issues/detail?id=#88)

**[DOWNLOAD v2.1.8](https://beanshell2.googlecode.com/svn/branches/v2.1/downloads/bsh-2.1.8.jar)**

# v2.1.6, 27th September 2013 #

## BeanShell and SecurityManager ##
This update changes the behaviour when defining classes which define protected methods, constructors or fields or which access inherited protected methods, constructors or fields. If your (script) code doesn't explicitly switch on the [accessibility mode](http://code.google.com/p/beanshell2/wiki/Accessibility) your **script code will break** with this update.

To receive the old behaviour either call `bsh.Capabilities.setAccessibility(true)` in your java code or `setAccessibility(true)` in your script code.

This change was done to **allow the usage of beanshell in [security restricted environments](http://code.google.com/p/beanshell2/wiki/SecurityManager)**. See [issue #88](https://code.google.com/p/beanshell2/issues/detail?id=#88) for code changes.

## Version Number Format ##
As you can see we dropped the old `<major>.<minor>b<release>` format, the new format is `<major>.<minor>.<release>`. This should work better with maven repositories.

# v2.1b5, 21st November 2011 #

  * Do-while loop does not check condition on "continue" - [issue #57](https://code.google.com/p/beanshell2/issues/detail?id=#57)
  * Using JSR-223, exception is thrown for clause that shouldn't be evaluated - [issue #60](https://code.google.com/p/beanshell2/issues/detail?id=#60)
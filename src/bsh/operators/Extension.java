/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bsh.operators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a static method as as an extended method in BeanShell.
 * Extended methods augment (or extend) a class with additional methods from outside of the class definition.
Any static method can be used as if it were an instance method of the first argument type.  
This is illustrated as shown below
<pre>
import bsh.operators.Extension;  // Import Extension annotation

class MyExtendedMethods {

  // Define extended method sayHello to all String types
  // Must be marked with the @Extension annotation
  &#064;Extension
  public static void sayHello(String name) {
     print("Hello " + name);
  }

}

// Invoke sayHello() as if it were an instance method of Bar
"Scott".sayHello();
// Prints "Hello Scott"

// You can also invoke this method using the following syntax
sayHello("Scott")
MyExtendedMethods.sayHello("Scott");

// If the method takes more than one argument, the additional arguments are specified in the
// parameter list
"Scott".sayHelloPlus(additional,args,here);
 
</pre>
 * <p>
 * Extended methods must be marked using the @Extension annotation.  This is unfortunate 
 * because it creates a dependency on BeanShell in the target source code, but without this
 * nearly every static method would be considered an extended method.  I originally 
 * tried not using this annotation tag and the results were not good.
 * 
 * @author Scott Stevenson
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Extension {
    
}

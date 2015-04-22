This is what a closure definition looks like:
```
  Person(first, last) {
     firstName = first;
     lastName = last;
     return this;
  }
```


Create and use instances:
```
  john = Person("John", "Doe");
  print(john.lastName);
```


With method definition:
```
  Person(first, last) {
     firstName = first;
     lastName = last;
     fullName() {
        return firstName + " " + lastName;
     }
     return this;
  }
```



Implementing interfaces is even simpler:
```
  myActionListener = new ActionListener() {
     actionPerformed(event) {
        print("actionPerformed");
     }
  }
```


This could be used in the same way as an anonymous class instance in Java:
```
   button = new JButton();
   button.addActionListener(button);
```


Unlike in Java not all methods of an interface must be overwritten. If you omit methods a call to this ommitted method will lead to a `java.lang.reflect.UndeclaredThrowableException` (caused by an `bsh.EvalError`). You could prevent this by defining a default method `invoke` like this:
```
   myMouseListener = new MouseListener() {
      void mouseClicked(MouseEvent e) {
         print("mouse clicked: " + e);
      }

      invoke(methodName, args) {
         print("undefined method call: " + methodName + Arrays.asList(args));
      }
   };
```


Instead of implementing interfaces you could also extend (abstract) classes like this:
```
   myMouseListener = new MouseAdapter() {
      void mouseClicked(MouseEvent e) {
         print("mouse clicked: " + e);
      }
   };
```

When extending classes be aware that you can use protected members only if you switch on _accessibility_:
```
  setAccessibility(true);
```
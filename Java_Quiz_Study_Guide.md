# Java Quiz Study Guide - Units 1-3

## Unit 1-3: Changes to Interfaces & Lambda Expressions

### Q1: What new types of methods were added to interfaces in JDK 1.8?
**A:** Default methods (methods with a body in an interface) and static methods.

**Explanation:** Before Java 8, all interface methods were abstract. Default methods let interfaces add functionality without breaking existing implementations. Static methods in interfaces provide utility/helper functions.

### Q2: Why were default methods introduced in interfaces?
**A:** To allow adding new methods to existing interfaces without forcing all implementing classes to override them.

**Explanation:** This helps evolve APIs over time while preserving backward compatibility.

### Q3: What is a functional interface (SAM)?
**A:** An interface with exactly one abstract method.

**Explanation:** Known as Single Abstract Method (SAM) interfaces. They can still have multiple default or static methods, but only one abstract method.

### Q4: Give an example of a functional interface.
**A:** Examples include `Runnable`, `Callable`, and `Comparator<T>`.

**Explanation:** These interfaces define one main abstract method, making them compatible with lambda expressions.

### Q5: What annotation is used to declare a functional interface?
**A:** `@FunctionalInterface`.

**Explanation:** This annotation is optional but ensures the compiler will throw an error if the interface has more than one abstract method.

### Q6: What is a lambda expression in Java?
**A:** A short block of code that takes parameters and returns a value, treated as an instance of a functional interface.

**Explanation:** Syntax: `(parameters) -> expression` or `(parameters) -> { statements }`.

### Q7: How do lambda expressions relate to functional interfaces?
**A:** A lambda expression can be assigned to a reference of a functional interface type.

**Explanation:** For example:
```java
Runnable r = () -> System.out.println("Hello");
```
Here, the lambda implements `Runnable.run()`.

### Q8: What advantages do lambda expressions provide?
**A:** Less boilerplate code, better readability, and the ability to pass behavior as parameters.

**Explanation:** They support functional programming style in Java.

### Q9: What's the difference between anonymous inner classes and lambda expressions?
**A:** Anonymous inner classes create a separate class; lambda expressions are more concise and can capture variables more cleanly.

**Explanation:** Lambdas are also treated as instances of functional interfaces, not full classes.

### Q10: Can a functional interface have default or static methods?
**A:** Yes, as long as it has only one abstract method.

**Explanation:** These extra methods do not affect its functional interface status.

---

## Unit 1-4: Exceptions & Exception Handling

### Q1: What is the root class of all exceptions and errors in Java?
**A:** `Throwable`.

**Explanation:** Both `Error` and `Exception` inherit from `Throwable`.

### Q2: What are the two main subclasses of Throwable?
**A:** `Error` and `Exception`.

**Explanation:** `Error` is for serious issues the program shouldn't handle (e.g., `OutOfMemoryError`), while `Exception` represents conditions that programs might catch.

### Q3: What is a RuntimeException?
**A:** An unchecked exception that occurs during program execution.

**Explanation:** It's a subclass of `Exception` but does not need to be declared in throws.

### Q4: What is the difference between checked and unchecked exceptions?
**A:**
- **Checked:** Must be declared in the method signature or handled with try-catch (e.g., `IOException`).
- **Unchecked:** Do not need to be declared or handled (e.g., `NullPointerException`).

**Explanation:** Checked exceptions are verified at compile time; unchecked at runtime.

### Q5: What are the two main ways to handle exceptions?
**A:**
1. **Confessing:** Declare with `throws` in the method signature.
2. **Handling:** Use try-catch blocks.

**Explanation:** "Confessing" passes the responsibility to the calling method.

### Q6: Why shouldn't we catch every possible exception?
**A:** Because it can hide bugs and make debugging harder.

**Explanation:** It's better to catch specific exceptions to handle only expected cases.

### Q7: What is the purpose of a finally block?
**A:** To execute code regardless of whether an exception occurs.

**Explanation:** Commonly used to close files, release resources, etc.

### Q8: How do you create a custom exception class?
**A:** Extend `Exception` or `RuntimeException`.

**Example:**
```java
class MyException extends Exception {
    public MyException(String msg) {
        super(msg);
    }
}
```

**Explanation:** Extend `Exception` for checked, `RuntimeException` for unchecked.

### Q9: Give an example of using try-catch-finally.
**A:**
```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("This always runs");
}
```

**Explanation:** `finally` runs whether or not an exception is thrown.

### Q10: Can finally block override a return statement in try/catch?
**A:** Yes, if it contains its own return statement.

**Explanation:** This is discouraged because it can make code confusing.

---

## Unit 2-1: MVC Pattern

### Q1: What does MVC stand for?
**A:** Model-View-Controller.

**Explanation:** Separates app logic into data handling (Model), UI (View), and interaction logic (Controller).

### Q2: What is the responsibility of the Model in MVC?
**A:** Manage data, logic, and rules of the application.

**Explanation:** It doesn't handle how data is presented — only stores and processes it.

### Q3: What is the responsibility of the View in MVC?
**A:** Present data to the user and handle basic UI rendering.

**Explanation:** It should not contain business logic.

### Q4: What is the responsibility of the Controller in MVC?
**A:** Receive input, process it, and update the Model and/or View.

**Explanation:** Acts as the "traffic manager" between Model and View.

### Q5: What are the advantages of using MVC?
**A:** Separation of concerns, easier maintenance, easier testing, reusability.

**Explanation:** Changes in UI don't break business logic.

### Q6: What is the "Oreo" MVC implementation?
**A:** The controller sits in the middle between Model and View, and Model and View only talk via the Controller.

**Explanation:** Like Oreo layers — Model and View never directly interact.

### Q7: What is the alternate MVC approach?
**A:** Model and View can communicate directly (with Observer pattern).

**Explanation:** Useful for real-time updates without constant Controller mediation.

---

## Unit 2-2 & 2-3: JDBC Basics

### Q8: What does JDBC stand for?
**A:** Java Database Connectivity.

**Explanation:** Java API for connecting and executing queries with databases.

### Q9: What are the four types of JDBC drivers?
**A:**
1. **Type 1:** JDBC-ODBC Bridge
2. **Type 2:** Native-API driver
3. **Type 3:** Network Protocol driver
4. **Type 4:** Thin driver (pure Java)

**Explanation:** Type 4 is preferred today.

### Q10: Which JDBC driver type is not supported after JDK 1.7?
**A:** Type 1 (JDBC-ODBC Bridge).

**Explanation:** Removed for security and portability reasons.

### Q11: What is the purpose of a Connection object?
**A:** Establish and manage a link to the database.

**Explanation:** Created using `DriverManager.getConnection()`.

### Q12: What is a Statement object used for?
**A:** Execute static SQL queries.

**Explanation:** Simple but vulnerable to SQL injection.

### Q13: What is a PreparedStatement object used for?
**A:** Execute parameterized SQL queries.

**Explanation:** Precompiled for efficiency and security.

### Q14: What is a ResultSet object used for?
**A:** Store and navigate results from a query.

**Explanation:** Acts like a table in memory.

### Q15: What are the steps to connect to a database and execute a query?
**A:**
1. Load driver (optional for modern JDBC).
2. Create Connection.
3. Create Statement/PreparedStatement.
4. Execute query/update.
5. Process ResultSet.
6. Close resources.

**Explanation:** Order is important to avoid resource leaks.

---

## Unit 2-4: Using ResultSet with JTable / JComboBox

### Q16: How can you process a ResultSet into a JTable?
**A:** Convert ResultSet into a TableModel using a utility method.

**Explanation:** JTable needs a TableModel to display data.

### Q17: How can you populate a JComboBox from a ResultSet?
**A:** Convert ResultSet into a ComboBoxModel and pass it to JComboBox.

**Explanation:** Useful for dropdown lists from database queries.

### Q18: What is the advantage of using utility classes for ResultSet conversion?
**A:** Reduces repetitive code and centralizes conversion logic.

**Explanation:** Improves maintainability.

---

## Unit 3-1: Intro to Threads

### Q1: What is the advantage of having an object run as a thread?
**A:** It allows multiple tasks to execute concurrently, improving responsiveness and efficiency.

**Explanation:** For example, a GUI can stay responsive while performing background tasks.

### Q2: What are two ways to create an object that runs on its own thread?
**A:**
1. Extend the `Thread` class.
2. Implement the `Runnable` interface.

**Explanation:** Implementing `Runnable` is preferred for flexibility.

### Q3: What is the Runnable interface used for?
**A:** To define a task that can be executed by a thread.

**Explanation:** Contains a single `run()` method.

### Q4: What does calling start() on a thread do?
**A:** It tells the JVM to start a new thread and call its `run()` method.

**Explanation:** This runs `run()` on a separate thread, not the main thread.

### Q5: Why should you never call run() directly?
**A:** Because it will execute on the current thread, not start a new one.

**Explanation:** `start()` is needed to launch parallel execution.

---

## Unit 3-1: Thread Life Cycle

### Q6: What are the five thread life cycle states?
**A:** New, Runnable, Running, Waiting/Blocked, and Terminated.

**Explanation:** Represents different stages from creation to completion.

### Q7: Why shouldn't stop(), suspend(), or resume() be used?
**A:** They are deprecated because they can cause deadlocks or inconsistent states.

**Explanation:** Use alternatives like `interrupt()`, `sleep()`, or `yield()`.

### Q8: What do sleep() and yield() do?
**A:**
- **sleep(ms):** Pauses thread for a given time.
- **yield():** Suggests to the scheduler to let other threads run.

**Explanation:** Neither releases object locks.

### Q9: What does interrupt() do?
**A:** Signals a thread to stop what it's doing.

**Explanation:** The thread must check `isInterrupted()` to respond.

### Q10: What does join() do?
**A:** Makes the current thread wait until another thread finishes.

**Explanation:** Useful for sequential execution control.

---

## Unit 3-2: Thread Scheduling

### Q11: How are threads scheduled on a single-core processor?
**A:** Time-slice / round-robin by the OS scheduler.

**Explanation:** Each thread gets a small execution window before switching.

### Q12: What is thread priority?
**A:** A value suggesting the importance of a thread to the scheduler.

**Explanation:** Higher priority threads may get more CPU time, but it's OS-dependent.

### Q13: Who schedules threads — JVM or OS?
**A:** Both.

**Explanation:** JVM scheduler hands threads to the OS scheduler.

### Q14: Do all OS schedulers use the same method?
**A:** No.

**Explanation:** Different operating systems have different thread scheduling strategies.

---

## Unit 3-2: Thread Safety & Synchronization

### Q15: What is the "lost update" problem?
**A:** When two threads update shared data and one overwrites the other's changes.

**Explanation:** Happens due to unsynchronized access.

### Q16: What is the difference between thread-safe and non-thread-safe data structures?
**A:** Thread-safe structures use synchronization or locks; non-thread-safe do not.

**Explanation:** Examples: `Vector` (safe) vs `ArrayList` (unsafe).

### Q17: What is thread contention?
**A:** Multiple threads competing for the same resource.

**Explanation:** Can slow performance.

### Q18: What is thread starvation?
**A:** When a thread is never scheduled because others keep getting CPU time.

**Explanation:** Happens with unfair scheduling.

### Q19: What is a fairness policy?
**A:** Ensures threads are served in the order they requested access.

**Explanation:** Prevents starvation.

### Q20: What is a race condition?
**A:** When the program outcome depends on the timing of threads.

**Explanation:** Prevented by synchronization.

### Q21: How can race conditions be prevented?
**A:** Using `synchronized`, locks, or thread-safe collections.

**Explanation:** Controls access to shared data.

### Q22: What does volatile do?
**A:** Ensures a variable is always read from main memory, not a thread's cache.

**Explanation:** Useful for flags shared across threads.

### Q23: How do locks work?
**A:** Allow only one thread at a time to access a critical section.

**Explanation:** Other threads wait until lock is released.

### Q24: What is deadlock?
**A:** When two or more threads wait indefinitely for each other's resources.

**Explanation:** Avoid by consistent lock ordering or using timeouts.

---

## Unit 3-4: Thread Pools

### Q25: What is a thread pool?
**A:** A group of pre-created threads that can be reused for tasks.

**Explanation:** Reduces overhead of creating new threads.

### Q26: What are the Executor and ExecutorService interfaces for?
**A:** Managing and executing threads in a pool.

**Explanation:** Part of `java.util.concurrent`.

### Q27: What is the Executors class used for?
**A:** Factory methods for creating thread pools.

**Explanation:** Example: `Executors.newFixedThreadPool(5)`.

### Q28: How can you limit the number of active threads in a pool?
**A:** Use `newFixedThreadPool(size)`.

**Explanation:** Controls maximum concurrency.

### Q29: What is the difference between shutdown() and shutdownNow()?
**A:**
- **shutdown():** Stops accepting new tasks but lets current tasks finish.
- **shutdownNow():** Attempts to stop all tasks immediately.

**Explanation:** Use with caution.

---

## Unit 3-5: Threads in GUI Apps

### Q30: What thread does a console program run on?
**A:** The `main()` thread.

**Explanation:** All code starts here.

### Q31: What thread does a Swing program run on?
**A:** The Event Dispatch Thread (EDT).

**Explanation:** All UI updates happen here.

### Q32: Are Swing components thread-safe?
**A:** No.

**Explanation:** Must update them on the EDT to prevent corruption.

### Q33: What is thread confinement in Swing?
**A:** Restricting GUI updates to the EDT only.

**Explanation:** Prevents race conditions in UI.

### Q34: What is a SwingWorker thread used for?
**A:** Running long tasks in the background while keeping the UI responsive.

**Explanation:** Separates background work from UI updates.

---

## Summary

This study guide covers essential Java concepts across multiple units:

- **Unit 1-3:** Interface enhancements and lambda expressions in Java 8
- **Unit 1-4:** Exception handling mechanisms and best practices
- **Unit 2:** MVC pattern and JDBC database connectivity
- **Unit 3:** Multithreading concepts, synchronization, and GUI threading

Key areas to focus on:
1. **Lambda expressions** and their relationship with functional interfaces
2. **Exception hierarchy** and proper handling techniques
3. **MVC pattern** for application architecture
4. **JDBC** for database operations
5. **Thread management**, synchronization, and safety considerations

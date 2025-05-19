# Semiar 4 information
## Task 1
a. se exception(s) to handle alternative flow 3-4a in Process Sale, see document with
tasks for seminar one. An exception shall be thrown to indicate that a search has
been made for an identifier that did not exist in the inventory catalog
b. Also use exception(s) to indicate that the database can not be called, it might be
for example that the database server is simply not running. Since there is no real
database, you must simulate this situation. That can be done by always throwing
a database failure exception when a search is made for a particular, hardcoded,
item identifier

The bullets below from chapter eight about exception handling must be implemented.
- Choose between checked and unchecked exceptions.
- Use the correct abstraction level for exceptions.
- Name the exception after the error condition.
- Include information about the error condition.
- Use functionality provided in java.lang.Exception
- Write javadoc comments for all exceptions.
- An object shall not change state if an exception is thrown.
- Notify users.
- Notify developers.
- Write unit tests for the exception handling.

The program shall produce the following output.

- The user interface shall show an informative message when an exception is caught
in the view. Apart from this, the grade is not affected no matter how simple or
advanced the view is.
- An error report shall be written to a log when an exception is caught, and that ex-
ception indicates the program is not functioning as intended. This logging of errors
shall be written to a file. How to print to a file is illustrated in se.leiflindback.
oodbook.polymorphism.logapi.FileLogger, which can be found in listing 9.1 in the
textbook and in the book’s git repository

## Task 2
To get two points for this lab you must give good solutions to both parts a and b below.
To get one point it is enough to solve only part a and not part b.
### Part a
In your Process Sale program, use the Observer pattern to implement a new function-
ality, namely to show the sum of the costs for all sales made since the program started.
This total income shall be handled by two new classes. The first, TotalRevenueView,
shall be placed in the view and show the total income on the user interface, for ex-
ample by printing to System.out. The second, TotalRevenueFileOutput, shall print
the total income to a file. How to print to a file is illustrated in se.leiflindback.
oodbook.polymorphism.logapi.FileLogger, which can be found in listing 9.1 in the text-
book and in the book’s git repository. These two classes handling the total income shall
never call the controller or any other class, but instead be updated using the Observer
pattern. Both shall implement the same observer interface. The grade is not affected no
matter how simple or advanced the view is.

### Part b, only for 2 pts
Use two more GoF patterns in your Process Sale program. You are free to choose any
GoF patterns, apart from Observer and Template Method, since the former is used here
in task 2a and the latter is used in Additional Higher Grade Tasks. You are allowed to
choose GoF patterns not covered at the lectures. A suggestion for one of the patterns is
to turn some registry/database into a singleton. Another suggestion is to use Strategy
(maybe also Composite) for discount calculation.
You are not allowed to copy entire files or classes from code samples written at the
lectures, even if you understand it and/or change it. You are for example not allowed
to use the logging example from the lecture on polymorphism, to implement the Strategy
pattern. You are, however, allowed to write code very similar to code examples from
lectures.

# Output - Result
Start Sale <br/>
Add 1 item with item id abc123 :<br/>
500g, whole grain oats, high fiber, gluten free<br/>
Price: 29.90 SEK<br/>
Total (incl VAT): 31.69 SEK<br/>
<br/>
Add 1 item with item id abc123 :<br/>
500g, whole grain oats, high fiber, gluten free<br/>
Price: 29.90 SEK<br/>
Total (incl VAT): 63.39 SEK<br/>
<br/>
Add 1 item with item id def456 :<br/>
240g, low sugar yoghurt, blueberry flavour<br/>
Price: 14.90 SEK<br/>
Total (incl VAT): 79.18 SEK<br/>
<br/>
Discount applied for customer CUST001: 0.00 SEK<br/>
<br/>
End sale :<br/>
Total (incl VAT): 79.18 SEK<br/>
<br/>
Customer pays: 100.00 SEK<br/>
<br/>
[TotalRevenueView] Cumulative revenue: 79.182 SEK<br/>
----- Begin receipt -----<br/>
Time of Sale: 2025-05-19 18:57<br/>
<br/>
BigWheel Oatmeal 2 x 29.90 = 59.80 SEK<br/>
YouGoGo Blueberry 1 x 14.90 = 14.90 SEK<br/>
<br/>
Total: 79.18 SEK<br/>
VAT:   4.48 SEK<br/>
Cash:  100.00 SEK<br/>
Change:20.82 SEK<br/>
------ End receipt ------<br/>
<br/>
Change to give the customer : 20.82 SEK
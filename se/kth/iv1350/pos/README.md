# Semiar 1 information
## Basic Flow
1. Customer arrives at POS with goods to purchase.
2. Cashier starts a new sale.
3. Cashier enters item identifier.
4. Program retrieves price, VAT (tax) rate, and item description from the external
inventory system. Program records the sold item. Program also presents item
description, price, and running total (including VAT).
5. Steps three and four are repeated until the cashier has registered all items.
6. Cashier asks customer if they want to buy anything more.
7. Customer answers ’no’ (a ’yes’ answer is not considered in this scenario).
8. Cashier ends the sale.
9. Program presents total price, including VAT.
10. Cashier tells customer the total, and asks for payment.
11. Customer pays cash.
12. Cashier enters amount paid
13. Program sends sale information to external accounting system (for accounting)
and external inventory system (to update inventory).
14. Program increases the amount present in the register with the amount paid.
15. Program prints receipt and tells how much change to give customer.
16. Customer leaves with receipt and goods.

## Alternative Flows
3-4a. No item with the specified identifier is found.
1. Program tells that identifier is invalid.
3-4b. An item with the specified identifier has already been entered in the current sale.
1. Program increases the sold quantity of the item, and presents item description,
price, and running total.
3-4c. Customer purchases multiple items of the same goods (with the same identifier),
and cashier registers them together.
1. Cashier enters item identifier.
2. Cashier enters quantity
3. Program calculates price, records the sold item and quantity, and presents item
description, price, and running total.
9a (may also be 10a or 11a) Customer says they are eligible for a discount.
1. Cashier signals discount request.
2. Cashier enters customer identification.
3. Program fetches discount from the discount database, see Business Rules and Clar-
ifications below.
4. Program presents price after discount, based on discount rules. See Business Rules
and Clarifications below for more details on discounts.

## Business Rules and Clarifications
Taxes/VAT The VAT mentioned in basic flow, bullets four and nine, is not included in
the price stored in the external inventory system. It must instead be added before
the total price is calculated. There are three different VAT rates: 25%, 12% and
6%. Each item description in the item registry must contain information about
that item’s VAT rate

## Discounts 
### The discounts mentioned in alternative flow 9a are calculated based on bought items, total cost for the entire sale, and customer id. A customer might be eligible for more than one type of discount. There’s already a database which contains information about all existing discounts, discount information must be fetched from this database. The discount database contains the following information:
- When passed a list of all bought items, it tells a sum to be reduced from the
total cost of the entire sale. The sum is zero if there’s no discount.
- When passed the total cost of the entire sale, it tells a percentage to be
reduced from this total cost. The percentage is zero if there’s no discount.
- When passed the customer id, it tells a percentage to be reduced from the
total cost of the entire sale. The percentage is zero if there’s no discount.

# Seminar 3 information
## Task 1
### Write a program implementing the basic flow, the startup scenario, and the alternative flow 3-4b specified in the document with tasks for seminar one, which you designed in seminar two. You do not have to program any other alternative flows or add any other functionality. You are also not required to code the view, you may replace the user interface with one single class, View, which contains hard-coded calls to the controller. Neither is there any requirement on databases or external systems. Instead of a database, you can just store the data in the object in the integration layer, which should have been responsible for calling the database if there had been one. The external systems can simply be omitted, but there must be objects responsible for calling them. The solution must meet the following requirements:

- The code shall be compilable and executable. You have to program in Java, since
everyone must be able to understand your solution at the seminar. Also, don’t use
exceptions now, since that’s a topic of seminar four, not understood by everyone
now at seminar three.
- If the user interface is replaced with hard-coded method calls, the class calling
the controller must print everything that is returned by the controller. Also the receipt must be printed (to System.out).
- Your code must follow all guidelines presented in chapter six in the text-
book. Regarding comments this means there must be one comment for each public
declaration.
- Try to follow the design from seminar two, but it is perfectly OK to change the
design if you discover flaws. The solution must however have high cohesion, low
coupling and good encapsulation with a well-defined public interface.

## Task 2
### Write tests for your program.
- To pass (1 point) you must write unit tests for two classes. Try to find something
more interesting to test than get/set methods. You have to write new test classes,
you’re not allowed to use the given tests, that is AmountTest from the textbook and
MainTest from the lecture Practice Programming and Unit Testing.
- To pass with distinction (2 points) you must write unit tests for all classes in the
layers controller, model, and integration, except classes that have just getters
and constructors that only store values. It is also not required to test that output
to System.out is correct, just ignore testing methods that only produce output to
System.out.

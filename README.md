# Instructions to Run

(*Note: should work on any modern OSX or Linux. Windows may require different
commands.)

1. Ensure Java 17 is available
2. Ensure the database is installed (see dump file if you don't want to create
   it by hand)
3. Ensure `application.properties` is filled out with the username/password for
   db
4. Run `MainApplication.java`

# Instructions To Run Tests

(*Due to time limitations, the unit tests must use the DB, so make sure the DB
is set up. In a normal environment, there would be an in-memory DB or some other
sort of solution.)

1. To run the entire suite, right-click on `test/java` and choose `run`
2. To run a single set, right-click on the file and choose `run`
3. To run a single test, right-click the name of the test and choose `run`

It is also possible to use Swagger, located at `http://localhost:8080/swagger-ui/index.html`.

# Concerns, Questions, and Looking Forward

This is, of course, a demo, and therefore has issues that would need to be addressed
before this project could be a true "greenfield" project. Below are some thoughts, intended
to anticipate questions, but not avoid further discussion.

## 1. Chargeable Days Minus Vs Plus

The current approach is to assume chargeable days equals number of days to rent the tool, and then subtract
based on holidays or weekends.

This might be a little harder to parse several months from now and might warrant flipping the minus to a plus
instead. The minus approach was used because it felt more intuitive to me personally. Flipping to a plus might
be more intuitive for a team, however, especially for a long-term product. It would certainly bear discussion
during design or even PR.

## 2. Error-Handling

Originally, if a tool could not be found, an exception was thrown, which didn't look very good on console. Exactly how
the exception would be handled depends a bit on the business logic, so I took some liberties. It could definitely be
handled a little more gracefully (I would most likely move validation down a layer if I hadn't run out of time) but will
do for a demo.

Messaging for validation and not-found tools could be better but at least the error output will show up cleanly on
Swagger, webpage, etc.

Null-safety was also a consideration for the rent agreement service when a tool couldn't be found.

## 3. Scalability

Currently, this demo isn't very scalable. Spring Boot JPA/Hibernate need tweaking out-of-the-box to ensure,
for example, that Entities aren't being queried while the View is being calculated. Furthermore, the query
to join Tool and ToolType is unlikely to scale well if the tools list gets beyond a couple hundred rows.

This isn't even considering the fact the app is a demo meant to run well on a single computer.

Transaction integrity should be fine even with a couple dozen users on the app at once, but more than that and
concerns about why there's no, for example, connection pooling starts becoming highly relevant.

## 4. Security

`application.properties` is not the best place for username and password. Username might be OK if it's highly secured
but passwords should be taken from elsewhere like, for example, Vault, to (a) ensure security and (b) make it easy to
change the password on the fly (restart the app instead of completely redeploying it).

The database itself is somewhat thrown together just enough to be serviceable for the demo. The structure would warrant
a little more attention if this was expected to be production-grade (or a demo ready to be turned into something
production-grade.)

## 5. Missing Tests

There are a few extra tests for the PricingCalculatorService beyond the required test suite.

As this is a demo, good code coverage isn't the greatest concern, but certainly there should be more unit tests,
and there should also be a mock, in-memory DB, etc for the unit tests, which currently use the live database. This
could obviously be an issue if the data changes for some reason, or if the application is changed to update data itself.

## 6. Extensibility vs YAGNI

(YAGNI = "You aren't gonna need it")

This demo was made with a balance between extensibility and YAGNI in mind. It tries not to have too many different
objects, but simultaneously wants to be extensible, which does require an architecture that "stretches out" a bit to
accommodate expansions, changes, bug fixes, etc.

Controller, db, model, service, and utilities are probably the baseline for extensibility, and could be further broken
out further depending on the specs and needs of the business. For example, `errorhandling` and `config` were early
pieces of this demo, with only `errorhandling` surviving refactors in this case.

For the demo, it could be argued the Entity classes should be DTOs, since Entity classes are intended to be used for
inserts, updates, etc., while DTOs are sufficient for reading. They are also easier to query, join, etc. However, they
would not be useful for long in any significant application, so Entities were used.

## 7. Swagger Improvements

Swagger was re-added late to the demo and therefore niceties such as customizing the OpenAPI page, adding the models,
etc. were not possible. While not entirely necessary, these are QoL improvements for the people who use the tool and
would be on the list of things to improve, albeit at lower priority.

## 8. Why is the DB Called Yankovic's Tools?

https://www.youtube.com/watch?v=DFI6cV9slfI

(A production-grade DB would most likely be or include the business's name or something similar.)
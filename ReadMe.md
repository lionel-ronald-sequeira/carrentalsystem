Problem Definition:
==================
**In this project, you will design and implement a database for keeping track of information about a car rental company.**
**You will first design an EER schema diagram for this database application. Then, you will map the EER schema into a **
**relational database schema and implement it on ORACLE (you can also use MySQL if you have your own version). Finally,**
**you will load some data into your database, and create some queries and update transactions.**

Assume that the following requirements were collected for this application:
* The database keeps track of CUSTOMERs. Each CUSTOMER has a unique IdNo (assume this is a unique integer generated by 
the system for each new CUSTOMER, such as 1, 2, 3, …), a Name (assume this is string consisting of an single initial and 
last name only for simplicity, such as “J.Smith” or “R.Wong”), and a Phone (a string of 12 characters such as “817-272-
3000”).

* The database keeps track of CARs available for rental, which are categorized based on their type. There are six main 
types: COMPACT,MEDIUM, LARGE, SUV (Sports Utility Vehice), TRUCK, and VAN. Each type of car has its own DailyRate and 
WeeklyRate (assume all cars of the same type have the same rental rates). For simplicity, we will assume that there is 
only one rental location.

* Each CAR has a VehicleID (a unique number for each car – assume it is a number 1001, 1002, 1003, …), Model (Chevy,
Toyota, Ford, …),and Year (2015, 2014, …).

* Each car is either owned by the rental company, a bank or an individual owner who leased the vehicle to the company on
a long term basis. Choose suitable attributes for these different type of owners to identify the right owner of a vehicle. 

* The database will keep track of the current (active) RENTALs as well as scheduled RENTALs of each CAR. The are two types 
of RENTAL: DAILY and WEEKLY. For each DAILY RENTAL, the information kept will include the specific CAR and CUSTOMER as well 
as the NoOfDays, StartDate, and ReturnDate (the ReturnDate can be calculated from the StartDate and NoOfDays). For each WEEKLY 
RENTAL, the information kept will include the specific CAR and CUSTOMER as well as the NoOfWeeks, StartDate, and ReturnDate (the 
ReturnDate can be calculated from the StartDate and NoOfWeeks). Each rental will also have the AmountDue for the rental, which is 
a derived value that can be calculated from the other information.

* The database will also keep track of which CARs are available for rental during which periods.
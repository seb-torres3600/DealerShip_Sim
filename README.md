# OOAD_project2
## Sebastian Torres | seto2103 | 109330786 
## Jack Ashburn | jaas0665 | 109190215

### Java version used (directly from 'java -version'): 
- openjdk 19.0.2 2023-01-17
- OpenJDK Runtime Environment (build 19.0.2+7-44)
- OpenJDK 64-Bit Server VM (build 19.0.2+7-44, mixed mode, sharing)
- Junit 4.13.2 for testing
- Hamcrest Core 1.3 for testing 

### TESTING ###
- You can run them all by running TestRunner.java
- They will print success or failure along with the test name
- You might need to download the appropriate .jar for JUnit
- Screenshot of test output is in Test_Screenshot

### Additional Comments from Creators (from newest to oldest):
- Our testing involves instantiating a dummy FNCD, which we did with "null" for required strings, so please expect files named null.txt after you run testing.
- Screenshots of Day 31 activity is in the Day_31_Screenshot folder and screenshot of test output is in Test_Screenshot
- the two dealerships are both writing to the same output file as specified, so one of them will always produce "File already exists" in the terminal; please ignore this.
- UML pdf and any other pdf will be located in the PDFs folder 
- We interpreted "Each Intern will be assigned a Washing Method when instantiated" to mean that each Intern's washing behavior will be randomized in the Intern constructor, so Intern is programmed as such. 
- I created a data type called IntIntTuple so that I could return the resulting cleanliness, whether or not the wash ended up breaking the vehicle, and the wash type. This is not necessary for the Detailed wash behavior, but is useful for returning whether or not the vehicle has become Like-New (Elbow Grease wash) or Broken (Chemical wash).
- We are creating new Drivers when they get injured, we are not promoting interns 
- The first day's ending total will likely not be $500,000 becuase we have the dealership purchase the initial inventory vehicles rather than start with them for free. 
    - With the added vehicle types, initial costs will cause a lot of bailouts on day 1. Will happen a lot the first couple days due to cost of super cars, monster trucks, and the number of cars we start off with
- We used a naming system of employee type then id number to make unique names (Example: intern_4 or mechanic_2)
    - When interns get promoted, per the write up the new staff member keeps interns name so we have mechanics or sales persons at times named "intern_#"
- Output file names are hardcoded in to the main function. If you need to run the code again, please delete these files first, because an error will be thrown if the file already exists
 
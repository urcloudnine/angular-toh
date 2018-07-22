# GUI-Tests for Angular Tour of Heroes 
This is a maven project, which contain e2e java-tests for Tour of Heroes web-application.

## Prepare enviroment
1. Ensure that you have jdk1.8.0_181 installed on your machine. (or download here: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Download and install latest version of Maven: https://maven.apache.org/download.cgi 
Check it by command "mvn -version".
3. *Important!* Put the path to chromedriver.exe into your environment variable PATH, otherwise you should define full path to chromedriver.exe in setup method of MainScenarios class. 

## Run tests
1. Start web-application Tour of Heroes
2. Go to directory of the project: ``cd testsfortoh``
3. Run tests by this command:  ``mvn test -Dtest=MainScenarios``
3`. If there are any problems with connection to Central Repository of maven, try to specify protocol by defining option: ``-Dhttps.protocols=TLSv1.2``
# UI Automation Test exercise for Airalo Website 

This project is a basic example of UI automation using Selenium WebDriver and TestNG for the Airalo website.
The script navigates to the Airalo homepage, searches for a specific eSIM package, and verifies various details of the selected package.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed (version 11.0.19).
- Maven installed.
- Chrome browser installed.

## Setup

1. **Clone the repository** :
   
   git clone (https://github.com/yagni-madhu/Airalo)
      
   **cd ui-automation-test**
Set up Maven project:
Ensure your pom.xml is configured to include the necessary dependencies for Selenium and TestNG. Add the following dependencies to your pom.xml if not already present:

<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.1.0</version>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.4.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>

**Running the Test**
Compile and run the test:
You can compile and run the test using your IDE or using Maven from the command line:
mvn test

**Test Description**
The test performs the following actions:

Opens the Airalo website.
Accepts cookies.
Closes any pop-ups.
Searches for the "Japan" eSIM package.
Selects the package and clicks "BUY NOW".
Verifies the following details of the selected package:
Operator name
Coverage
Data
Validity
Price

**Automate requests to the Airalo Partner API and validate the responses.**

This project contains API automation tests using RestAssured and TestNG. The purpose of these tests is to validate the status code and Response Bo .

**Table of Contents**

**Prerequisites**
Java JDK 11.0.19
Maven
An IDE (IntelliJ IDEA) 
**Setup**
Clone the repository
 git clone (https://github.com/yagni-madhu/Airalo)

Set up Maven project:
Ensure your pom.xml is configured to include the necessary dependencies for Rest-assured and TestNG. 
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.8.0</version>
            <scope>compile</scope>
        </dependency>

        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>5.1.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-path</artifactId>
            <version>5.1.1</version>
            <scope>test</scope>

  
**Project Structure**
**Steps:**
Posts an order to create eSIMs.
Retrieves the list of eSIMs.
Checks that the number of eSIMs is 6.
Verifies that each eSIM has the correct package slug.

**Endpoints**
**src/test/java/api_endpoints/Routes.java: Contains the endpoint URLs.**
The Routes class contains the URLs for the API endpoints used in this project. These include:
generateToken_url: URL to generate an access token.
post_url: URL to create an order for eSIMs.
get_url: URL to retrieve a list of eSIMs.

**src/test/java/api_endpoints/API_Resource.java: Contains the API methods and tests.**
**API_Resource Class**
This class contains the following methods:

authenticate()
Generates an access token using client credentials. ( It can be used to dynamically retrieve an OAuth2 token.)

postOrder()
Creates an order for 6 eSIMs with the package ID merhaba-7days-1gb.
Request Type: POST
Headers:
Authorization: Bearer token
Accept: application/json
Form Parameters:
quantity: 6
package_id: merhaba-7days-1gb
type: sim
description: creating order

getListOfEsims()
Retrieves a list of eSIMs.
Request Type: GET
Headers:
Authorization: Bearer token
Accept: application/json
Query Parameters:
include: order

**Usage**
To run the tests, use the following command:
mvn test

**Test Cases** 
testPostOrder()--> This method validates following order details
currency
package_id
quantity
type
description
esim_type
validity
package
data
price

testGetEsims()--> Validates that the API returns 6 eSIMs and that each eSIM has the correct package slug (merhaba-7days-1)




# Basic Java HCP Template

This repository is designed to be utilized as a quick start point for deploying a java rest service to Hana Cloud Platform.

### Prerequisites

1. Ensure Eclipse EE is installed [Eclipse Downloads](https://www.eclipse.org/downloads)
2. Install the appropriate SAP Cloud Tools [SAP Hana Tools Download](https://tools.hana.ondemand.com/)
3. Register for an account [Hana Account](https://account.hanatrial.ondemand.com)

### Getting Started

1. Clone or download this repository
```BASH
    $ git clone https://github.com/codeman869/Basic-Java-HCP-Template.git
```
2. Open the project in Eclipse  
> File -> Open Projects from File System

3. Right click the project and choose to Run as Maven Build  
> Run As -> 5 Maven Build... 

4. Enter the goals as "clean package install" and click run

5. Once the packages are downloaded and the WAR package is built, it can be deployed to the Hana Cloud Platform

6. Right click the project in Eclipse and choose to run on server  
> Run As -> 1 Run on Server

7. In the dialog that pops up, choose Manually define a new server, and choose SAP Cloud Platform underneath the SAP folder

8. Enter the region host in the box (eg. hanatrial.ondemand.com) and click Next

9. Enter the application name and enter your account information and password
> Subaccount -> sxxxxxxxxx  
> Username -> sxxxxxxx  
> Password -> xxxxxx  

10. Click finish and the application will be deployed to the cloud and started

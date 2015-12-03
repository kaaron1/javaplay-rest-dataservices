Java Hello World Web Starter

This sample application demonstrates how to write a Java Web application (powered by WebSphere Liberty) and deploy it on Bluemix.

Files

The Java Hello World Web Starter application contains the following contents:

*   javaHelloWorldApp.war

    This WAR file is actually the application itself. It is the only file that'll be pushed to and run on the Bluemix cloud. Every time your application code is updated, you'll need to regenerate this WAR file and push to Bluemix again. See the next section on detailed steps.
    
*   WebContent/

    This directory contains the client side code (HTML/CSS/JavaScript) of your application as well as compiled server side java classes and necessary JAR libraries.
    
*   src/

    This directory contains the server side code (JAVA) of your application. In this simple starter application, there's only one class: 'com.ibm.cloudoe.samples.HelloResource'
    
*   build.xml

    This file allows you to easily build your application using Ant.
    
*	dep-jar/

	This directory contains libraries that are needed to compile locally, but are provided by Bluemix and not included in the WAR file. 
	
	<h1>An alternative for deployment</h1>
	Badge: 
	![Bluemix Deployments](https://deployment-tracker.mybluemix.net/stats/74729048258053b317ecd0a4a13ab12e/badge.svg)
	
	[![Deploy to Bluemix](https://deployment-tracker.mybluemix.net/stats/74729048258053b317ecd0a4a13ab12e/button.svg)](https://bluemix.net/deploy?repository=https://github.com/kaaron1/javaplay-rest-dataservices.git)
	
	
	
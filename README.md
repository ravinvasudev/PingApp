# PingApp

A utility program to complement the command line ping functionality. The core features are written in Java 8 with Spring Boot support for bean life cycle management.

Send multiple iterative commands in parallel; all thanks to Java 8 concurrancy support.

Post reporting data to external HTTP URL.

# How-To's

1) BUILD RUNNABLE JAR AND START THE APPLICATION WITH "java -jar ping-app-1.0.jar"

2) ALL TIMES ARE IN MILLISECONDS.

3) ICMP AND TRACEROUTE HAS DIFFERENT CONVENTIONS TO WRITE COMMAND BASED ON OPERATING SYSTEM.

4) BY DEFAULT, WE ARE USING WINDOWS COMMAND FORMAT. YOU CAN CHANGE TO THE RESPECTIVE SYSTEM.

5) CHANGE CONFIGURATION VALUES IN application.properties FILE

6) IF YOU WISH TO CHANGE THE LOGGING FILE, ENSURE THAT THE LOGGING FILE NAME SHOULD MATCH WITH THE ONE PROVIDED (logback.xml), UNLESS OTHERWISE UPDATED IN application.properties FILE

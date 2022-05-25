# AppDynamics MQ Agent Plugin

This plugin is intended to replace the Machine Agent Based Extension for MQ, and collect the data needed by interception rather than query

## Requirements
- MQ Version 9.2.5.0+ (This is the client lib version in use, it may support many versions, we will have to see)
- Agent version 22.1+
- Java 8+


## Deployment steps
- Copy AgentPlugin jar file under <agent-install-dir>/ver.x.x.x.x/sdk-plugins

## Configuration options

If the users the application is authenticating with the MQ Queue manager are not authorized to monitor the queue, we will need to configure authentication information for the queue manager that is authorized. This is in an optional JSON file in the sdk-plugins directory named "IBMMQAgentPlugin-authentications.json"
Here is an example of such a file:

    [
        { "hostname": "localhost", "port": 1414, "userID": "admin", "password": "passw0rd" },
        { "hostname": "someOtherHost", "port": 1414, "userID": "someOtherUser", "password": "someOtherPassword" }
    ]
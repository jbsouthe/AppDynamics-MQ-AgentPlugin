package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException;
import com.cisco.josouthe.exception.MQErrorException;
import com.cisco.josouthe.util.ExceptionUtility;
import com.cisco.josouthe.exception.UserNotAuthorizedException;

import java.util.ArrayList;
import java.util.List;


public class PCFMessageAgentWrapper extends BaseWrapper{
    private IReflector send;
    private IReflector constructor, setWaitInterval;

    public PCFMessageAgentWrapper(ASDKPlugin aGenericInterceptor, MQQueueManagerWrapper mqQueueManager) throws UserNotAuthorizedException {
        super(aGenericInterceptor, null, mqQueueManager.getObject());
        try{
            this.object = constructor.execute( mqQueueManager.getObject().getClass().getClassLoader(), null, new Object[] { mqQueueManager.getObject() } );
            setWaitInterval(5);
            logger.debug("Initialized IBM MQ PCFMessageAgent for monitoring, with reflection");
        } catch (Exception exception) {
            if( ExceptionUtility.exceptionMatches(exception, "MQJE001: Completion Code '2', Reason '2035'.") )
                    throw new UserNotAuthorizedException( mqQueueManager, exception.getCause().getCause().getMessage());
            Throwable sourceException = ExceptionUtility.getRootCause(exception);
            MQErrorException mqErrorException = ExceptionUtility.processException(exception);
            if( mqErrorException == null ) {
                logger.debug(String.format("Error initializing reflective PCFMessageAgent Exception: %s", sourceException.toString()),sourceException);
            }else {
                logger.debug(String.format("Error initializing reflective PCFMessageAgent Exception: %s", mqErrorException.getMessage()),mqErrorException);
            }
        }
    }

    protected void initMethods() {
        send = makeInvokeInstanceMethodReflector("send", "com.ibm.mq.headers.pcf.PCFMessage");
        setWaitInterval = makeInvokeInstanceMethodReflector( "setWaitInterval", int.class.getCanonicalName(), int.class.getCanonicalName() );
        constructor = interceptor.getNewReflectionBuilder()
                .createObject("com.ibm.mq.headers.pcf.PCFMessageAgent", "com.ibm.mq.MQQueueManager")
                .build();
    }

    public void setWaitInterval( int wait ) {
        setWaitInterval(wait, wait);
    }

    public void setWaitInterval( int wait, int expiry ) {
        try {
            setWaitInterval.execute(this.object.getClass().getClassLoader(), this.object, new Object[]{wait, expiry});
        } catch (Exception e) {
            Throwable rootException = ExceptionUtility.getRootCause(e);
            MQErrorException mqErrorException = ExceptionUtility.processException(e);
            if( mqErrorException == null ) {
                logger.debug(String.format("Error in PCFMessageAgent.setWaitInterval( %d, %d ), message: %s", wait, expiry, rootException.getMessage()));
            }else {
                logger.debug(String.format("Error in PCFMessageAgent.setWaitInterval( %d, %d ), message: %s", wait, expiry, mqErrorException.getMessage()));
            }
        }
    }

    public List<PCFMessageWrapper> send( PCFMessageWrapper request ) {
        return send( request.getObject() );
    }

    public List<PCFMessageWrapper> send( Object request ) {
        List<PCFMessageWrapper> responses = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        Object[] responseObjects = null;
        try {
            responseObjects = (Object[]) send.execute(this.object.getClass().getClassLoader(), this.object, new Object[]{request});
        } catch (ReflectorException e) {
            Throwable rootException = ExceptionUtility.getRootCause(e);
            MQErrorException mqErrorException = ExceptionUtility.processException(e);
            if( mqErrorException == null ) {
                logger.debug(String.format("Error in PCFMessageAgent.send( '%s' ), message: %s", request, rootException.getMessage()));
            }else {
                logger.debug(String.format("Error in PCFMessageAgent.send( '%s' ), message: %s", request, mqErrorException.getMessage()));
            }
            return responses;
        }
        long durationTime = System.currentTimeMillis() - startTime;
        StringBuffer responseStrings = new StringBuffer();
        if( responseObjects != null )
            for( Object responseObject : responseObjects ) {
                responses.add(new PCFMessageWrapper(this.interceptor, responseObject, this.getObject()));
                responseStrings.append(responseObject.toString());
            }
        logger.debug(String.format("PCFMessageAgent.send( %s ) took %d milliseconds to return %d responses: '%s'", request, durationTime, responses.size(), responseStrings));
        return responses;
    }

}

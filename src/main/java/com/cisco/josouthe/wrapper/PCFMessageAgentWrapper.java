package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException;
import com.cisco.josouthe.exception.MQErrorException;
import com.cisco.josouthe.util.ExceptionUtility;
import com.cisco.josouthe.exception.UserNotAuthorizedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PCFMessageAgentWrapper extends BaseWrapper{
    private IReflector send;
    private IReflector constructor, setWaitInterval;

    public PCFMessageAgentWrapper(ASDKPlugin aGenericInterceptor, MQQueueManagerWrapper mqQueueManager) throws UserNotAuthorizedException {
        super(aGenericInterceptor, null, mqQueueManager.getObject());
        try{
            this.object = constructor.execute( mqQueueManager.getObject().getClass().getClassLoader(), null, new Object[] { mqQueueManager.getObject() } );
            setWaitInterval(5);
            logger.info("Initialized IBM MQ PCFMessageAgent for monitoring, with reflection");
        } catch (Exception exception) {
            if( ExceptionUtility.exceptionMatches(exception, "MQJE001: Completion Code '2', Reason '2035'.") )
                    throw new UserNotAuthorizedException( mqQueueManager, exception.getCause().getCause().getMessage());
            Throwable sourceException = ExceptionUtility.getRootCause(exception);
            //logger.info(String.format("Error initializing reflective PCFMessageAgent Exception: %s", sourceException.toString()),sourceException);
            MQErrorException mqErrorException = ExceptionUtility.processException(exception);
            if( mqErrorException == null ) {
                logger.info(String.format("Error initializing reflective PCFMessageAgent Exception: %s", sourceException.toString()),sourceException);
            }else {
                logger.info(String.format("Error initializing reflective PCFMessageAgent Exception: %s", mqErrorException.getMessage()),mqErrorException);
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

    public Map<String,Object> getMetrics(Integer command, Map<Integer, Integer[]> parameterMap) {
        logger.info(String.format("getMetrics( %d, %s) this wrapped object: %s", command, parameterMap, String.valueOf(this.object)));
        Map<String,Object> metrics = new HashMap<>();
        PCFMessageWrapper request = new PCFMessageWrapper(this.interceptor, this.object, command); //CMQCFC.MQCMD_INQUIRE_Q_MGR_STATUS
        if( parameterMap != null && parameterMap.size() > 0 )
            for( Integer parameter : parameterMap.keySet() )
                request.addParameter(parameter, parameterMap.get(parameter) ); //CMQCFC.MQIACF_Q_MGR_STATUS_ATTRS, new int[] { CMQCFC.MQIACF_ALL }
        PCFMessageWrapper[] responses = send(request.getObject());
        if( responses == null || responses.length == 0 ) return metrics;
        for( PCFMessageWrapper response : responses ) {
            metrics.put( response.toString(), response);
            logger.info(String.format("Queue %s response %s", response.getQueueName(), response.toString()));
        }
        return metrics;
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
                logger.info(String.format("Error in PCFMessageAgent.setWaitInterval( %d, %d ), message: %s", wait, expiry, rootException.getMessage()));
            }else {
                logger.info(String.format("Error in PCFMessageAgent.setWaitInterval( %d, %d ), message: %s", wait, expiry, mqErrorException.getMessage()));
            }
        }
    }

    public PCFMessageWrapper[] send( Object request ) {
        List<PCFMessageWrapper> responses = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        Object[] responseObjects = null;
        try {
            responseObjects = (Object[]) send.execute(this.object.getClass().getClassLoader(), this.object, new Object[]{request});
        } catch (ReflectorException e) {
            Throwable rootException = ExceptionUtility.getRootCause(e);
            MQErrorException mqErrorException = ExceptionUtility.processException(e);
            if( mqErrorException == null ) {
                logger.info(String.format("Error in PCFMessageAgent.send( '%s' ), message: %s", request, rootException.getMessage()));
            }else {
                logger.info(String.format("Error in PCFMessageAgent.send( '%s' ), message: %s", request, mqErrorException.getMessage()));
            }
        }
        long durationTime = System.currentTimeMillis() - startTime;
        if( responseObjects != null )
            for( Object object : responseObjects ) {
                responses.add(new PCFMessageWrapper(this.interceptor, object, this.getObject()));
            }
        logger.info(String.format("PCFMessageAgent.send( %s ) took %d milliseconds to return %d responses", request, durationTime, responses.size()));
        return responses.toArray(new PCFMessageWrapper[0]);
    }

}

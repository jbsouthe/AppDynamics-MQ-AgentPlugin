package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.util.ExceptionUtility;
import com.cisco.josouthe.exception.UserNotAuthorizedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cisco.josouthe.util.PrintUtility;

public class PCFMessageAgentWrapper extends BaseWrapper{
    private IReflector send;
    private IReflector constructor;

    public PCFMessageAgentWrapper(AGenericInterceptor aGenericInterceptor, MQQueueManagerWrapper mqQueueManager) throws UserNotAuthorizedException {
        super(aGenericInterceptor, null, mqQueueManager.getObject());
        try{
            send = makeInvokeInstanceMethodReflector("send", "com.ibm.mq.headers.pcf.PCFMessage");
            constructor = interceptor.getNewReflectionBuilder()
                    .createObject("com.ibm.mq.headers.pcf.PCFMessageAgent", "com.ibm.mq.MQQueueManager")
                    .build();
            this.object = constructor.execute( mqQueueManager.getObject().getClass().getClassLoader(), null, new Object[] { mqQueueManager.getObject() } );
            logger.info("Initialized IBM MQ PCFMessageAgent for monitoring, with reflection");
        } catch (Exception exception) {
            if( exception.getCause() instanceof java.lang.reflect.InvocationTargetException && "com.ibm.mq.headers.MQExceptionWrapper".equals(exception.getCause().getCause().getClass().getCanonicalName())) {
                if( "MQJE001: Completion Code '2', Reason '2035'.".equals( exception.getCause().getCause().getMessage() ) ) {
                    throw new UserNotAuthorizedException( mqQueueManager.getHostname(), mqQueueManager.getPort(), mqQueueManager.getUserID(), mqQueueManager.getChannel(), exception.getCause().getCause().getMessage());
                }
                //logger.info(String.format("Exception cause class: '%s' message: '%s'",mqException.getClass().getCanonicalName(), mqException.getMessage()));
            }
            Throwable sourceException = ExceptionUtility.getRootCause(exception);
            logger.info(String.format("Error initializing reflective PCFMessageAgent Exception: %s", sourceException.toString()),sourceException);
        }
    }

    public Map<String,Object> getMetrics(Integer command, Integer parameter, int... arguments) {
        logger.info(String.format("getMetrics( %d, %d, %s [%d]) this wrapped object: %s", command, parameter, PrintUtility.arrayToString(arguments), arguments.length, String.valueOf(this.object)));
        Map<String,Object> metrics = new HashMap<>();
        PCFMessageWrapper request = new PCFMessageWrapper(this.interceptor, this.object, command); //CMQCFC.MQCMD_INQUIRE_Q_MGR_STATUS
        if( parameter != null )
            request.addParameter(parameter, arguments ); //CMQCFC.MQIACF_Q_MGR_STATUS_ATTRS, new int[] { CMQCFC.MQIACF_ALL }
        PCFMessageWrapper[] responses = send(request.getObject());
        if( responses == null || responses.length == 0 ) return metrics;
        for( PCFMessageWrapper response : responses ) {
            metrics.put( response.toString(), response);
            logger.info(String.format("Queue %s response %s", response.getQueueName(), response.toString()));
        }
        return metrics;
    }



    public PCFMessageWrapper[] send( Object request ) {
        List<PCFMessageWrapper> responses = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        Object[] responseObjects = (Object[]) getReflectiveObject( this.object, send, request);
        long durationTime = System.currentTimeMillis() - startTime;
        if( responseObjects != null )
            for( Object object : responseObjects )
                responses.add( new PCFMessageWrapper(this.interceptor, object) );
        logger.info(String.format("PCFMessageAgent.send( %s ) took %d milliseconds to return %d responses", request, durationTime, responses.size()));
        return responses.toArray(new PCFMessageWrapper[0]);
    }

}

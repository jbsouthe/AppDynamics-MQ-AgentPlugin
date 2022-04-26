package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PCFMessageAgentWrapper extends BaseWrapper{
    IReflector send;

    public PCFMessageAgentWrapper(AGenericInterceptor aGenericInterceptor, Object mqQueueManagerObject) {
        super(aGenericInterceptor, null, mqQueueManagerObject);
        try{
            Class<?> pcfMessageAgentClass = mqQueueManagerObject.getClass().getClassLoader().loadClass("com.ibm.mq.headers.pcf.PCFMessageAgent");
            Class<?> mqQueueManagerClass = mqQueueManagerObject.getClass().getClassLoader().loadClass("com.ibm.mq.MQQueueManager");
            Constructor constructor = pcfMessageAgentClass.getConstructor( mqQueueManagerClass );
            this.object = constructor.newInstance(mqQueueManagerObject);
            send = makeInvokeInstanceMethodReflector("send", "com.ibm.mq.headers.pcf.PCFMessage");
            logger.info("Initialized IBM MQ PCFMessageAgent for monitoring, with reflection");
        } catch (Exception exception) {
            logger.info(String.format("Error initializing reflective PCFMessageAgent Exception: %s", exception.toString()),exception);
        }
    }

    public Map<String,Object> getMetrics(Integer command, Integer parameter, int... arguments) {
        logger.info(String.format("getMetrics( %d, %d, %s (%d) this wrapped object: %s", command, parameter, arrayToString(arguments), arguments.length, String.valueOf(this.object)));
        Map<String,Object> metrics = new HashMap<>();
        PCFMessageWrapper request = new PCFMessageWrapper(this.interceptor, this.object, command); //CMQCFC.MQCMD_INQUIRE_Q_MGR_STATUS
        if( parameter != null )
            request.addParameter(parameter, arguments ); //CMQCFC.MQIACF_Q_MGR_STATUS_ATTRS, new int[] { CMQCFC.MQIACF_ALL }
        PCFMessageWrapper[] responses = send(request.getObject());
        if( responses == null || responses.length == 0 ) return metrics;

        return metrics;
    }

    private String arrayToString( int... array ) {
        StringBuilder sb = new StringBuilder("int[] { ");
        for( int i : array )
            sb.append(i).append(" ");
        sb.append("}");
        return sb.toString();
    }


    public PCFMessageWrapper[] send( Object request ) {
        List<PCFMessageWrapper> responses = new ArrayList<>();
        Object[] responseObjects = (Object[]) getReflectiveObject( this.object, send, request);
        for( Object object : responseObjects )
            responses.add( new PCFMessageWrapper(this.interceptor, object) );
        return responses.toArray(new PCFMessageWrapper[0]);
    }

}

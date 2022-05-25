package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.exception.UserNotAuthorizedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PCFMessageAgentWrapper extends BaseWrapper{
    IReflector send;
    IReflector constructor;

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
            /*[main] 24 May 2022 12:04:25,933  INFO QManagerLifeCycleInterceptor - Error initializing reflective PCFMessageAgent Exception: com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException: java.lang.reflect.InvocationTargetException
com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException: java.lang.reflect.InvocationTargetException
	at com.singularity.ee.agent.appagent.kernel.reflection.reflector.step.ConstructObject.run(ConstructObject.java:36) ~[appagent.jar:?]
	at com.singularity.ee.agent.appagent.kernel.reflection.reflector.JavaReflector.execute(JavaReflector.java:51) ~[appagent.jar:?]
	at com.singularity.ee.agent.appagent.kernel.reflection.reflector.JavaReflector.execute(JavaReflector.java:36) ~[appagent.jar:?]
	at com.cisco.josouthe.wrapper.PCFMessageAgentWrapper.<init>(PCFMessageAgentWrapper.java:29) ~[?:?]
	at com.cisco.josouthe.monitor.MQMonitor.<init>(MQMonitor.java:30) ~[?:?]
	at com.cisco.josouthe.QManagerLifeCycleInterceptor.onMethodEnd(QManagerLifeCycleInterceptor.java:118) ~[?:?]
	at com.singularity.ee.agent.appagent.services.transactionmonitor.common.sdk.GenericInterceptorDispatch.onMethodEndTracked(GenericInterceptorDispatch.java:84) ~[?:?]
	at com.singularity.ee.agent.appagent.services.bciengine.AFastTrackedMethodInterceptor.onMethodEnd(AFastTrackedMethodInterceptor.java:83) ~[appagent-boot.jar:?]
	at com.singularity.ee.agent.appagent.kernel.bootimpl.FastMethodInterceptorDelegatorImpl.safeOnMethodEndNoReentrantCheck(FastMethodInterceptorDelegatorImpl.java:503) ~[?:?]
	at com.singularity.ee.agent.appagent.kernel.bootimpl.FastMethodInterceptorDelegatorImpl.safeOnMethodEnd(FastMethodInterceptorDelegatorImpl.java:429) ~[?:?]
	at com.singularity.ee.agent.appagent.entrypoint.bciengine.FastMethodInterceptorDelegatorBoot.safeOnMethodEnd(FastMethodInterceptorDelegatorBoot.java:119) ~[?:Server Agent #22.2.0.33545 v22.2.0 GA compatible with 4.4.1.0 r68a953e88be153c235c4051ac8ac89c6cff2e3f8 release/22.2.0]
	at com.singularity.ee.agent.appagent.entrypoint.bciengine.FastMethodInterceptorDelegatorBoot.safeOnMethodEndNormal(FastMethodInterceptorDelegatorBoot.java:97) ~[?:Server Agent #22.2.0.33545 v22.2.0 GA compatible with 4.4.1.0 r68a953e88be153c235c4051ac8ac89c6cff2e3f8 release/22.2.0]
	at com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl.createContext(JmsConnectionFactoryImpl.java:527) ~[?:?]
	at JmsPutGet.runOne(JmsPutGet.java:108) ~[?:?]
	at JmsPutGet.main(JmsPutGet.java:76) ~[?:?]
Caused by: java.lang.reflect.InvocationTargetException
	at jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[?:?]
	at jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:64) ~[?:?]
	at jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[?:?]
	at java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) ~[?:?]
	at java.lang.reflect.Constructor.newInstance(Constructor.java:481) ~[?:?]
	at com.singularity.ee.agent.appagent.kernel.reflection.reflector.step.ConstructObject.run(ConstructObject.java:34) ~[appagent.jar:?]
	... 14 more
Caused by: com.ibm.mq.headers.MQExceptionWrapper: MQJE001: Completion Code '2', Reason '2035'.
	at jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[?:?]
	at jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:64) ~[?:?]
	at jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[?:?]
	at java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) ~[?:?]
	at java.lang.reflect.Constructor.newInstance(Constructor.java:481) ~[?:?]
	at com.ibm.mq.headers.MQDataException.getMQDataException(MQDataException.java:303) ~[?:?]
	at com.ibm.mq.headers.pcf.PCFAgent.open(PCFAgent.java:634) ~[?:?]
	at com.ibm.mq.headers.pcf.PCFAgent.open(PCFAgent.java:494) ~[?:?]
	at com.ibm.mq.headers.pcf.PCFAgent.connect(PCFAgent.java:251) ~[?:?]
	at com.ibm.mq.headers.pcf.PCFAgent.<init>(PCFAgent.java:188) ~[?:?]
	at com.ibm.mq.headers.pcf.PCFMessageAgent.<init>(PCFMessageAgent.java:133) ~[?:?]
	at jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[?:?]
	at jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:64) ~[?:?]
	at jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[?:?]
	at java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) ~[?:?]
	at java.lang.reflect.Constructor.newInstance(Constructor.java:481) ~[?:?]
	at com.singularity.ee.agent.appagent.kernel.reflection.reflector.step.ConstructObject.run(ConstructObject.java:34) ~[appagent.jar:?]
	... 14 more


             */
            if( exception.getCause() instanceof java.lang.reflect.InvocationTargetException && "com.ibm.mq.headers.MQExceptionWrapper".equals(exception.getCause().getCause().getClass().getCanonicalName())) {
                if( "MQJE001: Completion Code '2', Reason '2035'.".equals( exception.getCause().getCause().getMessage() ) ) {
                    throw new UserNotAuthorizedException( mqQueueManager.getHostname(), mqQueueManager.getPort(), mqQueueManager.getUserID(), exception.getCause().getCause().getMessage());
                }
                //logger.info(String.format("Exception cause class: '%s' message: '%s'",mqException.getClass().getCanonicalName(), mqException.getMessage()));
            }
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
        if( responseObjects != null )
            for( Object object : responseObjects )
                responses.add( new PCFMessageWrapper(this.interceptor, object) );
        return responses.toArray(new PCFMessageWrapper[0]);
    }

}

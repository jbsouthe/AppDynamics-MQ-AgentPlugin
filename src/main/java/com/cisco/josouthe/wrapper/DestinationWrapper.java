package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException;

public class DestinationWrapper extends BaseWrapper{
    private IReflector getQueueName, getTopicName, toString;
    private boolean typeIsQueue = false;
    private boolean typeIsTopic = false;
    private String name;

    public DestinationWrapper(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
        try {
            name = (String) getTopicName.execute(this.getObject().getClass().getClassLoader(), this.getObject());
            typeIsTopic = true;
        } catch (ReflectorException reflectorException) {
            //no op
        }
        try {
            name = (String) getQueueName.execute(this.getObject().getClass().getClassLoader(), this.getObject());
            typeIsQueue = true;
        } catch (ReflectorException reflectorException) {
            //no op
        }
    }

    @Override
    void initMethods() {
        getQueueName = makeInvokeInstanceMethodReflector("getQueueName");
        getTopicName = makeInvokeInstanceMethodReflector("getTopicName");
        toString = makeInvokeInstanceMethodReflector("toString");
    }

    public boolean isTopic() { return typeIsTopic; }
    public boolean isQueue() { return typeIsQueue; }
    public String getName() { return name; }
}

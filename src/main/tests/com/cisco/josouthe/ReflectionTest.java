package com.cisco.josouthe;

import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflectionBuilder;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorFactory;
import com.singularity.ee.agent.appagent.java9.Java9Module;
import com.singularity.ee.agent.appagent.kernel.*;
import com.singularity.ee.agent.appagent.kernel.config.AgentDirectoryResolver;
import com.singularity.ee.agent.appagent.kernel.reflection.reflector.JavaReflector;
import com.singularity.ee.agent.util.scrub.SensitiveDataScrubber;
import com.singularity.ee.controller.api.constants.AgentType;
import com.singularity.ee.util.loader.FileSystemClassLoader;
import com.singularity.ee.util.logging.ILogger;
import com.singularity.ee.util.logging.SysOutLogger;
import junit.framework.TestCase;
import org.hamcrest.CoreMatchers;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectionTest extends Assume {
    private LifeCycleManager lifeCycleManager;
    private IReflectionBuilder getNewReflectionBuilder() throws Exception {
        return ReflectorFactory.getInstance().getNewReflectionBuilder();
    }

    @Before
    public void setUp() {
        Assume.assumeThat(System.getProperty("java.version"), CoreMatchers.startsWith("1.8"));
        try {
            ILogger logger = new SysOutLogger("test");
            logger.info("works?");
            Instrumentation instrumentation = new NoOpInstrumentation();
            AgentKernel agentKernel = new AgentKernel(
                    new AgentInstallManager("test-version", "~/Code/AppDynamics-Java-Agents/Feb242022-v22.2.0.33545", AgentType.APM_APP_AGENT, null, new AgentDirectoryResolver(logger), "."),
                    new SensitiveDataScrubber(),
                    new AgentProperties(),
                    "test-version",
                    null,
                    ".",
                    instrumentation
            );
            AgentKernelComponent injector = DaggerAgentKernelComponent.builder().withAgentKernel(agentKernel).withJava9Module(new Java9Module(instrumentation)).build();
            lifeCycleManager = new LifeCycleManager(null, new FileSystemClassLoader(getClass().getClassLoader(), logger), null);

            Field serviceInjector = LifeCycleManager.class.getDeclaredField("serviceInjector");
            serviceInjector.setAccessible(true);
            serviceInjector.set(null, injector.getServiceComponent(lifeCycleManager));
        } catch (Exception exception) {
            System.out.println("Exception caught: "+ exception.toString());
            exception.printStackTrace();
        }
        assumeNotNull(lifeCycleManager);
    }

    @Test
    public void testMapReflection() throws Exception {
        ReflectorFactory.initialize(JavaReflector.Builder.class);
        ReflectorFactory.getInstance().getNewReflectionBuilder();

        LifeCycleManager.getInjector();
        Map<String,String> testMap = new HashMap<>();
        testMap.put("test","test");

        IReflector get = getNewReflectionBuilder().invokeInstanceMethod("get", true, new String[]{ Object.class.getCanonicalName() }).build();
        //String result = (String) get.execute(testMap.getClass().getClassLoader(), testMap, new Object[]{"test"});
        IReflector toString = getNewReflectionBuilder().invokeInstanceMethod("toString", true).build();
        String string = (String) toString.execute(testMap.getClass().getClassLoader(), testMap);
        System.out.println("map: "+ string);
        assert "{test=test}".equals(string);
    }

}

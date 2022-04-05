package com.cisco.josouthe;

import com.appdynamics.instrumentation.sdk.Rule;
import junit.framework.TestCase;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Before;
import org.junit.Test;

public class QManagerLifeCycleInterceptorTest extends TestCase {

    QManagerLifeCycleInterceptor qManagerLifeCycleInterceptor = null;

    public QManagerLifeCycleInterceptorTest() {

    }

    @Before
    public void setUp() {
        Configurator.setAllLevels("", Level.ALL);
        //qManagerLifeCycleInterceptor = new QManagerLifeCycleInterceptor();
    }

    @Test
    public void testOnMethodBegin() {
    }

    @Test
    public void testOnMethodEnd() {
    }

    @Test
    public void testInitializeRules() {
        /* this is proving tough to test with junit
        for( Rule rule : qManagerLifeCycleInterceptor.initializeRules() ) {
            System.out.println("Rule: "+ rule);
        }

         */
    }
}
package com.docler.ping.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ICMPTest.class, TraceRouteTest.class, TCPTest.class })
public class PingTestSuite {

}

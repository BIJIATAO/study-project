package cn.bijiatao;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 测试启动类
 * @author bijiatao
 */
public class BasicAppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BasicAppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(BasicAppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }
}

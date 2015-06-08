package org.cald.max.examples.concurrency;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author max
 */
public class SyncNGTest {

    private Long myNtfSeqNbrCounter = new Long(0);

    public SyncNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test
    public void hello() {
        List<Runnable> runnables = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            Runnable r = new Runnable() {

                @Override
                public void run() {
                    getNotificationSequenceNumber();
                }

            };
            runnables.add(r);
            Thread thread = new Thread(r);
            threads.add(thread);
            thread.start();
        }
        System.out.println("myNtfSeqNbrCounter = " + myNtfSeqNbrCounter);
        Assert.assertEquals(myNtfSeqNbrCounter.longValue(), 9L);
    }

    private Long getNotificationSequenceNumber() {
        Long result = null;
        synchronized (myNtfSeqNbrCounter) {
            result = new Long(myNtfSeqNbrCounter.longValue() + 1);
            myNtfSeqNbrCounter = new Long(result.longValue());
        }
        return result;

    }
}

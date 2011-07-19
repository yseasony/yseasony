package org.nuxeo.common.debug;

import java.util.Hashtable;

/**
 * Simple tool to measure elapsed time in code blocks.
 * <p>
 * Usage:
 * 
 * <pre>
 * Profile.checkpoint(&quot;my_routine&quot;);
 * // ... here is your routine .. to be measured
 * Profile.print(&quot;my_routine&quot;); // -&gt; print elapsed time in seconds.
 * </pre>
 * 
 * You can use nested checkpoints as well.
 * 
 * @author bstefanescu
 */
// TODO: add memory alloc. info
public final class Profiler {

    private static final Hashtable<String, CheckPoint> checkpoints = new Hashtable<String, CheckPoint>();

    private Profiler() {
    }

    public static void checkpoint(String name) {
        checkpoints.put(name, new CheckPoint());
    }

    public static void print(String name) {
        CheckPoint cp = checkpoints.get(name);
        if (cp != null) {
            double tm = new CheckPoint().timeElapsed(cp);
            System.out.println("### " + name + " > " + tm + " sec.");
        } else {
            System.out.println("### " + name + " > N/A");
        }
    }

    static class CheckPoint {
        public final long timestamp;

        public CheckPoint() {
            timestamp = System.currentTimeMillis();
        }

        public final double timeElapsed(CheckPoint cp) {
            return ((double) timestamp - (double) cp.timestamp) / 1000;
        }
    }

}

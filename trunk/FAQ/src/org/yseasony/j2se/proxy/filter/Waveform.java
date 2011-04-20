package org.yseasony.j2se.proxy.filter;

public class Waveform {
  private static long counter;
  /**
 * @uml.property  name="id"
 */
private final long id = counter++;
  public String toString() { return "Waveform " + id; }
} ///:~

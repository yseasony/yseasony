package org.yseasony.j2se.proxy.filter;

public class HighPass extends Filter {
  /**
 * @uml.property  name="cutoff"
 */
double cutoff;
  public HighPass(double cutoff) { this.cutoff = cutoff; }
  public Waveform process(Waveform input) { return input; }
} ///:~

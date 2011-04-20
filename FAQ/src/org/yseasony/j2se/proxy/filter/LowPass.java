package org.yseasony.j2se.proxy.filter;

public class LowPass extends Filter {
  /**
 * @uml.property  name="cutoff"
 */
double cutoff;
  public LowPass(double cutoff) { this.cutoff = cutoff; }
  public Waveform process(Waveform input) {
    return input; // Dummy processing
  }
} ///:~

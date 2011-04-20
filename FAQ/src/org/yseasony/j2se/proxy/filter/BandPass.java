package org.yseasony.j2se.proxy.filter;

public class BandPass extends Filter {
  /**
 * @uml.property  name="lowCutoff"
 */
double lowCutoff;
/**
 * @uml.property  name="highCutoff"
 */
double highCutoff;
  public BandPass(double lowCut, double highCut) {
    lowCutoff = lowCut;
    highCutoff = highCut;
  }
  public Waveform process(Waveform input) { return input; }
} ///:~

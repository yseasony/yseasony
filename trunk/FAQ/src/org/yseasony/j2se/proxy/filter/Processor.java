package org.yseasony.j2se.proxy.filter;

public interface Processor {
  String name();
  Object process(Object input);
} ///:~

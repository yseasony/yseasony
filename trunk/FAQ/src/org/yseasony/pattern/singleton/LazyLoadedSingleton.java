package org.yseasony.pattern.singleton;

/**
 * Date: 2009-11-1
 * Time: 23:32:43
 */
public class LazyLoadedSingleton {
    private LazyLoadedSingleton() {
    }

    /**
	 * @author  yy
	 */
    private static class LazyHolder {  //holds the singleton class
        /**
		 * @uml.property  name="singletonInstatnce"
		 * @uml.associationEnd  
		 */
        private static final LazyLoadedSingleton singletonInstatnce = new LazyLoadedSingleton();
    }

    public static LazyLoadedSingleton getInstance() {
        return LazyHolder.singletonInstatnce;
    }
}
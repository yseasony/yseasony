package org.yseasony.pattern.factory;

/**
 * Date: 2009-11-3
 * Time: 2:32:17
 */
public class ConcreteFactory implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProduct();
    }
}
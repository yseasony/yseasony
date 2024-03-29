package org.yseasony.pattern.factory;

/**
 * User: liujih
 * Date: Mar 20, 2011
 * Time: 10:21:07 PM
 */
public class Client {
    private Factory factory;

    public Client(Factory factory) {
        this.factory = factory;
    }

    @SuppressWarnings("unused")
	public void doSomething(){
        Product product= factory.createProduct();
        //to do something...
    }

    public static void main(String[] args) {
         Client client = new Client(new ConcreteFactory());
         client.doSomething();
    }
}

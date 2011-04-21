package org.yseasony.pattern.state;

/**
 * Date: 2010-7-25
 * Time: 20:05:56
 */
public class BlueState implements State {
    @Override
    public void push(PaintBoard paintBoard) {
        paintBoard.setState(new RedState());
    }

    @Override
    public void pull(PaintBoard paintBoard) {
        paintBoard.setState(new GreenState());
    }

    @Override
    public String name() {
        return "BLUE";
    }
}
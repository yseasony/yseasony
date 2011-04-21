package org.yseasony.pattern.state.pattern_enum;

/**
 * Date: 2010-7-25
 * Time: 17:34:29
 */
public interface State {
    void push(PaintBoard paintBoard);

    void pull(PaintBoard paintBoard);

    String name();
}
package org.yseasony.pattern.strategy.people;

import org.yseasony.pattern.strategy.travellable.ByAir;
import org.yseasony.pattern.strategy.travellable.ByCoach;
import org.yseasony.pattern.strategy.travellable.Travellable;


/**
 * Date: 2009-11-18
 * Time: 0:23:43
 */
public class PassengerByAirAndCoach extends HappyPeople {
    private Travellable first;
    private Travellable second;

    public PassengerByAirAndCoach() {
        first = new ByAir();
        second = new ByCoach();
    }

    @Override
    protected void travel() {
        first.travel();
        second.travel();
    }
}
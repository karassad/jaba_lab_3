package interfaces;

import myException.HealthException;

public interface Fightable {
    void fight(Fightable target) throws HealthException;
    boolean isAlive(Object target);

}

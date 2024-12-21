package characters;
import interfaces.Fightable;
import interfaces.UsualMove;
import myException.HealthException;
import weapons.Weapon;

public abstract class Human implements UsualMove, Fightable{
    protected String name;
    protected Weapon stuff;
    protected int health;
    protected boolean isSleeping;
    protected float forceCoeff;
    protected boolean alive;

    public Human(String name, Weapon stuff, int health, boolean isSleaping, float forceCoeff){
        this.name = name;
        this.stuff = stuff;
        this.health = health;
        this.isSleeping = isSleaping;
        this.forceCoeff = forceCoeff;
        this.alive = true;
    }


    public abstract void fight(Fightable target) throws HealthException;
    public abstract void die();
    public abstract void speak(String speach);
    public abstract boolean isAlive(Object target);

    public String wakeUp() {
        if (isSleeping) {
            isSleeping = false;
            return name + "наконец-то проснулся!";
        } else {
            isSleeping = true;
            return name + "всё ещё спит(((";
            }
    }

    public float getForceCoeff() {
        return forceCoeff;
    }

    public String getName() {
        return name;
    }

    public boolean getIsSleeping() {
        return isSleeping;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", stuff=" + stuff +
                ", health=" + health +
                ", isSleeping=" + isSleeping +
                ", forceCoeff=" + forceCoeff +
                ", alive=" + alive +
                '}';
    }
}
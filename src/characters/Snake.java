package characters;
import interfaces.Fightable;
import interfaces.UsualMove;
import myException.HealthException;
import weapons.Weapon;
import java.util.Random;

public class Snake implements UsualMove, Fightable {
    protected String name;
    protected int numberOfHeads;
    protected float forceCoeff;

    public Snake(String name, int numberOfHeads, float forceCoeff){
        this.name = name;
        this.numberOfHeads = numberOfHeads;
        this.forceCoeff = forceCoeff;
    }

    @Override
    public void fight(Fightable f) throws HealthException {
        if (!(f instanceof Fightable)) {
            throw new HealthException("Цель не является бойцом");
        }
        //проверка, жив ли атакующий
        if (!this.isAlive(f)) {
            throw new HealthException(name + " не может атаковать, так как он мертв.");
        }
        if (!f.isAlive(f)) {
            throw new HealthException(f.toString() + " не может быть атакован, так как он мертв.");
        }
        if (f instanceof Human) {
            Human humanTarget = (Human) f;

            Random random = new Random();
            int baseDamage = random.nextInt(1, 5);
            int specialDamage = (int) (baseDamage * humanTarget.forceCoeff);
            humanTarget.health -= specialDamage;
            System.out.println(name + " жестко жахнула по " + humanTarget.name);
            if (humanTarget.health <= 0) {
                humanTarget.health = 0;
                System.out.println(humanTarget.name + " получила " + specialDamage + " урона. Наш бравый боец не справился(");
            } else {
                System.out.println(humanTarget.name + " получила " + specialDamage + " урона и у него осталось " + humanTarget.health + " HP");
            }
        }
    }

    @Override
    public boolean isAlive(Object target) {
        // Проверка на живость для объектов типа Snake
        if (target instanceof Snake snake) {
            return snake.numberOfHeads > 0; // Проверка на живость змеи
        }

        // Проверка на живость для объектов типа Human (например, для самой Чурки или Принцессы)
        if (target instanceof Human human) {
            return human.getHealth() > 0; // Проверка на живость человека
        }

        // Если тип не поддерживается, выбрасываем исключение
        throw new IllegalArgumentException("Недопустимый класс ChurkaisAlive: " + target.getClass().getSimpleName());
    }


    public void die(){
        System.out.println(name + " обезголовился :D");
    }

    public void speak(String speach){
        System.out.println(speach);
    }

    public void swim(String name){
        System.out.println(name + " плывет к берегу");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Snake:\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Numbers of head: ").append(numberOfHeads).append("\n");
        sb.append("Force coefficient: ").append(forceCoeff);
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public int getNumberOfHeads() {
        return numberOfHeads;
    }

    @Override
    public boolean equals(Object obj) {
        // Проверяем, ссылается ли объект на тот же экземпляр
        if (this == obj) {
            return true;
        }
        // Проверяем, является ли объект экземпляром OlkhovayaChurka
        if (!(obj instanceof Snake)) {
            return false;
        }
        // Приводим объект к типу OlkhovayaChurka
        Snake other = (Snake) obj;

        // Сравниваем необходимые поля для определения равенства
        return name.equals(other.name) &&
                numberOfHeads == other.numberOfHeads &&
                Float.compare(forceCoeff, other.forceCoeff) == 0;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + numberOfHeads;
        result = 31 * result + Float.floatToIntBits(forceCoeff);
        return result;
    }

}

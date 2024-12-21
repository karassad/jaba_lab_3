package characters;
import interfaces.Fightable;
import myException.HealthException;
import weapons.Weapon;
import java.util.Random;

public class OlkhovayaChurka extends Human {

    public OlkhovayaChurka(String name, Weapon stuff, int health, boolean isSleaping, float forceCoeff) {
        super(name, stuff, health, isSleaping, forceCoeff);
    }

    @Override
    public void fight(Fightable f) throws HealthException {

//        Snake snakeTarget = (Snake) target;
        if (!(f instanceof Fightable)){
            throw new HealthException("Цель не является бойцом");
        }
        //проверка, жив ли атакующий
        if (!this.isAlive(this)){
            throw new HealthException(name +  " не может атаковать, так как он мертв.");
        }
        if (!f.isAlive(f)){
            throw new HealthException(f.toString() + " не может быть атакован, так как он мертв.");
        }
        if (f instanceof Snake target){
//            Snake target = (Snake) f;
            Random random = new Random();
            int baseDamage = random.nextInt(1, 5);
            int specialDamage = (int) (baseDamage * target.forceCoeff);
            int extraHeads = (int) (specialDamage/2);

            target.numberOfHeads -= extraHeads;
            System.out.println(name + " тыкнул мечом по врагу");
            if (target.numberOfHeads <= 0){
                target.numberOfHeads = 0;
                System.out.println(target.name + " потерял " + extraHeads + " головы! Враг побежден!");
            } else {
                System.out.println(target.name + " потерял " + extraHeads + " головы! Чурке нужно отрубить еще " + target.numberOfHeads);
            }
        }



//        if (target.numberOfHeads>0) {
//            Random random = new Random();
//            int baseDamage = random.nextInt(1, 5);
//            int specialDamage = (int) (baseDamage * snakeTarget.forceCoeff);
//            int extraHeads = (int) (specialDamage/2);
//
//            snakeTarget.numberOfHeads -= extraHeads;
//            System.out.println(name + " тыкнул мечом по врагу");
//            if (snakeTarget.numberOfHeads <= 0){
//                snakeTarget.numberOfHeads = 0;
//                System.out.println(snakeTarget.name + " потерял " + extraHeads + " головы! Враг побежден!");
//            } else {
//                System.out.println(snakeTarget.name + " потерял " + extraHeads + " головы! Чурке нужно отрубить еще " + snakeTarget.numberOfHeads);
//            }
//        } else {
//            throw new HealthException(snakeTarget.name + " не может быть атакован, т.к. количество его голов равно " + snakeTarget.numberOfHeads);
//        }
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
        alive = false;
        System.out.println(name + " пал смертью храбрых!");
    }

    public void speak(String speach){
        System.out.println(speach);
    }

    public void sleep(String name){
        System.out.println(name + " всё ещё сладко спит - zzz-zzz-zzz");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Churka:\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Health: ").append(health).append("\n");
        sb.append("Sleep or not(?): ").append(isSleeping ? "Да" : "Нет").append("\n");
        sb.append("Force coefficient: ").append(forceCoeff).append("\n");
        sb.append("Weapon: ").append(stuff).append("\n");
        sb.append("Alive: ").append(alive).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        // Проверяем, ссылается ли объект на тот же экземпляр
        if (this == obj) {
            return true;
        }
        // Проверяем, является ли объект экземпляром OlkhovayaChurka
        if (!(obj instanceof OlkhovayaChurka)) {
            return false;
        }
        // Приводим объект к типу OlkhovayaChurka
        OlkhovayaChurka other = (OlkhovayaChurka) obj;

        // Сравниваем необходимые поля для определения равенства
        return name.equals(other.name) &&
                health == other.health &&
                isSleeping == other.isSleeping &&
                Float.compare(forceCoeff, other.forceCoeff) == 0 &&
                (stuff != null ? stuff.equals(other.stuff) : other.stuff == null);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + health;
        result = 31 * result + (isSleeping ? 1 : 0);
        result = 31 * result + Float.floatToIntBits(forceCoeff);
        result = 31 * result + (stuff != null ? stuff.hashCode() : 0);
        return result;
    }



}



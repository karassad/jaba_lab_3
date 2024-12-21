package characters;
import interfaces.Fightable;
import myException.HealthException;
import weapons.Weapon;
import java.util.Random;

public class Princess extends Human{

    public Princess(String name, Weapon stuff, int health, boolean isSleaping, float forceCoeff){
        super(name, stuff, health, isSleaping, forceCoeff);
    }

//    @Override
//    public void fight(Fightable f) throws HealthException{
//        if (!(f instanceof Fightable)){
//            throw new HealthException("Цель не является бойцом");
//        }
//        //проверка, жив ли атакующий
//        if (!this.isAlive(f)){
//            throw new HealthException(name +  " не может атаковать, так как он мертв.");
//        }
//        if (!f.isAlive(f)){
//            throw new HealthException(f.toString() + " не может быть атакован, так как он мертв.");
//        }
//        if (f instanceof Snake){
//            Snake target = (Snake) f;
//            Random random = new Random();
//            int baseDamage = random.nextInt(1, 5);
//            int specialDamage = (int) (baseDamage * target.forceCoeff);
//            int extraHeads = (int) (specialDamage/2);
//
//            target.numberOfHeads -= extraHeads;
//            System.out.println(name + " тыкнул мечом по врагу");
//            if (target.numberOfHeads <= 0){
//                target.numberOfHeads = 0;
//                System.out.println(target.name + " потерял " + extraHeads + " головы! Враг побежден!");
//            } else {
//                System.out.println(target.name + " потерял " + extraHeads + " головы! Чурке нужно отрубить еще " + target.numberOfHeads);
//            }
//    }

    @Override
    public void fight(Fightable f) throws HealthException {
        if (!(f instanceof Fightable)){
            throw new HealthException("Цель не является бойцом");
        }
        //проверка, жив ли атакующий
        if (!this.isAlive(f)){
            throw new HealthException(name +  " не может атаковать, так как он мертв.");
        }
        if (!f.isAlive(f)){
            throw new HealthException(f.toString() + " не может быть атакован, так как он мертв.");
        }
        if (f instanceof Human) {
            Human humanTarget = (Human) f;
            Random random = new Random();
            int baseDamage = random.nextInt(1, 5);
            int specialDamage = (int) (baseDamage * humanTarget.forceCoeff);
            humanTarget.health -= specialDamage;
            System.out.println(name + " тыкнула по " + humanTarget.name+ " с помощью " + stuff);}
    }

    @Override
    public boolean isAlive(Object target) {
        if (target instanceof Human human) {
            return human.getHealth() > 0; // Проверка на живость для объекта Human
        } else if (target instanceof Snake snake) {
            return snake.numberOfHeads > 0; // Проверка на живость для объекта Snake
        }

        // Если тип не поддерживается, выбрасываем исключение
        throw new IllegalArgumentException("Недопустимый класс: " + target.getClass().getSimpleName());
    }

    public void die(){
        System.out.println(name + " умерла от горя :(");
    }

    public void speak(String speach){
        System.out.println(speach);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Princess:\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Health: ").append(health).append("\n");
        sb.append("Sleep or not(?): ").append(isSleeping ? "Да" : "Нет").append("\n");
        sb.append("Force coefficient: ").append(forceCoeff).append("\n");
        sb.append("Weapon: ").append(stuff).append("\n");
        return sb.toString();
    }

    public void wakeUp(OlkhovayaChurka target) throws HealthException{
        if (target instanceof OlkhovayaChurka){

            Random random = new Random();
            boolean targetWakeUp = random.nextBoolean();

            if (!targetWakeUp) {
                System.out.println(name + " пытается разбудить " + target.getName());
                System.out.println(target.getName() + " все еще спит: " + target.isSleeping);
            } else {
                target.isSleeping = false;
                fight(target);
                System.out.println(target.getName() + " наконец-то проснулся!");
            }
        } else {

            System.out.println(name + " может будить только Чурку!");
        }

    }

    @Override
    public boolean equals(Object obj) {
        // Проверяем, ссылается ли объект на тот же экземпляр
        if (this == obj) {
            return true;
        }
        // Проверяем, является ли объект экземпляром OlkhovayaChurka
        if (!(obj instanceof Princess)) {
            return false;
        }
        // Приводим объект к типу OlkhovayaChurka
        Princess other = (Princess) obj;

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

import myException.HealthException;
import myWorld.World;
import weapons.WeaponType;

public class Main {
    public static void main(String[] args) throws HealthException {

       World world = new World();
       world.goStats();
       world.playStory();
       world.goStats();
//
//        WeaponType x = WeaponType.SWORD;
//        System.out.println(x);



    }
}

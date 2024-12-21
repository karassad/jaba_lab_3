package myWorld;

import characters.OlkhovayaChurka;
import characters.Princess;
import characters.Sea;
import characters.Snake;
import weapons.Weapon;
import weapons.WeaponType;
import myException.HealthException;

import java.util.Random;

public class World{


    private Princess princess;
    private OlkhovayaChurka olkhovayaChurka;
    private Snake snake;
    private Sea sea;

    public World(){
        // Создаем оружие для Чурки и Принцессы
        Weapon sword = new Weapon(WeaponType.SWORD, "Sharp knife", 1);
        Weapon knife = new Weapon(WeaponType.KNIFE, "Sharp sword", 8);

        // Создаем персонажей
        this.princess = new Princess("Sofa", knife, 10, false, 1.1f);
        this.olkhovayaChurka = new OlkhovayaChurka("Churka", sword, 20, true, 1.5f);
        this.sea = new Sea();

        Random random = new Random();
        int countOfHeads = random.nextInt(5, 10);
        this.snake = new Snake("Zloi snake", countOfHeads,  1.6f);
    }

    public void goStats() {
        System.out.println("---------СТАТИСТИКА ВАШИХ ПЕРСОНАЖЕЙ---------");
        // Создаем массив строк с информацией о персонажах
        String[] stats = {
                princess.toString(),
                olkhovayaChurka.toString(),
                snake.toString()
        };
        for (String stat : stats) {
            System.out.println(stat);
        }
        System.out.println("-------------------------------------------");
    }


    public void playStory() throws HealthException{

        String pName = princess.getName();
        String cName = olkhovayaChurka.getName();
        String sName = snake.getName();

        System.out.println("*Пляж. Солнце. Ничего не предвещало беды. " + cName + " мирно спал на коленях " + pName + "*");
        olkhovayaChurka.sleep(cName);
        snake.swim(sName);
        princess.speak(pName + " кричит: Боже, там змей плывет! " + cName + " вставай!!!");
        System.out.println();

        while (olkhovayaChurka.getIsSleeping()){
            sea.sway(sea);
            princess.wakeUp(olkhovayaChurka);
        }
        System.out.println();
        snake.speak(sName+ ": Ну что, " + cName + ", готов к поражению?");
        olkhovayaChurka.speak(cName + ": Хвастливые речи только слабосильным говорить. Лучше без лишних слов к делу приступить!");

        while (olkhovayaChurka.getHealth()>0 && snake.getNumberOfHeads()>0){
            olkhovayaChurka.fight(snake);
            if (snake.getNumberOfHeads()>0) {
                snake.fight(olkhovayaChurka);
            }
            System.out.println();
        }

        if (olkhovayaChurka.getHealth()<=0){
            olkhovayaChurka.die();
            princess.die();
            System.out.println();
        } else {
            snake.die();
            princess.speak(pName + ": " + cName + " пошли со мной во дворец! Покажу своего спасителя отцу!");
            olkhovayaChurka.speak(cName + ": " + pName + " прости, но я не хочу. Иди одна во дворец)");
            System.out.println();
        }



    }

}

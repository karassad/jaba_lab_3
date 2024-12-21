package characters;

public class Sea {
    protected int amountOfSway;

    public Sea() {
        this.amountOfSway = 0;
    }

    public void sway(Sea subject){

        if (subject == null){
            throw new IllegalArgumentException("Ошибка: Передан объект Sea равный null.");
        }

        int[] firstArray = {1, 5, 6, 7, 8, 9};
        int[] secondArray = {2, 3, 4};
        subject.amountOfSway += 1;
          if (contains(firstArray, subject.amountOfSway)){
              System.out.println("Море всколыхнулось " + subject.amountOfSway + " раз");
          } else {
              System.out.println("Море всколыхнулось " + subject.amountOfSway + " раза");
          }

    }

    private boolean contains(int[] array, int value) {
        for (int element : array){
            if (element == value){
                return true;
            }
        }
        return false;
    }

    public int getAmountOfSway() {
        return amountOfSway;
    }
}

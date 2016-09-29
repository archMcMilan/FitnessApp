/**
 * Created by Artem_Pryzhkov on 9/28/2016.
 */
public class Day {
    private int drunkWater;
    private int eaten;
    private int walked;

    public void drink(int waterLiters) {
        drunkWater+=waterLiters;
    }

    public int getDrunk() {
        return drunkWater;
    }

    public void eat(int eatenCalories) {
        eaten+=eatenCalories;
    }

    public int getEaten() {
        return eaten;
    }

    public void walk(int stepsAmount) {
        walked+=stepsAmount;
    }

    public int getWalked() {
        return walked;
    }
}

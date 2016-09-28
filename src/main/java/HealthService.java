import java.time.LocalTime;

/**
 * Created by Artem_Pryzhkov on 9/28/2016.
 */
public class HealthService {
    //private LocalTime time=LocalTime.of(8,23);
    private Human human;
    private double waterPerDay;
    private int mealPerDay;
    private int stepsPerDay;


    public HealthService(Human human) {
        this.mealPerDay = 2500;
        this.waterPerDay= 3.5;
        this.stepsPerDay=2000;
        this.human=human;
    }

//    public LocalTime getTime() {
//        return time;
//    }

    public Human getHuman() {
        return human;
    }

    public int getMealPerDay() {
        return mealPerDay;
    }

    public double getWaterPerDay() {
        return waterPerDay;
    }

    public double getStepsPerDay() {
        return stepsPerDay;
    }

    public void drink(double waterLiters) {
        human.drink(waterLiters);
    }

    public void eat(int mealCalories) {
        human.eat(mealCalories);
    }

    public void walk(int stepsAmount) {
        human.walk(stepsAmount);
    }

    public int leftToEatToDay() {
        return mealPerDay-human.getEaten();
    }

    public double leftToDrinkToDay() {
        return waterPerDay-human.getDrunk();
    }

    public int leftToWalkToDay() {
        return stepsPerDay-human.getWalked();
    }
}

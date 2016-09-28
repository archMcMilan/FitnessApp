import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 9/28/2016.
 */
public class HealthService {
    //private LocalTime time=LocalTime.of(8,23);
    private Human human;
    private double waterPerDay;
    private int mealPerDay;
    private int stepsPerDay;
    private Map<LocalDate,Human> history= new HashMap<>();

    public HealthService(Human human) {
        this.mealPerDay = 2500;
        this.waterPerDay= 3.5;
        this.stepsPerDay=2000;
        this.human=human;
        history.put(LocalDate.now(),human);
    }

//    public LocalTime getTime() {
//        return time;
//    }

    public Human getHuman() {
        return history.get(LocalDate.now());
    }

    public Human getHuman(LocalDate date){
        return history.get(date);
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
        history.put(LocalDate.now(),human);
    }

    public void eat(int mealCalories) {
        human.eat(mealCalories);
        history.put(LocalDate.now(),human);
    }

    public void walk(int stepsAmount) {
        human.walk(stepsAmount);
        history.put(LocalDate.now(),human);
    }

    public int leftToEatToDay() {
        return mealPerDay-history.get(LocalDate.now()).getEaten();
    }

    public double leftToDrinkToDay() {
        return waterPerDay-history.get(LocalDate.now()).getDrunk();
    }

    public int leftToWalkToDay() {
        return stepsPerDay-history.get(LocalDate.now()).getWalked();
    }
}

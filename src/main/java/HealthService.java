import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 9/28/2016.
 */
public class HealthService {
    private Day day;
    private double waterPerDay;
    private int mealPerDay;
    private int stepsPerDay;
    private Map<LocalDate, Day> history = new HashMap<>();

    public HealthService() {
        this.mealPerDay = 2500;
        this.waterPerDay = 3.5;
        this.stepsPerDay = 2000;
        this.day = new Day();
        //history.put(LocalDate.now(), new Day());
    }

//    public LocalTime getTime() {
//        return time;
//    }

    public Day getDay(LocalDate date) {
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

    public void drink(double waterLiters,LocalDate date) {
        addDay();
        Day day2=history.get(date);
        day2.drink(waterLiters);
        day.drink(waterLiters);
        history.put(date, day2);
    }

    public void eat(int mealCalories,LocalDate date) {
        day.eat(mealCalories);
        history.put(date, day);
    }

    public void walk(int stepsAmount,LocalDate date) {
        day.walk(stepsAmount);
        history.put(date, day);
    }

    public int leftToEatThatDay(LocalDate date) {
        return mealPerDay - history.get(date).getEaten();
    }

    public double leftToDrinkThatDay(LocalDate date) {
        return waterPerDay - history.get(date).getDrunk();
    }

    public int leftToWalkThatDay(LocalDate date) {
        return stepsPerDay - history.get(date).getWalked();
    }

    public Map<LocalDate, Day> getHistory() {
        return history;
    }

    public boolean addDay() {
        if (!history.containsKey(LocalDate.now())){
            history.put(LocalDate.now(), new Day());
            return true;
        }
        return false;

    }
}

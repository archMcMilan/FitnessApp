import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 9/28/2016.
 */
public class HealthService {
    //private Day day;
    private int waterPerDay;
    private int mealPerDay;
    private int stepsPerDay;
    private Map<LocalDate, Day> history = new HashMap<>();

    public HealthService() {
        this.mealPerDay = 2500;
        this.waterPerDay = 3500;
        this.stepsPerDay = 2000;
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

    public int getWaterPerDay() {
        return waterPerDay;
    }

    public int getStepsPerDay() {
        return stepsPerDay;
    }

    public void drink(int waterLiters, LocalDate date) {
        addDay(date);
        Day day = history.get(date);
        day.drink(waterLiters);
        history.put(date, day);
    }

    public void eat(int mealCalories, LocalDate date) {
        addDay(date);
        Day day = history.get(date);
        day.eat(mealCalories);
        history.put(date, day);
    }

    public void walk(int stepsAmount, LocalDate date) {
        addDay(date);
        Day day = history.get(date);
        day.walk(stepsAmount);
        history.put(date, day);
    }

    public int leftToEatThatDay(LocalDate date) {
        return mealPerDay - history.get(date).getEaten();
    }

    public int leftToDrinkThatDay(LocalDate date) {
        return waterPerDay - history.get(date).getDrunk();
    }

    public int leftToWalkThatDay(LocalDate date) {
        return stepsPerDay - history.get(date).getWalked();
    }

    public Map<LocalDate, Day> getHistory() {
        return history;
    }

    public boolean addDay(LocalDate date) {
        if (!history.containsKey(date)) {
            history.put(date, new Day());
            return true;
        }
        return false;

    }
//
//    public Day getAverage(LocalDate startDate, LocalDate endDate) {
//        Day averageDay=new Day();
//        LocalDate currentDate=startDate;
//
//        while(!currentDate.equals(endDate)){
//
//            averageDay.drink(history.get(currentDate).getDrunk());
//            averageDay.eat(history.get(currentDate).getEaten());
//            averageDay.walk(history.get(currentDate).getWalked());
//        }
//    }

    public int getAverageDrinkLiters(LocalDate startDate, LocalDate endDate) {
        return getAverage(startDate,endDate).getDrunk();
    }

    public int getAverageCalories(LocalDate startDate, LocalDate endDate) {
        return getAverage(startDate,endDate).getEaten();
    }

    public int getAverageSteps(LocalDate startDate, LocalDate endDate) {
        return getAverage(startDate,endDate).getWalked();
    }

    public Day getAverage(LocalDate startDate, LocalDate endDate) {
        int waterAmount = 0;
        int caloriesAmount = 0;
        int stepsAmount=0;
        int daysAmount = 0;
        LocalDate currentDate = startDate;
        while (!currentDate.equals(endDate.plusDays(1))) {
            daysAmount++;
            caloriesAmount += history.get(currentDate).getEaten();
            waterAmount+= history.get(currentDate).getDrunk();
            stepsAmount+=history.get(currentDate).getWalked();
            currentDate = currentDate.plusDays(1);
        }
        Day averageDay=new Day();
        averageDay.drink(waterAmount/daysAmount);
        averageDay.eat(caloriesAmount / daysAmount);
        averageDay.walk(stepsAmount/daysAmount);
        return averageDay;
    }
}

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class HealthService {
    private int waterPerDay;
    private int caloriesPerDay;
    private int stepsPerDay;
    private Map<LocalDate, Day> history = new HashMap<>();

    public HealthService(int waterPerDay, int caloriesPerDay, int stepsPerDay) {
        this.waterPerDay = waterPerDay;
        this.caloriesPerDay = caloriesPerDay;
        this.stepsPerDay = stepsPerDay;
    }

    public Day getDay(LocalDate date) {
        return history.get(date);
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
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
        return caloriesPerDay - history.get(date).getEaten();
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

    public int leftToDrinkInPercent(LocalDate date) {
        return (int) (((double)history.get(date).getDrunk()/waterPerDay)*100);
    }

    public int leftToEatInPercent(LocalDate date) {
        return (int) (((double)history.get(date).getEaten()/ caloriesPerDay)*100);
    }

    public int leftToWalkInPercent(LocalDate date) {
        return (int) (((double)history.get(date).getWalked()/ stepsPerDay)*100);
    }

    public int getMedianDay(LocalDate startDate, LocalDate endDate,Action flag) {
        List<Integer> daysFromPeriod=new ArrayList<>();
        LocalDate currentDate = startDate;
        int daysAmountInPeriod=Period.between(startDate,endDate).getDays()+1;
        while (!currentDate.equals(endDate.plusDays(1))) {
            if(flag.equals(Action.DRINK)){
                daysFromPeriod.add(history.get(currentDate).getDrunk());
            }else if(flag.equals(Action.EAT)){
                daysFromPeriod.add(history.get(currentDate).getEaten());
            }else if(flag.equals(Action.WALK)){
                daysFromPeriod.add(history.get(currentDate).getWalked());
            }
            currentDate = currentDate.plusDays(1);
        }
        Collections.sort(daysFromPeriod);
        if(daysAmountInPeriod%2==0){
            int leftMedianDay=daysFromPeriod.get(daysAmountInPeriod/2-1);
            int rightMedianDay=daysFromPeriod.get(daysAmountInPeriod/2);
            return (leftMedianDay+rightMedianDay)/2;
        }else{
            return daysFromPeriod.get(daysAmountInPeriod/2);
        }
    }

    public int getMedianDrinkLiters(LocalDate startDate,LocalDate endDate){
        return getMedianDay(startDate,endDate,Action.DRINK);
    }
    public int getMedianEatCalories(LocalDate startDate,LocalDate endDate){
        return getMedianDay(startDate,endDate,Action.EAT);
    }
    public int getMedianWalkedSteps(LocalDate startDate,LocalDate endDate){
        return getMedianDay(startDate,endDate,Action.WALK);
    }
}


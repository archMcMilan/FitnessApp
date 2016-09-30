import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;


public class HealthMeTest {
    Day day;
    HealthService service;
    @Before
    public void setUp(){
        day =new Day();
        service=new HealthService(3500,2500,2000);
    }

    @Test
    public void drink(){
        day.drink(500);
        Assert.assertEquals(500, day.getDrunk());
    }

    @Test
    public void eat(){
        day.eat(1000);
        Assert.assertEquals(1000, day.getEaten());
    }

    @Test
    public void walk(){
        day.walk(500);
        Assert.assertEquals(500, day.getWalked());
    }

    @Test
    public void haveBreakfast(){
        day.eat(1000);
        Assert.assertEquals(1500,service.getCaloriesPerDay()- day.getEaten());
    }

    @Test
    public void drinkInTheMorning(){
        day.drink(1000);
        Assert.assertEquals(2500,service.getWaterPerDay()- day.getDrunk());
    }

    @Test
    public void stepsInTheMorning(){
        day.walk(500);
        Assert.assertEquals(1500,service.getStepsPerDay()- day.getWalked());
    }

    @Test
    public void addDrunkLiters(){
        service.drink(500,LocalDate.now());
        Assert.assertEquals(500,service.getDay(LocalDate.now()).getDrunk());
    }

    @Test
    public void addEatenCalories(){
        service.eat(1000,LocalDate.now());
        Assert.assertEquals(1000,service.getDay(LocalDate.now()).getEaten());
    }

    @Test
    public void addWalkedSteps(){
        service.walk(800,LocalDate.now());
        Assert.assertEquals(800,service.getDay(LocalDate.now()).getWalked());
    }

    @Test
    public void leftToEatToday(){
        service.eat(1000,LocalDate.now());
        service.eat(500,LocalDate.now());
        Assert.assertEquals(1000,service.leftToEatThatDay(LocalDate.now()));
    }

    @Test
    public void leftToDrinkToday(){
        service.drink(500,LocalDate.now());
        service.drink(2000,LocalDate.now());
        Assert.assertEquals(1000,service.leftToDrinkThatDay(LocalDate.now()));
    }

    @Test
    public void leftToWalkToday(){
        service.walk(500,LocalDate.now());
        service.walk(1300,LocalDate.now());
        Assert.assertEquals(200,service.leftToWalkThatDay(LocalDate.now()));
    }

    @Test
    public void addDay(){
        Assert.assertEquals(true,service.addDay(LocalDate.now()));
    }

    @Test
    public void getYesterdayCalories(){
        LocalDate yesterday=LocalDate.of(2016,9,28);
        service.eat(2000,yesterday);
        Assert.assertEquals(2000,service.getDay(yesterday).getEaten());
    }

    @Test
    public void getAverageWaterLitersPerWeek(){
        for(int day=23;day<30;day++){
            LocalDate date=LocalDate.of(2016,9,day);
            service.drink(1500+day,date);
        }
        Assert.assertEquals(1526,service.getAverageDrinkLiters(LocalDate.of(2016,9,23),LocalDate.of(2016,9,29)));
    }

    @Test
    public void getAverageCaloriesPerWeek(){
        for(int day=23;day<30;day++){
            LocalDate date=LocalDate.of(2016,9,day);
            service.eat(2000+day,date);
        }
        Assert.assertEquals(2026,service.getAverageCalories(LocalDate.of(2016,9,23),LocalDate.of(2016,9,29)));
    }

    @Test
    public void getAverageStepsPerWeek(){
        for(int day=23;day<30;day++){
            LocalDate date=LocalDate.of(2016,9,day);
            service.walk(2001+day,date);
        }
        Assert.assertEquals(2027,service.getAverageSteps(LocalDate.of(2016,9,23),LocalDate.of(2016,9,29)));
    }

    @Test
    public void leftToDrinkTodayInPercent(){
        service.drink(500,LocalDate.now());
        service.drink(1500,LocalDate.now());
        Assert.assertEquals(57,service.leftToDrinkInPercent(LocalDate.now()));
    }

    @Test
    public void leftToEatTodayInPercent(){
        service.eat(1000,LocalDate.now());
        service.eat(500,LocalDate.now());
        Assert.assertEquals(60,service.leftToEatInPercent(LocalDate.now()));
    }

    @Test
    public void leftToWalkTodayInPercent(){
        service.walk(500,LocalDate.now());
        service.walk(1300,LocalDate.now());
        Assert.assertEquals(90,service.leftToWalkInPercent(LocalDate.now()));
    }


    @Test
    public void getMedianPerPairedDaysAmount(){
        for(int day=20;day<26;day++){
            LocalDate date=LocalDate.of(2016,9,day);
            service.drink(900+day*2,date);
            service.eat(1000+day,date);
            service.walk(500+day*3,date);
        }
        service.drink(4000,LocalDate.of(2016,9,22));
        service.eat(4000,LocalDate.of(2016,9,22));
        service.walk(0,LocalDate.of(2016,9,22));
        Assert.assertEquals(947,service.getMedianDrinkLiters(LocalDate.of(2016,9,20),LocalDate.of(2016,9,25)));
        Assert.assertEquals(1023,service.getMedianEatCalories(LocalDate.of(2016,9,20),LocalDate.of(2016,9,25)));
        Assert.assertEquals(567,service.getMedianWalkedSteps(LocalDate.of(2016,9,20),LocalDate.of(2016,9,25)));
    }


    @Test
    public void getMedianPerUnpairedDaysAmount(){
        for(int day=21;day<26;day++){
            LocalDate date=LocalDate.of(2016,9,day);
            service.drink(900+day*2,date);
            service.eat(1000+day,date);
            service.walk(500+day*3,date);
        }
        service.drink(4000,LocalDate.of(2016,9,22));
        service.eat(4000,LocalDate.of(2016,9,22));
        service.walk(0,LocalDate.of(2016,9,22));
        Assert.assertEquals(948,service.getMedianDrinkLiters(LocalDate.of(2016,9,21),LocalDate.of(2016,9,25)));
        Assert.assertEquals(1024,service.getMedianEatCalories(LocalDate.of(2016,9,21),LocalDate.of(2016,9,25)));
        Assert.assertEquals(569,service.getMedianWalkedSteps(LocalDate.of(2016,9,21),LocalDate.of(2016,9,25)));
    }
}

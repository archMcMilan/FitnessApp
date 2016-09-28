import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Created by Artem_Pryzhkov on 9/28/2016.
 */

public class HealthMeTest {
    public static final double EPSILON=0.0001;
    Day day;
    HealthService service;
    @Before
    public void setUp(){
        day =new Day();
        service=new HealthService();
    }

    @Test
    public void drink(){
        day.drink(0.5);
        Assert.assertEquals(0.5, day.getDrunk(),EPSILON);
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
        Assert.assertTrue(service.getMealPerDay()- day.getEaten()==1500);
    }

    @Test
    public void drinkInTheMorning(){
        day.drink(1);
        Assert.assertTrue(service.getWaterPerDay()- day.getDrunk()==2.5);
    }

    @Test
    public void stepsInTheMorning(){
        day.walk(500);
        Assert.assertTrue(service.getStepsPerDay()- day.getWalked()==1500);
    }

    @Test
    public void addDrunkLiters(){
        service.drink(0.5,LocalDate.now());
        Assert.assertTrue(service.getDay(LocalDate.now()).getDrunk()-0.5==0);
    }

    @Test
    public void addEatenCalories(){
        service.eat(1000,LocalDate.now());
        Assert.assertTrue(service.getDay(LocalDate.now()).getEaten()-1000==0);
    }

    @Test
    public void addWalkedSteps(){
        service.walk(800,LocalDate.now());
        Assert.assertTrue(service.getDay(LocalDate.now()).getWalked()-800==0);
    }

    @Test
    public void leftToEatToday(){
        service.eat(1000,LocalDate.now());
        service.eat(500,LocalDate.now());
        Assert.assertEquals(1000,service.leftToEatThatDay(LocalDate.now()));
    }

    @Test
    public void leftToDrinkToday(){
        service.drink(0.5,LocalDate.now());
        service.drink(2,LocalDate.now());
        Assert.assertEquals(1,service.leftToDrinkThatDay(LocalDate.now()),EPSILON);
    }

    @Test
    public void leftToWalkToday(){
        service.walk(500,LocalDate.now());
        service.walk(1300,LocalDate.now());
        Assert.assertEquals(200,service.leftToWalkThatDay(LocalDate.now()));
    }

    @Test
    public void addDay(){
        Assert.assertEquals(true,service.addDay());
    }

//    @Test
//    public void getYesterday(){
//        service.addDay()
//        Assert.assertEquals(false,service.addDay());
//    }
}

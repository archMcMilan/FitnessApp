import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Artem_Pryzhkov on 9/28/2016.
 */

public class HealthMeTest {
    public static final double EPSILON=0.0001;
    Human human;
    HealthService service;
    @Before
    public void setUp(){
        human=new Human();
        service=new HealthService(human);
    }

    @Test
    public void drink(){
        human.drink(0.5);
        Assert.assertEquals(0.5,human.getDrunk(),EPSILON);
    }

    @Test
    public void eat(){
        human.eat(1000);
        Assert.assertEquals(1000,human.getEaten());
    }

    @Test
    public void walk(){
        human.walk(500);
        Assert.assertEquals(500,human.getWalked());
    }

    @Test
    public void haveBreakfast(){
        human.eat(1000);
        Assert.assertTrue(service.getMealPerDay()-human.getEaten()==1500);
    }

    @Test
    public void drinkInTheMorning(){
        human.drink(1);
        Assert.assertTrue(service.getWaterPerDay()-human.getDrunk()==2.5);
    }

    @Test
    public void stepsInTheMorning(){
        human.walk(500);
        Assert.assertTrue(service.getStepsPerDay()-human.getWalked()==1500);
    }

    @Test
    public void addDrunkLiters(){
        service.drink(0.5);
        Assert.assertTrue(service.getHuman().getDrunk()-0.5==0);
    }

    @Test
    public void addEatenCalories(){
        service.eat(1000);
        Assert.assertTrue(service.getHuman().getEaten()-1000==0);
    }

    @Test
    public void addWalkedSteps(){
        service.walk(800);
        Assert.assertTrue(service.getHuman().getWalked()-800==0);
    }

    @Test
    public void leftToEatToday(){
        service.eat(1000);
        service.eat(500);
        Assert.assertEquals(1000,service.leftToEatToDay());
    }

    @Test
    public void leftToDrinkToday(){
        service.drink(0.5);
        service.drink(2);
        Assert.assertEquals(1,service.leftToDrinkToDay(),EPSILON);
    }

    @Test
    public void leftToWalkToday(){
        service.walk(500);
        service.walk(1300);
        Assert.assertEquals(200,service.leftToWalkToDay());
    }
}

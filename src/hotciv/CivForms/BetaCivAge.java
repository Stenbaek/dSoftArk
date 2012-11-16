package hotciv.CivForms;

import hotciv.framework.CivAgeStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 16-11-12
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
public class BetaCivAge implements CivAgeStrategy {

    private int age;

    public BetaCivAge(){
        age = -4000;
    }

    public int getAge() {
        return age;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void ageProgress() {
        if (age < -100) {
            age = age + 100;
        } else if (age == -100) {
            age = -1;
        } else if (age == -1) {
            age = 1;
        } else if (age == 1) {
            age = 50;
        } else if (age >= 50 && age < 1750) {
            age = age + 50;
        } else if (age >=  1750 && age < 1900) {
            age = age + 25;
        } else if (age >= 1900 && age < 1970) {
            age = age + 5;
        } else if (age >= 1970)
            age = age + 1;

    }

    public Integer getEndOfGameAge() {
        return null;  //does not end like AlphaCiv.
    }
}

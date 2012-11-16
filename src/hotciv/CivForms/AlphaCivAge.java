package hotciv.CivForms;

import hotciv.framework.CivAgeStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 16-11-12
 * Time: 12:24
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivAge implements CivAgeStrategy{

    private int age;

    public AlphaCivAge(){
        age=-4000;
    }

    public int getAge() {
        return age;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public void ageProgress() {
        age = age + 100; //To change body of implemented methods use File | Settings | File Templates.
    }


    public Integer getEndOfGameAge() {
        return -3000;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

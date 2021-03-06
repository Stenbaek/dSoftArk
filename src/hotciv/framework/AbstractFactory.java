package hotciv.framework;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 13.39
 */
public interface AbstractFactory {

    CivWinStrategy getWinningStrategy();

    CivWorldStrategy getWorldStrategy();

    CivActionStrategy getActionStrategy();

    CivAgeStrategy getAgeStrategy();

    CivAttackStrategy getAttackStrategy();

    CivCityStrategy getCityStrategy();

}

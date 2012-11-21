package hotciv.framework;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 13.39
 */
public interface AbstractFactory {

    CivWinStrategy getWinningStrategy();

    CivMapStrategy getWorldStrategy();

    CivActionStrategy getActionStrategy();

    CivAgeStrategy getAgeStrategy();

    CivUnitStrategy getUnitStrategy();

}

import controller.DrawingLoop;
import controller.GameLoop;
import model.Character;
import view.Platform;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import de.saxsys.mvvmfx.testingutils.jfxrunner.JfxRunner;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@RunWith(JfxRunner.class) public class CharacterTest {
    private Character floatingCharacter;
    private Character floatingCharacter2;
    private ArrayList<Character> characterListUnderTest;
    private Platform platformUnderTest;
    private GameLoop gameLoopUnderTest;
    private DrawingLoop drawingLoopUnderTest;
    private Method updateMethod;
    private Method redrawMethod;

    @Before
    public void setup() {
        floatingCharacter = new Character(30, 30, 0, 270, KeyCode.A, KeyCode.D, KeyCode.W, KeyCode.J);
        floatingCharacter2 = new Character(Platform.WIDTH - 60, 30, 0, 270, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.ENTER);
        characterListUnderTest = new ArrayList<Character>();
        characterListUnderTest.add(floatingCharacter);
        characterListUnderTest.add(floatingCharacter2);
        platformUnderTest = new Platform();
        gameLoopUnderTest = new GameLoop(platformUnderTest);
        drawingLoopUnderTest = new DrawingLoop(platformUnderTest);
        try {
            updateMethod = GameLoop.class.getDeclaredMethod("update", ArrayList.class);
            redrawMethod = DrawingLoop.class.getDeclaredMethod("paint", ArrayList.class);
            updateMethod.setAccessible(true);
            redrawMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            updateMethod = null;
            redrawMethod = null;
        }
    }

    @Test
    public void characterInitialValuesShouldMatchConstructorArguments() {
        assertEquals("Initial x", 30, floatingCharacter.getX(), 0);
        assertEquals("Initial y", 30, floatingCharacter.getY(), 0);
        assertEquals("Offset x", 0, floatingCharacter.getOffsetX(), 0.0);
        assertEquals("Offset y", 270, floatingCharacter.getOffsetY(), 0.0);
        assertEquals("Left key", KeyCode.A, floatingCharacter.getLeftKey());
        assertEquals("Right key", KeyCode.D, floatingCharacter.getRightKey());
        assertEquals("Up key", KeyCode.W, floatingCharacter.getUpKey());
    }

}
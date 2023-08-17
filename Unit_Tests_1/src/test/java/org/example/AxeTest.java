package org.example;
import org.junit.*;


public class AxeTest {
    private static final int DUMMY_START_HEALTH=10;
    private static final int DUMMY_XP=10;
    private static final int AXE_ATTACK_POINTS=10;
    private static final int AXE_DURABILITY=10;
    private static final int AXE_WITHOUT_DURABILITY=0;



    private Dummy dummy;


    @Before
    public void setup() {
        dummy = new Dummy(DUMMY_START_HEALTH, DUMMY_XP);
    }

    @Test
    public void testAxeLosesDurabilityAfterAttack() {
        // Arrange
        Axe axe = new Axe(AXE_ATTACK_POINTS, AXE_DURABILITY);
        Dummy dummy = new Dummy(10, 10);
        // Act
        axe.attack(dummy);
        // Assert
        Assert.assertEquals(AXE_DURABILITY - Axe.DURABILITY_LOSS, axe.getDurabilityPoints());
    }


    @Test(expected = IllegalStateException.class)
    public void testAttackWithBrokenAxeShouldThrowException(){
        //Arrange
        Axe axe = new Axe(10, 0);
        Dummy dummy= new Dummy(10, 10);
        //Act
        axe.attack(dummy);
    }




    @Test
    public void testAxeDurabilityNeverGoesNegative() {
        // Arrange
        Axe axe = new Axe(AXE_ATTACK_POINTS, AXE_WITHOUT_DURABILITY);
        Dummy dummy = new Dummy(10, 10);

        // Act
        try {
            axe.attack(dummy);
        } catch (IllegalStateException e) {
            // Expected exception
        }
        // Assert
        Assert.assertEquals(AXE_WITHOUT_DURABILITY, axe.getDurabilityPoints());
    }


    @Test
    public void testGetAttackPoints() {
        // Arrange
        Axe axe = new Axe(AXE_ATTACK_POINTS, AXE_DURABILITY);
        // Act
        int attackPoints = axe.getAttackPoints();
        // Assert
        Assert.assertEquals(AXE_ATTACK_POINTS, attackPoints);
    }
}

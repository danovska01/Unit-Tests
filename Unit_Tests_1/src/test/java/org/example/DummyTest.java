package org.example;

import org.junit.*;

public class DummyTest {

    private static final int DUMMY_START_HEALTH = 100;
    private static final int DUMMY_EXPERIENCE = 100;
    private static final int ATTACK_POINTS = 20;
    private static final int DEAD_DUMMY_HEALTH = 0;

    private Dummy dummy;
    private Dummy deadDummy;

    @Before
    public void setup() {
        dummy = new Dummy(DUMMY_START_HEALTH, DUMMY_EXPERIENCE);
        deadDummy = new Dummy(DEAD_DUMMY_HEALTH, DUMMY_EXPERIENCE);
    }

    @Test
    public void testAttackedDummyLosesHealth() {
        dummy.takeAttack(ATTACK_POINTS);
        Assert.assertEquals(DUMMY_START_HEALTH - ATTACK_POINTS, dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackDeadDummyThrowsException() {
        deadDummy.takeAttack(ATTACK_POINTS);
    }

    @Test
    public void testDeadDummyGivesXP() {
        int exp = deadDummy.giveExperience();
        Assert.assertEquals(DUMMY_EXPERIENCE, exp);
    }

    @Test(expected = IllegalStateException.class)
    public void testAliveDummyDoesntGiveXP() {
        dummy.giveExperience();
    }

    @Test
    public void testIsDeadReturnsTrueForDeadDummy() {
        Assert.assertTrue(deadDummy.isDead());
    }

    @Test
    public void testIsDeadReturnsFalseForAliveDummy() {
        Assert.assertFalse(dummy.isDead());
    }
}

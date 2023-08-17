package org.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HeroTest {

    private static final String HERO_NAME = "TestHero";
    private static final int DUMMY_START_HEALTH = 100;
    private static final int DUMMY_EXPERIENCE = 100;
    private static final int AXE_ATTACK_POINTS = 10;
    private static final int AXE_DURABILITY = 10;

    private Hero hero;
    private Dummy dummy;

    @Before
    public void setup() {
        hero = new Hero(HERO_NAME);
        dummy = new Dummy(DUMMY_START_HEALTH, DUMMY_EXPERIENCE);
    }

    @Test
    public void testHeroName() {
        assertEquals(HERO_NAME, hero.getName());
    }

    @Test
    public void testHeroInitialExperience() {
        assertEquals(0, hero.getExperience());
    }

    @Test
    public void testHeroInitialWeaponAttackPoints() {
        Axe weapon = hero.getWeapon();
        assertEquals(AXE_ATTACK_POINTS, weapon.getAttackPoints());
    }

    @Test
    public void testHeroInitialWeaponDurability() {
        Axe weapon = hero.getWeapon();
        assertEquals(AXE_DURABILITY, weapon.getDurabilityPoints());
    }

    @Test
    public void testAttackCallsWeaponAttackMethod() {
        Axe mockAxe = mock(Axe.class);
        hero.setWeapon(mockAxe);

        hero.attack(dummy);

        // Verify that the attack method of mockAxe is called
        verify(mockAxe, times(1)).attack(dummy);
    }



    @Test
    public void testAttackIncreasesHeroExperienceIfDummyDies() {
        // Arrange
        Dummy deadDummy = Mockito.mock(Dummy.class);
        when(deadDummy.isDead()).thenReturn(true); // Simulate that the dummy is dead
        when(deadDummy.giveExperience()).thenReturn(DUMMY_EXPERIENCE);

        // Act
        hero.attack(deadDummy);

        // Assert
        assertEquals(DUMMY_EXPERIENCE, hero.getExperience());
    }



    @Test
    public void testAttackDoesNotIncreaseHeroExperienceIfDummyIsAlive() {
        // Create an alive dummy
        Dummy aliveDummy = new Dummy(DUMMY_START_HEALTH, DUMMY_EXPERIENCE);

        hero.attack(aliveDummy);

        assertEquals(0, hero.getExperience());
    }

}

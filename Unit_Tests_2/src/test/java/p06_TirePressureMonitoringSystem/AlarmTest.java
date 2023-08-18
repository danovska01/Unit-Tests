package p06_TirePressureMonitoringSystem;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;


public class AlarmTest {

    private Sensor sensor;
    private Alarm alarm;

    @Before
    public void setUp() {
        sensor = mock(Sensor.class);
        alarm = new Alarm(sensor);
    }

    @Test
    public void testAlarmTurnsOnForLowPressure() {
        when(sensor.popNextPressurePsiValue()).thenReturn(15.0);

        alarm.check();

        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmTurnsOnForHighPressure() {
        when(sensor.popNextPressurePsiValue()).thenReturn(22.0);

        alarm.check();

        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmDoesNotTurnOnForNormalPressure() {
        when(sensor.popNextPressurePsiValue()).thenReturn(18.0);

        alarm.check();

        assertFalse(alarm.getAlarmOn());
    }

    @Test
    public void testCheckAlarmTurnsOnForLowPressure() {
        when(sensor.popNextPressurePsiValue()).thenReturn(15.0);

        alarm.check();

        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testCheckAlarmTurnsOnForHighPressure() {
        when(sensor.popNextPressurePsiValue()).thenReturn(22.0);

        alarm.check();

        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testCheckAlarmDoesNotTurnOnForNormalPressure() {
        when(sensor.popNextPressurePsiValue()).thenReturn(18.0);

        alarm.check();

        assertFalse(alarm.getAlarmOn());
    }
}

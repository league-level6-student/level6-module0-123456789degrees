package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import _07_intro_to_mocking.models.Engine;
import _07_intro_to_mocking.models.GasTank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;
    @Mock
    Car car;

    @Mock
    CellPhone cellphone;
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	deliveryDriver = new DeliveryDriver("Bob", car, cellphone);
    }

    @Test
    void itShouldWasteTime() {
        //given
    	boolean expected = true;
        //when
    	when(cellphone.browseCatMemes()).thenReturn(true);
    	boolean actual = deliveryDriver.wasteTime();
        //then
    	assertEquals(expected,actual);
    }

    @Test
    void itShouldRefuel() {
        //given
    	boolean expected = true;
        //when
    	when(car.fillTank(0)).thenReturn(true);
    	boolean actual = deliveryDriver.refuel(0);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldContactCustomer() {
        //given
    	boolean expected = true;
        //when
    	when(cellphone.call("")).thenReturn(true);
    	boolean actual = deliveryDriver.contactCustomer("");
        //then
    	assertEquals(expected, actual);
    }

}
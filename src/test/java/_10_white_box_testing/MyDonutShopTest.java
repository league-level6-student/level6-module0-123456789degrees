package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import _08_mocking.models.DeliveryDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

class MyDonutShopTest {

    MyDonutShop myDonutShop;
    @Mock
    BakeryService bakeryService;
    @Mock
    PaymentService paymentService;
    @Mock
    DeliveryService deliveryService;
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    
        myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
    }
    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
    	myDonutShop.openForTheDay();
    	 Order order = new Order("CUSTOMER_NAME",
                 "CUSTOMER_PHONE_NUMBER",
                 1,
                 5.00,
                 "CREDIT_CARD_NUMBER",
                 true);
        //when
    	when(bakeryService.getDonutsRemaining()).thenReturn(1);
    	when(paymentService.charge(order)).thenReturn(true);
    	myDonutShop.takeOrder(order);
        //then
    	verify(bakeryService, times(1)).removeDonuts(order.getNumberOfDonuts());
    	verify(deliveryService, times(1)).scheduleDelivery(order);
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
    	myDonutShop.openForTheDay();
    	Order order = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                6,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
    	when(bakeryService.getDonutsRemaining()).thenReturn(1);
    	assertThrows(IllegalArgumentException.class, () -> myDonutShop.takeOrder(order));
        //when

        //then
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given	
    	myDonutShop.closeForTheDay();
    	Order order = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                6,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        //when
    	assertThrows(IllegalStateException.class, () -> myDonutShop.takeOrder(order));
        //then
    }

}
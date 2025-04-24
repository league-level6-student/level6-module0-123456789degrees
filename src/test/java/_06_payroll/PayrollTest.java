package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	double hourlyWage = 30.25;
    	int numHours = 10;
    	double expected = 302.5;
        //when
    	double actual = payroll.calculatePaycheck(hourlyWage, numHours);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	int milesTraveled = 20;
    	double expected = 11.5;
        //when
    	double actual = payroll.calculateMileageReimbursement(milesTraveled);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String employeeName = "Bob";
    	double hourlyWage = 30.5;
    	String expected = "Hello Bob, We are pleased to offer you an hourly wage of 30.5";
        //when
    	String actual = payroll.createOfferLetter(employeeName, hourlyWage);
        //then
    	assertEquals(expected, actual);
    }

}
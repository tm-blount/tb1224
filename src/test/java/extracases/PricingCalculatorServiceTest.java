package extracases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yankovic.db.entities.Tool;
import org.yankovic.model.RentalPricingRecord;
import org.yankovic.service.PricingCalculatorService;
import sharedtestdata.tools.CHNSToolMock;
import sharedtestdata.tools.JAKToolMock;
import sharedtestdata.tools.LADWToolMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class PricingCalculatorServiceTest {
    private final PricingCalculatorService pricingCalculatorService = new PricingCalculatorService();

    @Test
    public void testJAKDNoHolidaysNoWeekends() {
        Tool jakd = JAKToolMock.mockJAKDTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(jakd, 0, 3, "9/10/24");

        assertEquals(3, record.chargeableDays());
        assertEquals((2.99 * 3), record.totalPrice());
    }

    @Test
    public void testJAKDHolidayChargeIndependenceDay() {
        Tool jakd = JAKToolMock.mockJAKDTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(jakd, 0, 3, "7/4/21");

        assertEquals(1, record.chargeableDays());
        assertEquals((2.99 * 1), record.totalPrice());
    }

    @Test
    public void testLADWWeekendCharge() {
        int numDaysToRent = 3;
        Tool ladw = LADWToolMock.mockLADWTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(ladw, 0, numDaysToRent, "10/25/24");

        assertEquals(3, numDaysToRent);
        assertEquals(3, record.chargeableDays());
        assertEquals((1.99 * 3), record.totalPrice());
    }

    @Test
    public void testLADWRentalIsAllWeekdays() {
        Tool ladw = LADWToolMock.mockLADWTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(ladw, 0, 3, "9/10/24");

        assertEquals(3, record.chargeableDays());
        assertEquals((1.99 * 3), record.totalPrice());
    }

    @Test
    public void testLADWHolidayChargeLaborDay() {
        int numDaysToRent = 3;
        Tool ladw = LADWToolMock.mockLADWTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(ladw, 0, numDaysToRent, "9/2/24");

        assertEquals(3, numDaysToRent);
        assertEquals(2, record.chargeableDays());
        assertEquals((1.99 * 2), record.totalPrice());
    }

    @Test
    public void testLADWHolidayChargeIndependenceDayWeekend() {
        Tool ladw = LADWToolMock.mockLADWTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(ladw, 0, 3, "7/4/21");

        assertEquals(2, record.chargeableDays());
        assertEquals((1.99 * 2), record.totalPrice());
    }

    // TODO jul 4th holiday not accounted for on weekdays
    @Test
    public void testLADWHolidayChargeIndependenceDayWeekday() {
        Tool ladw = LADWToolMock.mockLADWTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(ladw, 0, 3, "7/4/23");

        assertEquals(3, record.chargeableDays());
        assertEquals((1.99 * 3), record.totalPrice());
    }

    @Test
    public void testCHNSRentalIsAllWeekdays() {
        Tool chns = CHNSToolMock.mockCHNSTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(chns, 0, 3, "9/10/24");

        assertEquals(3, record.chargeableDays());
        assertEquals((1.49 * 3), record.totalPrice());
    }

    @Test
    public void testCHNSHolidayChargeLaborDay() {
        Tool chns = CHNSToolMock.mockCHNSTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(chns, 0, 3, "9/9/24");

        assertEquals(3, record.chargeableDays());
        assertEquals((1.49 * 3), record.totalPrice());
    }

    // CHNS has no weekend charges
    @Test
    public void testCHNSHolidayChargeIndependenceDayWeekend() {
        Tool chns = CHNSToolMock.mockCHNSTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(chns, 0, 3, "7/4/21");

        assertEquals(2, record.chargeableDays());
        assertEquals((1.49 * 2), record.totalPrice());
    }

    @Test
    public void testCHNSHolidayChargeIndependenceDayWeekday() {
        Tool chns = CHNSToolMock.mockCHNSTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(chns, 0, 3, "7/4/23");

        assertEquals(3, record.chargeableDays());
        assertEquals((1.49 * 3), record.totalPrice());
    }
}

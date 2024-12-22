package required;

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
public class RequiredPricingCalculatorServiceTest {
    private final PricingCalculatorService pricingCalculatorService = new PricingCalculatorService();

    @Test
    public void testLADWIndependenceDayOnWeekend() {
        Tool ladw = LADWToolMock.mockLADWTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(ladw, 10, 3, "7/2/20");

        assertEquals(2, record.chargeableDays());
        assertEquals(3.58, record.totalPrice());
    }

    @Test
    public void testCHNSIndependenceDayOnWeekend() {
        Tool chns = CHNSToolMock.mockCHNSTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(chns, 25, 5, "7/2/15");

        assertEquals(3, record.chargeableDays());
        assertEquals((1.49 * 3), record.totalPrice());
    }

    @Test
    public void testJAKDLaborDay() {
        Tool jakd = JAKToolMock.mockJAKDTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(jakd, 0, 6, "9/3/15");

        assertEquals(3, record.chargeableDays());
        assertEquals((2.99 * 3), record.totalPrice());
    }

    @Test
    public void testJAKRIndependenceDayOnWeekend() {
        Tool jakr = JAKToolMock.mockJAKRTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(jakr, 0, 6, "9/3/15");

        assertEquals(3, record.chargeableDays());
        assertEquals((2.99 * 3), record.totalPrice());
    }

    @Test
    public void testJAKRIndependenceDayWeekend() {
        Tool jakr = JAKToolMock.mockJAKRTool();

        RentalPricingRecord record =
                pricingCalculatorService.getPricingForRental(jakr, 50, 6, "7/2/20");

        assertEquals(3, record.chargeableDays());
        assertEquals(4.49, record.totalPrice());
    }
}

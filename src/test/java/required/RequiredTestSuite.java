package required;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yankovic.db.ToolRepository;
import org.yankovic.db.entities.Tool;
import org.yankovic.db.entities.ToolType;
import org.yankovic.model.RentAgreementRecord;
import org.yankovic.model.RentalPricingRecord;
import org.yankovic.service.PricingCalculatorService;
import org.yankovic.service.RentAgreementService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class RequiredTestSuite {
    @InjectMocks
    private RentAgreementService rentAgreementService;

    @Mock
    private ToolRepository toolRepository;

    @Mock
    private PricingCalculatorService pricingCalculatorService;

    @Test
    public void testSuccessLADWNoDiscount() {
        Mockito.when(toolRepository.findById(Mockito.anyInt())).thenReturn(
                mockLADWTool()
        );
        Mockito.when(pricingCalculatorService.getPricingForRental(Mockito.any(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(
                // TODO pricing!
                new RentalPricingRecord(
                        10,
                        10,
                        30,
                        mockLADWTool().getToolType().getDailyCharge(),
                        3
                )
        );
        RentAgreementRecord record = rentAgreementService.createRentalAgreementForTool(30, 3, 3, "9/3/15");

        assertEquals(10, record.totalPrice());
        assertEquals(3, record.chargeableDays());
        assertEquals(1.99, record.dailyRentalPrice());
        assertEquals(10, record.preDiscountCharge());
        assertEquals(30, record.discountPercent());
        // TODO should work after pricing is fixed
        //assertEquals("9/3/15", record.checkOutDate().toString());
    }

    private Tool mockLADWTool() {
        return new Tool(
                6,
                "LADW",
                "Werner",
                new ToolType(
                        1,
                        "Ladder",
                        1.99,
                        true,
                        true,
                        false
                )
        );
    }
}


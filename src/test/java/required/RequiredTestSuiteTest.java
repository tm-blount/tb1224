package required;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.yankovic.MainApplication;
import org.yankovic.db.ToolRepository;
import org.yankovic.db.entities.Tool;
import org.yankovic.model.RentAgreementRecord;
import org.yankovic.model.RentalPricingRecord;
import org.yankovic.service.PricingCalculatorService;
import org.yankovic.service.RentAgreementService;
import org.yankovic.utilities.PricingCalculatorUtils;
import sharedtestdata.tools.LADWToolMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO add rest of tests and maybe look at that one spec again
@SpringBootTest(classes = MainApplication.class)
@AutoConfigureMockMvc
public class RequiredTestSuiteTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RentAgreementService rentAgreementService;

    @MockitoBean
    private PricingCalculatorService pricingCalculatorService;

    @MockitoBean
    private ToolRepository toolRepository;

    @Test
    public void testInvalidDiscount() throws Exception {
        MvcResult result = mockMvc.perform(get("/rental/displayAgreement/{toolId}", 5)
                        .param("discount", String.valueOf(101))
                        .param("numDaysToRent", String.valueOf(5))
                        .param("checkoutDate", "9/3/15"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("", result.getResponse().getContentAsString());
    }

    @Test
    public void testLADWJul4thOnWeekend() throws Exception {
        Tool ladw = LADWToolMock.mockLADWTool();

        Mockito.when(toolRepository.findById(ladw.getId())).thenReturn(ladw);

        Mockito.doReturn(
                new RentalPricingRecord(
                        30.00,
                        40.00,
                        10,
                        ladw.getToolType().getDailyCharge(),
                        3
                )
        ).when(pricingCalculatorService).getPricingForRental(Mockito.any(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());

        Mockito.doReturn(
                new RentAgreementRecord(
                        ladw,
                        new RentalPricingRecord(
                                30.00,
                                40.00,
                                10,
                                ladw.getToolType().getDailyCharge(),
                                3
                        ),
                        30.00,
                        40.00,
                        ladw.getToolType().getDailyCharge(),
                        3,
                        10,
                        3,
                        PricingCalculatorUtils.formatDateString("7/2/20"),
                        PricingCalculatorUtils.formatDateString("7/5/20")
                )
        ).when(rentAgreementService).createRentalAgreementForTool(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.any());

        MvcResult result = mockMvc.perform(get("/rental/displayAgreement/{toolId}", ladw.getId())
                        .param("discount", String.valueOf(10))
                        .param("numDaysToRent", String.valueOf(3))
                        .param("checkoutDate", "7/2/20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(30))
                .andReturn();
    }
}


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
import sharedtestdata.records.CHNSRentAgreementRecordMock;
import sharedtestdata.records.CHNSRentalPricingRecordMock;
import sharedtestdata.records.LADWRentAgreementRecordMock;
import sharedtestdata.records.LADWRentalPricingRecordMock;
import sharedtestdata.tools.CHNSToolMock;
import sharedtestdata.tools.LADWToolMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MainApplication.class)
@AutoConfigureMockMvc
public class RequiredTestSuiteTest {
    @Autowired
    private MockMvc mockMvc;

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

    // Not required, but might as well add it
    @Test
    public void testInvalidRentalDays() throws Exception {
        MvcResult result = mockMvc.perform(get("/rental/displayAgreement/{toolId}", 5)
                        .param("discount", String.valueOf(0))
                        .param("numDaysToRent", String.valueOf(-5))
                        .param("checkoutDate", "9/3/15"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("", result.getResponse().getContentAsString());
    }
}


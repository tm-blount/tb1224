package required;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.tb.MainApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MainApplication.class)
@AutoConfigureMockMvc
public class RequiredRentAgreementControllerTest {
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

        assertEquals("{\"Error: \"Discount must be between 0 - 100\"}", result.getResponse().getContentAsString());
    }

    /**
     * NOTE: not required test cases, added here since it's where they belong
     */

    @Test
    public void testToolNotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/rental/displayAgreement/{toolId}", 100)
                        .param("discount", String.valueOf(0))
                        .param("numDaysToRent", String.valueOf(5))
                        .param("checkoutDate", "9/3/15"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("{\"Error: \"Tool not found\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void testInvalidRentalDays() throws Exception {
        MvcResult result = mockMvc.perform(get("/rental/displayAgreement/{toolId}", 5)
                        .param("discount", String.valueOf(0))
                        .param("numDaysToRent", String.valueOf(-5))
                        .param("checkoutDate", "9/3/15"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("{\"Error: \"A tool must be rented at least one day\"}", result.getResponse().getContentAsString());
    }
}


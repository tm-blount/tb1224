package sharedtestdata.tools;

import org.tb.db.entities.Tool;
import org.tb.db.entities.ToolType;

public class CHNSToolMock {
    public static Tool mockCHNSTool() {
        return new Tool(
                5,
                "CHNS",
                "Stihl",
                new ToolType(
                        2,
                        "Chainsaw",
                        1.49,
                        true,
                        false,
                        true
                )
        );
    }
}

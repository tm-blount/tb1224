package sharedtestdata.tools;

import org.tb.db.entities.Tool;
import org.tb.db.entities.ToolType;

public class JAKToolMock {
    public static Tool mockJAKDTool() {
        return new Tool(
                7,
                "JAKD",
                "DeWalt",
                new ToolType(
                        3,
                        "Jackhammer",
                        2.99,
                        true,
                        false,
                        false
                )
        );
    }

    public static Tool mockJAKRTool() {
        return new Tool(
                7,
                "JAKR",
                "Ridgid",
                new ToolType(
                        2,
                        "Jackhammer",
                        2.99,
                        true,
                        false,
                        false
                )
        );
    }
}

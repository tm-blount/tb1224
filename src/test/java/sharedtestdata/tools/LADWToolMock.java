package sharedtestdata.tools;

import org.tb.db.entities.Tool;
import org.tb.db.entities.ToolType;

public class LADWToolMock {
    public static Tool mockLADWTool() {
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

package org.tb.db.entities;

import jakarta.persistence.*;

@Entity(name = "tool_types")
public class ToolType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tool_id", insertable = false, updatable = false)
    private int toolId;

    private String toolName;
    private double dailyCharge;
    private boolean isWeekdayCharge;
    private boolean isWeekendCharge;
    private boolean isHolidayCharge;

    public ToolType() {
    }

    public ToolType(int toolId, String toolName, double dailyCharge, boolean isWeekdayCharge, boolean isWeekendCharge, boolean isHolidayCharge) {
        this.toolId = toolId;
        this.toolName = toolName;
        this.dailyCharge = dailyCharge;
        this.isWeekdayCharge = isWeekdayCharge;
        this.isWeekendCharge = isWeekendCharge;
        this.isHolidayCharge = isHolidayCharge;
    }

    public int getId() {
        return toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return isWeekdayCharge;
    }

    public boolean isWeekendCharge() {
        return isWeekendCharge;
    }

    public boolean isHolidayCharge() {
        return isHolidayCharge;
    }
}

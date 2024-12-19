package org.yankovic.db.entities;

import jakarta.persistence.*;

@Entity(name = "tools")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String toolCode;
    private String toolBrand;

    @OneToOne
    @JoinColumn(name = "tool_type_id")
    private ToolType toolType;

    protected Tool() {
    }

    public Tool(int id, String toolCode, String toolBrand, ToolType toolType) {
        this.id = id;
        this.toolCode = toolCode;
        this.toolBrand = toolBrand;
        this.toolType = toolType;
    }

    public int getId() {
        return id;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public ToolType getToolType() {
        return toolType;
    }
}

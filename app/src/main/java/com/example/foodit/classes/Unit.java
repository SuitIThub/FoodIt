package com.example.foodit.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Unit {
    g("g", "g", 1f, true),
    kg("kg", "g", 1000f, true),
    ml("ml", "ml", 1f, true),
    l("l", "ml", 1000f, true);

    private static final Map<String, Unit> units = new HashMap<>();

    static {
        for (Unit e : values()) {
            units.put(e.label, e);
        }
    }

    public final String label;
    public final float value;
    public final String base;
    public final boolean isDisplayable;

    private Unit(String label, String baseLabel, float value, boolean isDisplayable) {
        this.label = label;
        this.value = value;
        this.base = baseLabel;
        this.isDisplayable = isDisplayable;
    }

    public static float getValue(String label) {
        if (!units.containsKey(label))
            return Float.NaN;

        return units.get(label).value;
    }

    public static float calculateBaseValue(String label, float value) {
        if (!units.containsKey(label))
            return value;

        return units.get(label).value * value;
    }

    public static String getBase(String label) {
        if (!units.containsKey(label))
            return label;

        return units.get(label).base;
    }

    public static Unit[] getUnitsByBase(String baseUnit) {
        List<Unit> unitsOut = new ArrayList<>();
        for (String unit : units.keySet()) {
            Unit u = units.get(unit);
            if (u.base.equals(baseUnit))
                unitsOut.add(u);
        }

        return unitsOut.toArray(new Unit[0]);
    }

    public static Unit getUnit(String label) {
        if (!units.containsKey(label))
            return null;
        return units.get(label);
    }

    public static boolean hasUnit(String label) {
        return units.containsKey(label);
    }
}

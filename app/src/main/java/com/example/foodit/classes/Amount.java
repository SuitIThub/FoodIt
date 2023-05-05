package com.example.foodit.classes;

import android.util.Pair;

public class Amount {
    private String baseUnit;
    private float amount;

    public Amount(String unit, float amount) {
        baseUnit = Unit.getBase(unit);
        this.amount = Unit.calculateBaseValue(unit, amount);
    }

    public Amount(String unit, float amount, int portion) {
        baseUnit = Unit.getBase(unit);
        this.amount = Unit.calculateBaseValue(unit, amount / portion);
    }

    public Pair<String, Float> getBestUnit() {
        float amount = this.amount;
        Unit unit = Unit.getUnit(baseUnit);
        for (Unit u : Unit.getUnitsByBase(baseUnit)) {
            if (!u.isDisplayable)
                continue;
            float calc = this.amount / u.value;
            if (calc < amount && calc >= 1f) {
                amount = calc;
                unit = u;
            }
        }

        return new Pair<>(unit.label, amount);
    }

    public float getAmount(String label) {
        if (Unit.getUnit(label).base != baseUnit)
            return Float.NaN;
        return amount / Unit.getValue(label);
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public String toString(String... unit)
    {
        if (unit.length > 0) {
            if (unit[0].equals("best")) {
                Pair<String, Float> bestUnit = getBestUnit();
                return bestUnit.first + " " + bestUnit.second;
            }

            if (Unit.hasUnit(unit[0]) && Unit.getUnit(unit[0]).base == baseUnit) {
                return getAmount(unit[0]) + " " + unit[0];
            }
        }

        return amount + " " + baseUnit;
    }
}

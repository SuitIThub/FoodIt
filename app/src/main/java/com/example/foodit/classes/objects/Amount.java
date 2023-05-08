package com.example.foodit.classes.objects;

import android.util.Pair;

import com.example.foodit.classes.Unit;

import org.json.JSONException;
import org.json.JSONObject;

public class Amount {
    public static class Save {
        public final String baseUnit;
        public final double amount;

        public Save(Amount amount) {
            baseUnit = amount.baseUnit;
            this.amount = amount.amount;
        }
    }

    private String baseUnit;
    private double amount;

    public Amount(String unit, float amount) {
        baseUnit = Unit.getBase(unit);
        this.amount = Unit.calculateBaseValue(unit, amount);
    }

    public Amount(String unit, float amount, int portion) {
        baseUnit = Unit.getBase(unit);
        this.amount = Unit.calculateBaseValue(unit, amount / portion);
    }

    public Amount(Save save) {
        baseUnit = save.baseUnit;
        amount = save.amount;
    }

    public Pair<String, Double> getBestUnit() {
        double amount = this.amount;
        Unit unit = Unit.getUnit(baseUnit);
        for (Unit u : Unit.getUnitsByBase(baseUnit)) {
            if (!u.isDisplayable)
                continue;
            double calc = this.amount / u.value;
            if (calc < amount && calc >= 1f) {
                amount = calc;
                unit = u;
            }
        }

        return new Pair<>(unit.label, amount);
    }

    public double getAmount(String label) {
        if (Unit.getUnit(label).base != baseUnit)
            return Double.NaN;
        return amount / Unit.getValue(label);
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public String toString(String... unit)
    {
        if (unit.length > 0) {
            if (unit[0].equals("best")) {
                Pair<String, Double> bestUnit = getBestUnit();
                return bestUnit.first + " " + bestUnit.second;
            }

            if (Unit.hasUnit(unit[0]) && Unit.getUnit(unit[0]).base == baseUnit) {
                return getAmount(unit[0]) + " " + unit[0];
            }
        }

        return amount + " " + baseUnit;
    }
}

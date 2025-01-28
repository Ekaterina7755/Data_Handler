package com.yourpackage;

public class DataRecord implements Comparable<DataRecord> {
    private String name;
    private int value;

    public DataRecord(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(DataRecord other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return "DataRecord{name='" + name + "', value=" + value + "}";
    }
}

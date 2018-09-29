package accumulator;

import org.apache.spark.util.AccumulatorV2;

public class StringAccumulator extends AccumulatorV2<String, String> {
	 
    private String _value = "";
 
    public StringAccumulator() {
        this("");
    }
 
    public StringAccumulator(String initialValue) {
        if (initialValue != null) {
            _value = initialValue;
        }
    }
 
    public void add(String value) {
        _value = value() + " " + value.trim();
    }
 
    public StringAccumulator copy() {
        return (new StringAccumulator(value()));
    }
 
    public boolean isZero() {
        return (value().length() == 0);
    }
 
    public void merge(AccumulatorV2<String, String> other) {
        add(other.value());
    }
 
    public void reset() {
        _value = "";
    }
 
    public String value() {
        return (_value);
    }
}
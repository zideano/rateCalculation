package dto;

import java.io.Serializable;

public class Offer implements Serializable {
    private Double rate;
    private Double available;

    public Offer() {
    }

    public Offer(Double rate, Double available) {
        this.rate = rate;
        this.available = available;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getAvailable() {
        return available;
    }

    public void setAvailable(Double available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "rate=" + rate +
                ", available=" + available +
                '}';
    }
}

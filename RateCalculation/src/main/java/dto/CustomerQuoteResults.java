package dto;

import java.io.Serializable;

public class CustomerQuoteResults implements Serializable {
    private Double monthlyRepayment;
    private Double totalRepayment;
    private Integer amount;
    private Double quote;


    public CustomerQuoteResults(Integer amount, Double monthlyRepayment, Double totalRepayment, Double quote) {
        this.amount = amount;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
        this.quote = quote;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public void setMonthlyRepayment(Double monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }

    public Double getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(Double totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    public Double getQuote() {
        return quote;
    }

    public void setQuote(Double quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Requested amount: " + amount)
                .append("\n")
                .append(String.format("Rate: %.1f", quote*100))
                .append("%")
                .append("\n")
                .append(String.format("Monthly repayment: %.2f", monthlyRepayment))
                .append("\n")
                .append(String.format("Total repayment: %.2f", totalRepayment))
                .append("\n");

        return  stringBuilder.toString();
    }
}

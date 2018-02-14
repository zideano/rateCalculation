package loans;

import dto.CustomerQuoteResults;
import dto.Offer;

import java.util.List;

public interface ILoanQuotes {
    /**
     *
     * @param offers
     * @param amount
     * @return total payment over the 36 months period
     */
    CustomerQuoteResults quotes(List<Offer> offers, Integer amount, Integer length);
}

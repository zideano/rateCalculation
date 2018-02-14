package loans.impl;

import dto.CustomerQuoteResults;
import dto.Offer;
import loans.ILoanQuotes;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoanQuotesImpl implements ILoanQuotes {
    private static final Logger LOGGER = Logger.getLogger(LoanQuotesImpl.class.getName());
    private static final int MAX_LOAN = 15000;
    private static final int MIN_LOAN = 1000;
    private static final int COMPOUNDING_PERIOD = 12;
    private static final int NUM_OF_YEARS = 3;

    public CustomerQuoteResults quotes(List<Offer> offers, Integer amount, Integer length) {
        // Get the total available fund within our lending pool
        Double available = offers.stream().mapToDouble(Offer::getAvailable).sum();
        
        Double fundToBorrow = 0.0d;
        Double repaymentTotal = 0.0d;
        Double rate;
        Double monthlyRepayment;
        boolean isEligible = false;

        /*
         * Check if we have enough money to offer a loan from our pool of lenders
         * Otherwise return null as no loans are available
         */
        if (available < amount || offers.size() == 0)
        {
            LOGGER.log(Level.INFO, "Insufficient funds or no offers currently available.");
            return null;
        }

        /*
         *  Check that loan request falls between £1000 - £15,000
         *  and is an increment of £100 each time
         */
        if (amount % 100 == 0 && (amount >= MIN_LOAN && amount <= MAX_LOAN))
        {
            isEligible = true;
            LOGGER.log(Level.INFO, "Applicant is eligible for a loan, continuing with application");
        }


        if (isEligible) {
            // Sort the rates from lowest to highest
            offers.sort(Comparator.comparing(Offer::getRate));

            for(Offer offer : offers) {
                Double loan;

                // Get available loan from lenders pool
                loan = amount < fundToBorrow + offer.getAvailable() ?
                        amount - fundToBorrow : offer.getAvailable();

                repaymentTotal += loan*Math.pow((1 + (offer.getRate() / COMPOUNDING_PERIOD)), length);

                // Break when we reach the total amount that we need to borrow
                if ((fundToBorrow += loan) >= amount)
                {
                    break;
                }

            }

            rate = Math.log(repaymentTotal/amount) / NUM_OF_YEARS;
            monthlyRepayment = repaymentTotal / length;

            return new CustomerQuoteResults(amount,monthlyRepayment,repaymentTotal,rate);
        }

        return null;
    }

}

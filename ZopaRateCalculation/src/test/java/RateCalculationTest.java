import dto.CustomerQuoteResults;
import dto.Offer;
import loans.impl.LoanQuotesImpl;
import org.junit.Assert;
import org.junit.Test;
import service.impl.CSVFileReaderImpl;

import java.util.List;

public class RateCalculationTest {

    @Test
    public void quotesTest() {
        String filename = "MarketDataforExercise.csv";
        CSVFileReaderImpl csvFileReader = new CSVFileReaderImpl();
        List<Offer> offers = csvFileReader.readSCV(filename);
        Assert.assertNotNull(offers);
        Assert.assertEquals(offers.size(), 7);

        LoanQuotesImpl loanQuotes = new LoanQuotesImpl();

        // Test with the correct parameters
        CustomerQuoteResults customerQuoteResults = loanQuotes.quotes(offers,1000, 36);
        Assert.assertNotNull(customerQuoteResults);
        Assert.assertEquals(7, offers.size());

        // Test that we have enough funds to loan from the pool of lenders
        customerQuoteResults = loanQuotes.quotes(offers,3000, 36);
        Assert.assertNull("Insufficient fund availability", customerQuoteResults);

        // Test that only £100 increments between £1000 - £15,000 are allowed
        customerQuoteResults = loanQuotes.quotes(offers,1125, 36);
        Assert.assertNull("Only £100 increments between £1000 - £15,000 allowed", customerQuoteResults);
    }

    @Test
    public void readCSVTest() {
        String filename = "MarketDataforExercise.csv";
        CSVFileReaderImpl csvFileReader = new CSVFileReaderImpl();
        List<Offer> offers = csvFileReader.readSCV(filename);
        Assert.assertNotNull(offers);
        Assert.assertEquals(7, offers.size());
    }
}

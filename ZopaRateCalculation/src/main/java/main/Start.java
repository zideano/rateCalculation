package main;

import dto.CustomerQuoteResults;
import dto.Offer;
import loans.ILoanQuotes;
import loans.impl.LoanQuotesImpl;
import service.IReadCSV;
import service.impl.CSVFileReaderImpl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Start {
    private static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("missing [filename] [loanAmount] parameters");
        }

        String filename = args[0];
        Integer amount = Integer.valueOf(args[1]);

        IReadCSV csvFileReader = new CSVFileReaderImpl();
        List<Offer> offerList = csvFileReader.readSCV(filename);

        ILoanQuotes iLoanQuotes = new LoanQuotesImpl();
        CustomerQuoteResults customerQuoteResults = iLoanQuotes.
                quotes(offerList, amount, 36);

        if (customerQuoteResults != null) {
            System.out.println(customerQuoteResults.toString());
        } else {
            LOGGER.log(Level.INFO, "No offers currently available for your request.");
        }

    }
}

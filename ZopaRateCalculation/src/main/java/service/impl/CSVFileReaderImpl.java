package service.impl;

import dto.Offer;
import service.IReadCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVFileReaderImpl implements IReadCSV {
    private static final Logger LOGGER = Logger.getLogger(CSVFileReaderImpl.class.getName());

    public List<Offer> readSCV(String filename) {
        List<Offer> offers = null;

        BufferedReader br = null;
        String line;

        try {
            br = new BufferedReader(new FileReader(filename));
            br.readLine();
            offers = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                String[] csv = line.split(",");
                System.out.println(csv[0] + " " + csv[1] + " " + csv[2]);
                offers.add(new Offer(Double.valueOf(csv[1]), Double.valueOf(csv[2])));
            }

            System.out.println();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,  filename + " doesn't exist...");
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return offers;
    }
}

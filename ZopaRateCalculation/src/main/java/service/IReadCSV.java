package service;

import dto.Offer;

import java.util.List;

public interface IReadCSV {
    /**
     * Read the csv file and return a list of offers
     *
     * @param filename
     * @return
     */
    List<Offer> readSCV(String filename);
}

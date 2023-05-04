package fr.bruno.mamediatheque.service;

import fr.bruno.mamediatheque.bo.Band;

import java.util.List;

public interface BandService {

    public void addBand(Band band);

    public List<Band> listBand();

    public Band getBand(int id);

    public void deleteBand(int id);

    public Band getSingleBand(int idBand);
}

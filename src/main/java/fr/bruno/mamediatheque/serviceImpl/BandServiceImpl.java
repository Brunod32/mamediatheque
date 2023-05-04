package fr.bruno.mamediatheque.serviceImpl;

import fr.bruno.mamediatheque.bo.Band;
import fr.bruno.mamediatheque.dao.BandRepository;
import fr.bruno.mamediatheque.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandRepository bandRepository;

    @Override
    public void addBand(Band band) {
        bandRepository.save(band);
    }

    @Override
    public List<Band> listBand() {
        return bandRepository.findAll();
    }

    @Override
    public Band getBand(int id) {
        return bandRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBand(int id) {
        bandRepository.deleteById(id);
    }

    @Override
    public Band getSingleBand(int idBand) {
        return bandRepository.findById(idBand).get();
    }
}

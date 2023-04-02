package fr.bruno.mamediatheque.serviceImpl;

import fr.bruno.mamediatheque.bo.Writer;
import fr.bruno.mamediatheque.dao.WriterRepository;
import fr.bruno.mamediatheque.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl implements WriterService {

    @Autowired
    private WriterRepository writerRepository;

    @Override
    public void addWriter(Writer writer) {
        writerRepository.save(writer);
    }

    @Override
    public List<Writer> listWriter() {
        return writerRepository.findAll();
    }

    @Override
    public Writer getWriter(int id) {
        return writerRepository.findById(id).orElse(null); //Retourn null si pas trouvé
    }

    @Override
    public void deleteWriter(int id) {
        writerRepository.deleteById(id);
    }


}

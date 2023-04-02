package fr.bruno.mamediatheque.api;

import fr.bruno.mamediatheque.bo.Writer;
import fr.bruno.mamediatheque.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/auteur")
public class WriterRestController {

    @Autowired
    private WriterService writerService;

    @GetMapping
    public List<Writer> getWriters() {
        return writerService.listWriter();
    }

    @PostMapping
    public Writer writer(@RequestBody Writer writer) {
        writerService.addWriter(writer);
        return writer;
    }

    @DeleteMapping("/{idWriterToDelete}")
    public void deleteWriter(@PathVariable Integer idWriterToDelete) {
        if (idWriterToDelete != null) {
            writerService.deleteWriter(idWriterToDelete);
        }
    }

}

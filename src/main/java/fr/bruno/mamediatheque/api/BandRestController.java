package fr.bruno.mamediatheque.api;

import fr.bruno.mamediatheque.bo.Band;
import fr.bruno.mamediatheque.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/groupe")
public class BandRestController {

    @Autowired
    private BandService bandService;

    @GetMapping
    public List<Band> getBands() {
        return bandService.listBand();
    }

    @GetMapping("/{idBand}")
    public Band getSingleBand(@PathVariable Integer idBand) {
        return bandService.getSingleBand(idBand);
    }

    @PostMapping
    public Band band(@RequestBody Band band) {
        bandService.addBand(band);
        return band;
    }

    @DeleteMapping("/{idBandToDelete}")
    public void deleteBand(@PathVariable Integer idBandToDelete) {
        if (idBandToDelete != null) {
            bandService.deleteBand(idBandToDelete);
        }
    }
}

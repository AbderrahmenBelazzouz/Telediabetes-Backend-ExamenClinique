package com.esi.examenclinique.controller;

import com.esi.examenclinique.DTO.EleDTO;
import com.esi.examenclinique.entity.ExamenClinique;
import com.esi.examenclinique.repository.ExamenCliniqueRepository;
import com.esi.examenclinique.service.ExamenCliniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("EC")
public class ExamenCliniqueController {

    @Autowired
    private ExamenCliniqueService examenCliniqueService;

    @Autowired
    private ExamenCliniqueRepository examenCliniqueRepository;

    @PostMapping("/add")
    public ExamenClinique addExamenClinique(@RequestBody ExamenClinique examenClinique){
        return examenCliniqueService.saveExamenClinique(examenClinique);
    }
    @PostMapping("/addAll")
    public List<ExamenClinique> addExamensCliniques(@RequestBody List<ExamenClinique> examensCliniques){
        return examenCliniqueService.saveExamensCliniques(examensCliniques);
    }

    @GetMapping("/all")
    public List<ExamenClinique> getExamensCliniques(){
        return examenCliniqueService.getExamensCliniques();
    }

    @GetMapping("/{id}")
    public ExamenClinique getExamenCliniqueById(@PathVariable(name = "id") Long idEC){
        return examenCliniqueService.getExamenCliniqueById(idEC);
    }

    @PutMapping("/update")
    public ExamenClinique updateExamenClinique(@RequestBody ExamenClinique examenClinique){
        return examenCliniqueService.updateExamenClinique(examenClinique);
    }
    @PutMapping("/{id}/updateG")
    public ExamenClinique updateECG(@PathVariable(name = "id") Long idEC, @RequestBody Collection<Long> idsG){
        return examenCliniqueService.updateECG(idEC, idsG);
    }
    @PutMapping("/{id}/updateA")
    public ExamenClinique updateECA(@PathVariable(name = "id") Long idEC,@RequestBody Collection<Long> idsA){
        return examenCliniqueService.updateECA(idEC, idsA);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteExamenClinique(@PathVariable(name = "id") Long idEC){
        return examenCliniqueService.deleteExamenClinique(idEC);
    }

    @GetMapping("/{id}/Genral")
    public List<EleDTO> getGenralByEC(@PathVariable(name = "id") Long idEC){
        ExamenClinique ec = examenCliniqueRepository.findById(idEC).orElse(null);
        return examenCliniqueService.getGenralByEC(ec);
    }
    @GetMapping("/{id}/Appareil")
    public List<EleDTO> getAppareilByEC(@PathVariable(name = "id") Long idEC){
        ExamenClinique ec = examenCliniqueRepository.findById(idEC).orElse(null);
        return examenCliniqueService.getAppareilByEC(ec);
    }
}

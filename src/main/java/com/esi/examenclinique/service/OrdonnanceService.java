package com.esi.examenclinique.service;

import com.esi.examenclinique.entity.DoseJour;
import com.esi.examenclinique.entity.ExamenClinique;
import com.esi.examenclinique.entity.Ordonnance;
import com.esi.examenclinique.repository.DoseJourRepository;
import com.esi.examenclinique.repository.ExamenCliniqueRepository;
import com.esi.examenclinique.repository.OrdonnanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdonnanceService {
    @Autowired
    private OrdonnanceRepository ordonnanceRepository;
    @Autowired
    private ExamenCliniqueRepository examenCliniqueRepository;
    @Autowired
    private DoseJourRepository doseJourRepository;

    public ExamenClinique getECById(Long idEC){
        return examenCliniqueRepository.findById(idEC).orElse(null);
    }

    //post methods
    public Ordonnance saveOrdonnance(Long idec, Ordonnance ordonnance){
        ExamenClinique ec =  examenCliniqueRepository.findById(idec).orElse(null);
        ec.setOrdonnance(ordonnance);
        //examenCliniqueRepository.save(ec);
        // Set parent reference(EC) in child entity(ordonnance)
        ordonnance.setExamenClinique(ec);
        return ordonnanceRepository.save(ordonnance);
    }

    public DoseJour saveDoses(Long idec, DoseJour doses){
        ExamenClinique ec =  examenCliniqueRepository.findById(idec).orElse(null);
        Ordonnance ord =  ec.getOrdonnance();
        doses.setOrdonnance(ord);
        ord.setDoses(doses);
        // Set parent reference(ord) in child entity(doseJour)

        return doseJourRepository.save(doses);
    }


    //get methods
    public List<DoseJour> getDoses(){
        return doseJourRepository.findAll();
    }
    public List<Ordonnance> getOrdonnances(){
        return ordonnanceRepository.findAll();
    }

    public Ordonnance getOrdonnanceById(Long idEC){
        return ordonnanceRepository.findById(idEC).orElse(null);
    }

    //delete method
    public String deleteOrdonnance(Long idOrd){
        ExamenClinique ec =  getECById(idOrd);
        ec.setOrdonnance(null);
        ordonnanceRepository.deleteById(idOrd);
        return "Ordonnance Removed "+idOrd;
    }

    //update method
    public Ordonnance updateOrdonnance(Ordonnance ordonnance){
        Ordonnance exsistingOrd = ordonnanceRepository.findById(ordonnance.getId()).orElse(null);
        exsistingOrd.setDoses(ordonnance.getDoses());
        exsistingOrd.setAiguillesJ(ordonnance.getAiguillesJ());
        exsistingOrd.setBandelettesJ(ordonnance.getBandelettesJ());
        exsistingOrd.setLancettesJ(ordonnance.getLancettesJ());
        exsistingOrd.setAutre(ordonnance.getAutre());
        exsistingOrd.setExamenClinique(ordonnance.getExamenClinique());

        return ordonnanceRepository.save(exsistingOrd);
    }
}

package com.esi.examenclinique.service;


import com.esi.examenclinique.DTO.EleDTO;
import com.esi.examenclinique.entity.ExamenClinique;
import com.esi.examenclinique.repository.ExamenCliniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ExamenCliniqueService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExamenCliniqueRepository examenCliniqueRepository;

    //post methods
    public ExamenClinique saveExamenClinique(ExamenClinique examenClinique){
        return examenCliniqueRepository.save(examenClinique);
    }

    public List<ExamenClinique> saveExamensCliniques(List<ExamenClinique> examensCliniques){
        return examenCliniqueRepository.saveAll(examensCliniques);
    }

    //get methods
    public List<ExamenClinique> getExamensCliniques(){
        return examenCliniqueRepository.findAll();
    }

    public ExamenClinique getExamenCliniqueById(Long idEC){
        return examenCliniqueRepository.findById(idEC).orElse(null);
    }

    //delete method
    public String deleteExamenClinique(Long idEC){
        examenCliniqueRepository.deleteById(idEC);
        return "Examen Clinique Removed "+idEC;
    }

    //update method
    public ExamenClinique updateExamenClinique(ExamenClinique examenClinique){
        ExamenClinique exsistingEC = examenCliniqueRepository.findById(examenClinique.getId()).orElse(null);
        exsistingEC.setOrdonnance(examenClinique.getOrdonnance());
        exsistingEC.setCertificat(examenClinique.getCertificat());
        exsistingEC.setOrientation(examenClinique.getOrientation());
        exsistingEC.setEvacuation(examenClinique.getEvacuation());

        return examenCliniqueRepository.save(exsistingEC);
    }
    public ExamenClinique updateECA(Long idEC, Collection<Long> elesA){
        ExamenClinique ec = examenCliniqueRepository.findById(idEC).orElse(null);
        ec.getAppareil().clear();
        ec.getAppareil().addAll(elesA);
        return examenCliniqueRepository.save(ec);
    }
    public ExamenClinique updateECG(Long idEC, Collection<Long> elesG){
        ExamenClinique ec = examenCliniqueRepository.findById(idEC).orElse(null);
        ec.getGeneral().clear();
        ec.getGeneral().addAll(elesG);
        return examenCliniqueRepository.save(ec);

    }

    public List<EleDTO> getGenralByEC(ExamenClinique ec){
        List<EleDTO> genDTOList = new ArrayList<>();

        for (Long ele:ec.getGeneral()) {
            ResponseEntity<EleDTO> genResponse =  restTemplate.exchange(
                    "http://localhost:9002/ele/"+ele, HttpMethod.GET,null,EleDTO.class);
            EleDTO generalEle = genResponse.getBody();
            genDTOList.add(generalEle);
        }
        return genDTOList;
    }

    public List<EleDTO> getAppareilByEC(ExamenClinique ec){
        List<EleDTO> appDTOList = new ArrayList<>();

        for (Long ele:ec.getAppareil()) {
            ResponseEntity<EleDTO> appResponse =  restTemplate.exchange(
                    "http://localhost:9002/ele/"+ele, HttpMethod.GET,null,EleDTO.class);
            EleDTO appareilEle = appResponse.getBody();
            appDTOList.add(appareilEle);
        }
        return appDTOList;
    }
}

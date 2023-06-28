package lv.venta.fitness.controllers;

import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.services.IHealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.server.ExportException;
import java.util.Collection;

@RestController
@RequestMapping("/healthData")
public class HealthDataController {

    @Autowired
    private IHealthDataService healthDataService;


    @GetMapping("/showAll")
    Collection<HealthData> getAllHealthData(Model model){
        return healthDataService.selectAllHealthData();
    }

    @GetMapping("/showHealthDataByUserId/{idus}")
    Collection<HealthData> getHealthDataById(@PathVariable(name = "idus") long idus, Model model) throws Exception {
        return healthDataService.selectAllHealthDataByUserId(idus);

    }

    @GetMapping("/deleteById/{idhe}")
    void getDeleteHealthData(@PathVariable(name = "idhe") long idhe, Model model) throws Exception {
        healthDataService.deleteHealthDataById(idhe);
    }

    @GetMapping("/deleteByUserId/{idus}")
    void getDeleteExerciseByUserID(@PathVariable(name = "idus") long idus, Model model) throws Exception {
            healthDataService.deleteHealthDataByUserId(idus);
    }


}

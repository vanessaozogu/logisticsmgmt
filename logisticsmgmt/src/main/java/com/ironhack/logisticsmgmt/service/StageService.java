package com.ironhack.logisticsmgmt.service;

import com.ironhack.logisticsmgmt.model.Stage;
import com.ironhack.logisticsmgmt.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {
    private final StageRepository stageRepository;
    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }
    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public Stage getStageById(Long id) {
        return stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staging Location not found!"));
    }

    public Stage createStage(Stage stage) {
        return stageRepository.save(stage);
    }
    public Stage updateStage(Long id, Stage stageDetails) {
        Stage stage = getStageById(id);
        stage.setName(stageDetails.getName());
        stage.setLocation(stageDetails.getLocation());
        return stageRepository.save(stage);
    }

    public void deleteStageById(Long id) {
        stageRepository.deleteById(id);
    }

}

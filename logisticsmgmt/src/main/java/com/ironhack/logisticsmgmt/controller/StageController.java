package com.ironhack.logisticsmgmt.controller;

import com.ironhack.logisticsmgmt.model.Stage;
import com.ironhack.logisticsmgmt.service.StageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stages")
public class StageController {

    private final StageService stageService;
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }
    @GetMapping
    public List<Stage> getAllStages() {
        return stageService.getAllStages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        return ResponseEntity.ok(stageService.getStageById(id));
    }

    @PostMapping
    public ResponseEntity<Stage> createStage(@RequestBody Stage stage) {
        return ResponseEntity.ok(stageService.createStage(stage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable Long id, @RequestBody Stage stage) {
        return ResponseEntity.ok(stageService.updateStage(id, stage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        stageService.deleteStageById(id);
        return ResponseEntity.noContent().build();
    }

}

package com.btl.api.controller;

import com.btl.api.model.Preference;
import com.btl.api.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Preference")
public class PreferenceController {

    @Autowired
    private PreferenceService preferenceService;

    @GetMapping
    public ResponseEntity<List<Preference>> getAllPreferences() {
        List<Preference> Preference = preferenceService.getAllPreferences();
        return ResponseEntity.ok(Preference);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preference> getPreferencesById(@PathVariable Long id) {
        Preference Preference = preferenceService.getPreferencesById(id);
        if (Preference == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(Preference);
        }
    }

    @PostMapping
    public ResponseEntity<Preference> createPreferences(@RequestBody Preference Preference) {
        Preference createdPreferences = preferenceService.createPreferences(Preference);
        return ResponseEntity.ok(createdPreferences);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePreferences(@PathVariable Long id) {
        preferenceService.deletePreferences(id);
        return ResponseEntity.ok().build();
    }
}

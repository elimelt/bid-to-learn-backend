package com.btl.api.service;

import com.btl.api.model.Preference;
import com.btl.api.repository.PreferenceRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceService {
    @Autowired
    private PreferenceRepository preferenceRepository;

    @Transactional(readOnly = true)
    public List<Preference> getAllPreferences() {
        return preferenceRepository.findAll();
    }

    @Transactional
    public Preference createPreferences(Preference Preference) {
        // Additional validation or business logic if needed
        return preferenceRepository.save(Preference);
    }

    @Transactional
    public void deletePreferences(Long id) {
        preferenceRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Preference getPreferencesById(Long id) {
        return preferenceRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Preference> getPreferencesByUserId(Long userId) {
        return preferenceRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Preference> getPreferencesForUser(Long userId) {
        return preferenceRepository.findByUserId(userId);
    }
}

package com.btl.api.repository;

import com.btl.api.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    public List<Preference> findByUserId(Long userId);
}

package fr.pantheonsorbonne.repository;

import java.util.List;

import fr.pantheonsorbonne.model.Lender;

public interface CustomLenderRepository {
    List<Lender> findLendersByCarType(String carType);
    void updateLenderAvailability(Long lenderId, boolean isAvailable);
    Lender findLenderById(Long lenderId);
} 
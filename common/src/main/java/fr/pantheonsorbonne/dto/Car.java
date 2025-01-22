package fr.pantheonsorbonne.dto;

import java.time.LocalDate;

public record Car(Long id, String owner, String brand, String model, int pricePerDay, 
                  LocalDate startDateAvailability, LocalDate endDateAvailability, 
                  String type, String location) { }

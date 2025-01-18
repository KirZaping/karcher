package fr.pantheonsorbonne.model;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Assurance {
    private static final double TARIF_FORFAITAIRE = 25; // Constante pour le tarif forfaitaire
    private String type;
    private double price;

    // Getters et Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double age, double duree_permis, String type_assurance) {
        double coefficient_prix;
        coefficient_prix = switch (type_assurance) {
            case "Tout risques" -> 10;
            case "Arnaque" -> 5;
            case "Pigeon" -> 150;
            default -> 150;
        }; // Valeur par défaut si le type d'assurance ne correspond à aucun cas
        double confiance = (100-age) * (20-duree_permis); 
        if (confiance > 0.5) {
            this.price = TARIF_FORFAITAIRE + TARIF_FORFAITAIRE * confiance * coefficient_prix; // Utilisation de la constante pour le prix
        } else {
            this.price = TARIF_FORFAITAIRE * coefficient_prix;
        }
    }
}
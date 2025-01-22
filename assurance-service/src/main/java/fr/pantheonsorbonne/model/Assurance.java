package fr.pantheonsorbonne.model;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Assurance {
    private static final double TARIF_FORFAITAIRE = 25;
    private String type;
    private double price;
    private double age;
    private double duree_permis;
    private String assureur;

    // Getters et Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getDureePermis() {
        return duree_permis;
    }

    public void setDureePermis(double duree_permis) {
        this.duree_permis = duree_permis;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        double coefficient_prix = switch (this.type) {
            case "Tout risques" -> 10;
            case "Arnaque" -> 5;
            case "Pigeon" -> 150;
            default -> 150;
        };
        if (this.age > 65) {
            this.price = TARIF_FORFAITAIRE * coefficient_prix * (1 + (this.age - 65) * 0.1);
        } else {
            double confiance = (100 - this.age) * (20 - this.duree_permis);
            if (confiance > 0.5) {
                this.price = TARIF_FORFAITAIRE + TARIF_FORFAITAIRE * confiance * coefficient_prix;
            } else {
                this.price = TARIF_FORFAITAIRE * coefficient_prix;
            }
        }
        if (this.price > 500) {
            this.price = 500;
        }
        comparerAssureur();
    }

    public void setAssuranceInfo(double age, double duree_permis, String type_assurance) {
        setAge(age);
        setDureePermis(duree_permis);
        setType(type_assurance);
    }

    private void comparerAssureur() {
        double prixCovea = setCoveaPrice();
        double prixGroupama = setGroupamaPrice();
        double prixMMA = setMMAPrice();

        double prixMin = Math.min(prixCovea, Math.min(prixGroupama, prixMMA));
        this.price = prixMin;

        if (prixMin == prixCovea) {
            this.assureur = "Covéa";
        } else if (prixMin == prixGroupama) {
            this.assureur = "Groupama";
        } else {
            this.assureur = "MMA";
        }
    }

    private double setCoveaPrice() {
        double prixCovea = this.price;
        if (prixCovea > 400) {
            prixCovea = 400;
        }
        return prixCovea;
    }

    private double setGroupamaPrice() {
        double prixGroupama = this.price;
        if (this.age < 25 && this.duree_permis < 5) {
            prixGroupama *= 0.6;
        }
        return prixGroupama;
    }

    private double setMMAPrice() {
        double prixMMA = this.price;
        if (this.age > 60) {
            prixMMA *= 0.65;
        }
        return prixMMA;
    }

    public String getAssureur() {
        return assureur;
    }
}
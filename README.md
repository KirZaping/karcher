## Projet KARSHER - Plateforme de Location de Voitures entre Particuliers

## Objectifs du système à modéliser

Ce projet vise à modéliser une plateforme de location de voitures entre particuliers, intégrant des services associés comme :
- **Les assureurs** : Fournissant des devis d'assurance.
- **Les prêteurs** : Propriétaires proposant leurs véhicules à la location.

Le système permet :
- Aux utilisateurs (emprunteurs) de rechercher et réserver des voitures selon des critères de **date** et **localisation**, avec estimation des prix.
- De gérer la confirmation des locations et les coûts d'assurance proposés par des partenaires assureurs.

### Acteurs principaux du système
1. **Utilisateur (Emprunteur)** :  
   Recherche et réserve une voiture selon ses besoins.
2. **Prêteurs (Propriétaires)** :  
   Proposent leurs véhicules à la location.
3. **Assureurs (Partenaires)** :  
   Fournissent des devis pour les assurances des véhicules loués.

---

## Étapes principales du système

### 1. Accéder à la plateforme pour rechercher une voiture
- L'utilisateur fournit une **date** et une **localisation**.
- La plateforme retourne une liste des **voitures disponibles** et une **estimation des prix**.

### 2. Valider la disponibilité d'une voiture
- Une vérification est effectuée avec le **prêteur** pour confirmer la disponibilité à une date donnée.
- La plateforme retourne un **prix final**, hors assurance.

### 3. Récupérer le profil de l'utilisateur
- La plateforme demande à l'utilisateur des informations personnelles :
    - Nom, prénom, âge, date de permis, etc.

### 4. Récupérer les prix des assureurs
- La plateforme interroge les **assureurs** pour obtenir un devis basé sur le **profil de l'utilisateur**.

### 5. Récupération périodique des voitures disponibles
- La plateforme communique avec les **prêteurs** pour mettre à jour les :
    - Voitures disponibles.
    - Localisations.
    - Prix.

### 6. Confirmation de la location
- La plateforme gère la confirmation ou l'annulation des locations en impliquant :
    - L'utilisateur, le prêteur et l'assureur.

---

## Scénarios d'utilisation détaillés

### **Use Case 1 : Accéder à la plateforme pour voir les voitures disponibles**
- **Acteurs** : Emprunteur, Plateforme.
    1. L'utilisateur fournit une **date** et une **localisation**.
    2. La plateforme retourne une liste de **voitures disponibles** avec une estimation des prix.

---

### **Use Case 2 : Valider la disponibilité d'une voiture**
- **Acteurs** : Plateforme, Prêteur.
    1. La plateforme vérifie avec le prêteur la **disponibilité** de la voiture.
    2. Elle obtient le **prix final** pour la location.

---

### **Use Case 3 : Demande du profil utilisateur**
- **Acteurs** : Utilisateur, Plateforme.
    1. La plateforme récupère les **informations personnelles nécessaires** de l'utilisateur (nom, prénom, âge, etc.).

---

### **Use Case 4 : Récupération des prix des assureurs**
- **Acteurs** : Plateforme, Assureurs.
    1. La plateforme envoie le **profil utilisateur** aux assureurs.
    2. Elle récupère les **devis d'assurance**.

---

### **Use Case 5 : Récupération périodique des voitures disponibles**
- **Acteurs** : Plateforme, Prêteur.
    1. La plateforme actualise les **données des voitures disponibles**, leurs localisations et leurs prix auprès des prêteurs.

---

### **Use Case 6 : Confirmation de la location**
- **Acteurs** : Emprunteur, Plateforme, Prêteur, Assureur.
    1. La plateforme gère la confirmation ou l'annulation des **demandes de location**.
    2. Elle notifie tous les acteurs concernés.

---

## Fonctionnalités à venir
- Intégration d'une **passerelle de paiement sécurisée**.
- Fonctionnalité de **notation et avis** pour les prêteurs et emprunteurs.
- Optimisation des recherches avec des **algorithmes de recommandation**.

---

## Technologies utilisées
- Langage : **Java** / **Kotlin**
- Framework : **Spring Boot** ou **Quarkus**
- Base de données : **PostgreSQL**
- API externes : Intégration avec des assureurs partenaires.

---

## Installation et utilisation
1. Clonez le projet :
   ```bash
   git clone https://github.com/votre-projet.git
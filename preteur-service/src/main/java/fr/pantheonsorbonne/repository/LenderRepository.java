// package fr.pantheonsorbonne.repository;

// import fr.pantheonsorbonne.model.Lender;
// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import jakarta.enterprise.context.ApplicationScoped;

// import java.util.List;

// @ApplicationScoped
// public class LenderRepository implements PanacheRepository<Lender> {
    
//     @Override
//     public List<Lender> listAll() {
//         return findAll(); // Utilisation de la méthode fournie par PanacheRepository
//     }

//     @Override
//     public void persist(Lender lender) {
//         if (lender == null) {
//             throw new IllegalArgumentException("Lender cannot be null");
//         }
//         // Utilisation de la méthode persist fournie par PanacheRepository
//         this.getEntityManager().persist(lender);
//     }

//     @Override
//     public Lender findById(Long id) {
//         return find("id", id).firstResult(); // Utilisation de la méthode fournie par PanacheRepository
//     }
// }
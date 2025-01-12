// package fr.pantheonsorbonne.service;

// import fr.pantheonsorbonne.model.Lender;
// import fr.pantheonsorbonne.repository.LenderRepository;
// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;

// import java.util.List;

// @ApplicationScoped
// public class LenderService {

//     @Inject
//     LenderRepository lenderRepository;

//     public void addLender(Lender lender) {
//         lenderRepository.persist(lender);
//     }

//     public List<Lender> listLenders() {
//         return lenderRepository.listAll();
//     }

//     public Lender findLenderById(Long id) {
//         return lenderRepository.findById(id);
//     }
// }
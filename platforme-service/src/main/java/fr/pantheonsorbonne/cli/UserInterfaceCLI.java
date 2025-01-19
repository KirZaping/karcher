// package fr.pantheonsorbonne.cli;
// import org.beryx.textio.TextIO;
// import org.beryx.textio.TextTerminal;

// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;
// import org.eclipse.microprofile.rest.client.inject.RestClient;
// import fr.pantheonsorbonne.service.PlatformService;
// import fr.pantheonsorbonne.model.Car;

// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.List;

// @ApplicationScoped
// public class UserInterfaceCLI {

//     @Inject
//     @RestClient
//     PlatformService platformService;

//     @Inject
//     TextIO textIO; // Initialize TextIO through dependency injection
//     TextTerminal<?> terminal;

//     public void start() {
//         terminal = textIO.getTextTerminal(); // Initialize terminal

//         terminal.println("Welcome to the Car Rental Platform!");

//         // Step 1: User inputs
//         String location = textIO.newStringInputReader().read("Enter location: ");
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//         LocalDate startDate = LocalDate.parse(textIO.newStringInputReader().read("Enter start date (yyyy-MM-dd): "), formatter);
//         LocalDate endDate = LocalDate.parse(textIO.newStringInputReader().read("Enter end date (yyyy-MM-dd): "), formatter);

//         // Step 2: Get available cars
//         List<Car> availableCars = platformService.getAvailableCars(location, startDate, endDate);
//         if (availableCars.isEmpty()) {
//             terminal.println("No cars available for the given criteria.");
//             return;
//         }

//         terminal.println("Available Cars:");
//         for (Car car : availableCars) {
//             terminal.println("[" + car.getId() + "] " + car.getBrand() + " " + car.getModel() + " | Price/Day: " + car.getPricePerDay());
//         }

//         // Additional steps for user selection and booking can be added here
//     }
// }

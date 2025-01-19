// package fr.pantheonsorbonne.cli;

// import jakarta.inject.Inject;
// import org.beryx.textio.TextIO;
// import org.beryx.textio.TextIoFactory;
// import org.beryx.textio.swing.SwingTextTerminal;
// // Removed the problematic import for picocli
// import picocli.CommandLine.Command;
// // Removed the redundant import for picocli
// import picocli.CommandLine;
// import fr.pantheonsorbonne.cli.UserInterfaceCLI;

// @Command(name = "car-rental-cli", mixinStandardHelpOptions = true)
// public class Main implements Runnable {

//     @Inject
//     UserInterfaceCLI userInterfaceCLI;

//     @Override
//     public void run() {
//         // Set up the text terminal
//         System.setProperty(TextIoFactory.TEXT_TERMINAL_CLASS_PROPERTY, SwingTextTerminal.class.getName());
//         TextIO textIO = TextIoFactory.getTextIO();

//         // Ensure userInterfaceCLI is not null
//         if (userInterfaceCLI != null) {
//             // Start the user interface CLI
//             userInterfaceCLI.start();
//         } else {
//             System.err.println("UserInterfaceCLI is not initialized.");
//         }
//     }

//     public static void main(String[] args) {
//         // Create a CommandLine instance and run the CLI
//         CommandLine commandLine = new CommandLine(new Main());
//         commandLine.setCaseInsensitiveEnumValuesAllowed(true);
//         commandLine.execute(args);
//     }
// }

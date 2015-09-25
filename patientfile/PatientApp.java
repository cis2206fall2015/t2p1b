package patientfile;

import java.util.Scanner;

/**
 *
 * @author Your Name Here
 */
public class PatientApp {

    PatientDAO patList = new PatientDAO();
    Scanner sc = new Scanner(System.in);

    public PatientApp() {
        menuLoop();
    }

    private void menuLoop() {
        int bloodSugar, id, patientNumber;
        String date, comment;
        String choice = "1";
        while (!choice.equals("0")) {
            System.out.println("\nPatient App");
            System.out.println("0 = Quit");
            System.out.println("1 = List All Records");
            System.out.println("2 = Create New Record");
            System.out.println("3 = Retrieve Record");
            System.out.println("4 = Update Record");
            System.out.println("5 = Delete Record");
            choice = Validator.getLine(sc, "Number of choice: ", "^[0-5]$");

            switch (choice) {
                case "1":
                    System.out.println(patList.toString());
                    break;
                case "2":
                    id = Validator.getInt(sc, "New ID: ");
                    patientNumber = Validator.getInt(sc, "Patient Number: ");
                    date = Validator.getLine(sc, "Date: ");
                    bloodSugar = Validator.getInt(sc, "Blood Sugar: ");
                    comment = Validator.getLine(sc, "Comment: ");
                    patList.createRecord(new Patient(id, patientNumber, date, bloodSugar, comment));
                    break;
                case "3":
                    id = Validator.getInt(sc, "ID to retrieve: ");
                    System.out.println(patList.retrieveRecord(id));
                    break;
                case "4":
                    id = Validator.getInt(sc, "ID to update: ");
                    patientNumber = Validator.getInt(sc, "Patient Number: ");
                    date = Validator.getLine(sc, "Date Taken: ");
                    bloodSugar = Validator.getInt(sc, "Blood Sugar: ");
                    comment = Validator.getLine(sc, "Comment: ");
                    patList.updateRecord(new Patient(id, patientNumber, date, bloodSugar, comment));
                    break;
                case "5":
                    id = Validator.getInt(sc, "Patient ID to delete: ");
                    System.out.println(patList.retrieveRecord(id));
                    String ok = Validator.getLine(sc, "Delete this record? (y/n) ", "^[yYnN]$");
                    if (ok.equalsIgnoreCase("Y")) {
                        patList.deleteRecord(id);
                    }
                    break;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new PatientApp();
    }
}

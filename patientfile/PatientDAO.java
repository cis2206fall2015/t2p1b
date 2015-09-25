package patientfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jon Matson and David McLaughlin 
 */
public class PatientDAO {

    private final String fileName;
    protected final List<Patient> myList;

    public PatientDAO() {
        this("empdata.txt");
    }

    public PatientDAO(String fileName) {
        this.fileName = fileName;
        this.myList = new ArrayList<>();
        try {
            Files.createFile(Paths.get(fileName));
        } catch (FileAlreadyExistsException fae) {
            ;
        } catch (IOException ioe) {
            System.out.println("Create file error with " + ioe.getMessage());
        }
        readList();
    }

    public void createRecord(Patient patient) {
        myList.add(patient);
        writeList();
    }

    public Patient retrieveRecord(int id) {
        for (Patient patient : myList) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    public void updateRecord(Patient updatedPatient) {
        for (Patient patient : myList) {
            if (patient.getId() == updatedPatient.getId()) {
                patient.setPatientNumber(updatedPatient.getPatientNumber());
                patient.setDate(updatedPatient.getDate());
                patient.setBloodSugar(updatedPatient.getBloodSugar());
                patient.setComment(updatedPatient.getComment());
                break;
            }
        }
        writeList();
    }

    public void deleteRecord(int id) {
        for (Patient patient : myList) {
            if (patient.getId() == id) {
                myList.remove(patient);
                break;
            }
        }
        writeList();
    }

    public void deleteRecord(Patient patient) {
        myList.remove(patient);
        writeList();
    }

    protected void readList() {
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                int patientNumber = Integer.parseInt(data[1]);
                String date = data[2];
                int bloodSugar = Integer.parseInt(data[3]);
                String comment = data[4];
                Patient patient = new Patient(id, patientNumber, date, bloodSugar, comment);
                myList.add(patient);
            }
        } catch (IOException ioe) {
            System.out.println("Read file error with " + ioe.getMessage());
        }
    }

    protected void writeList() {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Patient patient : myList) {
                writer.write(String.format("%d,%d,%s,%d,%s\n",
                        patient.getId(),
                        patient.getPatientNumber(),
                        patient.getDate(),
                        patient.getBloodSugar(),
                        patient.getComment()));
            }
        } catch (IOException ioe) {
            System.out.println("Write file error with " + ioe.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        myList.stream().forEach((patient) -> {
            sb.append(String.format("%5d : %5d, %s, %4d, %s\n", patient.getId(),
                    patient.getPatientNumber(), patient.getDate(),
                    patient.getBloodSugar(), patient.getComment()));
        });

        return sb.toString();
    }
}

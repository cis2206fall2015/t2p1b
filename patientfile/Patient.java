package patientfile;

/**
 *
 * @author Jon Matson and David McLaughlin
 */
public class Patient {

    private int id;
    private int patientNumber;
    private String date;
    private int bloodSugar;
    private String comment;

    public Patient() {
        id = 0;
        patientNumber = 0;
        date = "";
        bloodSugar = 0;
        comment = "";
    }

    public Patient(int id, int patientNumber, String date, int bloodSugar, String comment) {
        this.id = id;
        this.patientNumber = patientNumber;
        this.date = date;
        this.bloodSugar = bloodSugar;
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(int patientNumber) {
        this.patientNumber = patientNumber;
    }

    public int getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(int bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", patientNumber=" + patientNumber 
                + ", date=" + date + ", bloodSugar=" + bloodSugar 
                + ", comment=" + comment + '}';
    }
}

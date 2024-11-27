public class Appointment {

    // Για κάθε πεδίο του πίνακα δημιουργούμε αντίστοιχο ιδιωτικό γνώρισμα της κλάσης
    private int appointmentId;
    private int userId;
    private int salonId;
    private int stylistId;
    private int serviceId;
    private Date date;
    private Time timeStart;
    private String status; // pending, confirmed, canceled

    // Στοιχεία σύνδεσης με τη βάση.
    // Θα τροποποιηθούν ανάλογα με τον διακομιστή της βάσης
    private static final String DB_SERVER = "jdbc:mysql://localhost:3306/hair_hub";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "******";

    /* Κατασκευαστής 1
     * Δέχεται σαν ορίσματα τιμές αντίστοιχες με τα ιδιωτικά γνωρίσματα της κλάσης
     * και εκχωρεί τις τιμές τους στα ιδιωτικά μέλη της
     */
    public Appointment(int appointmentId, int userId, int salonId, int stylistId, int serviceId, Date date, Time timeStart, String status) {
    	this.appointmentId = appointmentId;
    	this.userId = userId;
        this.salonId = salonId;
        this.stylistId = stylistId;
        this.serviceId = serviceId;
        this.date = date;
        this.timeStart = timeStart;
        this.status = status;
    }

    /* Κατασκευαστής 2
     * Δέχεται σαν ορίσματα τιμές αντίστοιχες με τα ιδιωτικά γνωρίσματα της κλάσης
     * εκτός από το appointmentId και εκχωρεί τις τιμές τους στα ιδιωτικά μέλη της.
     * Χρησιμοποιείται για να περνάμε το αντικείμενο σαν όρισμα στη μέθοδο
     * insertAppointment, όπου το appointmentId δεν είναι γνωστό πριν την εισαγωγή
     * της εγγραφής στη βάση
     */
    public Appointment(int userId, int salonId, int stylistId, int serviceId, Date date, Time timeStart, String status) {
    	this.userId = userId;
        this.salonId = salonId;
        this.stylistId = stylistId;
        this.serviceId = serviceId;
        this.date = date;
        this.timeStart = timeStart;
        this.status = status;
    }
    
    public Appointment() {
		// TODO Auto-generated constructor stub
	}

	/* Getters και Setters
     * Επιστρέφουν τις τιμές των ιδιωτικών γνωρισμάτων
     * Εκχωρούν στα ιδιωτικά γνωρίσματα τις τιμές των ορισμάτων
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSalonId() {
        return salonId;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
    }

    public int getStylistId() {
        return stylistId;
    }

    public void setStylistId(int stylistId) {
        this.stylistId = stylistId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

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
	
	// Default κατασκευαστής
	public Appointment() {
	}
	
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

    /* ********************************************
     *  Λειτουργίες σχετικές με τη βάση δεδομένων
     *  
     **********************************************/

    /* Εισαγωγή νέας εγγραφής στον πίνακα Appointments
     * Δέχεται σαν ορίσματα τις τιμές των γνωρισμάτων του appointment
     * και επιστρέφει το αντικείμενο appointment που δημιουργήθηκε στη βάση δεδομένων
     * με το appointmentId που του εκχωρήθηκε από τη βάση.
     */
    public Appointment insertNewAppointment(Appointment appointment) throws SQLException {
        
	try {
		// Σύνδεση με τη βάση δεδομένων
		Connection conn = DriverManager.getConnection(DB_SERVER, DB_USER, DB_PASSWORD);

		// Προετοιμασία της SQL ερώτησης για εκτέλεση στη βάση
		String sql = "INSERT INTO appointments (user_id, salon_id, stylist_id, service_id, date, time_start, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    stmt.setInt(1, appointment.getUserId());
	    stmt.setInt(2, appointment.getSalonId());
	    stmt.setInt(3, appointment.getStylistId());
	    stmt.setInt(4, appointment.getServiceId());
	    stmt.setDate(5, appointment.getDate());
	    stmt.setTime(6, appointment.getTimeStart());
	    stmt.setString(7, appointment.getStatus());
	    stmt.executeUpdate();

	    // Εάν η εισαγωγή του νέου appointment έγινε με επιτυχία
	    // βρίσκουμε το appointmentId που εκχώρησε η βάση δεδομένων
	    // στη νέα εγγραφή και ενημερώνουμε το αντικείμενο appointment
	    // που περάσαμε σαν όρισμα με αυτή.
	    ResultSet rs = stmt.getGeneratedKeys();
	    if (rs.next()) {
		appointment.setAppointmentId(rs.getInt(1));
		// Εάν η εισαγωγή έγινε με επιτυχία, επιστρέφουμε το αντικείμενο 
		return appointment;
	    }
	} catch(Exception e) {
		System.out.println(e.getMessage());
	}
	// Εάν η εγγραφή αποτύχει, επιστρέφουμε null
	return null;
    }
	
}

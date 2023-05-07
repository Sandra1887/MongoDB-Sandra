import org.bson.Document;

public class Employee extends Person {
    private String _employeeId;

    public Employee(String name, String age, String address) {
        super(name, age, address);
    }
    public Employee(String name, String age, String address, String employeeId) {
        super(name, age, address);
        set_employeeId(employeeId);
    }

    public String get_employeeId() {
        return _employeeId;
    }
    public void set_employeeId(String _employeeId) {
        this._employeeId = _employeeId;
    }
    @Override
    public String toString() {
        return "EmployeeID: " + _employeeId + ". " + super.getName() + " (" + super.getAge() + ") " +
                " at " + super.getAddress();
    }

    public static Person fromDoc(Document doc) {
        if (doc == null) {
            return new Client("", "", "", "");
        } else {
            return new Client(
                    doc.getString("name"),
                    doc.getString("age"),
                    doc.getString("address"),
                    doc.getString("_customerID"));
        }
    }
    @Override
    public Document toDoc() {
        return new Document("_employeeID", _employeeId)
                .append("name", super.getName())
                .append("age", super.getAge())
                .append("address", super.getAddress());
    }
}


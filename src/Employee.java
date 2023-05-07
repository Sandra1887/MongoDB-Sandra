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
    @Override
    public Document toDoc() {
        return new Document("name", super.getName())
                .append("_employeeID", _employeeId)
                .append("age", super.getAge())
                .append("address", super.getAddress());
    }
}


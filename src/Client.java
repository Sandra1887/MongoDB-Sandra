import org.bson.Document;

public class Client extends Person{
    private String _customerId;

    public Client(String name, String age, String address) {
        super(name, age, address);
    }

    public Client(String name, String age, String address, String customerId) {
        super(name, age, address);
        set_customerId(customerId);
    }


    public String get_customerId() {
        return _customerId;
    }

    public void set_customerId(String _customerId) {
        this._customerId = _customerId;
    }
    @Override
    public String toString() {
        return "CustomerID: " + _customerId + ". " + super.getName() + " (" + super.getAge() + ") " +
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
        return new Document("_customerid", _customerId)
                .append("name", super.getName())
                .append("age", super.getAge())
                .append("address", super.getAddress());
    }
}

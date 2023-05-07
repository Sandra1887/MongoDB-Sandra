import org.bson.Document;

public class Person {
    private String _id;
    private String name;
    private String age;
    private String address;

    public Person(String name, String age, String address) {
        setName(name);
        setAge(age);
        setAddress(address);
    }

    public Person(String id, String name, String age, String address) {
        set_id(id);
        setName(name);
        setAge(age);
        setAddress(address);
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return name + " (" + age + ") " + " at " + address;
    }

    public static Person fromDoc(Document doc) {
        if (doc == null) {
            return new Person("", "", "");
        } else {
            return new Person(
                    doc.getString("name"),
                    doc.getString("_id"),
                    doc.getString("age"),
                    doc.getString("address")
            );
        }
    }

    public Document toDoc() {
        return new Document("name", name)
                .append("_id", _id)
                .append("age", age)
                .append("address", address);
    }

    public Person fromJson(String json) {
        Document doc = Document.parse(json);
        return fromDoc(doc);
    }

    public String toJson() {
        return toDoc().toJson();
    }
}
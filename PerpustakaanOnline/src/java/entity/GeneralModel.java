package entity;

public class GeneralModel {

    private int id;
    private String name;
    private String type;
    private Object object;
    private String Value;

    public GeneralModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GeneralModel(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public GeneralModel(int id, String name, String type, Object object) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.object = object;
    }

    public GeneralModel(int id, String name, String type, Object object, String Value) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.object = object;
        this.Value = Value;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

}

package personal.carlthronson.dl.be.company;

import java.util.UUID;

public class Company {

    public String id;
    public String name;
    public String url;
    public String size;

    public Company() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    protected Company clone() {
        Company result = new Company();
        result.name = new String(this.name);
        result.url = new String(this.url);
        result.size = new String(this.size);
        return result;
    }
}
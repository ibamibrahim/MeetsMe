package id.meetsme.meetsme.services.models.response;

/**
 * Created by Ibam on 9/8/2017.
 */

public class DummyResultModel {
    private String name;
    private String occupation;
    private String interest;

    public DummyResultModel(String name, String occupation, String interest) {

        this.name = name;
        this.occupation = occupation;
        this.interest = interest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}

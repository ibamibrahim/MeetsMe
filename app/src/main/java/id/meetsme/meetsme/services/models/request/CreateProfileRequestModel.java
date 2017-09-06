package id.meetsme.meetsme.services.models.request;

/**
 * Created by Ibam on 9/7/2017.
 */

public class CreateProfileRequestModel {
    private String sex;
    private String occupation;
    private String user_interest;
    private String birth_date;

    public CreateProfileRequestModel(String sex, String occupation, String user_interest, String birth_date) {
        this.sex = sex;
        this.occupation = occupation;
        this.user_interest = user_interest;
        this.birth_date = birth_date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getUser_interest() {
        return user_interest;
    }

    public void setUser_interest(String user_interest) {
        this.user_interest = user_interest;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }
}

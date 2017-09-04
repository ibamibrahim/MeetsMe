
package id.meetsme.meetsme.services.models.response.login;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private User_ user;
    @SerializedName("sex")
    @Expose
    private Object sex;
    @SerializedName("occupation")
    @Expose
    private Object occupation;
    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("phone_number")
    @Expose
    private Object phoneNumber;
    @SerializedName("location_lat")
    @Expose
    private Object locationLat;
    @SerializedName("location_lon")
    @Expose
    private Object locationLon;
    @SerializedName("user_interest")
    @Expose
    private List<Object> userInterest = null;
    @SerializedName("birth_place")
    @Expose
    private Object birthPlace;
    @SerializedName("birth_date")
    @Expose
    private Object birthDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User_ getUser() {
        return user;
    }

    public void setUser(User_ user) {
        this.user = user;
    }

    public Object getSex() {
        return sex;
    }

    public void setSex(Object sex) {
        this.sex = sex;
    }

    public Object getOccupation() {
        return occupation;
    }

    public void setOccupation(Object occupation) {
        this.occupation = occupation;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Object locationLat) {
        this.locationLat = locationLat;
    }

    public Object getLocationLon() {
        return locationLon;
    }

    public void setLocationLon(Object locationLon) {
        this.locationLon = locationLon;
    }

    public List<Object> getUserInterest() {
        return userInterest;
    }

    public void setUserInterest(List<Object> userInterest) {
        this.userInterest = userInterest;
    }

    public Object getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Object birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Object getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Object birthDate) {
        this.birthDate = birthDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}

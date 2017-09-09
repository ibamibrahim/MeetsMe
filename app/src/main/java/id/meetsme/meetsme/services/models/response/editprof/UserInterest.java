
package id.meetsme.meetsme.services.models.response.editprof;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInterest {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("interest")
    @Expose
    private Integer interest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }

}

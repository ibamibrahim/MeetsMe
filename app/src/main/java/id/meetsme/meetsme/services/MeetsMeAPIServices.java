package id.meetsme.meetsme.services;

import id.meetsme.meetsme.services.models.request.LoginRequestModel;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Ibam on 8/28/2017.
 */

public interface MeetsMeAPIServices {

    @POST("/api/v1/account/login/")
    Observable<Response<LoginResponseModel>> login(@Body LoginRequestModel data);
}

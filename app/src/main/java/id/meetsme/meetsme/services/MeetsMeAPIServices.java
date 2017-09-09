package id.meetsme.meetsme.services;

import id.meetsme.meetsme.services.models.request.AddInterestRequestModel;
import id.meetsme.meetsme.services.models.request.CreateProfileRequestModel;
import id.meetsme.meetsme.services.models.request.LoginRequestModel;
import id.meetsme.meetsme.services.models.request.MatchMakingRequestModel;
import id.meetsme.meetsme.services.models.request.RegisterRequestModel;
import id.meetsme.meetsme.services.models.response.AddUserInterestResponse;
import id.meetsme.meetsme.services.models.response.MatchMakingResponse;
import id.meetsme.meetsme.services.models.response.editprof.EditProfResponseModel;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;
import id.meetsme.meetsme.services.models.response.register.RegisterResponseModel;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Ibam on 8/28/2017.
 */

public interface MeetsMeAPIServices {

    @POST("/api/v1/account/login/")
    Observable<Response<LoginResponseModel>> login(@Body LoginRequestModel data);

    @POST("/api/v1/account/register/")
    Observable<Response<RegisterResponseModel>> register(@Body RegisterRequestModel data);

    @PUT("/api/v1/account/user/{id}")
    Observable<Response<EditProfResponseModel>> createProf(@Header("Authorization") String token,
                                                           @Path("id") int id, @Body
                                                                   CreateProfileRequestModel model);

    @POST("/api/v1/account/add-interest/")
    Observable<Response<AddUserInterestResponse>> addInterest(@Header("Authorization") String token, @Body
            AddInterestRequestModel model);

    @POST("/api/v1/matchmaking/matchmaking-engine/")
    Observable<Response<MatchMakingResponse>> matchMaking(@Header("Authorization") String token,
                                                          @Body MatchMakingRequestModel model);
}

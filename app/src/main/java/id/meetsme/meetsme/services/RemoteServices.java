package id.meetsme.meetsme.services;

import id.meetsme.meetsme.services.models.request.LoginRequestModel;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Ibam on 8/28/2017.
 */

public class RemoteServices implements ServicesContract.Remote {

    private final String BASE_URL = "https://meetme-gemastik.herokuapp.com/api/v1/";
    private MeetsMeAPIServices retrofit;

    public Observable<Response<LoginResponseModel>> login(String email, String password){
        initRetrofit();
        return retrofit.login(new LoginRequestModel(email, password));
    }


    private void initRetrofit() {
        this.retrofit = getRetrofit(BASE_URL);
    }

    public MeetsMeAPIServices getRetrofit(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(MeetsMeAPIServices.class);
    }
}

package ada.osc.taskie.networking;

import ada.osc.taskie.model.RegistrationToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/register/")
    Call<RegistrationToken> registerUser(@Body RegistrationToken registrationToken);

}

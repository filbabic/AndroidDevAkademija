package ada.osc.taskie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationToken {

    @Expose
    @SerializedName("name")
    public final String userName;
    @Expose
    @SerializedName("email")
    public final String email;
    @Expose
    @SerializedName("password")
    public final String password;

    public RegistrationToken(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}

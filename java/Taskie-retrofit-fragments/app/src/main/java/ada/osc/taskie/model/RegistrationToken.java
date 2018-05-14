package ada.osc.taskie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationToken {
    @Expose
    @SerializedName("name")
    public String mUserName;
    @Expose
    @SerializedName("email")
    public String mEmail;
    @Expose
    @SerializedName("password")
    public String mPassword;
}

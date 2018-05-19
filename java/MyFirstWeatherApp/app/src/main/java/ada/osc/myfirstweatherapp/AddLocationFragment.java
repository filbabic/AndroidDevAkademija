package ada.osc.myfirstweatherapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Filip on 10/02/2016.
 */
public class AddLocationFragment extends Fragment implements View.OnClickListener {
    private EditText mEnterLocationNameEditText;
    private Button mAddLocationButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onSuccess() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.location_added_success_toast_message), Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    public void onLocationAlreadyExistsError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.location_already_exists_error_message));
    }

    public void onEmptyStringRequestError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.empty_location_string_error_message));
    }

    private void initUI(View view) {
        mEnterLocationNameEditText = (EditText) view.findViewById(R.id.fragment_add_location_enter_city_edit_text);
        mAddLocationButton = (Button) view.findViewById(R.id.fragment_add_location_button);
        mAddLocationButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == mAddLocationButton) {
        }
    }
}

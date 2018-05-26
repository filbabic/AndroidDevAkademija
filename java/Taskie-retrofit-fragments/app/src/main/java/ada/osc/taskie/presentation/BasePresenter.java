package ada.osc.taskie.presentation;

public interface BasePresenter<View> {

    void setView(View view);

    void onStop();

    void onStart();
}

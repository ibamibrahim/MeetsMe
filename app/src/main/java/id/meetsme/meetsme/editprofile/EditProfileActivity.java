package id.meetsme.meetsme.editprofile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.thomashaertel.widget.MultiSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.helper.Helper;
import id.meetsme.meetsme.services.models.response.editprof.EditProfResponseModel;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public class EditProfileActivity extends BaseActivity implements EditProfileContract.View {
    private static final String TAG = "EditProfileActivity";
    EditProfilePresenter mPresenter;
    @BindView(R.id.join_now_button)
    TextView joinNowButton;
    @BindView(R.id.create_interest)
    MultiSpinner inputInterest;
    @BindView(R.id.create_birthday)
    EditText inputBirthday;
    @BindView(R.id.create_city)
    EditText inputCity;
    @BindView(R.id.create_occup)
    EditText inputOccup;
    @BindView(R.id.create_sex)
    RadioGroup inputSex;
    @BindView(R.id.edit_title)
    TextView title;
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.female)
    RadioButton female;
    @BindView(R.id.edit_subtitle)
    TextView subtitle;

    SpinnerOnClickListener listener;
    Calendar myCalendar;
    LoginResponseModel user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        ButterKnife.bind(this);
        myCalendar = Calendar.getInstance();
        title.setText("Edit profile");
        subtitle.setText("");
        joinNowButton.setText("Save");
        initIntent();
        initPresenter();
        initInterest();
        initDatePicker();
    }

    private void initIntent() {
        Intent intent = getIntent();
        String json = intent.getStringExtra("data");
        user = Helper.jsonToObject(json, LoginResponseModel.class);
        if (user != null) {
            inputBirthday.setText(user.getUser().getBirthDate());
            if (user.getUser().getSex().equals("male")) {
                male.setSelected(true);
            } else {
                female.setSelected(true);
            }
            inputBirthday.setVisibility(View.GONE);
            inputSex.setVisibility(View.GONE);
            inputOccup.setText(user.getUser().getOccupation());
            inputInterest.setText(user.getUser().getUserInterest().toString());
            Log.i(TAG, "initIntent: " + user.getUser().getUserInterest().size());
        }
    }

    private void initPresenter() {
        mPresenter = new EditProfilePresenter();
        mPresenter.setView(this);
    }

    private void initInterest() {
        ArrayAdapter<String> adapter = initAdapter();
        listener = new SpinnerOnClickListener();
        inputInterest.setAdapter(adapter, false, listener);
    }

    private ArrayAdapter<String> initAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.addAll("Football", "Taekwondo", "Formula One", "Basketball", "Badminton", "Teaching", "Education", "Environment", "Renewable", "Energy", "Artificial Intelligence", "Public Speaking", "Politic");
        return adapter;
    }

    private void updateLabel() {
        //String myFormat = "MM/dd/yy"; //In which you need put here
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        inputBirthday.setText(sdf.format(myCalendar.getTime()));
    }

    private void initDatePicker() {

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        inputBirthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EditProfileActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @OnClick(R.id.join_now_button)
    public void joinNow() {
        int selectedSex = inputSex.getCheckedRadioButtonId();
        RadioButton selected = (RadioButton) findViewById(selectedSex);

        String occupation = inputOccup.getText().toString();
        String birthday = user.getUser().getBirthDate();
        String interest = listener.getSelected();
        Log.i(TAG, "joinNow: interest " + interest);
        String sex;
        try {
            sex = user.getUser().getSex();
            if (occupation.equals("") || birthday.equals("") || interest.equals("") || sex.equals("")) {
                showToast("All field must be filled!");
            } else {
                //showToast(occupation + " " + birthday + " " + interest + " " + sex);
                createProfile(sex, occupation, interest, birthday);
            }
        } catch (NullPointerException e) {
            showToast("All field must be filled!");
        }
    }

    private void createProfile(String sex, String occup, String interest, String birthday) {
        showProgressDialog("Loading...");
        mPresenter.createProfile(sex, occup, interest, birthday, getApplicationContext());
    }

    @Override
    public void createProfileStatus(boolean status, String message, EditProfResponseModel model) {
        hideDialog();
        if (status) {
            finish();
        }
    }

    @Override
    public void setPresenter(EditProfileContract.Presenter presenter) {
        this.mPresenter = (EditProfilePresenter) presenter;
    }

    private class SpinnerOnClickListener implements MultiSpinner.MultiSpinnerListener {

        private String selectedStr;

        @Override
        public void onItemsSelected(boolean[] selected) {
            selectedStr = "";
            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) {
                    selectedStr = selectedStr + i + ",";
                }
            }

            selectedStr = selectedStr.substring(0, selectedStr.length() - 1);
        }

        public String getSelected() {
            return selectedStr;
        }
    }
}

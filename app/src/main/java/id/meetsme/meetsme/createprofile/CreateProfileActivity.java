package id.meetsme.meetsme.createprofile;

import android.app.DatePickerDialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;

/**
 * Created by Ibam on 8/28/2017.
 */

public class CreateProfileActivity extends BaseActivity implements CreateProfileContract.View {
    CreateProfilePresenter mPresenter;

    @BindView(R.id.join_now_button)
    TextView joinNowButton;

    @BindView(R.id.create_interest)
    MultiAutoCompleteTextView inputInterest;

    @BindView(R.id.create_birthday)
    EditText inputBirthday;

    Calendar myCalendar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        ButterKnife.bind(this);
        myCalendar = Calendar.getInstance();
        initInterest();
        initDatePicker();
    }

    private void initInterest() {
        String[] androidVersionNames = {"Aestro", "Blender", "CupCake", "Donut", "Eclair", "Froyo", "Gingerbread", "HoneyComb", "IceCream Sandwich", "Jellibean", "Kitkat", "Lollipop", "MarshMallow"};
        ArrayAdapter<String> versionNames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, androidVersionNames);
        inputInterest.setAdapter(versionNames);
        inputInterest.setThreshold(1);
        inputInterest.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
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
                new DatePickerDialog(CreateProfileActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @OnClick(R.id.join_now_button)
    public void joinNow() {
        showToast(inputInterest.getText().toString());
    }

    @Override
    public void setPresenter(CreateProfileContract.Presenter presenter) {
        this.mPresenter = (CreateProfilePresenter) presenter;
    }
}

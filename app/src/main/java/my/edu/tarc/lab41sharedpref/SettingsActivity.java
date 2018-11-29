package my.edu.tarc.lab41sharedpref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
    private static final String PREF_FILE = "my.edu.tarc.lab41sharedpref";
    private EditText editTextName;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonfemale;
    private ImageView imageViewProfile;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        editTextName = findViewById(R.id.editTextName);
        radioButtonfemale = findViewById(R.id.radioButtonFemale);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        sharedPreferences = getSharedPreferences(PREF_FILE, MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name;
        int gender; //-1 = default, 1 = male, 0 = female
        name = sharedPreferences.getString(getString(R.string.user_name), getString(R.string.no_name));
        gender = sharedPreferences.getInt(getString(R.string.user_gender), -1);
        editTextName.setText(name);
        if(gender == 1){
            radioButtonMale.setChecked(true);
            imageViewProfile.setImageResource(R.drawable.male);
        } else if(gender == 0){
            radioButtonfemale.setChecked(true);
            imageViewProfile.setImageResource(R.drawable.female);
        } else {
            imageViewProfile.setImageResource(R.drawable.profile);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //1. Create a SharedPref editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //2. edit the SharedPreferences
        String name;
        int gender;
        name = editTextName.getText().toString();
        editor.putString(getString(R.string.user_name), name);
        gender = radioGroupGender.getCheckedRadioButtonId();
        if(gender == R.id.radioButtonMale){
            editor.putInt(getString(R.string.user_gender), 1);
        } else if (gender == R.id.radioButtonFemale){
            editor.putInt(getString(R.string.user_gender), 0);
        }
        editor.commit();
    }
}

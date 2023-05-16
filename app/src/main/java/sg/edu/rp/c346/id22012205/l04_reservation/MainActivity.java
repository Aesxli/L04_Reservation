package sg.edu.rp.c346.id22012205.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText NInput;
EditText HPInput;
EditText GSInput;
RadioGroup Smokearea;
DatePicker DP;
TimePicker TP;
Button confirmation;
Button reset;
RadioButton nsmokea;

RadioButton smokea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NInput=findViewById(R.id.NameText);
        HPInput=findViewById(R.id.HPText);
        GSInput=findViewById(R.id.GSText);
        Smokearea=findViewById(R.id.SmokeRadio);
        smokea=findViewById(R.id.SButton);
        DP=findViewById(R.id.datePicker);
        TP=findViewById(R.id.timePicker);
        confirmation=findViewById(R.id.ConfirmButton);
        reset=findViewById(R.id.ResetButton);
        nsmokea=findViewById(R.id.NSButton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NInput.setText("");
                HPInput.setText("");
                GSInput.setText("");
                Smokearea.clearCheck();
                TP.setCurrentHour(19);
                TP.setCurrentMinute(30);
                int day=1;
                int month=5;
                int year=2023;
                DP.updateDate(year,month,day);
            }
        });
        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NInput.getText().toString().trim().length()!=0){
                    if(HPInput.getText().toString().trim().length()!=0){
                        if(GSInput.getText().toString().trim().length()!=0){
                            nsmokea.setChecked(true);
                            int area=Smokearea.getCheckedRadioButtonId();
                            String date=DP.getDayOfMonth()+"/"+DP.getMonth()+"/"+ DP.getYear();
                            String time = "";
                            if(TP.getCurrentMinute()<10) {
                                 time = TP.getCurrentHour() + "." + "0"+TP.getCurrentMinute();
                            }else{
                                 time = TP.getCurrentHour() + "."+TP.getCurrentMinute();
                            }
                                String details="Name"+NInput.getText().toString()+"\n HP:"+HPInput.getText().toString()+"\n Group Size:"+GSInput.getText().toString()+"\n Dine in area:"+area+"\n Date:"+date+"\n Time:"+time;
                                Toast.makeText(MainActivity.this,details,Toast.LENGTH_SHORT).show();
                        }else{
                            GSInput.setError("Please Enter your Group Size!");
                            Toast.makeText(MainActivity.this,"Please Enter your Group Size!",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        HPInput.setError("Please Enter your Phone Number!");
                        Toast.makeText(MainActivity.this,"Please Enter your Phone Number!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    NInput.setError("Please Enter your Name!");
                    Toast.makeText(MainActivity.this,"Please Enter your Name!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        TP.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            if(hourOfDay<8){
                TP.setCurrentHour(8);
                TP.setCurrentMinute(00);
                Toast.makeText(MainActivity.this,"Please set time between 8AM and 8.59PM!",Toast.LENGTH_SHORT).show();
            }else if(hourOfDay>=21){
                TP.setCurrentHour(20);
                TP.setCurrentHour(59);
                Toast.makeText(MainActivity.this,"Please set time between 8AM and 8.59PM!",Toast.LENGTH_SHORT).show();
            }
            }
        });
        
        }
    }

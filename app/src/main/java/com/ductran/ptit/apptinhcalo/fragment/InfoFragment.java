package com.ductran.ptit.apptinhcalo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ductran.ptit.apptinhcalo.R;

public class InfoFragment extends Fragment {
    public View mView;
    public EditText chieucaoEd, cannangEd, tuoiEd;
    public RadioButton NamRd, NuRd;
    public Button calBtn;
    public TextView bmiTv, ttTv, caloTv,proteinTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_info, container, false);

        //anh xa
        chieucaoEd =  mView.findViewById(R.id.chieucaoEd);
        cannangEd  =  mView.findViewById(R.id.cannangEd);
        tuoiEd     =  mView.findViewById(R.id.tuoiEd);
        NamRd      =  mView.findViewById(R.id.NamRd);
        NuRd       =  mView.findViewById(R.id.NuRd);
        calBtn     =  mView.findViewById(R.id.calBtn);
        bmiTv      =  mView.findViewById(R.id.bmiTv);
        ttTv       =  mView.findViewById(R.id.ttTv);
        caloTv     =  mView.findViewById(R.id.caloTv);
        proteinTv  =  mView.findViewById(R.id.proteinTv);

        //xu ly nut tinh
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double cc = Double.parseDouble(chieucaoEd.getText().toString());
                double cn = Double.parseDouble(cannangEd.getText().toString());
                int t = Integer.parseInt(tuoiEd.getText().toString());
                if(NamRd.isChecked())
                {
                    double bmi = (double) Math.round ((cn/(cc*2))*10.0)/10.0;
                    bmiTv.setText(String.valueOf(bmi));
                    if(bmi < 18.5){
                        ttTv.setText("Gầy");
                    }
                    else if( bmi > 18.5 && bmi < 25){
                        ttTv.setText("Bình thường");
                    }
                    else if( bmi >=25 && bmi < 30 ){
                        ttTv.setText("Hơi béo");
                    }
                    double bmr = Math.round(((13.397*cn) + (4.799*cc*100) - (5.677*t) + 88.362)*1.55);
                    caloTv.setText(String.valueOf(bmr));

                    double protein = Math.round(cn*2.2);
                    proteinTv.setText(String.valueOf(protein)+"g");
                }

                else if(NuRd.isChecked()){
                    double bmi = (double) Math.round (cn/(cc*2)*10.0)/10.0;
                    bmiTv.setText(String.valueOf(bmi));
                    if(bmi < 18.5){
                        ttTv.setText("Gầy");
                    }
                    else if( bmi > 18.5 && bmi < 25){
                        ttTv.setText("Bình thường");
                    }
                    else if( bmi >=25 && bmi < 30 ){
                        ttTv.setText("Hơi béo");
                    }
                    double bmr = Math.round(((9.247*cn) + (3.098*cc*100) - (4.330*t) + 44.7593)*1.55);
                    caloTv.setText(String.valueOf(bmr));

                    double protein = Math.round(cn*1.5);
                    proteinTv.setText(String.valueOf(protein)+" g");
                }
            }
        });
        return mView;
    }
}
package com.example.week11.ui.EditText;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.week11.R;
import com.example.week11.TextViewModel;

public class EditTextFragment extends Fragment {


   // private TextViewModel textViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        //textViewModel = ViewModelProviders.of(this).get(TextViewModel.class);
        final TextViewModel textViewModel = new ViewModelProvider(requireActivity()).get(TextViewModel.class);


        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final EditText editText = root.findViewById(R.id.Edit_Text);
        editText.setFocusable(false);

        final Switch canEdit = root.findViewById(R.id.switch1);
        canEdit.setChecked(false);

        canEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editText.setFocusableInTouchMode(true);
                    editText.setFocusable(true);

                } else {
                    editText.setFocusable(false);
                }
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    System.out.println("Updated string");
                    textViewModel.setText(editText.getText().toString());
                }
            }
        });


        textViewModel.getText().observe(getViewLifecycleOwner(),new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editText.setText(s);
            }
        });




        return root;
    }
}

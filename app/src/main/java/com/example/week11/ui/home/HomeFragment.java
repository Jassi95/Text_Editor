package com.example.week11.ui.home;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.week11.R;
import com.example.week11.TextViewModel;


public class HomeFragment extends Fragment  {

    //private TextViewModel TextViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*
        TextViewModel =
                ViewModelProviders.of(this).get(TextViewModel.class);*/

        final TextViewModel textViewModel = new ViewModelProvider(requireActivity()).get(TextViewModel.class);


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setMovementMethod(new ScrollingMovementMethod());

        textViewModel.getText().observe(getViewLifecycleOwner(),new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);

            }
        });


        textViewModel.getFontSize().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setTextSize(integer.floatValue());
            }
        });
        textViewModel.getLineSpacingExtra().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setLineSpacing(integer.floatValue(),(float)1.0);
            }
        });
        textViewModel.getTextColor().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setTextColor(Color.parseColor(s));
            }
        });
        textViewModel.getFontFamily().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setTypeface(Typeface.create(s,Typeface.NORMAL));
            }
        });


        return root;
    }

}

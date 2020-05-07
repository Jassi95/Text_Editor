package com.example.week11.ui.Settings;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.week11.LocaleHelper;
import com.example.week11.MainActivity;
import com.example.week11.R;
import com.example.week11.TextViewModel;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static androidx.core.app.ActivityCompat.recreate;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SettingsFragment extends Fragment {

    private SettingsModel slideshowViewModel;
    private Spinner fontFamily,lineSpacing,fontSize,language;
    private Button button;
    private int curLanguage = 1;
    //private String name;
    private EditText nameHolder;
    private static final String[] fonts = new String[]{"sans-serif","casual", "cursive","monospace","sans-serif-black","sans-serif-light","serif","serif-monospace"};
    private static final String[] languages = new String[]{"Finnish","English"};
    private static final List<Integer> lineSpacesHolder = IntStream.iterate(8,x->x + 2).limit(20).boxed().collect(Collectors.toList());
    public View onCreateView (@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final TextViewModel textViewModel = new ViewModelProvider(requireActivity()).get(TextViewModel.class);

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        // all view elements
        fontFamily= root.findViewById(R.id.fontFamily);
        lineSpacing = root.findViewById(R.id.lineSpacing);
        fontSize = root.findViewById(R.id.fontSize);
        nameHolder = root.findViewById(R.id.name);
        button = root.findViewById(R.id.button);
        language = root.findViewById(R.id.languageSpinner);
        // sets spinners
        ArrayAdapter<String> adapterFont = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, fonts);
        adapterFont.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontFamily.setAdapter(adapterFont);

        ArrayAdapter<Integer> adapterFontSize = new ArrayAdapter<Integer>(this.getActivity(), android.R.layout.simple_spinner_item, lineSpacesHolder);
        adapterFontSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontSize.setAdapter(adapterFontSize);
        lineSpacing.setAdapter(adapterFontSize);

        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(this.getActivity(), R.array.languages, android.R.layout.simple_spinner_item); //todo
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language.setAdapter(adapterLanguage);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                textViewModel.setName(nameHolder.getText().toString());
                System.out.println("Vaihdettiin tekijän nimi: "+ nameHolder.getText().toString());
            }
        });


        //updates setting parameters
        textViewModel.getFontSize().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                fontSize.setSelection(lineSpacesHolder.indexOf(integer));
            }
        });

        textViewModel.getLineSpacingExtra().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                lineSpacing.setSelection(lineSpacesHolder.indexOf(integer));
            }
        });

        textViewModel.getFontFamily().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                for(int i=0; i<fonts.length; i++) {
                    if(fonts[i].equals(s)){
                    fontFamily.setSelection(i);
                    }
                }

            }
        });

        textViewModel.getName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                nameHolder.setText(s);
            }
        });


        fontSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //updates fontsize
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textViewModel.setFontSize(lineSpacesHolder.get(position));
                System.out.println("Vaihdettiin fontSize: "+ lineSpacesHolder.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lineSpacing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textViewModel.setLineSpacingExtra(lineSpacesHolder.get(position));
                System.out.println("Vaihdettiin line spacing: "+ lineSpacesHolder.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fontFamily.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textViewModel.setFontFamily(fonts[position]);
                System.out.println("Vaihdettiin Font family: "+ fonts[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //todo
                System.out.println("positio on: "+position);
                if(position == 1 && position!=textViewModel.getLanguage()){
                    //LocaleHelper.setLocale(getActivity(),"eng");
                    textViewModel.setLanguage(position);
                    System.out.println("Change language to eng");
                    ((MainActivity) getActivity()).changeLanguage( "eng");

                    //MainActivity.changeLanguage("eng");

                    //recreate(getActivity());
                } else if(position == 2 && position!=textViewModel.getLanguage()){
                    //LocaleHelper.setLocale(getActivity(),"fi");
                    System.out.println("Change language to FI");
                    textViewModel.setLanguage(position);
                    ((MainActivity) getActivity()).changeLanguage("fi");
                    //recreate(getActivity());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        ColorPickerView Color = root.findViewById(R.id.color_picker_view);
        Color.addOnColorSelectedListener(new OnColorSelectedListener() {

            @Override
            public void onColorSelected(int selectedColor) {
                System.out.println("Updated color to: #"+ Integer.toHexString(selectedColor));
                textViewModel.setTextColor("#"+Integer.toHexString(selectedColor));
            }
        });


        return root;
    }



}


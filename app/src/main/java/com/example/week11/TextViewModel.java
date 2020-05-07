package com.example.week11;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TextViewModel extends ViewModel {
    private String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas et sem dolor. " +
            "Aenean in ante vitae diam ornare semper. Aenean vitae libero faucibus sem ultrices egestas sed eget lorem. " +
            "Nulla interdum, arcu at maximus tempus, orci risus dictum justo, ut ultricies quam eros id arcu. Etiam orci elit, ornare pharetra.";

    private MutableLiveData<String> mText = new MutableLiveData<>(text);
    private MutableLiveData<Integer> fontSize = new MutableLiveData<>(14);
    private MutableLiveData<String> fontFamily = new MutableLiveData<>("serif");
    private MutableLiveData<Integer> lineSpacingExtra = new MutableLiveData<>(0);
    private MutableLiveData<String> textColor = new MutableLiveData<>("#000000");
    private MutableLiveData<String> name = new MutableLiveData<>("");
    private MutableLiveData<Integer> language = new MutableLiveData<>(-1);




    public LiveData<String> getText() {

        if(name.getValue().equals("")){return mText;}
        else{
            MutableLiveData<String> a = new MutableLiveData<>();
            a.setValue(mText.getValue() + ("\n~ "+name.getValue()));
            return a;
        }
    }
    public void setLanguage(int i){
        MutableLiveData<Integer> j = new MutableLiveData<>(i);
        System.out.println("muutettu kieli: "+j);
        this.language = j;
    }
    public int getLanguage(){
        int i = language.getValue();
        System.out.println("kieli on: "+i);
        return i;
    }

    public void setText(String s) {
        String[] a = s.split("\n~"); //Removes the signature
        mText.setValue(a[0]);
        System.out.println("Updating: "+a[0]);

    }

    public void setName(String s){
        name.setValue(s);
    }
    public LiveData<String> getName(){return name;}

    public LiveData<Integer> getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        MutableLiveData<Integer> Fs = new MutableLiveData<>(fontSize);
        this.fontSize = Fs;
    }

    public LiveData<String> getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String s) {
        fontFamily.setValue(s);

    }

    public LiveData<Integer> getLineSpacingExtra() {
        return lineSpacingExtra;
    }

    public void setLineSpacingExtra(int lineSpacingExtra) {
        MutableLiveData<Integer> Lse = new MutableLiveData<>(lineSpacingExtra);
        this.lineSpacingExtra = Lse;
    }

    public LiveData<String> getTextColor() {
        return textColor;
    }

    public void setTextColor(String s) {
        textColor.setValue(s);
    }
}

package com.rodbase.rodbase;

import androidx.annotation.NonNull;

import com.rodbase.rodbase.Language.Language;

public class RodbaseException extends Exception {
    public RodbaseException(String message){
        super(message);
    }
    public RodbaseException(RodbaseException e){
        super(e.getMessage());
    }

    @NonNull
    @Override
    public String toString() {
        String message = getMessage();
        assert message != null;
        Language language = Language.Language(Rodbase.getInstance().languageCode);
        if(message.contains(language.exception())){
            message = message.replaceAll(language.exception()+" ", "");
        }
        return language.exception().replaceAll("%e", message);
    }
}

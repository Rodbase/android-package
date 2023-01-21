package com.rodbase.rodbase.Language;

import com.rodbase.rodbase.Rodbase;

import java.util.HashMap;
import java.util.Map;

public class Language implements LanguageInterface{

    static Map<String, Language> supportedLanguages = new HashMap<String, Language>() {{
        put("en", new LanguageEn());
        put("tr", new LanguageTr());
    }};

    public static Language Language(String languageCode) {
        if(supportedLanguages.containsKey(languageCode)){
            return (Language)supportedLanguages.get(languageCode);
        }else if(Rodbase.getInstance().customLanguages.containsKey(languageCode)){
            return Rodbase.getInstance().customLanguages.get(languageCode);
        }else{
            return supportedLanguages.get("en");
        }
    }
}

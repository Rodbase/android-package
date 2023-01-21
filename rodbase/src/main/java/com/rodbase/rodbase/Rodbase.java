package com.rodbase.rodbase;

import android.content.Context;

import com.rodbase.rodbase.Language.Language;

import java.util.HashMap;
import java.util.Map;

public class Rodbase {
     String apiUrl;
     public Paths paths;
     boolean  enableDebug;
     String apiKey;
     public String languageCode;
     public Map<String, Language> customLanguages;
     private Context context;
     private static final String _defaultLanguageCode = "en";

     private Rodbase(){

     }
     public Context getContext(){
         return context;
    }
     private Rodbase(Context context, String apiUrl, String apiKey, Paths paths, String languageCode, Map<String, Language> customLanguages, boolean enableDebug){
         this.context = context;
         this.apiUrl = apiUrl;
         this.paths = paths;
         this.apiKey = apiKey;
         this.languageCode = languageCode;
         this.customLanguages = customLanguages;
         this.enableDebug = enableDebug;
     }

     private static Rodbase instance = new Rodbase();

     public static Rodbase getInstance(){
         return instance;
     }

     public static void initialize(Context context, String apiUrl, String apiKey){
         init(context, apiUrl, apiKey, null, null, false);
     }
     public static void initialize(Context context, String apiUrl, String apiKey, String languageCode){
         init(context, apiUrl, apiKey, languageCode, null, false);
     }
     public static void initialize(Context context, String apiUrl, String apiKey, String languageCode, Map<String, Language> customLanguages){
         init(context, apiUrl, apiKey, languageCode, customLanguages, false);
     }
     public static void initialize(Context context, String apiUrl, String apiKey, String languageCode, Map<String, Language> customLanguages,boolean enableDebug){
         init(context, apiUrl, apiKey, languageCode, customLanguages, enableDebug);
     }
     private static void init(Context context, String apiUrl, String apiKey, String languageCode, Map<String, Language> customLanguages, boolean enableDebug){
         instance = new Rodbase(context, apiUrl, apiKey,new Paths(apiUrl),languageCode == null ? _defaultLanguageCode : languageCode, customLanguages == null ? new HashMap<String, Language>() : customLanguages, enableDebug);
     }
     boolean apiUrlExists() throws RodbaseException {
        boolean exception = false;
        if (apiUrl == null) {
            exception = true;
        } else {
            if (apiUrl.equals("")) {
                exception = true;
            }
        }
        if (exception) {
            throw new RodbaseException(Language.Language(languageCode).initialize_method());
        } else {
            return true;
        }
     }

     public void setLanguageCode(String languageCode) {
         instance.languageCode = languageCode;
     }

     public void addCustomLanguages(Map<String, Language> _customLanguages) {
         for (Map.Entry<String, Language> _customLanguage : _customLanguages.entrySet()) {
             if(instance.customLanguages.containsKey(_customLanguage.getKey())){
                instance.customLanguages.remove(_customLanguage.getKey());
             }
             instance.customLanguages.put(_customLanguage.getKey(), _customLanguage.getValue());
         }
     }
}

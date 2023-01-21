package com.rodbase.rodbase;

import java.util.HashMap;
import java.util.Map;

class RodbaseBase {
    RodbaseBase(){

    }
    public static RodbaseBase getInstance(){
        RodbaseBase rodbaseBase = new RodbaseBase();
        rodbaseBase.add_to_all(rodbaseBase.api_key,Rodbase.getInstance().apiKey);
        rodbaseBase.add_to_all(rodbaseBase.lang_key, Rodbase.getInstance().languageCode);
        return rodbaseBase;
    }
    public final String prefix = "rodbase_";
    public final String success_str = "Successfull";
    public final String error_str = "error";
    public final String message_str = "message";
    public final String result_str = "result";
    public final String api_key = "api_key";
    public final String upload_path_str = "uploads/";
    public final String path_key = "path";
    public final String download_url_str = "download_url";
    public final String file_name_key = "file_name";
    public final String file_key = "file";
    public final String lang_key = "lang";
    public String auth_id_key = prefix+"auth_id";
    public final String id_key = "id";
    public final String name_key = "name";
    public final String custom_informations_key = "custom_informations";
    public final String email_key = "email";
    public final String email_confirmed_key = "email_confirmed";
    public final String password_key = "password";
    public final String user_key = "user";
    public final String confirmation_code_key = "confirmation_code";
    public final String timeout_key = "timeout";

    public final Map<String, String> data_all = new HashMap<>();
    public void add_to_all(String key, String value) {
        data_all.remove(key);
        data_all.put(key, value);
    }
    public void remove_from_all(String key) {
        data_all.remove(key);
    }
    public void clear_all() {
        data_all.clear();
    }
}

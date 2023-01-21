package com.rodbase.rodbase;

import android.content.ClipData;
import android.content.Context;

import com.rodbase.rodbase.Language.Language;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RodbaseAuthUser{
    public String id;
    public String email;
    public String name;
    public boolean email_confirmed;
    public Map<String, Object> custom_informations;
    private RodbaseBase _rodbaseBase;
    private RodbaseAuthUser(String id, String email, String name,boolean email_confirmed,Map<String, Object> custom_informations, RodbaseBase _rodbaseBase){
        this.id = id;
        this.email = email;
        this.name = name;
        this.email_confirmed = email_confirmed;
        this.custom_informations = custom_informations;
        this._rodbaseBase = _rodbaseBase;
    }
    static RodbaseAuthUser _fromMap(Map<String, Object> data) throws JSONException {
        RodbaseBase rodbaseBase = RodbaseBase.getInstance();
        String userID = null;
        if (data.get(rodbaseBase.id_key) != null){
            userID = (String) data.get(rodbaseBase.id_key);
        }
        String email = null;
        if (data.get(rodbaseBase.email_key) != null){
            email = (String) data.get(rodbaseBase.email_key);
        }
        String name = null;
        if (data.get(rodbaseBase.name_key) != null) {
            name = (String) data.get(rodbaseBase.name_key);
        }
        boolean email_confirmed = false;
        if (data.get(rodbaseBase.email_confirmed_key) != null) {
            email_confirmed = Objects.equals((String) data.get(rodbaseBase.email_confirmed_key), "1");
        }
        Map<String, Object> custom_informations = new HashMap<>();
        if (data.get(rodbaseBase.custom_informations_key) != null) {
            custom_informations =
                    RodbaseJson.jsonToMap((String)data.get(rodbaseBase.custom_informations_key));
        }
        if(userID != null){
            return new RodbaseAuthUser(userID, email, name, email_confirmed, custom_informations, rodbaseBase);
        }else{
            return null;
        }
    }
    public interface ConfirmationCodeListener{
        void onCodeSent();
        void onError(String message);
    }
    void sendConfirmationCode(int seconds, ConfirmationCodeListener listener) throws RodbaseException {
        try {
            Map<String, String> postData = new HashMap<>();
            postData.put(_rodbaseBase.email_key, email);
            postData.put(_rodbaseBase.id_key, id);
            postData.put(_rodbaseBase.timeout_key, seconds+"");
            postData.putAll(_rodbaseBase.data_all);
            Map<String, Object> map =  Post.map(Rodbase.getInstance().paths.send_confirmation_code, postData);

            if(Objects.equals((String) ((Map<?, ?>) map.get(_rodbaseBase.error_str)).get(_rodbaseBase.message_str), _rodbaseBase.success_str)){
                boolean codeSent = Objects.equals((String) map.get(_rodbaseBase.result_str), "true");
                if(codeSent){
                    listener.onCodeSent();
                }else{
                    listener.onError(Language.Language(Rodbase.getInstance().languageCode).an_error_occurred());
                }
            }else {
                throw new RodbaseException((String)((Map<?, ?>) map.get(_rodbaseBase.error_str)).get(_rodbaseBase.message_str));
            }
        } catch (RodbaseException e) {
            listener.onError(e.getMessage());
        } catch (JSONException e) {
            throw new RodbaseException(e.getMessage());
        }
    }
    RodbaseAuthUser confirmCode(Context context, String code, RodbaseAuthListener listener) throws JSONException, RodbaseException {
        try {
            Map<String, String> postData = new HashMap<>();
            postData.put(_rodbaseBase.id_key, id);
            postData.put(_rodbaseBase.confirmation_code_key, code);
            postData.putAll(_rodbaseBase.data_all);
            Map<String, Object> map =  Post.map(Rodbase.getInstance().paths.confirm_code, postData);
            if (Objects.equals((String) ((Map<?, ?>) map.get(_rodbaseBase.error_str)).get(_rodbaseBase.message_str), _rodbaseBase.success_str)) {
                RodbaseAuthUser user =
                        RodbaseAuthUser._fromMap((Map<String, Object>)map.get(_rodbaseBase.result_str));
                if (user != null) {
                    RodbaseAuth.getInstance()._saveUser(user.id);
                    listener.OnCompleted(user);
                    return user;
                } else {
                    listener.OnError( Language.Language(Rodbase.getInstance().languageCode).an_error_occurred());
                }
            } else {
                throw new RodbaseException(
                        ((String)((Map<?, ?>) map.get(_rodbaseBase.error_str)).get(_rodbaseBase.message_str)));
            }
        } catch (RodbaseException e) {
            listener.OnError(e.getMessage());
        } catch (Exception e) {
            throw new RodbaseException(e.getMessage());
        }
        return null;
    }
    private RodbaseAuthUser _update(String email, String name, String password, Map<String, Object> custom_informations, RodbaseAuthUserCustomInformationMode rodbaseAuthUserCustomInformationMode, RodbaseAuthListener listener) throws RodbaseException {
        try {
            Map<String, String> postData = new HashMap<>();
            postData.put(_rodbaseBase.id_key, id);
            postData.putAll(_rodbaseBase.data_all);
            if (email != null) {
                postData.put(_rodbaseBase.email_key, email);
            }
            if (name != null) {
                postData.put(_rodbaseBase.name_key, name);
            }
            if (password != null) {
                postData.put(_rodbaseBase.password_key, password);
            }
            if (custom_informations != null) {
                switch (rodbaseAuthUserCustomInformationMode) {
                    case update:
                        postData.put(_rodbaseBase.custom_informations_key, _updateCustomInformations(custom_informations).toString());
                        break;
                    case delete:
                        postData.put(_rodbaseBase.custom_informations_key, _deleteCustomInformations(custom_informations).toString());
                        break;
                }
            }
            Map<String, Object> map = Post.map(Rodbase.getInstance().paths.update_user, postData);
            if (Objects.equals((String) ((Map<?, ?>) map.get(_rodbaseBase.error_str)).get(_rodbaseBase.message_str), _rodbaseBase.success_str)) {
                RodbaseAuthUser user =
                        RodbaseAuthUser._fromMap((Map<String, Object>)map.get(_rodbaseBase.result_str));
                if (user != null) {
                    this.email = user.email;
                    this.name = user.name;
                    this.custom_informations = user.custom_informations;
                    email_confirmed = user.email_confirmed;
                    listener.OnCompleted(user);
                } else {
                    listener.OnError(Language.Language(Rodbase.getInstance().languageCode).an_error_occurred());
                }
                return user;
            } else {
                throw new RodbaseException(
                        ((String)((Map<?, ?>) map.get(_rodbaseBase.error_str)).get(_rodbaseBase.message_str)));
            }
        } catch (RodbaseException e) {
            listener.OnError(e.getMessage());
            return null;
        } catch (Exception e) {
            listener.OnError(e.getMessage());
            throw new RodbaseException(e.getMessage());
        }
    }
    Map<String, Object> _updateCustomInformations(
            Map<String, Object> custom_informations) {
        Map<String, Object> custom_information_temp = new HashMap<>();
        for (Map.Entry<String, Object> data : custom_informations.entrySet()) {
            custom_information_temp.remove(data.getKey());
            custom_information_temp.put(data.getKey(), data.getValue());
        }
        return custom_information_temp;
    }
    Map<String, Object> _deleteCustomInformations(
            Map<String, Object> custom_informations) {
        Map<String, Object> custom_information_temp = new HashMap<>(this.custom_informations);
        for (Map.Entry<String, Object> data : custom_informations.entrySet()) {
            custom_information_temp.remove(data.getKey());
        }
        return custom_information_temp;
    }
    public RodbaseAuthUser deleteCustomInformations(
            List<String> custom_informations, RodbaseAuthListener listener){
        Map<String, Object> custom_informations_map = new HashMap<>();
        for(int i = 0; i < custom_informations.size(); i++){
            custom_informations_map.put(custom_informations.get(i), custom_informations.get(i));
        }
        try {
            return _update(null, null, null, custom_informations_map,
                    RodbaseAuthUserCustomInformationMode.delete,
                    listener
            );
        } catch (RodbaseException e) {
            return null;
        }
    }
    public RodbaseAuthUser update(String email, String name, String password, Map<String, Object> custom_informations, RodbaseAuthListener listener) {
        try {
            return _update(
                    email,
                    name,
                    password,
                    custom_informations,
                    RodbaseAuthUserCustomInformationMode.update,
                    listener
            );
        } catch (RodbaseException e) {
            return null;
        }
    }
}
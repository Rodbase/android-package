package com.rodbase.rodbase;


import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.rodbase.rodbase.Language.Language;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RodbaseAuth {

    private RodbaseAuth(){

    }

    static RodbaseAuth _instance = new RodbaseAuth();
    public Context context;
    static RodbaseAuth  getInstance(Context context) {
        _instance = new RodbaseAuth();
        _instance.context = context;
        _instance.sharedPreferences = _instance.context.getSharedPreferences(_instance._rodbaseBase.prefix, MODE_PRIVATE);
        return _instance;
    }

    final RodbaseBase _rodbaseBase = new RodbaseBase();

    public RodbaseAuthUser signUpWithEmail(String email, String password, String name, Map<String, String> custom_informations, RodbaseAuthListener listener) throws RodbaseException {
        try {
            Map<String, String> postData = new HashMap<>();
            postData.put(_rodbaseBase.email_key, email);
            postData.put(_rodbaseBase.password_key, password);
            if (name != null) {
                postData.put(_rodbaseBase.name_key, name);
            }
            if (custom_informations != null) {
                postData.put(_rodbaseBase.custom_informations_key, custom_informations.toString());
            }

            postData.putAll(_rodbaseBase.data_all);
            Map<String, Object> map = Post.map(Rodbase.getInstance().paths.sign_up_with_email, postData);
            if (Objects.equals((String) ((Map<?, ?>) map.get(_rodbaseBase.error_str)).get(_rodbaseBase.message_str), _rodbaseBase.success_str)) {
                RodbaseAuthUser user =
                        RodbaseAuthUser._fromMap((Map<String, Object>)map.get(_rodbaseBase.result_str));
                if (user != null) {
                    _saveUser(user.id);
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
        } catch (JSONException e) {
            listener.OnError(e.getMessage());
            throw new RodbaseException(e.getMessage());
        }
    }
    RodbaseAuthUser signInWithEmail(String email, String password, RodbaseAuthListener listener) throws RodbaseException {
        try {
            Map<String, String> postData = new HashMap<>();
            postData.put(_rodbaseBase.email_key, email);
            postData.put(_rodbaseBase.password_key, password);
            postData.putAll(_rodbaseBase.data_all);
            Map<String, Object> map = Post.map(Rodbase.getInstance().paths.sign_in_with_email, postData);
            if (Objects.equals((String) ((Map<?, ?>) map.get(_rodbaseBase.error_str)).get(_rodbaseBase.message_str), _rodbaseBase.success_str)) {
                RodbaseAuthUser user =
                        RodbaseAuthUser._fromMap((Map<String, Object>)map.get(_rodbaseBase.result_str));
                if (user != null) {
                     _saveUser(user.id);
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
    RodbaseAuthUser signInAnonymously(RodbaseAuthListener listener) throws RodbaseException {
        try {
            Map<String, String> postData = new HashMap<>(_rodbaseBase.data_all);
            Map<String, Object> map =  Post.map(Rodbase.getInstance().paths.sign_in_anonymously, postData);
            if (Objects.equals((String) ((Map<?, ?>) map.get(_rodbaseBase.error_str)).get(_rodbaseBase.message_str), _rodbaseBase.success_str)) {
                RodbaseAuthUser user =
                        RodbaseAuthUser._fromMap((Map<String, Object>)map.get(_rodbaseBase.result_str));
                if (user != null) {
                    _saveUser(user.id);
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
    public SharedPreferences sharedPreferences;
    void _saveUser(String id){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(_rodbaseBase.auth_id_key, id);
        edit.apply();
    }
    String _getUser() {
        return sharedPreferences.getString(_rodbaseBase.auth_id_key, "");
    }
    void _removeUser() {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(_rodbaseBase.auth_id_key);
        edit.apply();
    }
    RodbaseAuthUser user() {
        try {
            String userID = _getUser();
            Map<String, String> postData = new HashMap<>();
            postData.put(_rodbaseBase.id_key, userID);
            postData.putAll(_rodbaseBase.data_all);
            Map<String, Object> map =  Post.map(Rodbase.getInstance().paths.get_user, postData);
            if (Objects.equals((String) ((Map<?, ?>) map.get(_rodbaseBase.error_str)).get(_rodbaseBase.message_str), _rodbaseBase.success_str)) {
                return RodbaseAuthUser._fromMap((Map<String, Object>)map.get(_rodbaseBase.result_str));
            } else {
                return null;
            }
        }catch (RodbaseException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}


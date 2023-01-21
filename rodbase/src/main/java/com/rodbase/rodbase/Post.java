package com.rodbase.rodbase;


import static java.lang.Math.log;
import static java.lang.Math.pow;

import org.json.JSONException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class Post {
    private static String _formatBytes(int bytes, int decimals) {
        if (bytes <= 0) return "0 B";
        List<String> suffixes = Arrays.asList("B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB");
        double i = Math.floor(log(bytes) / log(1024));
        return Math.floor(bytes / pow(1024, i)) + " " + suffixes.get((int)i);
    }
    private static final OkHttpClient client = new OkHttpClient();
    public static String response(String url, Map<String, String> datas) throws RodbaseException {
         MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        for (Map.Entry<String, String> data : datas.entrySet()) {
             builder.addFormDataPart(data.getKey(), data.getValue());
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()){
                throw new RodbaseException("Unexpected code " + response);
            }
            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
            assert response.body() != null;
            System.out.println(response.body().string());
            return response.body().string();
        } catch (Exception e) {
            throw new RodbaseException(e.toString());
        }
    }
    public static Map<String, Object> map(String url, Map<String, String> datas) throws RodbaseException, JSONException {
        return RodbaseJson.jsonToMap(response(url,datas));
    }
    public static List<Object> list(String url, Map<String, String> datas) throws RodbaseException, JSONException {
        return RodbaseJson.jsonToList(response(url,datas));
    }

}

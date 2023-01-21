package com.rodbase.rodbase;


class Paths {
    public Paths(String apiUrl){
        this.apiUrl = apiUrl;
    }
    private String apiUrl;
    private String apiStr = "api";
    String apiUrl() {
        if (apiUrl.endsWith("/"+apiStr+"/") || apiUrl.endsWith("/"+apiStr)) {
            if (apiUrl.endsWith("/"+apiStr)) {
                return apiUrl+"/";
            }
            return apiUrl;
        } else {
            if (apiUrl.endsWith("/")) {
                return apiUrl+apiStr+"/";
            }
            return apiUrl+"/"+apiStr+"/";
        }
    }

    String result = apiUrl() + "result";

    String get_compiled_select = apiUrl() + "get_compiled_select";

    String num_rows = apiUrl() + "num_rows";

    String count_all_results = apiUrl() + "count_all_results";

    String count_all = apiUrl() + "count_all";

    String insert = apiUrl() + "insert";

    String insert_batch = apiUrl() + "insert_batch";

    String get_compiled_insert = apiUrl() + "get_compiled_insert";

    String replace = apiUrl() + "replace";

    String update = apiUrl() + "update";

    String get_compiled_update = apiUrl() + "get_compiled_update";

    String delete = apiUrl() + "delete";

    String get_compiled_delete =  apiUrl() + "get_compiled_delete";

    String empty_table = apiUrl() + "empty_table";

    String truncate = apiUrl() + "truncate";

    String upload =apiUrl() + "upload";

    String get_download_url = apiUrl() + "get_download_url";

    String get_user = apiUrl() + "get_user";

    String sign_up_with_email = apiUrl() + "sign_up_with_email";

    String sign_in_with_email = apiUrl() + "sign_in_with_email";

    String sign_in_anonymously = apiUrl() + "sign_in_anonymously";

    public String send_confirmation_code = apiUrl() + "send_confirmation_code";

    String  confirm_code = apiUrl() + "confirm_code";

    String send_password_reset_link = apiUrl() + "send_password_reset_link";

    String update_user = apiUrl() + "update_user";
}
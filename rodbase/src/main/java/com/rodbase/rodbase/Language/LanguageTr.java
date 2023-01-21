package com.rodbase.rodbase.Language;

public class LanguageTr extends Language {
    public LanguageTr() {

    }
    @Override
    public String initialize_method() {
        return "apiUrl null olamaz. Lütfen main() bloğunun içinde runApp'den önce Rodbase.initialize(apiUrl: api url'niz) methodunu kullanın;\nBloğun başında WidgetsFlutterBinding.ensureInitialized(); methodunu kullanmayı unutmayın!";
    }
    @Override
    public String where_method_usage(){
        return "Belirtilmemiş kullanım. Geçerli örnek: where(\"column_name\", \"value\") ya da where(<String,String>{\"column_name1\": \"value1\",\"column_name2\": \"value2\",...})";
    }
    @Override
    public String order_by_method_usage(){
        return "Belirtilmemiş kullanım. Geçerli örnek: order_by(\"column_name\", order_by.asc)\n Ya da map olarak order_by(<String,order_by>{\"column_name1\": order_by.asc,\"column_name2\": order_by.desc,...})\nYa da tek satır olarak: order_by(\"column_name1 ASC, column_name2 DESC ...\")";
    }
    @Override
    public String string_convert_integet_error(){
        return "String değeri integer'e dönüştürülemiyor!";
    }
    @Override
    public String unimplemented_usage(){
        return "Geçersiz kullanım.";
    }
    @Override
    public String unimplemented_usage_string_or_list(){
        return "Geçersiz kullanım. String ya da List<String> kullanın.";
    }
    @Override
    public String table_name_can_not_be_empty(){
        return "Tablo ismi boş olamaz!";
    }
    @Override
    public String table_names_can_not_be_empty(){
        return "tablo ismi/isimleri boş olamaz";
    }
    @Override
    public String set_table_name_exception(){
        return "get(\"tablo_adi\") methoduyla bir tablo adı belirleyin Ya da özel veritabanı komutları için query(\"veritabanı komutunuz\") methodunu kullanın.";
    }
    @Override
    public String rodbase_result(){
        return "Rodbase Sonucu:";
    }
    @Override
    public String first_parameter_string_or_list(){
        return "İlk parametre String ya da List<String> olmalı!";
    }
    @Override
    public String no_query(){
        return "QUERY YOK";
    }
    @Override
    public String exception(){
        return "Hata: %e";
    }
    @Override
    public String path_file_name_exception(){
        return "path sonunda dosya adı belirtmelisiniz";
    }
    @Override
    public String an_error_occurred(){
        return "Bir hata oluştu! Lütfen daha sonra tekrar deneyin!";
    }
}

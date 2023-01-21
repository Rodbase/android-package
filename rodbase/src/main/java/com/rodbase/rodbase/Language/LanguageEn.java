package com.rodbase.rodbase.Language;

public class LanguageEn extends Language {

    public LanguageEn() {

    }

    @Override
    public String initialize_method(){
        return "apiUrl can't be null. Please initialize app in main() method uşing Rodbase.initialize(apiUrl: your api url) before runApp;\nDon't forget to use WidgetsFlutterBinding.ensureInitialized(); before";
    }

    @Override
    public String where_method_usage(){
        return "Unimplemented using. Usage example: where(\"column_name\", \"value\") or where(<String,String>{\"column_name1\": \"value1\",\"column_name2\": \"value2\",...})";
    }

    @Override
    public String order_by_method_usage(){
        return "Unimplemented using. Usage example: order_by(\"column_name\", order_by.asc)\n Or with Map order_by(<String,order_by>{\"column_name1\": order_by.asc,\"column_name2\": order_by.desc,...})\nOr with single line order_by(\"column_name1 ASC, column_name2 DESC ...\")";
    }

    @Override
    public String string_convert_integet_error(){
        return "String could not converted to integer!";
    }

    @Override
    public String unimplemented_usage() {
        return "Unimplemented usage.";
    }

    @Override
    public String unimplemented_usage_string_or_list(){
        return "Unimplemented usage. Use String or List<String>";
    }

    @Override
    public String table_name_can_not_be_empty(){
        return "Table name can not be empty!";
    }

    @Override
    public String table_names_can_not_be_empty(){
        return "table name(s) could not be empty";
    }

    @Override
    public String set_table_name_exception(){
        return "You should set table name with get(\"table_name\") method OR use query(\"your database command\")";
    }

    @Override
    public String rodbase_result(){
        return "Rodbase Result:";
    }

    @Override
    public String first_parameter_string_or_list(){
        return "First parameter should be String or List<String>";
    }

    @Override
    public String no_query(){
        return "NO QUERY";
    }

    @Override
    public String exception(){
        return "Exception: %e";
    }

    @Override
    public String path_file_name_exception(){
        return "You should add file name to end of path";
    }

    @Override
    public String an_error_occurred(){
        return "An error occurred! Please try again later!";
    }
}

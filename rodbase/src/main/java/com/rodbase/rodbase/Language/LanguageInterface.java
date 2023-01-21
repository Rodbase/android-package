package com.rodbase.rodbase.Language;

interface  LanguageInterface {

    default String initialize_method(){
        return "initialize_method";
    }
    default String where_method_usage() {
         return "where_method_usage";
    }
    default String order_by_method_usage(){
        return "order_by_method_usage";
    }
    default String string_convert_integet_error(){
        return "string_convert_integet_error";
    }
    default String unimplemented_usage(){
        return "unimplemented_usage";
    }
    default String unimplemented_usage_string_or_list(){
        return "unimplemented_usage_string_or_list";
    }
    default String table_name_can_not_be_empty(){
        return "table_name_can_not_be_empty";
    }
    default String table_names_can_not_be_empty(){
        return "table_names_can_not_be_empty";
    }
    default String set_table_name_exception(){
        return "set_table_name_exception";
    }
    default String rodbase_result(){
        return "rodbase_result";
    }
    default String first_parameter_string_or_list(){
        return "first_parameter_string_or_list";
    }
    default String no_query(){
        return "no_query";
    }
    default String exception(){
        return "exception";
    }
    default String path_file_name_exception(){
        return "path_file_name_exception";
    }
    default String an_error_occurred(){
        return "an_error_occurred";
    }
}

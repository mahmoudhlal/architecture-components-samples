package com.ma7moud.newsapp.utils;

import com.ma7moud.newsapp.models.CountryModel.Country;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    private static Constants constants ;
    public  final String API_KEY = "111f0795b6d845b9ab38a833168c3be4" ;
    public  List<Country> COUNTRY_LIST = new ArrayList<>() ;

    public static synchronized Constants getInstance(){
        if (constants == null){
            constants =  new Constants() ;
        }
        return constants ;
    }

    /**
     * fill static country list
     */
    private Constants() {
        COUNTRY_LIST.add(new Country( "Select Country" , "empty")) ;
        COUNTRY_LIST.add(new Country( "United Arab Emirates" , "ae")) ;
        COUNTRY_LIST.add(new Country( "Argentina" , "ar")) ;
        COUNTRY_LIST.add(new Country( "Austria" , "at")) ;
        COUNTRY_LIST.add(new Country( "Belgium" , "be")) ;
        COUNTRY_LIST.add(new Country( "Bulgaria" , "bg")) ;
        COUNTRY_LIST.add(new Country( "Brazil" , "br")) ;
        COUNTRY_LIST.add(new Country("Canada" , "ca")) ;
        COUNTRY_LIST.add(new Country( "Switzerland" , "ch")) ;
        COUNTRY_LIST.add(new Country( "China" , "cn")) ;
        COUNTRY_LIST.add(new Country( "Colombia" , "co")) ;
        COUNTRY_LIST.add(new Country( "Cuba" , "cu")) ;
        COUNTRY_LIST.add(new Country( "Czech Republic" , "cz")) ;
        COUNTRY_LIST.add(new Country( "Germany" , "de")) ;
        COUNTRY_LIST.add(new Country( "Egypt" , "eg")) ;
        COUNTRY_LIST.add(new Country( "France" , "fr")) ;
        COUNTRY_LIST.add(new Country( "United Kingdom" , "gb")) ;
        COUNTRY_LIST.add(new Country( "Greece" , "gr")) ;
        COUNTRY_LIST.add(new Country( "Hong Kong" , "hk")) ;
        COUNTRY_LIST.add(new Country( "Hungary" , "hu")) ;
        COUNTRY_LIST.add(new Country( "Indonesia" , "id")) ;
        COUNTRY_LIST.add(new Country( "Ireland" , "ie")) ;
        COUNTRY_LIST.add(new Country( "India" , "in")) ;
        COUNTRY_LIST.add(new Country( "Italy" , "it")) ;
        COUNTRY_LIST.add(new Country("Japan" , "jp")) ;
        COUNTRY_LIST.add(new Country("Korea (Republic of)" , "kr")) ;
        COUNTRY_LIST.add(new Country( "Lithuania" , "lt")) ;
        COUNTRY_LIST.add(new Country( "Latvia" , "lv")) ;
        COUNTRY_LIST.add(new Country( "Morocco" , "ma")) ;
        COUNTRY_LIST.add(new Country( "Mexico" , "mx")) ;
        COUNTRY_LIST.add(new Country( "Malaysia" , "my")) ;
        COUNTRY_LIST.add(new Country( "Nigeria" , "ng")) ;
        COUNTRY_LIST.add(new Country( "Netherlands" , "nl")) ;
        COUNTRY_LIST.add(new Country( "Norway" , "no")) ;
        COUNTRY_LIST.add(new Country( "New Zealand" , "nz")) ;
        COUNTRY_LIST.add(new Country( "Philippines" , "ph")) ;
        COUNTRY_LIST.add(new Country( "Poland" , "pl")) ;
        COUNTRY_LIST.add(new Country("Portugal" , "pt")) ;
        COUNTRY_LIST.add(new Country( "Romania" , "ro")) ;
        COUNTRY_LIST.add(new Country( "Serbia" , "rs")) ;
        COUNTRY_LIST.add(new Country( "Russian Federation" , "ru")) ;
        COUNTRY_LIST.add(new Country( "Saudi Arabia" , "sa")) ;
        COUNTRY_LIST.add(new Country( "Sweden" , "se")) ;
        COUNTRY_LIST.add(new Country( "Singapore" , "sg")) ;
        COUNTRY_LIST.add(new Country( "Slovenia" , "si")) ;
        COUNTRY_LIST.add(new Country( "Slovakia" , "sk")) ;
        COUNTRY_LIST.add(new Country( "Thailand" , "th")) ;
        COUNTRY_LIST.add(new Country( "Turkey" , "tr")) ;
        COUNTRY_LIST.add(new Country("Taiwan" , "rw")) ;
        COUNTRY_LIST.add(new Country( "Ukraine" , "ua")) ;
        COUNTRY_LIST.add(new Country( "United States of America" , "us")) ;
        COUNTRY_LIST.add(new Country( "Venezuela (Bolivarian Republic of)" , "ve")) ;
        COUNTRY_LIST.add(new Country( "South Africa" , "za")) ;
    }
}

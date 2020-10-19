package Chapter2.controller;

import Chapter2.model.Currency;
import Chapter2.model.CurrencyEntity;

import java.util.ArrayList;

public class Initialize {
    public static void initialize_app(){
        Currency c = new Currency("USD");
        ArrayList<CurrencyEntity> c_list = FetchData.fetch_range(c.getShortCode(), 14);
        c.setHistorical(c_list);
        c.setCurrent(c_list.get(c_list.size() -1 ));
        //Launcher.setCurrency(c); 2.4.7
        ArrayList<Currency> currencyList = new ArrayList<>();
        currencyList.add(c);
        Launcher.setCurrency(currencyList);
    }
}

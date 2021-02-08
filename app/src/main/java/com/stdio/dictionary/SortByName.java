package com.stdio.dictionary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class SortByName implements Comparator<DataModel> {

    @Override
    public int compare(DataModel o1, DataModel o2) {
        return o1.title.compareTo(o2.title);
    }

}
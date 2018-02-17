package com.udacity.sandwichclub.utils;

import java.util.List;

/**
 * Created by Flynorc on 17-Feb-18.
 */

public class ListUtils {
    public static String implodeList(String delimiter, List<String> list)
    {
        if(list.size() == 0) {
            return "/";
        }

        String imploded = "";
        for (int i=0; i<list.size(); i++) {
            //only add the delimiiter if it is not the first element
            if(i > 0) {
                imploded += delimiter;
            }

            imploded += list.get(i);
        }

        return imploded;
    }
}

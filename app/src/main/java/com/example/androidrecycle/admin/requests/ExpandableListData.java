package com.example.androidrecycle.admin.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableListData {
    public static Map<String, List<String>> getData() {
        Map<String, List<String>> expandableListDetail = new HashMap<>();

        List<String> pending = new ArrayList<>();
        pending.add("Item 1.1");
        pending.add("Item 1.2");
        pending.add("Item 1.3");

        List<String> completed = new ArrayList<>();
        completed.add("Item 2.1");
        completed.add("Item 2.2");
        completed.add("Item 2.3");

        expandableListDetail.put("Pending", pending);
        expandableListDetail.put("Completed", completed);

        return expandableListDetail;
    }
}

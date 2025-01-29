package org.example.onlineshopbackend.model.utils;

import java.util.ArrayList;
import java.util.List;

public class PageLinkFactory {

    public static List<Integer> getPageLink(int pageTotal, int current) {

        List<Integer> pageLinks = new ArrayList<>();
        pageLinks.add(current);

        while (pageLinks.size() < 3 && pageLinks.getFirst() > 0) {
            pageLinks.addFirst(pageLinks.getFirst() - 1);
        }

        while (pageLinks.size() < 5 && pageLinks.getLast() < pageTotal) {
            pageLinks.add(pageLinks.getLast() + 1);
        }

        while (pageLinks.size() < 5 && pageLinks.getFirst()  > 0) {
            pageLinks.add(pageLinks.getFirst() - 1);
        }

        return pageLinks;
    }
}

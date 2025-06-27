package com.example.AppPublico.models;

import java.util.List;

public class PageResponse<T> {
    private List<T> content;
    private int totalPages;
    private int number;
    private boolean last;

    public List<T> getContent() {
        return content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getNumber() {
        return number;
    }

    public boolean isLast() {
        return last;
    }
}

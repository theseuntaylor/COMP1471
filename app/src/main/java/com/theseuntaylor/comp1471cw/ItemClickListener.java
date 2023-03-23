package com.theseuntaylor.comp1471cw;

public interface ItemClickListener {
    void edit(int position);
    void view(int position);

    void delete(int position);
}
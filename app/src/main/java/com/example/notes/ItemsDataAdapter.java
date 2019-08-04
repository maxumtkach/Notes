package com.example.notes;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemsDataAdapter extends BaseAdapter {
NotesActivity notesActivity;
    private static final String TITLE_FILE_NAME = "title text";//---------------------------------------------
    private static final String SUBTITLE_FILE_NAME = "subtitle text";//---------------------------------------------
    private static final String DEADLINE_FILE_NAME = "deadline text";//---------------------------------------------

    // Хранит список всех элементов списка
    private List<ItemData> items;

    // LayoutInflater – класс, который из
    // layout-файла создает View-элемент.
    private LayoutInflater inflater;

    // Конструктор, в который передается контекст
    // для создания контролов из XML. И первоначальный список элементов.
    ItemsDataAdapter(Context context, List<ItemData> items) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Добавляет элемент в конец списка.
    // notifyDataSetChanged сообщает об обновлении данных и переотрисовывает.
    // Вы можете как угодно менять items в других местах.
    // Но не забывайте вызывать notifyDataSetChanged чтобы все обновилось.
    void addItem(ItemData item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    // Удаляет элемент списка.
    public void removeItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }

    // Обязательный метод абстрактного класса BaseAdapter.
    // Он возвращает колличество элементов списка.
    @Override
    public int getCount() {
        return items.size();
    }

    // Тоже обязательный метод.
    // Должен возвращать элемент списка на позиции - position
    @Override
    public ItemData getItem(int position) {
        if (position < items.size()) {
            return items.get(position);
        } else {
            return null;
        }
    }

    // И это тоже обязательный метод.
    // Возвращает идентификатор. Обычно это position.
     @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int finalPusition = position;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_view, parent, false);
        }

        ItemData itemData = items.get(position);

        TextView title = view.findViewById(R.id.title);
        TextView subtitle = view.findViewById(R.id.subtitle);
        TextView deadline =view.findViewById(R.id.deadline);

        title.setText(itemData.getTitle());
        subtitle.setText(itemData.getSubtitle());
        deadline.setText(itemData.getDeadline());

        return view;
    }

}

package sourceit.com.mylistviewwithholdercontacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sourceit.com.mylistviewwithholdercontacts.model.MyContact;

/**
 * Created by Lenovo on 11.05.2017.
 */

public class MyAdapter extends ArrayAdapter<MyContact> {

    //Для адаптера определям View-элемент, соответствующий пункту списка.
    //View-элементы, содержащиеся на пункте списка определяем во View-холдере.
    View rowView;

    public MyAdapter(Context context, List<MyContact> values) {
        super(context, R.layout.item, values);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //Объявляем ссыолочную переменную для View-холдера.
        final ViewHolder holder;
        //View-элемент, соответствующий пункту списка.
        rowView = convertView;


        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.item, null, true);
            /*
            Так было в одходе без View-холдера:
            //С помощью LayoutInflater получаем View-элемент пункта списка из R.layout.item.
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
             */
            holder = new ViewHolder();

            holder.tvName = (TextView) rowView.findViewById(R.id.name);
            holder.tvEmail = (TextView) rowView.findViewById(R.id.email);
            holder.rectangle = (View) rowView.findViewById(R.id.rectangle);

            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        //settings code

        //Берём каждый объект MyContact из массива под номером position.
        MyContact myContact = getItem(position);

        //и заполняем поля списка полями из объекта MyContact.
        holder.tvName.setText(myContact.getName());
        holder.tvEmail.setText(myContact.getEmail());
        holder.rectangle.setBackgroundColor(myContact.getColor());

        return rowView;
    }


    private static class ViewHolder {
        //View-элементы, содержащиеся на пункте списка определяем во View-холдере:
        //tvName, tvEmail, rectangle.
        TextView tvName;
        TextView tvEmail;
        View rectangle;


        public ViewHolder() {
        }
    }
}
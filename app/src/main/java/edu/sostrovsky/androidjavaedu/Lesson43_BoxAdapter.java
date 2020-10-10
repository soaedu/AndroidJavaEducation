package edu.sostrovsky.androidjavaedu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

import java.util.ArrayList;

/**
 * Created by sostrovschi on 12/13/16.
 */

public class Lesson43_BoxAdapter extends BaseAdapter {

    private Context ctx;
    private LayoutInflater lInflater;

    private ArrayList<Lesson43_Product> objects;

    Lesson43_BoxAdapter(Context context, ArrayList<Lesson43_Product> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;

        if (view == null) {
            view = lInflater.inflate(R.layout.list_item_lesson43, parent, false);
        }

        Lesson43_Product p = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.Lesson43_Description_TV)).setText(p.getName());
        ((TextView) view.findViewById(R.id.Lesson43_Price_TV)).setText(p.getPrice() + "");
        ((ImageView) view.findViewById(R.id.Lesson43_Image_IV)).setImageResource(p.getImage());

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.Lesson43_CHB);

        // присваиваем чекбоксу обработчик
        cbBuy.setOnCheckedChangeListener(myCheckChangeList);

        // пишем позицию
        cbBuy.setTag(position);

        // заполняем данными из товаров: в корзине или нет
        cbBuy.setChecked(p.isBox());

        return view;
    }

    // товар по позиции
    Lesson43_Product getProduct(int position) {
        return ((Lesson43_Product) getItem(position));
    }

    // содержимое корзины
    ArrayList<Lesson43_Product> getBox() {
        ArrayList<Lesson43_Product> box = new ArrayList<Lesson43_Product>();
        for (Lesson43_Product p : objects) {
            // если в корзине
            if (p.isBox())
                box.add(p);
        }
        return box;
    }

    // обработчик для чекбоксов
    CompoundButton.OnCheckedChangeListener myCheckChangeList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // меняем данные товара (в корзине или нет)
            getProduct((Integer) buttonView.getTag()).setBox(isChecked); // == isChecked;
        }
    };
}

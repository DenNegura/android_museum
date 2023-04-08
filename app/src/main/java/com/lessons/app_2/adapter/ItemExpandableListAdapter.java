package com.lessons.app_2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import com.lessons.app_2.R;
import com.lessons.app_2.models.Category;
import com.lessons.app_2.models.CategoryMuseums;
import com.lessons.app_2.models.Item;
import com.lessons.app_2.models.Museum;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ItemExpandableListAdapter extends BaseExpandableListAdapter {

    Context context;

    List<CategoryMuseums> categories;

    public ItemExpandableListAdapter(Context context, List<CategoryMuseums> categories) {
        this.context = context;
        this.categories = categories;
    }
    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categories.get(groupPosition).museums.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categories.get(groupPosition).category;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categories.get(groupPosition).museums.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final CategoryMuseums categoryMuseums = categories.get(groupPosition);

        LayoutInflater layoutInflater = (LayoutInflater) this.context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.list_item, null);

        ImageView image = convertView.findViewById(R.id.imageItemList);
        TextView title = convertView.findViewById(R.id.titleItemList);
        TextView description = convertView.findViewById(R.id.descriptionItemList);

        try {
            assert categoryMuseums.category != null;
            try (InputStream imageStream = context.getAssets().open(categoryMuseums.category.getImage())) {
                Drawable drawable = Drawable.createFromStream(imageStream, null);
                image.setImageDrawable(drawable);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        } catch (IOException | NullPointerException e) {
            image.setImageResource(R.drawable.missing);
        }
        title.setText(categoryMuseums.category.getTitle());
        description.setText(categoryMuseums.museums.size() + " " +
                getDescriptionToCategory(categoryMuseums.museums.size()));
        return convertView;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Museum museum = (Museum) getChild(groupPosition, childPosition);
            LayoutInflater layoutInflater = (LayoutInflater)
                    this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);

        ImageView image = convertView.findViewById(R.id.imageItemList);
        TextView title = convertView.findViewById(R.id.titleItemList);
        TextView description = convertView.findViewById(R.id.descriptionItemList);

        try {
            assert museum != null;
            try (InputStream imageStream = context.getAssets().open(museum.getImage())) {
                Drawable drawable = Drawable.createFromStream(imageStream, null);
                image.setImageDrawable(drawable);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        } catch (IOException | NullPointerException e) {
            image.setImageResource(R.drawable.missing);
        }
        title.setText(museum.getTitle());
        title.setTextSize(15);
        description.setText(museum.getSubtitle());
        convertView.setPadding(80, 0, 0, 0);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private String getDescriptionToCategory(int count) {
        String countStr = String.valueOf(count);
        count = Integer.parseInt(countStr
                .substring(countStr.length() - 1));
        if(count == 1) return "музей.";
        if(count >= 2 && count <= 4) return "музея.";
        return "музеев.";
    }
}

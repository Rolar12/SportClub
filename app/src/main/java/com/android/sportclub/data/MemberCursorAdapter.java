package com.android.sportclub.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.android.sportclub.R;

public class MemberCursorAdapter extends CursorAdapter {


    public MemberCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView firstNameTextView = view.findViewById(R.id.firstNameTextView);
        TextView lastNameTextView = view.findViewById(R.id.lastNameTextView);
        TextView sportTextView = view.findViewById(R.id.sportTextView_two);

        String firstNameBody = cursor.getString(cursor.getColumnIndexOrThrow(SportClubContract.MemberEntry.COLUMN_FIRST_NAME));
        String lastNameBody = cursor.getString(cursor.getColumnIndexOrThrow(SportClubContract.MemberEntry.COLUMN_LAST_NAME));
        String sportBody = cursor.getString(cursor.getColumnIndexOrThrow(SportClubContract.MemberEntry.COLUMN_SPORT));

        firstNameTextView.setText(firstNameBody);
        lastNameTextView.setText(lastNameBody);
        sportTextView.setText(sportBody);
    }
}

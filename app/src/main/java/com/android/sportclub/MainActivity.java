package com.android.sportclub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.sportclub.data.MemberCursorAdapter;
import com.android.sportclub.data.SportClubContract.MemberEntry;


import com.android.sportclub.data.SportClubDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    ListView membersListView;
    public static final int MEMBER_LOADER = 123;
    MemberCursorAdapter memberCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        membersListView = findViewById(R.id.memberListView);

        FloatingActionButton addUserButton = findViewById(R.id.floatingActionButton);
        addUserButton.setOnClickListener(this);

        memberCursorAdapter = new MemberCursorAdapter(this, null, false);
        membersListView.setAdapter(memberCursorAdapter);

        membersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AddMemberActivity.class);
                Uri currentMemberUri = ContentUris.withAppendedId(MemberEntry.CONTENT_URI, id);
                intent.setData(currentMemberUri);
                startActivity(intent);
            }
        });

        getSupportLoaderManager().initLoader(MEMBER_LOADER, null, this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AddMemberActivity.class);
        startActivity(intent);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        String [] projection = {MemberEntry._ID, MemberEntry.COLUMN_FIRST_NAME, MemberEntry.COLUMN_LAST_NAME, MemberEntry.COLUMN_SPORT};
        CursorLoader cursorLoader = new CursorLoader(this, MemberEntry.CONTENT_URI, projection, null, null, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        memberCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        memberCursorAdapter.swapCursor(null);
    }
}

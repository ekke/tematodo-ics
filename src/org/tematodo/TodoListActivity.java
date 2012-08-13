package org.tematodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class TodoListActivity extends FragmentActivity
        implements TodoListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        if (findViewById(R.id.todo_detail_container) != null) {
            mTwoPane = true;
            ((TodoListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.todo_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(TodoDetailFragment.ARG_ITEM_ID, id);
            TodoDetailFragment fragment = new TodoDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.todo_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, TodoDetailActivity.class);
            detailIntent.putExtra(TodoDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}

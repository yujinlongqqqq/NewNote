package com.yujinlong.note.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.yujinlong.note.R;
import com.yujinlong.note.view.bottomdialog.BottomDialog;
import com.yujinlong.note.view.bottomdialog.Item;
import com.yujinlong.note.view.bottomdialog.OnItemClickListener;
import com.yujinlong.note.view.swipeback.BaseSwipeBackActivity;
public class Main2Activity extends BaseSwipeBackActivity {
    private View view = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = m_inflater.inflate(R.layout.activity_main2, null);
        m_contentView.addView(view);
        getNavigationBar().setAppWidgeTitle("页面二");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        findViewById(R.id.horizontal_single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomDialog(Main2Activity.this)
                        .title(R.string.share_title)
                        .orientation(BottomDialog.HORIZONTAL)
                        .inflateMenu(R.menu.menu_share)
                        .itemClick(new OnItemClickListener() {
                            @Override
                            public void click(Item item) {
                                Toast.makeText(Main2Activity.this, getString(R.string.share_title) + item.getTitle(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.horizontal_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomDialog(Main2Activity.this)
                        .title(R.string.share_title)
                        .orientation(BottomDialog.HORIZONTAL)
                        .inflateMenu(R.menu.menu_share)
                        .itemClick(new OnItemClickListener() {
                            @Override
                            public void click(Item item) {
                                Toast.makeText(Main2Activity.this, getString(R.string.share_title) + item.getTitle(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .inflateMenu(R.menu.menu_main)
                        .itemClick(new OnItemClickListener() {
                            @Override
                            public void click(Item item) {
                                Toast.makeText(Main2Activity.this, getString(R.string.share_title) + item.getTitle(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.vertical).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomDialog(Main2Activity.this)
                        .title(R.string.title_item)
                        .orientation(BottomDialog.VERTICAL)
                        .inflateMenu(R.menu.menu_share)
                        .itemClick(new OnItemClickListener() {
                            @Override
                            public void click(Item item) {
                                Toast.makeText(Main2Activity.this, getString(R.string.share_title) + item.getTitle(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.grid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomDialog(Main2Activity.this)
                        .title(R.string.title_item)
                        .layout(BottomDialog.GRID)
                        .orientation(BottomDialog.VERTICAL)
                        .inflateMenu(R.menu.menu_share)
                        .itemClick(new OnItemClickListener() {
                            @Override
                            public void click(Item item) {
                                Toast.makeText(Main2Activity.this, getString(R.string.share_title) + item.getTitle(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });
    }
}

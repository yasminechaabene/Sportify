package com.chaabene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chaabene.adapter.LevelAdapter;
import com.chaabene.domain.Level;

import java.util.ArrayList;
import java.util.List;


public class LevelActivity extends Activity {
    ListView levelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        List<Level> levels = new ArrayList<Level>(){{
            add(new Level(){{
                title = "Novice";
                description = getString(R.string.novice);
            }});
            add(new Level(){{
                title = "Average";
                description = getString(R.string.average);
            }});
            add(new Level(){{
                title = "Above Average";
                description = getString(R.string.above_average);
            }});
        }};

        levelList = (ListView)findViewById(R.id.level_list);
        levelList.setAdapter(new LevelAdapter(this, R.layout.level_row, levels));

        levelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle extras = new Bundle();
                extras.putInt("level", (position + 1));
                intent.putExtras(extras);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

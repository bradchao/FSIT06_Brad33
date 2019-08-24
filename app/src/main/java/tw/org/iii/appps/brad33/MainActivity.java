package tw.org.iii.appps.brad33;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expList;
    private LinkedList<HashMap<String,String>> groups;
    private LinkedList<HashMap<String,String>> items1;
    private LinkedList<HashMap<String,String>> items2;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expList = findViewById(R.id.expList);
        init();
    }

    private void init(){
        groups = new LinkedList<>();
        items1 = new LinkedList<>();
        items2 = new LinkedList<>();

        HashMap<String,String> gitem1 = new HashMap<>();
        gitem1.put("group", "訂單");groups.add(gitem1);
        HashMap<String,String> gitem2 = new HashMap<>();
        gitem2.put("group", "結案");groups.add(gitem2);

        for (int i=0; i<40; i++){
            HashMap<String,String> row = new HashMap<>();
            row.put("title", "Item1:" + i);
            items1.add(row);
        }

        for (int i=100; i<150; i++){
            HashMap<String,String> row = new HashMap<>();
            row.put("title", "Item2:" + i);
            items2.add(row);
        }


        adapter = new MyAdapter();
        expList.setAdapter(adapter);
        expList.expandGroup(0);
        expList.expandGroup(1);

    }

    private class MyAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return groups.size();
        }

        @Override
        public int getChildrenCount(int i) {
            if (i==0){
                return  items1.size();
            }else if (i==1){
                return  items2.size();
            }
            return 0;
        }

        @Override
        public Object getGroup(int i) {
            return groups.get(i);
        }

        @Override
        public Object getChild(int i, int j) {
            if (i==0){
                return items1.get(j);
            }else if (i==1){
                return items2.get(j);
            }
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i*1000 + i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            View groupView = inflater.inflate(R.layout.item_parent,viewGroup,false);

            TextView title = (TextView) groupView.findViewById(R.id.group_title);
            title.setText(groups.get(i).get("group"));

            return groupView;
        }

        @Override
        public View getChildView(int i, int j, boolean b, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            View childView = inflater.inflate(R.layout.item_child,viewGroup,false);

            TextView title = (TextView) childView.findViewById(R.id.child_title);
            if (i==0){
                title.setText(items1.get(j).get("title"));
            }else{
                title.setText(items2.get(j).get("title"));
            }
            return childView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }

}

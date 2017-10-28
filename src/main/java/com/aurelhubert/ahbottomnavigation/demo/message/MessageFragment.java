package com.aurelhubert.ahbottomnavigation.demo.message;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.demo.R;
import com.aurelhubert.ahbottomnavigation.demo.lounge.LoungeRecyclerAdapter;
import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import java.util.ArrayList;

/**
 * 세 번째 탭인 메시지 탭에 대한 프래그먼트
 */
public class MessageFragment extends Fragment {

    /**
     * Create a new instance of the fragment
     */
    public static MessageFragment newInstance() {

        // 여기서 index 가 0,1,2,3,4 까지에 따라서 Fragment 설정해주면 되겠다.
        MessageFragment fragment = new MessageFragment(); // Fragment 를 새로 생성해서 반환해주는구나
        Bundle b = new Bundle();
        b.putInt("index", 2); // 2 번째 index of 탭에 들어갈 프래그먼트
        fragment.setArguments(b); // Fragment 에 index 설정하고 반환해준다
        return fragment;

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 나머지 1,2,3,4 페이지
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        initView(view);
        return view;

    }


    public void initView(View view) {
        // Construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        arrayOfUsers.add(new User("KCJ","111111111"));
        arrayOfUsers.add(new User("HWJ","222222222"));

// Create the adapter to convert the array to views
        UsersAdapter adapter = new UsersAdapter(this.getContext(), arrayOfUsers);
// Attach the adapter to a ListView
        SlideAndDragListView slideAndDragListView = (SlideAndDragListView) view.findViewById(R.id.slide_and_drag_list_view);
        slideAndDragListView.setMenu(createMenu());
        slideAndDragListView.setAdapter(adapter);
    }


    public Menu createMenu() {

        Menu menu = new Menu(true, 0);//the first parameter is whether can slide over
        menu.addItem(new MenuItem.Builder().setWidth(90)//set Width
                .setBackground(new ColorDrawable(Color.RED))// set background
                .setText("One")//set text string
                .setTextColor(Color.GRAY)//set text color
                .setTextSize(20)//set text size
                .setIcon(getResources().getDrawable(R.drawable.ic_apps_black_24dp))// set icon
                .build());
        menu.addItem(new MenuItem.Builder().setWidth(120)
                .setBackground(new ColorDrawable(Color.BLACK))
                .setDirection(MenuItem.DIRECTION_RIGHT)//set direction (default DIRECTION_LEFT)
                .setIcon(getResources().getDrawable(R.drawable.ic_maps_local_bar))// set icon
                .build());
        return menu;

    }



    //========== Adapter ============== //
    private class UsersAdapter extends ArrayAdapter<User> {


        public UsersAdapter(Context context, ArrayList<User> users) {
            super(context, 0, users);


        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            User user = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_message, parent, false);
            }
            // Lookup view for data population
            TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
            TextView roleTextVeiw = (TextView) convertView.findViewById(R.id.role);
            // Populate the data into the template view using the data object
            nameTextView.setText(user.name);
            roleTextVeiw.setText(user.role);
            // Return the completed view to render on screen
            return convertView;
        }
    }


    private class User {
        public String name;
        public String role;

        public User(String name, String role) {
            this.name = name;
            this.role = role;
        }


    }
}

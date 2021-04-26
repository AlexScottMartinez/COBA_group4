package com.example.coba_group4.forum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.coba_group4.R;
import com.example.coba_group4.occurence.Occurrence;

import java.util.ArrayList;

public class ForumListAdapter extends BaseAdapter
    {
        private Context context;
        private ArrayList<Forum> ForumList;

        public ForumListAdapter(Context context, ArrayList<Forum> forumList)
      {
        this.context = context;
        this.ForumList = forumList;
    }

    @Override
    public int getCount()
    {
        return this.ForumList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return ForumList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_forums, null);
        TextView Title = (TextView)convertView.findViewById(R.id.title1);
        TextView Description = (TextView)convertView.findViewById(R.id.desc);


        Forum forum = ForumList.get(position);

       Title.setText(forum.getTitle());
       Description.setText(forum.getDescription());

        return convertView;
    }
}

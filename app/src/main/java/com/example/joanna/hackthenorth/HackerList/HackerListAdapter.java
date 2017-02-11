package com.example.joanna.hackthenorth.HackerList;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joanna.hackthenorth.Hacker;
import com.example.joanna.hackthenorth.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Joanna on 2017-02-09.
 */

public class HackerListAdapter extends RecyclerView.Adapter<HackerListAdapter.ItemRowHolder> {

    private HackerListActivity mActivity;
    private ArrayList<Hacker> mHackers;

    public HackerListAdapter(HackerListActivity activity, ArrayList<Hacker> hackers) {
        super();
        mActivity = activity;
        mHackers = hackers;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mActivity.getLayoutInflater().inflate(R.layout.hacker_list_item,parent,false);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemRowHolder holder, final int position) {
        Hacker hacker = mHackers.get(position);
        holder.rowName.setText(hacker.getmName());
        if (holder.rowSkills.getText() == "") {
            for (int i = 0; i < hacker.getmSkills().size(); i++) {
                Hacker.Skill skill = hacker.getmSkills().get(i);
                holder.rowSkills.append(skill.getmName() + " " + skill.getmRating().intValue());
                                    holder.skillNames.add(skill.getmName());
                    holder.skillRatings.add(skill.getmRating().intValue());
                if (i != hacker.getmSkills().size()-1) {
                    holder.rowSkills.append(" • ");
                }
            }
        }
        holder.itemView.setTag(hacker);
        holder.rowImage.setImageBitmap(null);
        Picasso.with(holder.rowImage.getContext()).cancelRequest(holder.rowImage);
        Picasso.with(holder.rowImage.getContext()).load(Uri.parse(hacker.getmPicture())).into(holder.rowImage);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mHackers.size();
    }

    class ItemRowHolder extends RecyclerView.ViewHolder {
        TextView rowName;
        ImageView rowImage;
        TextView rowSkills;
        ArrayList<String> skillNames;
        ArrayList<Integer> skillRatings;

        public ItemRowHolder(View itemView) {
            super(itemView);
            rowName = (TextView) itemView.findViewById(R.id.hacker_name);
            rowImage = (ImageView) itemView.findViewById(R.id.hacker_image);
            rowSkills = (TextView) itemView.findViewById(R.id.hacker_skills);
            skillNames = new ArrayList<>();
            skillRatings = new ArrayList<>();
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();

                    Hacker hacker = mHackers.get(pos);
                    Intent intent = new Intent(v.getContext(), HackerDetailActivity.class);

                    intent.putExtra("name", hacker.getmName());
                    intent.putExtra("picture", hacker.getmPicture());
                    intent.putExtra("company", hacker.getmCompany());
                    intent.putExtra("phone", hacker.getmPhone());
                    intent.putExtra("email", hacker.getmEmail());
                    intent.putStringArrayListExtra("skillNames", skillNames);
                    intent.putIntegerArrayListExtra("skillRatings", skillRatings);
                    intent.putExtra("latitude", hacker.getmLatitude());
                    intent.putExtra("longitude", hacker.getmLongitude());

                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}

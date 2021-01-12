package com.example.conversationsapp;


import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_SENDER = 1;
    private static final int TYPE_RECEIVER = 2;


    private ArrayList<Chat_message> messages=new ArrayList<>();

    public RecyclerViewAdapter(ArrayList<Chat_message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_SENDER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_item, parent, false);
            return new ViewHolderOne(view);
        } else if (viewType == TYPE_RECEIVER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_receiver_list, parent, false);
            return new ViewHolderTwo(view);
        } else {
            throw new RuntimeException("type not matched");
        }
        // View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_item,parent,false);
        //ViewHolder holder=new ViewHolder(view);
        //return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case TYPE_SENDER:
                initLayoutOne((ViewHolderOne)holder, position);
                break;
            case TYPE_RECEIVER:
                initLayoutTwo((ViewHolderTwo) holder, position);
                break;
            default:
                break;
        }

    }

    private void initLayoutOne(ViewHolderOne holder, int pos) {

        Spannable str = new SpannableString("You\n" + this.messages.get(pos).getMessage());
        str.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        str.setSpan(new ForegroundColorSpan(Color.rgb(104, 174, 243)), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.message.setText(str);
        holder.timeSent.setText(this.messages.get(pos).getTimeSent());

        holder.message.setGravity(Gravity.END);
        holder.message.setBackgroundColor(Color.rgb(4, 88, 187));
        holder.message.setTextColor(Color.WHITE);


        holder.timeSent.setGravity((Gravity.START));
        holder.timeSent.setBackgroundColor(Color.rgb(4, 88, 187));
        holder.timeSent.setTextColor(Color.WHITE);
    }

    private void initLayoutTwo(ViewHolderTwo holder, int pos) {

        Spannable str = new SpannableString(this.messages.get(pos).getSenderName()+"\n" + this.messages.get(pos).getMessage());
        str.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, this.messages.get(pos).getSenderName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        str.setSpan(new ForegroundColorSpan(Color.BLUE), 0, this.messages.get(pos).getSenderName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.message2.setText(str);
        holder.timeSent2.setText(this.messages.get(pos).getTimeSent());

        holder.message2.setGravity(Gravity.START);

        holder.message2.setBackgroundColor(Color.rgb(237, 240, 240));
        holder.message2.setTextColor(Color.BLACK);

        holder.timeSent2.setGravity((Gravity.END));
        holder.timeSent2.setBackgroundColor(Color.rgb(237, 240, 240));
        holder.timeSent2.setTextColor(Color.BLACK);

    }

    public void setMessages(ArrayList<Chat_message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {

        if ((position % 2)==0) {
            return TYPE_SENDER;
        } else if (position % 2==1) {
            return TYPE_RECEIVER;
        } else {
            return -1;
        }
    }

    public static class ViewHolderOne extends RecyclerView.ViewHolder
    {
        private TextView message;
        private TextView timeSent;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            message=itemView.findViewById(R.id.textMsg);
            timeSent=itemView.findViewById(R.id.timetext);
        }

    }

    public static class ViewHolderTwo extends RecyclerView.ViewHolder
    {

        private TextView message2;
        private TextView timeSent2;

        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            message2=itemView.findViewById(R.id.textMsg2);
            timeSent2=itemView.findViewById(R.id.timetext2);
        }
    }
}


package com.app.qunadai.content.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.content.ui.bbs.AnswerActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/5/14.
 */

public class QuestionAdapter extends RecyclerView.Adapter {

    private Context context;

    private JSONArray questions;

    public QuestionAdapter(Context context) {
        this.context = context;
    }

    public void setQuestions(JSONArray questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false);
        QuestionHolder holder = new QuestionHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof QuestionHolder){
            QuestionHolder qh = (QuestionHolder) viewHolder;
            qh.setData();
        }
    }

    class QuestionHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_question)
        LinearLayout ll_question;
        @BindView(R.id.tv_title)
        TextView tv_title;

        public QuestionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            JSONObject obj = questions.optJSONObject(getAdapterPosition());
            if (obj != null) {
                final String title =  obj.optString("title");
                final String content = obj.optString("content");
                tv_title.setText(title);
                ll_question.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, AnswerActivity.class);
                        intent.putExtra("title",title);
                        intent.putExtra("content",content);
                        context.startActivity(intent);
                    }
                });
            }
        }


    }


    @Override
    public int getItemCount() {
        return questions == null ? 0 : questions.length();
    }
}

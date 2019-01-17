package com.example.student.teamproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertHolder> {

    private Context context;
    private List<NotesModel> list;

    public AlertAdapter(Context context, List<NotesModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AlertHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.alert_item, null);
        AlertHolder holder = new AlertHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AlertHolder alertHolder, int i) {
        final NotesModel alert = list.get(i);
        alertHolder.tvTitle.setText(alert.getTitle());
        alertHolder.tvDescription.setText(alert.getDescription());
        //String strDate = alert.getYear() + "." + alert.getMonth() + "." + alert.getDay() + " " + alert.getHour() + ":" + alert.getMinute();
        alertHolder.tvTime.setText(alert.getDate());
        //Toast.makeText(context, DateUtilities.strToDate(alert.getDate(), context), Toast.LENGTH_LONG).show();
        alertHolder.setEventClickListener(new EventClickListener() {
            @Override
            public void onClick(final View view, final int pos, boolean isLongClick) {
                if(isLongClick)
                {
                    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which)
                            {
                                case DialogInterface.BUTTON_POSITIVE:
                                    SqliteDbUtils dbUtils = new SqliteDbUtils(context);
                                    dbUtils.deleteItem(alertHolder.tvTime.getText().toString(), alertHolder.tvTitle.getText().toString());
                                    list.remove(pos);
                                    notifyDataSetChanged();
                                    Calendar date = DateUtilities.dateFormat(alert.getDate());
                                    int requestCode = Alert.getRequestCode(date);
                                    Notification.cancel(context, requestCode);
                                    Snackbar.make(view, context.getResources().getString(R.string.event_deleted),
                                            Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                    dialogBuilder.setMessage(context.getResources().getString(R.string.delete_event_dialog))
                            .setPositiveButton(context.getResources().getString(R.string.btn_yes), dialogListener)
                            .setNegativeButton("Anuluj", dialogListener).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AlertHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        ImageView icon;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvTime;
        EventClickListener eventClickListener;

        public AlertHolder(@NonNull View itemView) {
            super(itemView);
            icon =  itemView.findViewById(R.id.ivIcon);
            tvTitle =  itemView.findViewById(R.id.tvTitle);
            tvDescription =  itemView.findViewById(R.id.tvDescr);
            tvTime =  itemView.findViewById(R.id.tvTime);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setEventClickListener(EventClickListener listener)
        {
            eventClickListener = listener;
        }

        @Override
        public void onClick(View v) {
            eventClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            eventClickListener.onClick(v, getAdapterPosition(), true);
            return false;
        }
    }
}

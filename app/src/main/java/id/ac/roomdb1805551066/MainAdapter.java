package id.ac.roomdb1805551066;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    //initialize variable
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    //create construktor
    public MainAdapter(Activity context,List<MainData> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //initialize main data
        MainData data = dataList.get(position);
        //initialize database
        database = RoomDB.getInstance(context);
        //set text on text view
        holder.textView.setText(data.getText());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize main data
                MainData d = dataList.get(holder.getAdapterPosition());
                //get id
                final int sID = d.getID();
                //get text
                String sText = d.getText();

                //create
                final Dialog dialog = new Dialog(context);
                //set content view
                dialog.setContentView(R.layout.dialog_update);
                //initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //initialize height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //set layout
                dialog.getWindow().setLayout(width,height);
                //show dialog
                dialog.show();

                //initialize & assign variable
                final EditText editText = dialog.findViewById(R.id.edit_text);
                Button btnUpdate = dialog.findViewById(R.id.btn_update);

                //set text on edit text
                editText.setText(sText);

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //dismiss dialog
                        dialog.dismiss();
                        //get update text from edit text
                        String uText = editText.getText().toString().trim();
                        //update text in db
                        database.maindDao().update(sID,uText);
                        //notify when data is update
                        dataList.clear();
                        dataList.addAll(database.maindDao().getAll());
                        notifyDataSetChanged();

                    }
                });

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize main data
                MainData d = dataList.get(holder.getAdapterPosition());
                //delete text from database
                database.maindDao().delete(d);
                //notify when data is deleted
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //initialize variable
        TextView textView;
        ImageView btnEdit,btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            textView = itemView.findViewById(R.id.text_view);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}

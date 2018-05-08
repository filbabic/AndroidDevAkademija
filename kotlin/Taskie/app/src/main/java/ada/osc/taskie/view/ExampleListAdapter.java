package ada.osc.taskie.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import ada.osc.taskie.R;
import ada.osc.taskie.model.Task;

// TODO: 08/05/2018 Ovaj adapter sam ostavio u Javi, cisto da vidite usporedbu Kotlin i Java adaptera

public class ExampleListAdapter extends BaseAdapter {

    private List<Task> mTasks;

    public ExampleListAdapter() {
        mTasks = new ArrayList<>();
    }

    public void updateTasks(List<Task> tasks){
        mTasks.clear();
        mTasks.addAll(tasks);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Override
    public Object getItem(int position) {
        return mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TaskViewHolder holder = null;

        if(convertView == null){

            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_task,
                    parent,
                    false
            );

            holder = new TaskViewHolder(convertView);
            convertView.setTag(holder);

        }else{
            holder = (TaskViewHolder) convertView.getTag();
        }

        Task current = mTasks.get(position);

//        holder.mTitle.setText(current.getTitle());
//        holder.mDescription.setText(current.getDescription());

        int color = R.color.taskPriority_Unknown;
        switch (current.getPriority()){
            case LOW: color = R.color.taskpriority_low; break;
            case MEDIUM: color = R.color.taskpriority_medium; break;
            case HIGH: color = R.color.taskpriority_high; break;
        }
//        holder.mPriority.setImageResource(color);

        return convertView;
    }

    static class TaskViewHolder{
//        @BindView(R.id.taskTitle) TextView mTitle;
//        @BindView(R.id.taskDescription) TextView mDescription;
//        @BindView(R.id.taskPriority) ImageView mPriority;

        public TaskViewHolder(View itemView) {
        }
    }
}

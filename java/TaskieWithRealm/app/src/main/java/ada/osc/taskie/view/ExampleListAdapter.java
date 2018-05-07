package ada.osc.taskie.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ada.osc.taskie.R;
import ada.osc.taskie.model.Task;
import butterknife.BindView;
import butterknife.ButterKnife;

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

        holder.mTitle.setText(current.getTitle());
        holder.mDescription.setText(current.getDescription());

        int color = R.color.taskPriority_Unknown;
        switch (current.getTaskPriorityEnum()){
            case LOW: color = R.color.taskpriority_low; break;
            case MEDIUM: color = R.color.taskpriority_medium; break;
            case HIGH: color = R.color.taskpriority_high; break;
        }
        holder.mPriority.setImageResource(color);

        return convertView;
    }

    static class TaskViewHolder{
        @BindView(R.id.textview_task_title) TextView mTitle;
        @BindView(R.id.textview_task_description) TextView mDescription;
        @BindView(R.id.imageview_task_priority) ImageView mPriority;

        public TaskViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}

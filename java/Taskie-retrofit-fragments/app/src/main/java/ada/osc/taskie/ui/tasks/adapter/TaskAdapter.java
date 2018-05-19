package ada.osc.taskie.ui.tasks.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ada.osc.taskie.R;
import ada.osc.taskie.model.Task;
import ada.osc.taskie.listener.TaskClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> mTasks;
    private TaskClickListener mListener;

    public TaskAdapter(TaskClickListener listener) {
        mListener = listener;
        mTasks = new ArrayList<>();
    }

    public void updateTasks(List<Task> tasks) {
        mTasks.clear();
        mTasks.addAll(tasks);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        Task current = mTasks.get(position);
        holder.mTitle.setText(current.getTitle());
        holder.mDescription.setText(current.getDescription());

        int color = R.color.taskPriority_Unknown;
//        switch (current.getPriority()) {
//            case LOW:
//                color = R.color.taskpriority_low;
//                break;
//            case MEDIUM:
//                color = R.color.taskpriority_medium;
//                break;
//            case HIGH:
//                color = R.color.taskpriority_high;
//                break;
//        }

        holder.mPriority.setImageResource(color);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public void removeTask(String taskId) {
        int index = -1;

        for (Task task : mTasks) {
            if (task.getmId().equals(taskId)) {
                index = mTasks.indexOf(task);
            }
        }

        if (index != -1) {
            mTasks.remove(index);
            notifyItemRemoved(index);
        }
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_task_title)
        TextView mTitle;
        @BindView(R.id.textview_task_description)
        TextView mDescription;
        @BindView(R.id.imageview_task_priority)
        ImageView mPriority;

        public TaskViewHolder(View itemView, TaskClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        public void onTaskClick() {
            mListener.onClick(mTasks.get(getAdapterPosition()));
        }

        @OnLongClick
        public boolean onTaskLongClick() {
            mListener.onLongClick(mTasks.get(getAdapterPosition()));
            return true;
        }
    }
}

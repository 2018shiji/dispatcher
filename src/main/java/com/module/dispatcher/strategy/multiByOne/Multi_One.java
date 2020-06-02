package com.module.dispatcher.strategy.multiByOne;

import com.module.dispatcher.Task;
import com.module.dispatcher.strategy.ITaskDispatcher;
import com.module.dispatcher.handler.ITaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 独木桥
 */
@Component("Multi_One")
public class Multi_One implements ITaskDispatcher {
    @Autowired
    @Qualifier("oralHandler")
    private ITaskHandler oralHandler;
    private BlockingQueue<Task> tasksQueue;
    private BlockingQueue<Task> taskDispatchQueue;

    public Multi_One(){
        tasksQueue = new LinkedBlockingQueue<>();
        taskDispatchQueue = new SynchronousQueue<>();
    }

    @Override
    public void dispatchTask(Task task) {
        System.out.println("more by one dispatcher");
        task.setTaskName("more by one task");
        tasksQueue.add(task);
    }

    @Override
    public ITaskHandler getTaskHandler() {
        return oralHandler;
    }

    @Override
    public void handleTask() {
        try {
            Task task = tasksQueue.take();
            taskDispatchQueue.put(task);
            oralHandler.handleTask(this, task);
            taskDispatchQueue.take();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void processed(boolean processed) {
        if(tasksQueue.isEmpty()) {
            System.out.println("任务队列已空 + more to one dispatcher");
            /**
             * do other thing
             */
        } else {
            System.out.println("开始处理下一个任务 + more to one dispatcher");
        }
    }
}

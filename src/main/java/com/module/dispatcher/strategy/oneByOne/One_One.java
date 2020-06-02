package com.module.dispatcher.strategy.oneByOne;

import com.module.dispatcher.Task;
import com.module.dispatcher.strategy.ITaskDispatcher;
import com.module.dispatcher.handler.ITaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 基于一请求一响应的单线程的阻塞模式
 * 线程安全，支持一边添加任务，一边提取任务
 */
@Component("One_One")
public class One_One implements ITaskDispatcher {
    @Autowired
    @Qualifier("oralHandler")
    private ITaskHandler oralHandler;
    private BlockingQueue<Task> taskQueue;

    public One_One(){
        taskQueue = new SynchronousQueue<>();
    }

    @Override
    public void dispatchTask(Task task) {
        System.out.println("one to one dispatcher");
        task.setTaskName("one by one task");
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ITaskHandler getTaskHandler() {
        return oralHandler;
    }

    @Override
    public void handleTask() {
        try {
            oralHandler.handleTask(this, taskQueue.take());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void processed(boolean processed) {
        System.out.println("开始处理下一个任务 + one to one dispatcher");

    }
}

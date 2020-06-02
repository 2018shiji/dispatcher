package com.module.dispatcher.handler;

import com.module.dispatcher.Task;
import com.module.dispatcher.strategy.TaskCallBack;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 基于多线程的Bio Handler
 */
@Data
@Component("asynHandler")
public class AsynHandler implements ITaskHandler {
    private ThreadPoolExecutor threadPool;

    public AsynHandler(){
        threadPool = new ThreadPoolExecutor(10, 20, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    @Override
    public void handleTask(TaskCallBack callBack, Task task) {
        System.out.println("asynHandler for task");
        threadPool.execute(new TaskThread(callBack, task));
    }

    private class TaskThread implements Runnable {
        private Task task;
        private TaskCallBack callBack;

        public TaskThread(TaskCallBack callBack, Task task) {
            this.task = task;
        }

        @Override
        public void run() {
            task.taskLogic();
            callBack.processed(true);
        }
    }
}

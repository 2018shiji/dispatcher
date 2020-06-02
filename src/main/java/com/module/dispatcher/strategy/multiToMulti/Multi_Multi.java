package com.module.dispatcher.strategy.multiToMulti;

import com.module.dispatcher.Task;
import com.module.dispatcher.strategy.ITaskDispatcher;
import com.module.dispatcher.handler.ITaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 基于多请求多响应的多线程模式
 * 生产者-消费者模式
 */
@Component("Multi_Multi")
public class Multi_Multi implements ITaskDispatcher {
    @Autowired
    @Qualifier("asynHandler")
    private ITaskHandler asynHandler;
    private BlockingQueue<Task> blockingQueue;

    public Multi_Multi(){
        blockingQueue = new LinkedBlockingQueue();
    }

    @Override
    public void dispatchTask(Task task) {

        System.out.println("more to more dispatcher");
        task.setTaskName("more to more Task");
        blockingQueue.add(task);
    }

    @Override
    public ITaskHandler getTaskHandler() {
        return asynHandler;
    }

    @Override
    public void handleTask() {
        try{
            asynHandler.handleTask(this, blockingQueue.take());

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void processed(boolean processed) {
        if(blockingQueue.isEmpty()) {
            System.out.println("任务队列已空 + more to more dispatcher");
            /**
             * do other thing
             */
        } else {
            System.out.println("开始处理下一个任务 + more to more dispatcher");
        }
    }
}

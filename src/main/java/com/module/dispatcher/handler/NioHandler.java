package com.module.dispatcher.handler;

import com.module.dispatcher.Task;
import com.module.dispatcher.strategy.TaskCallBack;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

/**
 * 基于Netty的Nio Handler
 */
@Component("nioHandler")
public class NioHandler extends ChannelInboundHandlerAdapter implements ITaskHandler {

    @Override
    public void handleTask(TaskCallBack callBack, Task task) {
        System.out.println("nioHandler for task");
        task.taskLogic();
    }


}

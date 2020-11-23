package cn.edu.xmu.oomall.listener;

import cn.edu.xmu.oomall.bo.ExecutableTask;
import cn.edu.xmu.oomall.dto.TaskMessageBody;
import cn.edu.xmu.oomall.util.TaskFactory;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xincong yao
 * @date 2020-11-21
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "timer", selectorType = SelectorType.TAG, selectorExpression = "activity", consumeMode = ConsumeMode.CONCURRENTLY, consumeThreadMax = 10, consumerGroup = "timer")
public class TimerTaskListener implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

	@Autowired
	private TaskFactory taskFactory;

	@Override
	public void onMessage(String message) {
		log.info("onMessage: consume message = " + message);
		try {
			ExecutableTask task = taskFactory.getExecutableTask(JSON.parseObject(message, TaskMessageBody.class));
			task.execute();
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			log.error("任务构造失败");
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error("任务执行错误");
		}

	}

	@Override
	public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
		log.info("prepareStart: consumerGroup = " + defaultMQPushConsumer.getConsumerGroup());
	}
}

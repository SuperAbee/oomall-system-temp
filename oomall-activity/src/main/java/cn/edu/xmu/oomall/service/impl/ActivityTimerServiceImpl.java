package cn.edu.xmu.oomall.service.impl;

import cn.edu.xmu.oomall.bo.LocalClass;
import cn.edu.xmu.oomall.dto.LoadActivityTimerRequest;
import cn.edu.xmu.oomall.dto.TaskMessageBody;
import cn.edu.xmu.oomall.service.ActivityService;
import cn.edu.xmu.oomall.service.IActivityTimerService;
import cn.edu.xmu.oomall.service.ITimerService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xincong yao
 * @date 2020-11-22
 */
@Slf4j
@DubboService(version = "0.0.1-SNAPSHOT")
public class ActivityTimerServiceImpl implements IActivityTimerService {

	@Autowired
	private ActivityService activityService;

	@DubboReference(version = "${oomall.producer.timer-service.version}", cache = "false", async = false, timeout = 5000)
	private ITimerService timerService;

	@Override
	public Boolean loadActivityTimerTask(LoadActivityTimerRequest request) throws NoSuchMethodException {
		log.info("in loadActivityTimerTask");
		TaskMessageBody message = new TaskMessageBody();
		message.setStartTime(request.getStartTime());
		message.setPrepareTime(request.getPrepareTime());
		Method m = activityService.getClass().getMethod("loadActivity", LocalClass.class);
		message.setServiceClassName(m.getDeclaringClass().getName());
		message.setServiceMethodName(m.getName());
		message.setReturnTypeClassName(m.getReturnType().getName());
		message.setTag(request.getTag());
		message.setTopic(request.getTopic());

		List<TaskMessageBody.Param> params = new ArrayList<>();
		TaskMessageBody.Param p1 = new TaskMessageBody.Param();
		p1.setSequence(0);
		p1.setClassName(LocalClass.class.getName());
		p1.setObjectJson(JSON.toJSONString(new LocalClass(request.getActivityId(), "test string")));
		params.add(p1);
		message.setParams(params);

		return timerService.newTimerTask(message);
	}
}

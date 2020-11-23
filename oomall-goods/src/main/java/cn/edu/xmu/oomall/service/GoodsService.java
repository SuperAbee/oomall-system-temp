package cn.edu.xmu.oomall.service;

import cn.edu.xmu.oomall.dto.LoadActivityTimerRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GoodsService {

	@DubboReference(version = "0.0.1-SNAPSHOT", cache = "false", async = false, timeout = 5000)
	private IActivityTimerService activityTimerService;

	public void loadActivity() throws NoSuchMethodException {
		LoadActivityTimerRequest request = new LoadActivityTimerRequest();
		request.setActivityId(1231L);
		request.setTopic("timer");
		request.setTag("activity");
		request.setPrepareTime(LocalDateTime.now().plusSeconds(100));
		request.setStartTime(LocalDateTime.now().plusSeconds(120));
		activityTimerService.loadActivityTimerTask(request);
	}
}

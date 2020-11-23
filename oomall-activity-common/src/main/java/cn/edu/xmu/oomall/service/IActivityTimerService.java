package cn.edu.xmu.oomall.service;

import cn.edu.xmu.oomall.dto.LoadActivityTimerRequest;

public interface IActivityTimerService {

	Boolean loadActivityTimerTask(LoadActivityTimerRequest request) throws NoSuchMethodException;
}

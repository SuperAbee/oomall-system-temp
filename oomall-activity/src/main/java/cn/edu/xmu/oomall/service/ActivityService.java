package cn.edu.xmu.oomall.service;

import cn.edu.xmu.oomall.bo.LocalClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xincong yao
 * @date 2020-11-22
 */
@Service
@Slf4j
public class ActivityService {

	public Boolean loadActivity(LocalClass lc) {
		log.info("load activity successfully, id: " + lc.getId());
		return true;
	}
}

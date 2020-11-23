package cn.edu.xmu.oomall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xincong yao
 * @date 2020-11-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadActivityTimerRequest implements Serializable {

	private Long activityId;
	private LocalDateTime prepareTime;
	private LocalDateTime startTime;
	private String topic;
	private String tag;

}

package cn.edu.xmu.oomall.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalClass implements Serializable {

	private Long id;
	private String string;
}

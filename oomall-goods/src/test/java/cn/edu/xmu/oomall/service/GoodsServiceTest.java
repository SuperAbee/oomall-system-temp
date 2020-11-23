package cn.edu.xmu.oomall.service;

import cn.edu.xmu.oomall.OomallGoodsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = OomallGoodsApplication.class)
@RunWith(SpringRunner.class)
public class GoodsServiceTest {

	@Autowired
	private GoodsService goodsService;

	@Test
	public void loadActivityTest() throws NoSuchMethodException, InterruptedException {
		goodsService.loadActivity();
	}
}

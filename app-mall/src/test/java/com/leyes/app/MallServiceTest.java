/*package com.leyes.app;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.leyes.app.AppStarter;
import com.leyes.app.dto.comsystem.ServiceChargeDto;
import com.leyes.app.dto.shop.GoodsInfoDto;
import com.leyes.app.service.MallService;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStarter.class)
@WebAppConfiguration
public class MallServiceTest {
	@Autowired
	private MallService mallService;
	@Test
	public void testUserService() throws Exception {
		List<GoodsInfoDto> goods = new ArrayList<GoodsInfoDto>();
		GoodsInfoDto g1 = new GoodsInfoDto();
		g1.setGoodsId("1");
		g1.setNumber(1);
		GoodsInfoDto g2 = new GoodsInfoDto();
		g2.setGoodsId("10");
		g2.setNumber(1);
		goods.add(g1);
		goods.add(g2);
		ServiceChargeDto s = new ServiceChargeDto();
		s.setCondition(20);
		s.setExpense(5);
		mallService.placeGoodsOrder("8ab385c54edf2f95014ee1ab98520017", "44F807C8F5F94224939D779F163C5A7B", "060C603CD177436CB061E1E2236D1024", goods, s);
		assert(true);
	}
}
*/
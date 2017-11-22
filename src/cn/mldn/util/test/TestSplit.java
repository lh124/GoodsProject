package cn.mldn.util.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.mldn.service.back.IGoodsServiceBack;
import cn.mldn.util.factory.Factory;
import cn.mldn.vo.Goods;
import cn.mldn.vo.Item;
import cn.mldn.vo.Tag;

public class TestSplit {
	public static void main(String[] args) throws Exception {
//		long allRecorders = 26 ;
//		int lineSize = 5 ;
//		// (26 + 5 - 1) / 5 = 6
//		// (25 + 5 - 1) / 5 = 5
//		long allPages = (allRecorders + lineSize - 1) / lineSize ;
//		System.out.println(allPages);
		
		
		IGoodsServiceBack service = Factory.getServiceInstance("goods.service.back");
		Map<String, Object> map = service.preEdit(2L);
		List<Item> item = (List<Item>) map.get("item");
		List<Tag> tag = (List<Tag>) map.get("tag");
		Iterator<Item> iters= item.iterator();
		Iterator<Tag> itag = tag.iterator();
		Goods goods=(Goods)map.get("goods");
		System.out.println(map);
	}
}

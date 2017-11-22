package cn.mldn.service.back.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.mldn.dao.IGoodsDAO;
import cn.mldn.dao.IGoodsTagDAO;
import cn.mldn.dao.IItemDAO;
import cn.mldn.dao.ITagDAO;
import cn.mldn.service.back.IGoodsServiceBack;
import cn.mldn.util.factory.Factory;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.vo.Goods;
import cn.mldn.vo.Tag;

public class GoodsServiceBackImpl extends AbstractService implements IGoodsServiceBack{

	@Override
	public Map<String, Object> preAdd() throws Exception {
		Map<String,Object> map=new HashMap<>();
		IItemDAO itemdao=Factory.getDAOInstance("item.dao");
		ITagDAO tagdao=Factory.getDAOInstance("tag.dao");
		map.put("item", itemdao.findAll());
		map.put("tag", tagdao.findAll());
		return map;
	}

	@Override
	public boolean add(Goods vo, Set<Long> tids) throws Exception {
		if(tids.size()==0||tids==null) {
			return false;
		}
		vo.setDflag(0);
		IGoodsDAO goodsdao=Factory.getDAOInstance("goods.dao");
		if(goodsdao.doCreate(vo)) {
			Long currentdate=goodsdao.findCreateLastId();
			return goodsdao.doCreateGoodsTag(currentdate, tids);
		}
		return false;
	}
	

	@Override
	public Map<String, Object> list(String column, String keyWord, Long currentPage, Integer lineSize)
			throws Exception {
		IGoodsDAO goodsdao=Factory.getDAOInstance("goods.dao");
		IItemDAO itemdao=Factory.getDAOInstance("item.dao");

		Map<String,Object> map= new HashMap<>();
		map.put("allitem", itemdao.findAllMap());
		if(super.isLikeSearch(keyWord)&&super.isLikeSearch(column)) {
			map.put("allsplits", goodsdao.findSplit(column, keyWord, currentPage, lineSize));
			map.put("counts", goodsdao.getSplitCount(column, keyWord));
		}else {
			map.put("allsplits", goodsdao.findAll(currentPage, lineSize));
			map.put("counts", goodsdao.getAllCount());
		}

		return map;
	}

	@Override
	public boolean delete(Set<Long> ids, Integer dflag) throws Exception {
		if(ids==null||ids.size()==0) {
			return false;
		}  
		IGoodsDAO goodsdao=Factory.getDAOInstance("goods.dao");

		return goodsdao.doEditDflag(ids, 1);
	}



	@Override
	public Map<String, Object> preEdit(Long id) throws Exception {
		Map<String,Object> map=new HashMap<>();
		IGoodsDAO goodsdao=Factory.getDAOInstance("goods.dao");
		Goods goods=goodsdao.findById(id);
		map.put("goods", goods);
		if(goods!=null) {
			IItemDAO itemdao=Factory.getDAOInstance("item.dao");
			ITagDAO tagdao=Factory.getDAOInstance("tag.dao");
			map.put("item", itemdao.findAll());
			map.put("tag", tagdao.findAll());
			map.put("tid", goodsdao.findGoodsTas(id));
			System.out.println(map.get("tid"));
		}
		return map;
	}

	@Override
	public boolean edit(Goods vo, Set<Long> tids) throws Exception {
		if(tids.size()==0||tids==null) {
			return false;
		}
		IGoodsDAO goodsdao=Factory.getDAOInstance("goods.dao");
		if(goodsdao.doEdit(vo)) {
			if(goodsdao.doRemoveGoodsTag(vo.getGid())) {
				return goodsdao.doCreateGoodsTag(vo.getGid(), tids);
			}
		}
		return false;
	}

}

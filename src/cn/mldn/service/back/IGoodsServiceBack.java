package cn.mldn.service.back;

import java.util.Map;
import java.util.Set;

import cn.mldn.vo.Goods;
import cn.mldn.vo.Tag;

public interface IGoodsServiceBack {

	
	public Map<String,Object> preAdd ()throws Exception;
	/**
	 * 数据增加
	 * @param vo
	 * @param tids 
	 * @return
	 * @throws Exception
	 */
	public boolean add(Goods vo,Set<Long> tids) throws Exception;
	
	public Map<String,Object> list(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception;
	
	public boolean delete(Set<Long> ids,Integer dflag)throws  Exception;
	
	public boolean edit(Goods vo,Set<Long> tids) throws Exception ;
	
	public Map<String,Object> preEdit(Long id)throws Exception;
	
}
  
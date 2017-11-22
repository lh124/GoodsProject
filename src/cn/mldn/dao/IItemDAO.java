package cn.mldn.dao;

import java.sql.SQLException;
import java.util.Map;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vo.Item;

public interface IItemDAO extends IBaseDAO<Long, Item> {

	
	 public  Map<Long,String> findAllMap() throws SQLException;
}

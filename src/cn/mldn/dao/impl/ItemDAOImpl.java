package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dao.IItemDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vo.Item;

public class ItemDAOImpl  extends AbstractDAO implements IItemDAO{

	@Override
	public boolean doCreate(Item vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Item vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAll() throws SQLException {
		List<Item> all=new ArrayList<>();
		String sql="select iid,title from item";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			Item item=new Item();
			item.setIid(rs.getLong(1));
			item.setTitle(rs.getString(2));
			all.add(item);
		}
		return all;
	}

	@Override
	public List<Item> findAll(Long currentPage, Integer lineSize) throws Exception {
	
		return null;
	}

	@Override
	public List<Item> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSplitCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Long, String> findAllMap() throws SQLException {
	
		Map<Long,String> map=new HashMap<>();
		String sql="select iid,title from item";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()) {
			 map.put(rs.getLong(1), rs.getString(2));
		}
		return map;
	}

}

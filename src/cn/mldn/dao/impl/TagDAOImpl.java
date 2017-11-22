package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.dao.ITagDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vo.Item;
import cn.mldn.vo.Tag;

public class TagDAOImpl extends AbstractDAO implements ITagDAO{

	@Override
	public boolean doCreate(Tag vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Tag vo) throws SQLException {
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
	public Tag findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> findAll() throws SQLException {
		List<Tag> all=new ArrayList<>();
		String sql="select tid,title from tag";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			Tag tag=new Tag();
			tag.setTid(rs.getLong(1));
			tag.setTitle(rs.getString(2));
			all.add(tag);
		}
		return all;
	}

	@Override
	public List<Tag> findAll(Long currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
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

}

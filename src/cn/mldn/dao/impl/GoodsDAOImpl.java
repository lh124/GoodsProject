package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.dao.IGoodsDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vo.Goods;
import cn.mldn.vo.GoodsTag;

public class GoodsDAOImpl extends AbstractDAO implements IGoodsDAO {
	@Override
	public boolean doCreate(Goods vo) throws SQLException {
		String sql="insert into goods (name,price,photo,dflag,iid) values (?,?,?,?,?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1,vo.getName());
		super.pstmt.setDouble(2, vo.getPrice());
		super.pstmt.setString(3, vo.getPhoto());
		super.pstmt.setInt(4, vo.getDflag());
		super.pstmt.setLong(5, vo.getIid());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doEdit(Goods vo) throws SQLException {
		String sql="update  goods set name=?,price=?,photo=?,iid=? where gid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1,vo.getName());
		super.pstmt.setDouble(2, vo.getPrice());
		super.pstmt.setString(3, vo.getPhoto());
		super.pstmt.setLong(4, vo.getIid());
		super.pstmt.setLong(5, vo.getGid());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemove(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		
		return false;
	}

	@Override
	public Goods findById(Long id) throws SQLException {
	String sql="select gid,name,price,photo,iid  from goods  where dflag=0 and gid=?";
	super.pstmt=super.conn.prepareStatement(sql);
	super.pstmt.setLong(1, id);
	ResultSet rs=super.pstmt.executeQuery();
	Goods vo=null;
	if(rs.next()) {
		 vo=new Goods();
		vo.setGid(rs.getLong(1));
		vo.setName(rs.getString(2));
		vo.setPrice(rs.getDouble(3));
		vo.setPhoto(rs.getString(4));
		vo.setIid(rs.getLong(5));
	
		System.out.println(vo);
		return vo;
	}
		
		return null;
	}

	@Override
	public List<Goods> findAll() throws SQLException {
	
		List<Goods> all=new ArrayList<Goods>();
		String sql="select gid,name,price,photo,iid from goods where dflag=0";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			Goods vo=new Goods();
			vo.setGid(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setPrice(rs.getDouble(3));  
			vo.setPhoto(rs.getString(4));
			vo.setIid(rs.getLong(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Goods> findAll(Long currentPage, Integer lineSize) throws Exception {
		//insert into goods (name,price,photo,dflag,iid) values (?,?,?,?,?)
		List<Goods> all=new ArrayList<Goods>();
		String sql="select gid,name,price,photo,iid from goods where dflag=0 limit ?,?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, (currentPage-1)*lineSize);
		super.pstmt.setLong(2, lineSize);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			Goods vo=new Goods();
			vo.setGid(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			vo.setIid(rs.getLong(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Goods> findSplit(String column, String keyWord, Long currentPage, Integer lineSize) throws Exception {
		List<Goods> all=new ArrayList<Goods>();
		String sql="select gid,name,price,photo,iid from goods where dflag=0 and "+column+" like ?  limit ?,?   ";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%"+keyWord+"%");
		super.pstmt.setLong(2,  (currentPage-1)*lineSize);
		super.pstmt.setLong(3, lineSize);
	
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			Goods vo=new Goods();
			vo.setGid(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			vo.setIid(rs.getLong(5));
			all.add(vo);
		}
		return all;
		
	}

	@Override
	public Long getAllCount() throws SQLException {
		String sql="select count(*) from goods where dflag=0 ";
		super.pstmt=super.conn.prepareStatement(sql);
		
		ResultSet rs=super.pstmt.executeQuery();
		
		if(rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}
  
	@Override
	public Long getSplitCount(String column, String keyWord) throws SQLException {
		String sql="select count(*) from goods where dflag=0 "+column+" like ?  ";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%"+keyWord+"%");
		ResultSet rs=super.pstmt.executeQuery();
		
		if(rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Long findCreateLastId() throws SQLException {
		String sql="select last_insert_id()";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()) {
			return rs.getLong(1);
		}
		return null;
	}

	@Override
	public boolean doCreateGoodsTag(Long id, Set<Long> tid) throws SQLException {
		String sql="insert into goods_tag (gid,tid) values(?,?)";
		super.pstmt=super.conn.prepareStatement(sql);
		Iterator<Long> iter=tid.iterator();
		while(iter.hasNext()) {
			super.pstmt.setLong(1, id);
			super.pstmt.setLong(2, iter.next());
			super.pstmt.addBatch();
		}
		
		int date[]=super.pstmt.executeBatch();
		for(int i=0;i<date.length;i++) {
			if(date[i]==0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean doEditDflag(Set<Long> gids, Integer flag) throws SQLException {
	
		String sql="update  goods set dflag=?   where gid=?";
		
		super.pstmt=super.conn.prepareStatement(sql);
		Iterator<Long> ids=gids.iterator();
		while(ids.hasNext()) {
			super.pstmt.setInt(1,flag);
			super.pstmt.setLong(2,ids.next());
			super.pstmt.addBatch();
		}
		int date[]=super.pstmt.executeBatch();
		for(int i=0;i<date.length;i++) {
			if(date[i]==0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean doRemoveGoodsTag(Long id) throws SQLException {
	
		String sql="delete  from goods_tag where gid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, id);
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public Set<Long> findGoodsTas(Long id) throws SQLException {
		String sql="select tid from goods_tag where gid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, id);
		ResultSet rs=super.pstmt.executeQuery();
		Set<Long> ids=new HashSet<>();
		while(rs.next()) {
			ids.add(rs.getLong(1));
		}
		return ids;
	}
}

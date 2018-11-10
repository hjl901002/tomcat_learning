package org.huangjl.ch09.bookstore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BookDBBean implements Serializable {
	
	private DataSource ds = null;
	
	public BookDBBean() throws NamingException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bookstore");
	}
	
	/*
	* 得到数据库连接
	* 
	*/
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	/**
	* 关闭连接对象
	* 
	*/
	protected void closeConnection(Connection conn) {
		if(conn != null) {
			try{
				conn.close();
				conn = null;
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	* 关闭Statement对象
	* 
	*/
	protected void closeStatement(Statement stmt) {
		if(stmt != null) {
			try{
				stmt.close();
				stmt = null;
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	* 关闭PreparedStatement对象
	* 
	*/
	protected void closePreparedStatement(PreparedStatement pstmt) {
		if(pstmt != null) {
			try{
				pstmt.close();
				pstmt = null;
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	* 关闭ResultSet对象
	* 
	*/
	protected void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try{
				rs.close();
				rs = null;
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/*
	* 得到数据库中所有的图书信息
	* 
	*/
	public Collection<BookBean> getBooks() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from bookinfo");
			while(rs.next()) {
				BookBean book = new BookBean(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6),
					rs.getInt(7), rs.getString(8));
				bookList.add(book);
			}
			return bookList;
		}finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}
	}
	
	/*
	* 得到选择的图书信息
	* 
	*/
	public BookBean getBook(int bookId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from bookinfo where id = ?");
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			BookBean book = null;
			if(rs.next()) {
				book = new BookBean(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6),
					rs.getInt(7), rs.getString(8));
			}
			return book;
		}finally {
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
	}
	
	/**
	* 通过关键字来搜索图书的信息
	* 
	*/
	public Collection<BookBean> searchBook(String keyword) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select * from bookinfo where title like '%" + keyword + "%'";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BookBean book = new BookBean(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6),
					rs.getInt(7), rs.getString(8));
				bookList.add(book);
			}
			return bookList;
		}finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}
	}
	
	
	/**
	* 判断剩余图书的数量是否大于客户购买的数量
	* 
	*/
	public boolean isAmountEnough(int bookId, int quantity) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean bEnough = false;
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select amount from bookinfo where id = " + bookId);
			while(rs.next()) {
				int amount = rs.getInt(1);
				if(amount >= quantity)
					bEnough = true;
			}
		}finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}
		return bEnough;
	}
	
	/**
	* 购买购物车中的所有图书
	* 
	*/
	public synchronized void buyBooks(CartBean cart) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Iterator<CartItemBean> it = cart.getItems().iterator();
		try{
			conn = getConnection();
			String sql = "update bookinfo set amount = amount - ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			
			while(it.hasNext()) {
				CartItemBean item = (CartItemBean)it.next();
				BookBean book = item.getBook();
				int bookId = book.getId();
				int quantity = item.getQuantity();
				
				pstmt.setInt(1, quantity);
				pstmt.setInt(2, bookId);
				
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
		}finally {
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
	}
}
package com.musictour;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.musictour.dbManager.DBManager;

/**
 * Servlet implementation class AddLikes
 */
public class AddLikes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLikes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String cid = request.getParameter("cid");
		
		DBManager ma = new DBManager();
		ma.getDirver();
		ma.connect();
		int out = ma.addLikes(uname, cid);
		ma.shutdown();
		JSONObject obj = new JSONObject();
		String info = null;
		if(out == 0) info = "fail";
		if(out == 1) info = "success";
		obj.put("status", new String(info));
		response.getWriter().write(obj.toJSONString());
	}

}

package com.musictour.dbManager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class DeleteFollow
 */
public class DeleteFollow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFollow() {
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
		String follower = request.getParameter("follower");
		
		DBManager ma = new DBManager();
		ma.getDirver();
		ma.connect();
		int out = ma.deleteFollow(uname, follower);
		ma.shutdown();
		JSONObject obj = new JSONObject();
		String info = null;
		if(out == 0) info = "fail";
		if(out == 1) info = "success";
		obj.put("status", new String(info));
		response.getWriter().write(obj.toJSONString());
	}

}

package com.musictour;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.musictour.dbManager.DBManager;

/**
 * Servlet implementation class registerUser
 */
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		int flag = Integer.parseInt(request.getParameter("flag"));
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		
		DBManager ma = new DBManager();
		ma.getDirver();
		ma.connect();
		int out = ma.insertUser(username, password, 0,
				"", "", currentTime,
				email, "", "", flag);
		ma.shutdown();
		String info = null;
		if(out == 0) info = "illegal input";
		if(out == 1) info = "success";
		if(out == 2) info = "exist email";
		if(out == 3) info = "exist name";
		JSONObject obj = new JSONObject();
		obj.put("status", new String(info));
		response.getWriter().write(obj.toJSONString());
	}

}

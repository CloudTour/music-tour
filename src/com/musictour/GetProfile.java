package com.musictour;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.musictour.dbManager.DBManager;

/**
 * Servlet implementation class GetBandProfile
 */
public class GetProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProfile() {
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
		DBManager ma = new DBManager();
		String username = request.getParameter("username");
		int flag = Integer.parseInt(request.getParameter("flag"));
		
		ma.getDirver();
		ma.connect();
		String out = ma.getProfile(username, flag);
		ma.shutdown();
		response.getWriter().write(out);
	}

}

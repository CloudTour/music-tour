package com.musictour;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.musictour.dbManager.DBManager;

/**
 * Servlet implementation class AddConcertByUser
 */
public class AddConcertByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddConcertByUser() {
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
		//cname bname cdatetime cprice cwebsite vname uname confirmed
		String cname = request.getParameter("cname");
		String bname = request.getParameter("bname");
		String cdatetime = request.getParameter("cdatetime");
		String cprice = request.getParameter("cprice");
		String cwebsite = request.getParameter("cwebsite");
		String vname = request.getParameter("vname");
		String uname = request.getParameter("uname");
		String confirmed = request.getParameter("confirmed");
		
		DBManager ma = new DBManager();
		ma.getDirver();
		ma.connect();
		int score = ma.getScoreByUser(uname);
		if(score < 10) {
			JSONObject obj = new JSONObject();
			obj.put("status", "not enough score.");
			response.getWriter().write(obj.toJSONString());
		} else {
			int out = ma.addConcert(cname, bname, cdatetime,
					cprice, cwebsite, vname, uname,
					confirmed);
			ma.shutdown();
			JSONObject obj = new JSONObject();
			String info = null;
			if(out == 0) info = "fail";
			if(out == 1) info = "success";
			obj.put("status", new String(info));
			response.getWriter().write(obj.toJSONString());
		}
	}

}

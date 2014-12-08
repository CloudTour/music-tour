package com.musictour;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.musictour.dbManager.DBManager;

/**
 * Servlet implementation class RateConcertByUser
 */
public class RateConcertByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RateConcertByUser() {
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
		//uname cid rate review 
		String uname = request.getParameter("uname");
		String cid = request.getParameter("cid");
		String rate = request.getParameter("rate");
		String review = request.getParameter("review");
		
		DBManager ma = new DBManager();
		ma.getDirver();
		ma.connect();
		int out = 0;
		String info = null;
		int isRate = ma.addRate(uname, cid, rate, review);
		if(isRate == 1) {
			out = ma.updateUserScore(uname, 1); // rate for 1 point
		} else {
			info = "rate fail";
		}
		ma.shutdown();
		JSONObject obj = new JSONObject();
		if(out == 0) info = "update score fail";
		if(out == 1) info = "success";
		obj.put("status", new String(info));
		response.getWriter().write(obj.toJSONString());
	}

}

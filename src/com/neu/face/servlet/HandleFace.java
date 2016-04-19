package com.neu.face.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

/**
 * ʶ������ͼ�񲢷��ؽ��
 */
public class HandleFace extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger ( UploadImage.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleFace() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			String imagePath = request.getParameter("imagePath");
			imagePath = request.getSession().getServletContext()
					.getRealPath("/")
				+imagePath;

			HttpRequests hrs = new HttpRequests(
					"8ee48b2f0466d2ec250cb85a9f6a865a",
					"hQl18yxDS9y6axykz21qa-PIrTDAlN9U");
			PostParameters pps = new PostParameters();
			pps.setImg(new File(imagePath));
			JSONObject json = hrs.detectionDetect(pps);
			JSONArray array = json.getJSONArray("face");
			for (int i = 0; i < array.length(); i++) {
				
				JSONObject obj = array.getJSONObject(i);
				JSONObject attrObj = obj.getJSONObject("attribute");

				StringBuffer result = new StringBuffer();
				JSONObject age = attrObj.getJSONObject("age");

				int range = age.getInt("range");
				int value = age.getInt("value");
				// ��ȡ����
				result.append("���䣺").append(value).append("�꣨��Χ��");
				result.append(range).append("�꣩<br>");
				// ��ȡ�Ա�
				String genderStr = attrObj.getJSONObject("gender").getString(
						"value");
				Double confidence = attrObj.getJSONObject("gender").getDouble(
						"confidence");

				result.append("�Ա�").append(genderStr).append("����ȷ�ʣ�");
				result.append(confidence).append("%)<br>");

				// ��ȡ����
				String raceStr = attrObj.getJSONObject("race").getString(
						"value");
				confidence = attrObj.getJSONObject("race").getDouble(
						"confidence");

				result.append("���壺").append(raceStr).append("����ȷ�ʣ�");
				result.append(confidence).append("%)<br>");

				// ��ȡ�ǲ�����Ц
				Double smiling = attrObj.getJSONObject("smiling").getDouble(
						"value");
				result.append("����Ц��").append(smiling).append("%");

				PrintWriter out = response.getWriter();
				out.print(result.toString());
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			logger.error("file is not exsit! ");
			e.printStackTrace();
		}
	}

}

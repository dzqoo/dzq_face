package com.neu.face.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadImage extends HttpServlet {

	/**
	 * java doc ע��
	 * <p>
	 * ����ͼƬ�ϴ�
	 * </p>
	 * Constructor of the object.
	 */
	public UploadImage() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����ļ��ϴ�
		boolean isM = ServletFileUpload.isMultipartContent(request);
		InputStream in = null;
		OutputStream ots = null;
		try {
			if (isM) {
				// �ļ���Ŀ����
				FileItemFactory fif = new DiskFileItemFactory();
				// �ļ��ϴ�������
				ServletFileUpload sfu = new ServletFileUpload(fif);
				List<FileItem> files = sfu.parseRequest(request);
				// ��ȡ��һ���ļ�������ת��������
				in = files.get(0).getInputStream();

				String path = request.getSession().getServletContext()
						.getRealPath("/");
				String uuid = UUID.randomUUID().toString();
				path = path + "/photos";
				
				request.setAttribute("imagePath", "photos/"+uuid);
				// �Զ������ļ���
				File image = new File(path);
				if (!image.exists()) {
					image.mkdirs();
				}

				path = path + "/" + uuid;
				ots = new FileOutputStream(path);
				// ����������ȡ���ݣ�д�������

				byte[] b = new byte[1024];
				int n = 0;
				while ((n = in.read(b)) != -1) {
					ots.write(b, 0, n);
				}
			}
		} catch (Exception e) {
			// ��¼����־
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
			if (ots != null) {
				ots.close();
			}
		}
		
		request.getRequestDispatcher("showImage").forward(request, response);;
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

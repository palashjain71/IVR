package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccessLayer.DB_IVR;
import sampleIVR.Locales.IVR_ModalClass;

public class ControllerServlet {

	private static final long serialVersionUID = 1L;
	private DB_IVR dB_IVR;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		dB_IVR = new DB_IVR(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertBook(request, response);
				break;
			case "/delete":
				deleteBook(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateBook(request, response);
				break;
			default:
				listLocales(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listLocales(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<IVR_ModalClass> listIVR = dB_IVR.listAllLoacles();
		request.setAttribute("listIVR", listIVR);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String ivrLocales = request.getParameter("locales");
		IVR_ModalClass existingBook = dB_IVR.getLocales(ivrLocales);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		request.setAttribute("localess", existingBook);
		dispatcher.forward(request, response);

	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String ivrLocales = request.getParameter("locales");
		String ivrLocalesDescrption = request.getParameter("locale_EntryMsg");

		IVR_ModalClass newIVR = new IVR_ModalClass(ivrLocales, ivrLocalesDescrption);
		dB_IVR.insertIVR(newIVR);
		response.sendRedirect("list");
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String ivrLocales = request.getParameter("locales");
		String ivrLocalesDescrption = request.getParameter("locale_EntryMsg");

		IVR_ModalClass newIVR = new IVR_ModalClass(ivrLocales, ivrLocalesDescrption);
		dB_IVR.updateIVR(newIVR);
		response.sendRedirect("list");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String ivrLocales = request.getParameter("locales");
		String ivrLocalesDescrption = request.getParameter("locale_EntryMsg");
		IVR_ModalClass newIVR = new IVR_ModalClass(ivrLocales, ivrLocalesDescrption);
		dB_IVR.deleteIVR(newIVR);
		response.sendRedirect("list");

	}

}

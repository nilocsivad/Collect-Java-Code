<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="com.defence.mp2017.rs.util.ConstUtil"%>

<%!//////////////////////////////////////////////////
	//////////
	//////////
	//////////
	String URL = null;
	String HTP = null;
	//////////
	//////////
	//////////
	//////////////////////////////////////////////////%>
<%
	URL = request.getAttribute("URL") == null ? null : request.getAttribute("URL") + "";
	if (URL == null) {

		String path = request.getContextPath();
		request.setAttribute("URL", path);

		int port = request.getServerPort();
		String scheme = request.getScheme() + "://";
		String serverNamePort = request.getServerName() + (port == 80 ? "" : ":" + port);
		HTP = scheme + serverNamePort;

		URL = scheme + (serverNamePort + "/" + path).replace("//", "/");
		if (URL.endsWith("/"))
			URL = URL.substring(0, URL.length() - 1);

		request.setAttribute("FURL", URL);
		request.setAttribute("HTP", HTP);
		request.setAttribute("RES", HTP + "/" + ConstUtil.getResourcePrefix());
	}
%>
package jumpframework.crud;

import java.beans.FeatureDescriptor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jumpframework.createproject.FileUtil;

import org.apache.commons.lang3.text.WordUtils;

public class WriteView extends Write {

	/*
	 * table's name 
	 */
	private String tableName;
	
	/*
	 * all of field in table : array 
	 */
	private String[][] fields;
	
	/*
	 * Project's path only.
	 */
	private String path;
	
	/*
	 * the view file
	 */
	private File listFile,addFile,updateFile;
	
	private String subPath = "\\src\\main\\webapp\\WEB-INF\\pages\\";
	
	/**
	 * create and write file view.
	 * @param tableName = table's name
	 * @param fields = 2d array -> field's name, data type, is AUTOINCREMENT
	 * @param path = Project's path for create file
	 */
	public WriteView(String tableName,String[][] fields, String path) {
		this.tableName = tableName;
		this.fields = fields;
		this.path = path;
	}

	/**
	 * create and write file 
	 */	
	public void createView(){
		createFile();
		writeFile();
	}	
	

	/*
	 * create file from FileUtil ;using createFile method
	 */
	private void createFile() {
		
		FileUtil fu = new FileUtil();
		FileUtil.createDirectory(tableName.toLowerCase(), path+subPath);
		subPath += tableName.toLowerCase()+"\\";
		listFile = fu.createFile("list", path+subPath, "jsp");
		addFile = fu.createFile("add", path+subPath, "jsp");
		updateFile = fu.createFile("update", path+subPath, "jsp");
	}
	
	/*
	 * write file each lines 
	 */
	private void writeFile() {
		
		createListView();
		createAddView();
		createUpdateView();		
		
	}

	/*
	 * create List all table | list.jsp
	 */
	private void createListView() {
		try {
			FileWriter fw = new FileWriter(listFile);
			fw.write(wT(0)+"<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n");
			fw.write(wT(0)+"pageEncoding=\"UTF-8\"%>\n");
			fw.write(wT(0)+"<%@ include file=\"/WEB-INF/pages/includes.jsp\"%>\n");
			fw.write(wT(0)+"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
			fw.write(wT(0)+"<html>\n");
			fw.write(wT(0)+"<head>\n");
			fw.write(wT(1)+"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
			fw.write(wT(1)+"<meta charset=\"utf-8\">\n");
			fw.write(wT(1)+"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");			
			fw.write(wT(1)+"<meta name=\"description\" content=\"\">\n");
			fw.write(wT(1)+"<meta name=\"author\" content=\"\">\n\n");
			fw.write(wT(1)+"<title>"+WordUtils.capitalize(tableName)+"</title>\n\n");
			fw.write(wT(1)+"<link href=\"<c:url value=\"/Theme/ColorFull/css/bootstrap.min.css\" />\" rel=\"stylesheet\">\n");
			fw.write(wT(1)+"<link href=\"<c:url value=\"/Theme/ColorFull/css/app.css\" />\" rel=\"stylesheet\">\n\n");
			fw.write(wT(0)+"</head>\n");
			//body
			fw.write(wT(0)+"<body>\n\n");
			//nav
			fw.write(wT(1)+"<nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\n");
			fw.write(wT(2)+"<div class=\"container\">\n");
			fw.write(wT(3)+"<div class=\"navbar-header\">\n");
			fw.write(wT(4)+"<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">\n");
			fw.write(wT(5)+"<span class=\"sr-only\">Toggle navigation</span>\n");
			fw.write(wT(5)+"<span class=\"icon-bar\"></span>\n");
			fw.write(wT(5)+"<span class=\"icon-bar\"></span>\n");
			fw.write(wT(5)+"<span class=\"icon-bar\"></span>\n");
			fw.write(wT(4)+"</button>\n");
			fw.write(wT(4)+"<a class=\"navbar-brand\" href=\"<c:url value='/"+tableName.toLowerCase()+"' />\">JUMP FRAMEWORK</a>\n");
			fw.write(wT(3)+"</div>\n\n");
			fw.write(wT(3)+"<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n");
			fw.write(wT(4)+"<ul class=\"nav navbar-nav\">\n");
			fw.write(wT(5)+"<li><a href=\"<c:url value='/about' />\">About</a></li>\n");
			fw.write(wT(5)+"<li><a href=\"<c:url value='/contact' />\">Contact</a></li>\n");
			fw.write(wT(4)+"</ul>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(2)+"</div>\n");
			fw.write(wT(1)+"</nav>\n\n");
			// menu
			fw.write(wT(1)+"<div class=\"container\">\n");
			fw.write(wT(2)+"<div class=\"row\">\n");
			fw.write(wT(3)+"<div class=\"col-md-3\">\n");
			fw.write(wT(4)+"<p class=\"lead\">"+WordUtils.capitalize(tableName)+"</p>\n");
			fw.write(wT(4)+"<div class=\"list-group\">\n");
			fw.write(wT(5)+"<a href=\"<c:url value='/"+tableName.toLowerCase()+"' />\" class=\"list-group-item active\">List all "+WordUtils.capitalize(tableName)+"</a>\n");
			fw.write(wT(5)+"<a href=\"<c:url value='/"+tableName.toLowerCase()+"/add' />\" class=\"list-group-item\">Create new "+WordUtils.capitalize(tableName)+"</a>\n");
			fw.write(wT(4)+"</div>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(3)+"<div class=\"col-md-9\">\n");
			fw.write(wT(4)+"<div class=\"thumbnail\">\n");
			fw.write(wT(5)+"<div class=\"caption-full\">\n");
			
			//content
			fw.write(wT(6)+"<h3>List all "+WordUtils.capitalize(tableName)+"</h3>\n");
			fw.write(wT(6)+"<table class=\"table table-bordered table-responsive\">\n");
			// writes <thead>
			for(int i=0;i<fields.length;i++){
				fw.write(wT(7)+"<th>"+WordUtils.capitalize(fields[i][0])+"</th>\n");
			}			
			fw.write(wT(7)+"<th>manage</th>\n");
			// writes <tbody>
			fw.write(wT(7)+"<c:forEach items=\"${"+tableName.toLowerCase()+"List}\" var=\""+tableName.toLowerCase()+"\">\n");
			fw.write(wT(8)+"<tr>\n");
			for(int i=0;i<fields.length;i++){
				fw.write(wT(9)+"<td>${"+tableName.toLowerCase()+"."+fields[i][0].toLowerCase()+"}</td>\n");
			}
			fw.write(wT(9)+"<td>\n");
			fw.write(wT(10)+"<a href=\"<c:url value='/"+tableName.toLowerCase()+"/${"+tableName.toLowerCase()+"."+fields[0][0].toLowerCase()+"}/update' />\" >Update</a>\n");
			fw.write(wT(10)+"<a href=\"<c:url value='/"+tableName.toLowerCase()+"/${"+tableName.toLowerCase()+"."+fields[0][0].toLowerCase()+"}/delete' />\" >Delete</a>\n");
			fw.write(wT(9)+"</td>\n");
			fw.write(wT(8)+"</tr>\n");
			fw.write(wT(7)+"</c:forEach>\n");
			fw.write(wT(6)+"</table>\n");
			//end content
			
			fw.write(wT(5)+"</div>\n");
			fw.write(wT(4)+"</div>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(2)+"</div>\n");
			fw.write(wT(1)+"</div>\n\n");
			fw.write(wT(1)+"<div class=\"container\">\n");
			fw.write(wT(2)+"<hr>\n");
			fw.write(wT(2)+"<footer>\n");
			fw.write(wT(3)+"<div class=\"row\">\n");
			fw.write(wT(4)+"<div class=\"col-lg-12\">\n");
			fw.write(wT(5)+"<p>Copyright &copy; Your Website 2016</p>\n");
			fw.write(wT(4)+"</div>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(2)+"</footer>\n");
			fw.write(wT(1)+"</div>\n");
			fw.write(wT(1)+"<script src=\"<c:url value=\"/Theme/ColorFull/js/jquery.js\" />\"></script>\n");
			fw.write(wT(1)+"<script src=\"<c:url value=\"/Theme/ColorFull/js/bootstrap.min.js\" />\"></script>\n");
			fw.write(wT(0)+"</body>\n");
			fw.write(wT(0)+"</html>\n");			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * create add data  | add.jsp
	 */
	private void createAddView() {
		try {
			FileWriter fw = new FileWriter(addFile);
			fw.write(wT(0)+"<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n");
			fw.write(wT(0)+"pageEncoding=\"UTF-8\"%>\n");
			fw.write(wT(0)+"<%@ include file=\"/WEB-INF/pages/includes.jsp\"%>\n");
			fw.write(wT(0)+"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
			fw.write(wT(0)+"<html>\n");
			fw.write(wT(0)+"<head>\n");
			fw.write(wT(1)+"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
			fw.write(wT(1)+"<meta charset=\"utf-8\">\n");
			fw.write(wT(1)+"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");			
			fw.write(wT(1)+"<meta name=\"description\" content=\"\">\n");
			fw.write(wT(1)+"<meta name=\"author\" content=\"\">\n\n");
			fw.write(wT(1)+"<title>"+WordUtils.capitalize(tableName)+"</title>\n\n");
			fw.write(wT(1)+"<link href=\"<c:url value=\"/Theme/ColorFull/css/bootstrap.min.css\" />\" rel=\"stylesheet\">\n");
			fw.write(wT(1)+"<link href=\"<c:url value=\"/Theme/ColorFull/css/app.css\" />\" rel=\"stylesheet\">\n\n");
			fw.write(wT(0)+"</head>\n");
			//body
			fw.write(wT(0)+"<body>\n\n");
			//nav
			fw.write(wT(1)+"<nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\n");
			fw.write(wT(2)+"<div class=\"container\">\n");
			fw.write(wT(3)+"<div class=\"navbar-header\">\n");
			fw.write(wT(4)+"<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">\n");
			fw.write(wT(5)+"<span class=\"sr-only\">Toggle navigation</span>\n");
			fw.write(wT(5)+"<span class=\"icon-bar\"></span>\n");
			fw.write(wT(5)+"<span class=\"icon-bar\"></span>\n");
			fw.write(wT(5)+"<span class=\"icon-bar\"></span>\n");
			fw.write(wT(4)+"</button>\n");
			fw.write(wT(4)+"<a class=\"navbar-brand\" href=\"<c:url value='/"+tableName.toLowerCase()+"' />\">JUMP FRAMEWORK</a>\n");
			fw.write(wT(3)+"</div>\n\n");
			fw.write(wT(3)+"<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n");
			fw.write(wT(4)+"<ul class=\"nav navbar-nav\">\n");
			fw.write(wT(5)+"<li><a href=\"<c:url value='/about' />\">About</a></li>\n");
			fw.write(wT(5)+"<li><a href=\"<c:url value='/contact' />\">Contact</a></li>\n");
			fw.write(wT(4)+"</ul>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(2)+"</div>\n");
			fw.write(wT(1)+"</nav>\n\n");
			// menu
			fw.write(wT(1)+"<div class=\"container\">\n");
			fw.write(wT(2)+"<div class=\"row\">\n");
			fw.write(wT(3)+"<div class=\"col-md-3\">\n");
			fw.write(wT(4)+"<p class=\"lead\">"+WordUtils.capitalize(tableName)+"</p>\n");
			fw.write(wT(4)+"<div class=\"list-group\">\n");
			fw.write(wT(5)+"<a href=\"<c:url value='/"+tableName.toLowerCase()+"' />\" class=\"list-group-item\">List all "+WordUtils.capitalize(tableName)+"</a>\n");
			fw.write(wT(5)+"<a href=\"<c:url value='/"+tableName.toLowerCase()+"/add' />\" class=\"list-group-item active\">Create new "+WordUtils.capitalize(tableName)+"</a>\n");
			fw.write(wT(4)+"</div>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(3)+"<div class=\"col-md-9\">\n");
			fw.write(wT(4)+"<div class=\"thumbnail\">\n");
			fw.write(wT(5)+"<div class=\"caption-full\">\n");
			
			// content
			fw.write(wT(6)+"<h3>Create new "+WordUtils.capitalize(tableName)+"</h3>\n");
			fw.write(wT(6)+"<c:url var='addAction' value='/"+tableName.toLowerCase()+".do' />\n");
			fw.write(wT(6)+"<form:form action=\"${addAction}\" method=\"POST\" commandName=\""+tableName.toLowerCase()+"\" class=\"form\">\n");
			for(int i=0;i<fields.length;i++){
				fw.write(wT(7)+"<div class=\"form-group\">\n");
				fw.write(wT(8)+"<form:label path=\""+fields[i][0].toLowerCase()+"\">"+WordUtils.capitalize(fields[i][0])+": </form:label>\n");
				if(getTypeHtml().get(fields[i][1]).equals("textarea")){
					fw.write(wT(8)+"<form:textarea path=\""+fields[i][0].toLowerCase()+"\" class=\"form-control\"/>\n");
				}else{
					fw.write(wT(8)+"<form:input path=\""+fields[i][0].toLowerCase()+"\" class=\"form-control\" type=\""+getTypeHtml().get(fields[i][1])+"\" />\n");
				}
				
				fw.write(wT(7)+"</div>\n");
			}
			
			fw.write(wT(7)+"<input type=\"submit\" name=\"action\" value=\"Add\" />\n");
			fw.write(wT(6)+"</form:form>\n");
			// end content
			
			fw.write(wT(5)+"</div>\n");
			fw.write(wT(4)+"</div>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(2)+"</div>\n");
			fw.write(wT(1)+"</div>\n\n");
			fw.write(wT(1)+"<div class=\"container\">\n");
			fw.write(wT(2)+"<hr>\n");
			fw.write(wT(2)+"<footer>\n");
			fw.write(wT(3)+"<div class=\"row\">\n");
			fw.write(wT(4)+"<div class=\"col-lg-12\">\n");
			fw.write(wT(5)+"<p>Copyright &copy; Your Website 2016</p>\n");
			fw.write(wT(4)+"</div>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(2)+"</footer>\n");
			fw.write(wT(1)+"</div>\n");
			fw.write(wT(1)+"<script src=\"<c:url value=\"/Theme/ColorFull/js/jquery.js\" />\"></script>\n");
			fw.write(wT(1)+"<script src=\"<c:url value=\"/Theme/ColorFull/js/bootstrap.min.js\" />\"></script>\n");
			fw.write(wT(0)+"</body>\n");
			fw.write(wT(0)+"</html>\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * create update table | update.jsp
	 */
	private void createUpdateView() {
		try {
			FileWriter fw = new FileWriter(updateFile);
			fw.write(wT(0)+"<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n");
			fw.write(wT(0)+"pageEncoding=\"UTF-8\"%>\n");
			fw.write(wT(0)+"<%@ include file=\"/WEB-INF/pages/includes.jsp\"%>\n");
			fw.write(wT(0)+"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
			fw.write(wT(0)+"<html>\n");
			fw.write(wT(0)+"<head>\n");
			fw.write(wT(1)+"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
			fw.write(wT(1)+"<meta charset=\"utf-8\">\n");
			fw.write(wT(1)+"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");			
			fw.write(wT(1)+"<meta name=\"description\" content=\"\">\n");
			fw.write(wT(1)+"<meta name=\"author\" content=\"\">\n\n");
			fw.write(wT(1)+"<title>"+WordUtils.capitalize(tableName)+"</title>\n\n");
			fw.write(wT(1)+"<link href=\"<c:url value=\"/Theme/ColorFull/css/bootstrap.min.css\" />\" rel=\"stylesheet\">\n");
			fw.write(wT(1)+"<link href=\"<c:url value=\"/Theme/ColorFull/css/app.css\" />\" rel=\"stylesheet\">\n\n");
			fw.write(wT(0)+"</head>\n");
			//body
			fw.write(wT(0)+"<body>\n\n");
			//nav
			fw.write(wT(1)+"<nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\n");
			fw.write(wT(2)+"<div class=\"container\">\n");
			fw.write(wT(3)+"<div class=\"navbar-header\">\n");
			fw.write(wT(4)+"<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">\n");
			fw.write(wT(5)+"<span class=\"sr-only\">Toggle navigation</span>\n");
			fw.write(wT(5)+"<span class=\"icon-bar\"></span>\n");
			fw.write(wT(5)+"<span class=\"icon-bar\"></span>\n");
			fw.write(wT(5)+"<span class=\"icon-bar\"></span>\n");
			fw.write(wT(4)+"</button>\n");
			fw.write(wT(4)+"<a class=\"navbar-brand\" href=\"<c:url value='/"+tableName.toLowerCase()+"' />\">JUMP FRAMEWORK</a>\n");
			fw.write(wT(3)+"</div>\n\n");
			fw.write(wT(3)+"<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n");
			fw.write(wT(4)+"<ul class=\"nav navbar-nav\">\n");
			fw.write(wT(5)+"<li><a href=\"<c:url value='/about' />\">About</a></li>\n");
			fw.write(wT(5)+"<li><a href=\"<c:url value='/contact' />\">Contact</a></li>\n");
			fw.write(wT(4)+"</ul>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(2)+"</div>\n");
			fw.write(wT(1)+"</nav>\n\n");
			// menu
			fw.write(wT(1)+"<div class=\"container\">\n");
			fw.write(wT(2)+"<div class=\"row\">\n");
			fw.write(wT(3)+"<div class=\"col-md-3\">\n");
			fw.write(wT(4)+"<p class=\"lead\">"+WordUtils.capitalize(tableName)+"</p>\n");
			fw.write(wT(4)+"<div class=\"list-group\">\n");
			fw.write(wT(5)+"<a href=\"<c:url value='/"+tableName.toLowerCase()+"' />\" class=\"list-group-item\">List all "+WordUtils.capitalize(tableName)+"</a>\n");
			fw.write(wT(5)+"<a href=\"<c:url value='/"+tableName.toLowerCase()+"/add' />\" class=\"list-group-item\">Create new "+WordUtils.capitalize(tableName)+"</a>\n");
			fw.write(wT(4)+"</div>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(3)+"<div class=\"col-md-9\">\n");
			fw.write(wT(4)+"<div class=\"thumbnail\">\n");
			fw.write(wT(5)+"<div class=\"caption-full\">\n");

			// content
			fw.write(wT(6)+"<h3>Update "+WordUtils.capitalize(tableName)+" | ${"+tableName.toLowerCase()+"."+fields[0][0].toLowerCase()+"}</h3>\n");
			fw.write(wT(6)+"<c:url var='addAction' value='/"+tableName.toLowerCase()+".do' />\n");
			fw.write(wT(6)+"<form:form action=\"${addAction}\" method=\"POST\" commandName=\""+tableName.toLowerCase()+"\" class=\"form\">\n");
			for(int i=0;i<fields.length;i++){
				fw.write(wT(7)+"<div class=\"form-group\">\n");
				fw.write(wT(8)+"<form:label path=\""+fields[i][0].toLowerCase()+"\">"+WordUtils.capitalize(fields[i][0])+": </form:label>\n");
				if(getTypeHtml().get(fields[i][1]).equals("textarea")){
					fw.write(wT(8)+"<form:textarea path=\""+fields[i][0].toLowerCase()+"\" class=\"form-control\"/>\n");
				}else{
					fw.write(wT(8)+"<form:input path=\""+fields[i][0].toLowerCase()+"\" class=\"form-control\" type=\""+getTypeHtml().get(fields[i][1])+"\" />\n");
				}
				
				fw.write(wT(7)+"</div>\n");
			}
			
			fw.write(wT(7)+"<input type=\"submit\" name=\"action\" value=\"Edit\" />\n");
			fw.write(wT(6)+"</form:form>\n");
			// end content
			
			fw.write(wT(5)+"</div>\n");
			fw.write(wT(4)+"</div>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(2)+"</div>\n");
			fw.write(wT(1)+"</div>\n\n");
			fw.write(wT(1)+"<div class=\"container\">\n");
			fw.write(wT(2)+"<hr>\n");
			fw.write(wT(2)+"<footer>\n");
			fw.write(wT(3)+"<div class=\"row\">\n");
			fw.write(wT(4)+"<div class=\"col-lg-12\">\n");
			fw.write(wT(5)+"<p>Copyright &copy; Your Website 2016</p>\n");
			fw.write(wT(4)+"</div>\n");
			fw.write(wT(3)+"</div>\n");
			fw.write(wT(2)+"</footer>\n");
			fw.write(wT(1)+"</div>\n");
			fw.write(wT(1)+"<script src=\"<c:url value=\"/Theme/ColorFull/js/jquery.js\" />\"></script>\n");
			fw.write(wT(1)+"<script src=\"<c:url value=\"/Theme/ColorFull/js/bootstrap.min.js\" />\"></script>\n");
			fw.write(wT(0)+"</body>\n");
			fw.write(wT(0)+"</html>\n");			
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

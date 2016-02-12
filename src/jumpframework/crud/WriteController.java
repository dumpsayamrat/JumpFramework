package jumpframework.crud;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import jumpframework.createproject.FileUtil;

import org.apache.commons.lang3.text.WordUtils;

public class WriteController extends Write {
	
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
	 * the controller file
	 */
	private File file;
	
	private String subPath = "\\src\\main\\java\\com\\spring\\controller\\";
	
	/**
	 * create and write file controller.
	 * @param tableName = table's name
	 * @param fields = 2d array -> field's name, data type, is AUTOINCREMENT
	 * @param path = Project's path for create file
	 */
	public WriteController(String tableName,String[][] fields, String path) {
		this.tableName = tableName;
		this.fields = fields;
		this.path = path;
	}

	/**
	 * create and write file 
	 */	
	public void createController(){
		createFile();
		writeFile();
	}	
	

	/*
	 * create file from FileUtil ;using createFile method
	 */
	private void createFile() {
		
		FileUtil fu = new FileUtil();
		file = fu.createFile(WordUtils.capitalize(tableName)+"Controller", path+subPath, "java");
	}
	
	/*
	 * write file each lines 
	 */
	private void writeFile() {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("package com.spring.controller;\n\n");
			fw.write("import java.util.Map;\n\n");
			fw.write("import org.springframework.beans.factory.annotation.Autowired;\n");
			fw.write("import org.springframework.stereotype.Controller;\n");
			fw.write("import org.springframework.validation.BindingResult;\n");
			fw.write("import org.springframework.web.bind.annotation.ModelAttribute;\n");
			fw.write("import org.springframework.web.bind.annotation.PathVariable;\n");
			fw.write("import org.springframework.web.bind.annotation.RequestMapping;\n");
			fw.write("import org.springframework.web.bind.annotation.RequestMethod;\n");
			fw.write("import org.springframework.web.bind.annotation.RequestParam;\n\n");
			fw.write("import com.spring.model."+WordUtils.capitalize(tableName)+";\n");
			fw.write("import com.spring.service."+WordUtils.capitalize(tableName)+"Service;\n\n");
			fw.write("@Controller\n");
			fw.write("public class "+WordUtils.capitalize(tableName)+"Controller {\n\n");
			fw.write(wT(1)+"@Autowired\n");
			fw.write(wT(1)+"private "+WordUtils.capitalize(tableName)+"Service "+tableName.toLowerCase()+"Service;\n\n");
			
			//all list method.
			fw.write(wT(1)+"@RequestMapping(value = \"/"+tableName.toLowerCase()+"\")\n");
			fw.write(wT(1)+"public String list(Map<String, Object> map){\n");
			fw.write(wT(2)+"map.put(\""+tableName.toLowerCase()+"List\", "+tableName.toLowerCase()+"Service.getAll"+WordUtils.capitalize(tableName)+"());\n");
			fw.write(wT(2)+"return \""+tableName.toLowerCase()+"/list\";\n");
			fw.write(wT(1)+"}\n\n");
			// add method
			fw.write(wT(1)+"@RequestMapping(value = \"/"+tableName.toLowerCase()+"/add\")\n");
			fw.write(wT(1)+"public String add(Map<String, Object> map){\n");
			fw.write(wT(2)+""+WordUtils.capitalize(tableName)+" "+tableName.toLowerCase()+" = new "+WordUtils.capitalize(tableName)+"();\n");
			fw.write(wT(2)+"map.put(\""+tableName.toLowerCase()+"\", "+tableName.toLowerCase()+");\n");
			fw.write(wT(2)+"return \""+tableName.toLowerCase()+"/add\";\n");
			fw.write(wT(1)+"}\n\n");
			//delete method
			fw.write(wT(1)+"@RequestMapping(\"/"+tableName.toLowerCase()+"/{id}/delete\")\n");
			fw.write(wT(1)+"public String delete(@PathVariable(\"id\") int id){\n");
			fw.write(wT(2)+""+tableName.toLowerCase()+"Service.delete(id);\n");
			fw.write(wT(2)+"return \"redirect:/"+tableName.toLowerCase()+"\";\n");
			fw.write(wT(1)+"}\n\n");
			//edit method
			fw.write(wT(1)+"@RequestMapping(\"/"+tableName.toLowerCase()+"/{id}/update\")\n");
			fw.write(wT(1)+"public String edit(@PathVariable(\"id\") int id, Map<String, Object> map){\n");
			fw.write(wT(2)+"map.put(\""+tableName.toLowerCase()+"\", "+tableName.toLowerCase()+"Service.get"+WordUtils.capitalize(tableName)+"(id));\n");
			fw.write(wT(2)+"return \""+tableName.toLowerCase()+"/update\";\n");
			fw.write(wT(1)+"}\n\n");
			// action method
			fw.write(wT(1)+"@RequestMapping(value = \"/"+tableName.toLowerCase()+".do\",method=RequestMethod.POST)\n");
			fw.write(wT(1)+"public String doAction(@ModelAttribute "+WordUtils.capitalize(tableName)+" "+tableName.toLowerCase()+", BindingResult result, @RequestParam String action, Map<String, Object> map){\n\n");
			fw.write(wT(2)+""+WordUtils.capitalize(tableName)+" "+tableName.toLowerCase()+"Result = new "+WordUtils.capitalize(tableName)+"();\n");
			fw.write(wT(2)+"switch(action.toLowerCase()){\n");
			fw.write(wT(2)+"case \"add\":\n");
			fw.write(wT(3)+""+tableName.toLowerCase()+"Service.add("+tableName.toLowerCase()+");\n");
			fw.write(wT(3)+""+tableName.toLowerCase()+"Result = "+tableName.toLowerCase()+";\n");
			fw.write(wT(3)+"break;\n");
			fw.write(wT(2)+"case \"edit\":\n");
			fw.write(wT(3)+""+tableName.toLowerCase()+"Service.edit("+tableName.toLowerCase()+");\n");
			fw.write(wT(3)+""+tableName.toLowerCase()+"Result = "+tableName.toLowerCase()+";\n");
			fw.write(wT(3)+"break;\n");
			fw.write(wT(2)+"case \"delete\":\n");
			fw.write(wT(3)+""+tableName.toLowerCase()+"Service.delete("+tableName.toLowerCase()+".get"+WordUtils.capitalize(fields[0][0])+"());\n");
			fw.write(wT(3)+""+tableName.toLowerCase()+"Result = new "+WordUtils.capitalize(tableName)+"();\n");
			fw.write(wT(3)+"break;\n");
			fw.write(wT(2)+"case \"search\":\n");
			fw.write(wT(3)+""+WordUtils.capitalize(tableName)+" searched"+WordUtils.capitalize(tableName)+" = "+tableName.toLowerCase()+"Service.get"+WordUtils.capitalize(tableName)+"("+tableName.toLowerCase()+".get"+WordUtils.capitalize(fields[0][0])+"());\n");
			fw.write(wT(3)+""+tableName.toLowerCase()+"Result = searched"+WordUtils.capitalize(tableName)+" !=null ? searched"+WordUtils.capitalize(tableName)+" : new "+WordUtils.capitalize(tableName)+"();\n");
			fw.write(wT(3)+"break;\n");
			fw.write(wT(2)+"}\n");
			fw.write(wT(2)+"map.put(\""+tableName.toLowerCase()+"\", "+tableName.toLowerCase()+"Result);\n");
			fw.write(wT(2)+"map.put(\""+tableName.toLowerCase()+"List\", "+tableName.toLowerCase()+"Service.getAll"+WordUtils.capitalize(tableName)+"());\n");
			fw.write(wT(2)+"return \"redirect:/"+tableName.toLowerCase()+"\";\n");
			fw.write("\t}\n\n");
			fw.write("}");
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

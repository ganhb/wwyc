package com.dragon.pojo.template;

import java.util.List;



/**
 * @Title Template.java
 * @Description 
 * @author ganhb
 * @date 2016-6-29
 */

public class Template {
	// 接受者的openid
	private String touser;
	private String templateId;
	private String topcolor;
	private String url;
	private List<TemplateParam> templateParamsList;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public List<TemplateParam> getTemplateParamsList() {
		return templateParamsList;
	}

	public void setTemplateParamsList(List<TemplateParam> templateParamsList) {
		this.templateParamsList = templateParamsList;
	}
	
	public String toJSON(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("{");
		buffer.append(String.format("\"touser\":\"%s\"", this.touser)).append(",");
		buffer.append(String.format("\"template_id\":\"%s\"", this.templateId)).append(",");	
		buffer.append(String.format("\"url\":\"%s\"", this.url)).append(",");
		buffer.append(String.format("\"topcolor\":\"%s\"", this.topcolor)).append(",");
		buffer.append(String.format("\"data\":{"));
		TemplateParam templateParam=null;
		for (int i = 0; i < this.templateParamsList.size(); i++) {
			templateParam=templateParamsList.get(i);
			if (i<this.templateParamsList.size()-1) {
				buffer.append(String.format("\"%s\":{\"value\":\"%s\",\"color\":\"%s\"},", templateParam.getName(),templateParam.getValue(),templateParam.getColor()));
			}else {
				buffer.append(String.format("\"%s\":{\"value\":\"%s\",\"color\":\"%s\"}",templateParam.getName(),templateParam.getValue(),templateParam.getColor()));
			}
		}
		buffer.append("}");
		buffer.append("}");
		return buffer.toString();
		
		
	}
}


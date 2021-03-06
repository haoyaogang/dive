package jb.tag;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.BaseData;
import jb.service.BasedataServiceI;

public class SelectTag extends TagSupport{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2709846727239749266L;
	private String name;
	private String dataType;
	private String value;
	private boolean mustSelect;
	private boolean multiple;
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override  
    public int doStartTag() throws JspException {  
		JspWriter out = pageContext.getOut();  		  
        try{
        	out.print("<select name=\""+name+"\" id=\""+name+"\" class=\"easyui-combobox\" data-options=\"width:140,height:29,multiple:"+multiple+",editable:false\">");
        	BasedataServiceI service = Application.getBasedataService();
        	BaseData baseData = new BaseData();
			baseData.setBasetypeCode(dataType);
        	List<BaseData> baseDataList = service.getBaseDatas(baseData);
        	if(!mustSelect)
        	out.print("<option value=\"\">    </option>");
        	for(BaseData bd : baseDataList){
        		if(multiple) {
                	out.print("<option value=\""+bd.getId()+"\">"+bd.getName()+"</option>");
        		} else {
        			if(F.empty(value)||!value.equals(bd.getId())){
                    	out.print("<option value=\""+bd.getId()+"\">"+bd.getName()+"</option>");
            		}else{
                    	out.print("<option value=\""+bd.getId()+"\" selected=\"selected\">"+bd.getName()+"</option>");
            		}
        		}
        		
        	}
        	out.print("</select>");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return super.doStartTag();
        //return TagSupport.EVAL_BODY_INCLUDE;//输出标签体内容  
        //return TagSupport.SKIP_BODY;//不输出标签体内容  
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMustSelect() {
		return mustSelect;
	}
	public void setMustSelect(boolean mustSelect) {
		this.mustSelect = mustSelect;
	}
	public boolean isMultiple() {
		return multiple;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}  
	
	
}

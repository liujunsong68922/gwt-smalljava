package com.liu.gwt.gwt_smalljava.level4_block.blockvo;

import com.liu.gwt.gwt_smalljava.common.UuidObjectManager;
import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

/**
 * ��ElementWrapperҲʵ�ֱ�����ķ��ʽӿڣ�
 * �����Ϳ���ֱ������ElementWrapper��Ϊ��������ʹ��
 * @author liujunsong
 *
 */
public class ElementWrapper implements IVarTable  {

	@Override
	public boolean setVarValue(String varname, VarValue varvalue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VarValue getVarValue(String varname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean defineVar(String varname, String vartype) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid(String varname) {
		// TODO Auto-generated method stub
		return false;
	}
//	private Element element;
//
//	public String getBlockContent() {
//		if(element !=null) {
//			return element.attributeValue("BlockContent");
//		}else {
//			return null;
//		}
//	}
//	
//	@SuppressWarnings("deprecation")
//	public void setBlockContent(String computestring) {
//		if(element != null) {
//			//�˴�������Ҫ����CDATA��ʽ��ת������
//			element.setAttributeValue("BlockContent", computestring);
//			System.out.println(element.asXML());
//		}
//		
//	}	
//	
//	@SuppressWarnings("deprecation")
//	public void setComputestring(String s1) {
//		if(element != null) {
//			//�˴�������Ҫ����CDATA��ʽ��ת������
//			element.setAttributeValue("computestring", s1);
//		}
//	}
//	
//	public String getComputestring() {
//		if(element !=null) {
//			return element.attributeValue("computestring","");
//		}else {
//			return null;
//		}
//	}
//
//	public Element getElement() {
//		return element;
//	}
//
//	public void setElement(Element element) {
//		this.element = element;
//	}
//
//	@Override
//	public boolean setVarValue(String varname, VarValue varvalue) {
//		if (varname == null || varname.length() == 0) {
//			System.out.println("setVarValue����������󣬱�����Ϊ�ջ���Ϊ���ַ���");
//			return false;
//		}
//		if (varvalue == null) {
//			System.out.println("setVarValue����������󣬱������ò���Ϊnull");
//			return false;
//		}
//		
//		//���ݱ������������������ڵ�Element�ڵ�
//		Element varElement = this._getVarElement(varname);
//
//		if (varElement == null) {
//			System.out.println("setVarValue����ִ������û���ҵ��������壺" + varname);
//			return false;
//		} else {
//			//�޸�domԪ�ص�����
//			String datatype = varElement.attributeValue("datatype");
//			if(datatype.equals("int") || datatype.equals("long")
//					|| datatype.equals("double") || datatype.equals("float")) {
//				varElement.setText(""+varvalue.getVarsvalue());
//			}else if(datatype.equals("boolean")) {
//				varElement.setText(""+varvalue.getVarsvalue());
//			}else if(datatype.equals("String")) {
//				varElement.setText(varvalue.getVarsvalue());
//			}else {
//				//����������
//				if(varvalue.getVarsvalue()==null) {
//					Object obj = UuidObjectManager.getObject(varvalue.getVarsvalue());
//					if(obj ==null) {
//						varElement.setText("");
//					}else {
//						//����Ϊ�գ����Զ��ٱ����ʱ������һ��uuid
//						//String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//						//д�����ӳ���
//						//UuidObjectManager.setObject(uuid, varvalue.getVarobjvalue());
//						varElement.setText(varvalue.getVarsvalue());
//					}
//				}else {
//					//д�����Object����ֵ��uuid
//					varElement.setText(varvalue.getVarsvalue());
//				}
//			}
//
//			return true;
//		}
//	}
//
//	@Override
//	/**
//	 * ��element�����ȡһ���������������������ֻ���ģ����޸�element
//	 */
//	public VarValue getVarValue(String varname) {
//		//step1:���Ȼ�ȡ��һ����Ч�ı�����
//		Element varmap = this._getFirstValidMap();
//		if(varmap == null) {
//			System.out.println("��ERROR�����ұ�����ʧ��.");
//			return null;
//		}
//		
//		//step2.�ӱ������������������varname
//		//xpath�ñ�����������
//		Element pnode = this.element;
//		while (pnode != null) {
//			String xpath=varname;
//			Element dataelement = (Element) varmap.selectSingleNode(xpath);
//			
//			//if (varmap.containsKey(varname)) {
//			if(dataelement !=null) {
//				// ���varname������Ϊһ�����ر�������ӱ���HashMap�л�ȡ��ֵ
//				VarValue varvalue = this._fromVarElement(dataelement);
//				return varvalue;
//			}
//			// ��������ƶ������洢�ڵ������д���
//			pnode = pnode.getParent();
//		}
//		// ѭ�����ң�ָ���Ҳ���varname,�򷵻�null�����Ҳ����������
//		return null;
//
//	}
//
//	@Override
//	public boolean defineVar(String varname, String vartype) {
//		
//		//check blockelement
//		if(this.element == null) {
//			log("��ERROR��Program Logic Error. blockelement is null");
//			return false;
//		}
//
//		//step2.�ҵ���һ����Ч�ı�����ڵ�
//		Element varmaps = this._getFirstValidMap();
//		if(varmaps == null) {
//			log("��ERROR�����Զ������ʱ�����ұ�����ʧ��");
//			return false;
//		}
//		//�ж��Ƿ����ظ��ı�������
//		String xpath = varname;
//		Element varElement = (Element) varmaps.selectObject(xpath);
//		if (varElement !=null) {
//			log("��ERROR������������󣬴˴����ظ�����ı�����:" + varname);
//			return false;
//		}
//		if (vartype == null) {
//			log("��ERROR������������󣬱�������ʱ����Ϊ��." + varname);
//			return false;
//		}
//		
//		VarValue varvalue = new VarValue();
//		varvalue.setVarname(varname);
//		varvalue.setVartype(vartype);
//		if(vartype.equals("int") || vartype.equals("long")
//				|| vartype.equals("float") || vartype.equals("double")) {
//			varvalue.setVarsvalue("0");
//		}else if(vartype.equals("boolean")) {
//			varvalue.setVarsvalue("false");
//		}else if(vartype.equals("String")) {
//			varvalue.setVarsvalue("");
//		}else {
//			varvalue.setVarsvalue("");
//		}
//		//varvalue.setVarsvalue(null);
//		//varvalue.setVarbvalue(false);
//
//		Element newelement = varmaps.addElement(varname);
//		//����newelement ��datatype,Text����
//		String strdatatype = varvalue.getVartype();
//		newelement.addAttribute("datatype", strdatatype);
//		if(strdatatype.equals("int") || strdatatype.equals("long")
//				||strdatatype.equals("double") || strdatatype.equals("float")) {
//			newelement.setText("0");
//		}else if(strdatatype.equals("String")) {
//			newelement.setText("");
//		}else if(strdatatype.equals("boolean")) {
//			newelement.setText("false");
//		}
////		this.myvarmap.put(varname, varvalue);
//		return true;
//	}
//	
//	private Element _getLocalvarmap(Element root) {
//		// �˴���Ҫ����Element�������õ�Name�����������ж�
//		// ���Name���ض���Χ�ڣ��򱾵ص�varmap�ڵ㽫���ɷ���(���۴������)
//		String s1 = root.getName();
//		if (s1 == null) {
//			// XML�У�NAME��һ����������ԣ��߼��ϲ�Ӧ���ߵ�������
//			log("��ERROR���߼������е�XML�ڵ㶼Ӧ��ӵ��Name����");
//			return null;
//		}
//		if (s1 != null) {
//			// �����г������в����õ�blocktype
//			if (s1.equals(SmallJavaBlockConst.Expression) 
//					|| s1.equals(SmallJavaBlockConst.EmptyBlock)
//					|| s1.equals(SmallJavaBlockConst.WhileNode)
//					|| s1.equals(SmallJavaBlockConst.WhileCondition)
//					|| s1.equals(SmallJavaBlockConst.IfConditionBlock)
//					|| s1.equals(SmallJavaBlockConst.ForBeginNode)
//					|| s1.equals("ForConditionNode") 
//					|| s1.equals("ForLoopNode")) {
//				// ���ʽ�Լ�����������,ʹ���ϼ��ڵ�ı�����
//				return null;
//			}
//		}
//		//ʹ��VARTABLE�ؼ��ʽ��м������������,�򷵻ش˽ڵ�
//		//�������ڵ㲻���ڣ��򴴽��˽ڵ�
//		String xpath="VARTABLE";
//		Element xpathelement = (Element) root.selectObject(xpath);
//		if(xpathelement != null) {
//			return xpathelement;
//		}else {
//			//����������ڵ�
//			xpathelement = root.addElement("VARTABLE");
//			return xpathelement;
//		}
//	}
//	
//	// �ӱ����ڵ�,ѭ�����ϲ��ҵ�һ����Ч�ı��������
//	// ֱ�����ҵ��ϼ��ڵ�Ϊnull�Ľڵ�Ϊֹ
//	// �������map�����б���ֵ��д��
//	private Element _getFirstValidMap() {
//		Element retmap = null;
//		//����ָ��
//		Element blockpoint = this.element;
//		// ѭ����������ǰretmap == null
//		// ���ҵ�ǰ�ڵ�ָ�벻Ϊnullָ��
//		while (retmap == null && blockpoint != null) {
//			retmap = _getLocalvarmap(blockpoint);
//			//ָ���ϼ��ڵ�
//			blockpoint = blockpoint.getParent();
//		}
//		return retmap;
//	}
//	
//	private void log(String s1) {
//		System.out.println(s1);
//	}
//	
//	/**
//	 * MEMO:��element����ת����VarValue����
//	 * @param element
//	 * @return
//	 * @throws Exception 
//	 */
//	private VarValue _fromVarElement(Element element) {
//		//step1:������
//		if(element == null) {
//			System.out.println("��ERROR��Program Logic Error,element cannot be null.");
//			return null;
//			//throw new Exception("Element is null.");
//		}
//		
//		VarValue varvalue = new VarValue();
//		varvalue.setVarname(element.getName());
//		varvalue.setVartype(element.attributeValue("datatype"));
//		varvalue.setVarsvalue(element.getText());
//		if(varvalue.getVartype().equals("int") 
//				|| varvalue.getVartype().equals("long")
//				|| varvalue.getVartype().equals("double")
//				|| varvalue.getVartype().equals("float")
//				|| varvalue.getVartype().equals("boolean")
//				|| varvalue.getVartype().equals("String")) {
//			//��Щ�����Ƕ�������,ʹ��svalue����
//		}else {
//			//�Ӷ���ӳ����ж�ȡ����
//			if(element.getText().equals("")) {
//				//varvalue.setVarobjvalue(null);
//				varvalue.setVarsvalue("");
//			}else {
//				//��ӳ����ж�ȡ����д��ȥ
//				//varvalue.setVarobjvalue(UuidObjectManager.getObject(element.getText()));
//				varvalue.setVarsvalue(element.getText());
//			}
//		}
//		
//		return varvalue;
//	}
//	
//	/**
//	 * ���ݱ�����������һ���Ϸ��ı����ڵ�
//	 * @param varname
//	 * @return
//	 */
//	private Element _getVarElement(String varname) {
//		//step1:���Ȼ�ȡ��һ����Ч�ı�����
//		Element varmap = this._getFirstValidMap();
//		if(varmap == null) {
//			System.out.println("��ERROR�����ұ�����ʧ��.");
//			return null;
//		}
//		
//		//step2.�ӱ������������������varname
//		Element pnode = this.element;
//		while (pnode != null) {
//			//xpath�ñ�����������
//			String xpath=varname;
//			Element dataelement = (Element) varmap.selectSingleNode(xpath);
//			
//			if(dataelement !=null) {
//				return dataelement;
//			}
//			// ��������ƶ������洢�ڵ������д���
//			pnode = pnode.getParent();
//		}
//		// ѭ�����ң�ָ���Ҳ���varname,�򷵻�null�����Ҳ����������
//		return null;
//
//	}
//
//	public String getBlocktype() {
//		if(element !=null) {
//			return element.getName();
//		}else {
//			return null;
//		}
//	}
//
//	@Override
//	public boolean isValid(String varname) {
//		Element e1 = this._getVarElement(varname);
//		if(e1 == null) {
//			return false;
//		}else {
//			return true;
//		}
//	}
}

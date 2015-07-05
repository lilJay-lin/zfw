package com.mimi.zfw.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class BaseDaoPlugin extends PluginAdapter {
    private FullyQualifiedJavaType baseExample;
    private FullyQualifiedJavaType baseModel;

    public BaseDaoPlugin() {
	super();
	baseExample = new FullyQualifiedJavaType(
		"com.mimi.zfw.plugin.BaseExample");
	baseModel = new FullyQualifiedJavaType("com.mimi.zfw.plugin.IBaseModel<String>"); //$NON-NLS-1$
    }

    @Override
    public boolean validate(List<String> warnings) {
	return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze,
	    TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
	String str = "com.mimi.zfw.plugin.IBaseDao<"
		+ introspectedTable.getBaseRecordType() + ","
		+ introspectedTable.getExampleType() + ",String>";
	FullyQualifiedJavaType baseDao = new FullyQualifiedJavaType(str);
	interfaze.addImportedType(baseDao);
	interfaze.addSuperInterface(baseDao);
	return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
	    IntrospectedTable instrospectedTable) {
	topLevelClass.setSuperClass(baseExample);
	topLevelClass.addImportedType(baseExample);
	return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
	    IntrospectedTable introspectedTable) {
	makeSerializable(topLevelClass, introspectedTable);
	return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
	    IntrospectedTable introspectedTable) {
	makeSerializable(topLevelClass, introspectedTable);
	return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(
	    TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
	makeSerializable(topLevelClass, introspectedTable);
	return true;
    }

    protected void makeSerializable(TopLevelClass topLevelClass,
	    IntrospectedTable introspectedTable) {
	topLevelClass.addImportedType(baseModel);
	topLevelClass.addSuperInterface(baseModel);
	Field field = new Field();
	field.setFinal(true);
	field.setInitializationString("1L");
	field.setName("serialVersionUID"); 
	field.setStatic(true);
	field.setType(new FullyQualifiedJavaType("long"));
	field.setVisibility(JavaVisibility.PRIVATE);
	context.getCommentGenerator().addFieldComment(field, introspectedTable);
	topLevelClass.addField(field);
    }
}

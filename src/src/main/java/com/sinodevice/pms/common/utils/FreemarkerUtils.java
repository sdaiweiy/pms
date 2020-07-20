package com.sinodevice.pms.common.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

/**
 * <p>
 * Freemarker 工具类
 * </p>
 *
 * @author jobob
 * @Date 2018-10-23
 */
public class FreemarkerUtils {

    /**
     * 字符串模板名称
     */
    private static final String STRING_TEMPLATE = "StringTemplate";


    /**
     * <p>
     * 执行字符串模板数据模型填充
     * </p>
     * <p>
     * 模板："PT&<#assign name=pinyin4j.converterToFirstSpell(\"${contract.operator}\")>${name}2018"
     * 标签 assign 引用 Pinyin4jUtils 工具类调用方法 converterToFirstSpell 执行，自定义结果 name 打印内容
     * </p>
     *
     * @param context 字符串模板
     * @param model   数据模型
     * @return
     */
    public static String process(String context, Object model) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        configuration.setDefaultEncoding("UTF-8");
        StringTemplateLoader loader = new StringTemplateLoader();
        loader.putTemplate(STRING_TEMPLATE, context);
        configuration.setTemplateLoader(loader);
        Template template = configuration.getTemplate(STRING_TEMPLATE, "UTF-8");
        return processTemplateIntoString(template, model);
    }

    /**
     * <p>
     * 执行模板数据模型填充
     * </p>
     *
     * @param name  模板名称
     * @param model 数据模型
     * @return
     */
    public static String emailTpl(String name, Object model) throws IOException, TemplateException {
        return process("/templates", String.format("/email/%s.ftl", name), model);
    }

    /**
     * <p>
     * 执行模板数据模型填充
     * </p>
     *
     * @param location     模板位置
     * @param templatePath 模板路径
     * @param model        数据模型
     * @return
     */
    public static String process(String location, String templatePath, Object model) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(FreemarkerUtils.class, location);
        Template template = configuration.getTemplate(templatePath);
        return processTemplateIntoString(template, model);
    }

    public static String processTemplateIntoString(Template template, Object model)
            throws IOException, TemplateException {
        StringWriter result = new StringWriter();
        template.process(model, result);
        return result.toString();
    }
}

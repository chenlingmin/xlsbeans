package net.java.amateras.xlsbeans.xml;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Loads annotation informations as {@link net.java.amateras.xlsbeans.xml.XMLInfo}
 * from the XML file.
 *
 * @author Naoki Takezoe
 */
public class XMLLoader {

    public static XMLInfo load(InputStream in) throws XMLException {
        try {
            XMLInfo info = new XMLInfo();

            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbfactory.newDocumentBuilder();
            Document doc = builder.parse(in);

            Element root = doc.getDocumentElement();
            NodeList classes = root.getElementsByTagName("class");
            for (int i = 0; i < classes.getLength(); i++) {
                Element clazz = (Element) classes.item(i);
                String className = clazz.getAttribute("name");
                if (className == null || className.length() == 0) {
                    throw new XMLException("'name' attribute is required.");
                }
                AnnotationInfo[] classAnns =
                        getAnnotationInfo(clazz.getElementsByTagName("annotation"));

                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassName(className);
                for (AnnotationInfo annInfo : classAnns) {
                    classInfo.addAnnotationInfo(annInfo);
                }

                NodeList methods = clazz.getElementsByTagName("method");
                for (int j = 0; j < methods.getLength(); j++) {
                    Element method = (Element) methods.item(j);
                    String methodName = method.getAttribute("name");
                    if (methodName == null || methodName.length() == 0) {
                        throw new XMLException("'name' attribute is required.");
                    }
                    AnnotationInfo[] methodAnns =
                            getAnnotationInfo(method.getElementsByTagName("annotation"));

                    MethodInfo methodInfo = new MethodInfo();
                    methodInfo.setMethodName(methodName);
                    for (AnnotationInfo annInfo : methodAnns) {
                        methodInfo.addAnnotationInfo(annInfo);
                    }

                    classInfo.addMethodInfo(methodInfo);
                }

                NodeList fields = clazz.getElementsByTagName("field");
                for (int j = 0; j < fields.getLength(); j++) {
                    Element field = (Element) fields.item(j);
                    String fieldName = field.getAttribute("name");
                    if (fieldName == null || fieldName.length() == 0) {
                        throw new XMLException("'name' attribute is required.");
                    }
                    AnnotationInfo[] fieldAnns =
                            getAnnotationInfo(field.getElementsByTagName("annotation"));

                    FieldInfo fieldInfo = new FieldInfo();
                    fieldInfo.setFieldName(fieldName);
                    for (AnnotationInfo annInfo : fieldAnns) {
                        fieldInfo.addAnnotationInfo(annInfo);
                    }

                    classInfo.addFieldInfo(fieldInfo);
                }

                info.addClassInfo(classInfo);
            }

            return info;

        } catch (XMLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new XMLException(ex);
        }
    }

    private static AnnotationInfo[] getAnnotationInfo(NodeList annotations) throws XMLException {
        List<AnnotationInfo> list = new ArrayList<AnnotationInfo>();
        for (int i = 0; i < annotations.getLength(); i++) {
            Element annotation = (Element) annotations.item(i);
            String className = annotation.getAttribute("name");
            if (className == null || className.length() == 0) {
                throw new XMLException("'name' attribute is required.");
            }

            AnnotationInfo info = new AnnotationInfo();
            info.setAnnotationClass(className);

            Map<String, String> attrs = getAttributes(annotation);
            for (Map.Entry<String, String> entry : attrs.entrySet()) {
                info.addAnnotationAttribute(entry.getKey(), entry.getValue());
            }

            list.add(info);
        }
        return list.toArray(new AnnotationInfo[list.size()]);
    }

    private static Map<String, String> getAttributes(Element annotations) throws XMLException {
        Map<String, String> map = new HashMap<String, String>();
        NodeList attrs = annotations.getElementsByTagName("attribute");
        for (int i = 0; i < attrs.getLength(); i++) {
            Element attr = (Element) attrs.item(i);
            String name = attr.getAttribute("name");
            String value = getChildText(attr);

            if (name == null || name.length() == 0) {
                throw new XMLException("'name' attribute is required.");
            }

            map.put(name, value);
        }
        return map;
    }

    private static String getChildText(Element element) {
        StringBuffer sb = new StringBuffer();
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Text) {
                sb.append(node.getNodeValue());
            }
        }
        return sb.toString().trim();
    }

}

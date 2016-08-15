package com.clt.framework.utils;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * bean 操作
 * 
 * @ClassName: BeanUtils
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: liu jinbang
 * @date: 2015年1月13日 下午6:37:22
 *
 */
public class BeanUtils {

	/**
	 * 复制对象属性
	 * 
	 * @param from
	 * @param clazz
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object copyProperties(Object from, Class clazz)
			throws Exception {

		// 拷贝父类中的属性值
		Object to = Class.forName(clazz.getName()).newInstance();
		try {
			to = copyFatherProperties(from, clazz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Method[] fromMethods = from.getClass().getDeclaredMethods();
		Method[] toMethods = to.getClass().getDeclaredMethods();
		Method[] toMethods_f = to.getClass().getSuperclass().getMethods();
		Method fromMethod = null, toMethod = null;
		String fromMethodName = null, toMethodName = null;
		for (int i = 0; i < fromMethods.length; i++) {
			fromMethod = fromMethods[i];
			fromMethodName = fromMethod.getName();
			if (!fromMethodName.contains("get")) {
				continue;
			}

			toMethodName = "set" + fromMethodName.substring(3);
			toMethod = findMethodByName(toMethods, toMethodName);
			if (toMethod == null) {
				toMethod = findMethodByName(toMethods_f, toMethodName);
				if (toMethod == null) {
					continue;
				}
			}
			Object value = fromMethod.invoke(from, new Object[0]);
			if (value == null) {
				continue;
			}
			// 集合类判空处理
			if (value instanceof Collection) {
				Collection newValue = (Collection) value;
				if (newValue.size() <= 0)
					continue;
			}
			Type[] type = toMethod.getGenericParameterTypes();
			String types = type[0].toString();
			if (types.equals("class java.lang.Integer")) {
				toMethod.invoke(to, Double.valueOf(value.toString()).intValue());
			} else if (types.equals("class java.lang.Double")) {
				toMethod.invoke(to, Double.valueOf(value.toString()));
			} else if(types.equals("class java.lang.String")){
				toMethod.invoke(to, value.toString());
			}else{
				toMethod.invoke(to, new Object[] { value });
			}
		}
		return to;
	}

	/**
	 * 赋值父类中的属性值
	 * 
	 * @param from
	 * @param clazz 返回参数类型 暂不支持list
	 *
	 * @return
	 * @throws Exception
	 * @author: liu jinbang
	 * @date: 2015年1月17日 下午3:24:59
	 */
	private static Object copyFatherProperties(Object from, Class clazz)
			throws Exception {


		Object to = Class.forName(clazz.getName()).newInstance();

		if (from.getClass() != null && from.getClass().getSuperclass() != null) {

			Method[] fromMethods = from.getClass().getSuperclass()
					.getDeclaredMethods();

			Method[] toMethods = to.getClass().getSuperclass().getDeclaredMethods();
			Method fromMethod = null, toMethod = null;
			String fromMethodName = null, toMethodName = null;
			for (int i = 0; i < fromMethods.length; i++) {
				fromMethod = fromMethods[i];
				fromMethodName = fromMethod.getName();
				if (!fromMethodName.contains("get")) {
					continue;
				}

				toMethodName = "set" + fromMethodName.substring(3);
				toMethod = findMethodByName(toMethods, toMethodName);
				if (toMethod == null) {
					continue;
				}
				Object value = fromMethod.invoke(from, new Object[0]);
				if (value == null) {
					continue;
				}
				// 集合类判空处理
				if (value instanceof Collection) {
					Collection newValue = (Collection) value;
					if (newValue.size() <= 0) {
						continue;
					}
				}
				Type[] type = toMethod.getGenericParameterTypes();
				String types = type[0].toString();
				if (types.equals("class java.lang.Integer")) {
					toMethod.invoke(to, Double.valueOf(value.toString())
							.intValue());
				} else if (types.equals("class java.lang.Double")) {
					toMethod.invoke(to, Double.valueOf(value.toString()));
				} else {
					toMethod.invoke(to, new Object[] { value });
				}
			}
		}
		return to;
	}

	/**
	 * 从方法数组中获取指定名称的方法
	 * 
	 * @param methods
	 * @param name
	 * @return
	 */
	public static Method findMethodByName(Method[] methods, String name) {
		for (int j = 0; j < methods.length; j++) {
			if (methods[j].getName().equals(name))
				return methods[j];
		}
		return null;
	}

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 * 
	 */
	public static Map<String, Object> convertBean2Map(Object bean)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		Class type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}

	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InstantiationException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertMap2Bean(Class type, Map map)
			throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，避免当一个属性赋值失败影响其他属性赋值。
				Object value = map.get(propertyName);

				Object[] args = new Object[1];
				args[0] = value;
				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

}

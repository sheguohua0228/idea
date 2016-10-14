package com.leyes.app.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class SpringContextUtils implements BeanFactoryAware {
	private BeanFactory factory=null;
	private static SpringContextUtils instance=null;
	
	@Override
	public void setBeanFactory(BeanFactory factory) throws BeansException {
		instance=this;
		this.factory=factory;
		
	}
	public static BeanFactory getBeanFacotory(){
		if(instance==null)
			throw new RuntimeException("系统还未初始化，请注意检查代码！");
		
		return instance.factory;
	}
	 /**
	  * 根据名称获取Bean
	  * @param name
	  * @return
	  */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		return (T) getBeanFacotory().getBean(name);
	}
	
	/**
	 * 根据类获取Bean
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getBeanFacotory().getBean(clazz);
	}
	 
    /**
     * 获取指定包下面实现此接口的所有类实例
     * @param clazz
     * @param packagePath
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getAllClassInstanceByInterface(Class<?> clazz,String packagePath) {
		List<T> returnClassList = null;
		try {
			if (clazz.isInterface()) {
				// 获取当前包下以及子包下所以的类
				List<T> allClass = (List<T>) getClasses(packagePath);
				if (allClass != null) {
					returnClassList = new ArrayList<T>();
					for (T classes : allClass) {
						// 判断是否是同一个接口
						if (clazz.isAssignableFrom((Class<?>) classes)) {
							// 本身不加入进去
							if (!clazz.equals(classes)) { 
								String cls=classes.toString().split(" ")[1];
								T t=(T) Class.forName(cls).newInstance(); 
								returnClassList.add(t);
		                          
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ddada:"+e.getMessage());
		}
		return returnClassList;
	}
	
	/**
	 *  获取该接口所在包下面实现此接口的所有类实例
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getAllInstanceClassByInterface(Class<T> clazz){
		List<T> returnClassList=null;
		if(clazz.isInterface()){
			// 获取当前的包名
            String packageName = clazz.getPackage().getName();
            try {
            	// 获取当前包下以及子包下所以的类
            	List<T> allClass =  (List<T>) getClasses(packageName);
            	if(allClass != null) {
                    returnClassList = new ArrayList<T>();
                    for(T classes : allClass) {
                        // 判断是否是同一个接口
                        if(clazz.isAssignableFrom((Class<?>) classes)) {
                            // 本身不加入进去
                            if(!clazz.equals(classes)) {
                            	String cls=classes.toString().split(" ")[1];
								T t=(T) Class.forName(cls).newInstance(); 
								returnClassList.add(t);
                            }
                        }
                    }
                }
            }
            catch(Exception e){
            	
            }
		}
		return returnClassList;
	}
	
	/**
	 * 从一个包中查找出所有的类，在jar包中不能查找
	 * @param packageName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> getClasses(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		// 用'/'代替'.'路径
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes;
	}
	/**
	 * 
	 * @param directory
	 * @param packageName
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "rawtypes" })
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file,
						packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				// 去掉'.class'
				classes.add(Class.forName(packageName
						+ '.'
						+ file.getName().substring(0,
								file.getName().length() - 6)));

			}
		}
		return classes;
	}
}

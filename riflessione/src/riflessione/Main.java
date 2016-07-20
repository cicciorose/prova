package riflessione;


import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import sun.tools.jar.resources.jar;


public class Main {
	/**
	 * Returns list of all the classes in the jar
	 * 
	 * @param pathToJar Path of the jar 
	 * 
	 * @return List&ltClass&lt?&gt&gt list of all the classes in the jar   
	 * 
	 * @author Francesco Rose
	 * 
	 * @exception ClassNotFoundException
	 * 
	 * @exception IOException
	 * 
	 * @since 1.0
	 * 
	 * @see java.lang.reflect
	 * 
	 *  
	 * */
	public static List<Class<?>> loadClass(String pathToJar)throws IOException, ClassNotFoundException{
		
		List<Class<?>> list=new ArrayList<Class<?>>();
		
		JarFile jarFile = new JarFile(pathToJar);
		Enumeration<JarEntry> e = jarFile.entries();
		URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);
		while (e.hasMoreElements()) {
		    JarEntry je = e.nextElement();
		    if(je.isDirectory() || !je.getName().endsWith(".class")){
		        continue;
		    }
		    // -6 because of .class
		    String className = je.getName().substring(0,je.getName().length()-6);
		    className = className.replace('/', '.');
		    Class<?> c = cl.loadClass(className);
		    list.add(c);
		}
		jarFile.close();
		return list;
	}
	/**
	 * Rreturns the class named <code>name</code> if this is possible
	 * 
	 * @param pathToJar Path of the jar 
	 * 
	 * @param name Name of class
	 * 
	 * @return Class&lt?&gt class named name in the jar 
	 * 
	 * @author Francesco Rose
	 * 
	 * @exception ClassNotFoundException
	 * 
	 * @exception IOException
	 * 
	 * @since 1.0
	 * 
	 * @see java.lang.reflect
	 *   
	 * */
	public static Class<?> loadClassForName(String pathToJar,String name) throws IOException, ClassNotFoundException{
		
		Class<?> classe=null;
		JarFile jarFile = new JarFile(pathToJar);
		URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);
		classe=cl.loadClass(name);		
		jarFile.close();
		return classe;
	}
	/**
	 * EXAMPLE load all class and class for name
	 * 
	 * @return void ,print two method
	 * 
	 * 
	 * */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		List<Class<?>>classes=Main.loadClass("file/test.jar");		
		Class<?> nuova=classes.get(0);		
		if(nuova.getSuperclass()==ProvaAstratta.class){
			System.out.println("yep");
		}		
		try {			
			ProvaAstratta p=(ProvaAstratta)nuova.newInstance();			
			System.out.println(p.getNome()+" "+p.getTipo());
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		//STAMPA
		//yep
		//ciccio concreto
		Class<?> daNome=Main.loadClassForName("file/test.jar", "riflessione.ProvaConcreta");
		try {
			ProvaAstratta p=(ProvaAstratta) daNome.newInstance();
			
			System.err.println(p.getNome()+" "+p.getTipo());
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}	
		//STAMPA
		//ciccio concreto
	}

}

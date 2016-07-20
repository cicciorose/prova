package riflessione;


import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class Main {
	
	public static List<Class<?>> loadClass(String pathToJar)throws IOException, ClassNotFoundException{
/*		File dir = new File("file");//		
//		for (File file : dir.listFiles()){
//			System.out.println(file.getPath());
//			jarLoader.addFile(file.getPath());			
		}String pathToJar="file/test.jar";
*/
		
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

	}

}

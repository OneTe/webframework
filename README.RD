该web-framework支持的功能（来自黄勇的从零开始写javaWeb框架一书）
1，加载配置项ConfigHelper类，获取onete.properties配置文件的配置项
2，类加载器，由ClassUtil类提供，加载基础包名下的所有类
3，提供注解，比如@Controller等(Controller注解来定义Controller类，通过Inject注解来实现依赖注入，通过Action注解来
   定义Action方法)

4，ClassHelper类，提供获取所有Bean类的方法，比如：
        /*
          获取应用包名下的所有Controller类
        */
       public static Set<Class<?>> getControllerClassSet(){
           Set<Class<?>> classSet = new HashSet<Class<?>>();
           for(Class<?> cls : CLASS_SET){
               if(cls.isAnnotationPresent(Controller.class)){
                   classSet.add(cls);
               }
           }
           return classSet;
       }
5，实现Bean容器，由BeanHelper类提供，Map结构，传入一个Bean类，就能获取该类的实例
   Map<Class<?>,Object>
6，实现依赖注入功能，由IocHelper类提供，因为从BeanHelper类获取Bean Map，所有的对象都是单例的
7，加载Controller类，处理逻辑如下：
   通过ClassHelper，我们可以获取所有定义了Controller注解的类，可以通过反射获取该类中所有带Action注解
 的方法，获取Action注解中的请求表达式，进而获取请求方法与请求路径，封装一个请求对象（Request）与处理对象（Handler）
 最后将Request与Handler建立一个映射关系，放入一个Action Map中，并提供一个可根据请求方法与请求路径获取处理对象的方法。
8，使用HelperLoader集中加载框架类
9,核心类DispatcherServlet处理所有请求,继承HttpServlet，初始化Helper类，处理Action方法，并返回结果
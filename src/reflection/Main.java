package reflection;

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        User user = new User("Ivan", "Petrov", 26);

        Class<? extends User> mirror = user.getClass();//user.getClass() - in to variable
        Field[] declaredFields = mirror.getDeclaredFields();//mirror.getDeclaredFields() - in to array
        for (Field field : declaredFields) {//iterate array
            field.setAccessible(true);//access to private fields
            System.out.println(field.getType() + " " + field.getName() + " " + field.get(user));// add IllegalAccessException

            if (field.getType().toString().equals("int")) {//
                field.set(user, 55);
            }
        }
        System.out.println(user);//user after iterate and set age
        //Part two

        Class<User> userClass = User.class;
        User userDefaultConstructor = userClass.getDeclaredConstructor().newInstance();// add NoSuchMethodException/InstantiationException
        System.out.println(userDefaultConstructor);//userDefaultConstructor

        User userAllArgsConstructor = userClass.getDeclaredConstructor(String.class, String.class, int.class)
                .newInstance("Kolya", "Vasiliev", 49);// add NoSuchMethodException/InstantiationException
        System.out.println(userAllArgsConstructor);//userAllArgsConstructor


        Method[] methods = userClass.getDeclaredMethods();//get Methods
        for (Method method : methods) {
            //System.out.println(method.getName() + " " + method.getReturnType() + " " + method.getParameterCount());


            Constructor<?>[] declaredConstructors = userClass.getDeclaredConstructors();
            for (Constructor<?> constructor : declaredConstructors) {
                Parameter[] parameters = constructor.getParameters();//get parameters array
                for (Parameter parameter : parameters) {//iter parameters
                    // System.out.println(parameter.getName());
                }
            }
        }
        User user1 = new User("", "Makarova", -33);
        handlerValidateAgeAnnotation(user1);
        handlerValidateNameAnnotation(user1);
        System.out.println(user1);
    }

    public static void handlerValidateAgeAnnotation(User user) throws IllegalAccessException {
        Class<? extends User> mirrorUser = user.getClass();
        Field[] fields = mirrorUser.getDeclaredFields();// get all fields
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ValidateAge.class)) {
                System.out.println("+++++++++++" + field.getName());//check field where annotation is
                if ((int) field.get(user) < 0) {
                    System.out.println("Age is less then 0");
                }
            }
        }

    }

    public static void handlerValidateNameAnnotation(User user) throws IllegalAccessException {
        Class<? extends User> aClass = user.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ValidateName.class)) {
                System.out.println("***********" + field.getName());
                if (field.getName().equals("name")) {
                    System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
                }
                if (((String)field.get(user)).isEmpty()) {
                    System.out.println("Name is empty");
                }
            }
        }
    }
}



//                    if ((String) field.get(user). ){
//                        System.out.println("Name is empty");
//                    }
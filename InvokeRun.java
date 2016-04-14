package invoke;

import java.lang.reflect.Field;

public class InvokeRun {

	public static void main(String[] args) throws Exception {

		String line = "-------------------------------------------";

		System.out.println(line);

		Object obj = new CheckInOutModel();
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();

		for (Field field : fields) {
			String f = field.getName();
			System.out.println(String.format("<if test=\"%s != null and %s != ''\"> AND %s = #{%s} </if>", f, f, f, f));
		}

		System.out.println(line);
		for (Field field : fields) {
			System.out.print("\"" + field.getName() + "\",");
		}
		System.out.println();

		System.out.println(line);
		for (Field field : fields) {
			System.out.println(field.getName() + ",");
		}

		System.out.println(line);
		for (Field field : fields) {
			System.out.println("#{obj." + field.getName() + "},");
		}

		System.out.println(line);
		for (Field field : fields) {
			System.out.println("#{" + field.getName() + "},");
		}

	}

}

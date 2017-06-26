/**
 * /// 32位字符串唯一 ID ///
 * @author Colin <br/>
 * 格式: ID{14位时间戳}{13位随机字符}{三位服务器唯一标识} <br/>
 * {服务器唯一标识} {@link IDUtil#serverSerializableNo}
 */
public final class IDUtil {

	/**
	 * /// 唯一 ID 尾部的三位标识, 对应服务器编号, 使用 36 进制编码 /// <br/>
	 * 000 001 002 ... 00F 00G 00H ... 00X 00Y 00Z 010 011 012 ... <br/>
	 * Long.parseLong("010", 36) = 36 (36进制中的010等于36) <br/>
	 * 最大编号为 36*36*36=46656 (最大支持46656台服务器生成唯一编号)
	 */
	private static String serverSerializableNo = "--";

	/**
	 * @param serverSerializableNo {@link IDUtil#serverSerializableNo}
	 */
	public IDUtil(String serverSerializableNo) {
		IDUtil.serverSerializableNo = serverSerializableNo;
	}

	/**
	 * /// 生成唯一32位字符串编号 /// <br/>
	 * 格式: ID{14位时间戳}{13位随机字符}{三位服务器唯一标识} <br/>
	 * {服务器唯一标识} {@link IDUtil#serverSerializableNo}
	 */
	public static final synchronized String getUniqueID() {
		long lTimeMillis = System.currentTimeMillis();
		return String.format("ID%014d%s%s", lTimeMillis, getRandomString(13), serverSerializableNo);
	}






























	private static final String[] CODES = {
			"A",
			"B",
			"C",
			"D",
			"E",
			"F",
			"G",
			"H",
			"I",
			"J",
			"K",
			"L",
			"M",
			"N",
			"O",
			"P",
			"Q",
			"R",
			"S",
			"T",
			"U",
			"V",
			"W",
			"X",
			"Y",
			"Z",
			"0",
			"1",
			"2",
			"3",
			"4",
			"5",
			"6",
			"7",
			"8",
			"9" };
	private static final int MAX = CODES.length;

	private static String getRandomString(int len) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < len; ++i)
			buf.append(CODES[(int) (Math.random() * MAX)]);
		return buf.toString();
	}











}

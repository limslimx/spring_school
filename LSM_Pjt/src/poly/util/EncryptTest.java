package poly.util;

public class EncryptTest {

	public static void main(String[] args) throws Exception {
		
		System.out.println("------------------");
		System.out.println("�ؽ� ��ȣȭ �˰���");
		
		String str="��ȣȭ�� ���ڿ�";
		
		String hashEnc=EncryptUtil.encHashSHA256(str);
		
		System.out.println("hashEnc: "+hashEnc);
		System.out.println("------------------");
		System.out.println("AES128-CBC ��ȣȭ, ��ȣȭ �˰���");
		
		String enc=EncryptUtil.encAES128CBC(str);
		
		System.out.println("enc: "+enc);
		
		String dec=EncryptUtil.decAES128CBC(enc);
		
		System.out.println("dec: "+dec);
		System.out.println("------------------");
	}
}

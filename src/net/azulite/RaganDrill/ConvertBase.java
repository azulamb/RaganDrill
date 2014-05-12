package net.azulite.RaganDrill;

public class ConvertBase
{
	protected String docroot;
	protected String delimiter;
	protected String dir_android;
	protected String dir_ios;

	public ConvertBase()
	{
		docroot = ".";
		delimiter = "\\";
		dir_android = "res";
		dir_ios = "ios_res";
	}

	/**
	 * �o�̓f�B���N�g���B
	 * �o�̓f�B���N�g���ȉ���res/��ios_res/�Ȃǂ̃f�B���N�g���𐶐�����B
	 * @param path �o�̓f�B���N�g���B
	 */
	public void setDocumentRoot( String path )
	{
		docroot = path;
	}

	/**
	 * �p�X�̋�؂�̐ݒ�B
	 * @param newdelimiter ��؂蕶���B
	 */
	public void setDelimiter( String newdelimiter )
	{
		delimiter = newdelimiter;
	}
}
package net.azulite.RaganDrill;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ConvertImage extends ConvertBase
{
	private float dpi;
	private ArrayList<OutputOption> dpilist;
	private ArrayList<String> imglist;

	public ConvertImage()
	{
		super();
		dpi = 160.0f;

		dpilist = new ArrayList<OutputOption>();
		imglist = new ArrayList<String>();
	}

	/**
	 * DPI�̐ݒ�B
	 * @param newdpi �ǂݍ��މ摜��DPI�B
	 */
	public void setInputDPI( float newdpi )
	{
		dpi = newdpi;
	}

	/**
	 * �o��DPI�̐ݒ�B
	 * DPIList�̒萔�𗘗p����B
	 * @param dpilist_type DPIList�̒萔�B
	 */
	public void addOutputDPI( int dpilist_type )
	{
		String dir;
		boolean iosretina = false;

		if ( DPIList.isAnroid( dpilist_type ) )
		{
			dir = dir_android;
		} else
		{
			dir = dir_ios;
			if ( DPIList.is2x( dpilist_type ) ){ iosretina = true; }
		}

		OutputOption opt = new OutputOption(
				DPIList.getDPI( dpilist_type ),
				dir + delimiter + DPIList.getAndroidPath( dpilist_type ),
				iosretina );

		dpilist.add( opt );
	}

	/**
	 * ���͉摜�̒ǉ��B
	 * @param path �摜�t�@�C���ւ̃p�X�B
	 */
	public void addInputImage( String path )
	{
		imglist.add( path );
	}

	public void convert()
	{
		// TODO:createdirectory
		for ( int i = 0 ; i < imglist.size() ; ++i )
		{
			BufferedImage img;
			try
			{
				img = ImageIO.read( new File( imglist.get( i ) ) );
				for ( int j = 0 ; j < dpilist.size() ; ++j )
				{
					convert( img, imglist.get( i ), dpilist.get( j ) );
				}
			} catch (IOException e) {}
		}
	}

	private void convert( BufferedImage img, String name, OutputOption opt )
	{
		int w = convertSize( img.getWidth(), opt.getDPI() );
		int h = convertSize( img.getHeight(), opt.getDPI() );
		BufferedImage output = new BufferedImage( w, h, BufferedImage.TYPE_4BYTE_ABGR );
		Graphics g = output.getGraphics();
		g.drawImage( img, 0, 0, w, h, null );
		g.dispose();
		try
		{
			ImageIO.write( output, "PNG", new File( docroot + delimiter + opt.getFilePath( name, delimiter ) ) );
		} catch ( IOException e ) {}
	}

	/**
	 * �o�̓T�C�Y��Ԃ��A
	 * @param size ���摜�̃T�C�Y�B
	 * @param dpi �o��DPI�B
	 */
	private int convertSize( int size, float dpi )
	{
		return Math.round( dpi / this.dpi * size );
	}
}


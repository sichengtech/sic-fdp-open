/**
 * 本作品使用 木兰公共许可证,第2版（Mulan PubL v2） 开源协议，请遵守相关条款，或者联系sicheng.net获取商用授权。
 * Copyright (c) 2016 SiCheng.Net
 * This software is licensed under Mulan PubL v2.
 * You can use this software according to the terms and conditions of the Mulan PubL v2.
 * You may obtain a copy of Mulan PubL v2 at:
 *          http://license.coscl.org.cn/MulanPubL-2.0
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PubL v2 for more details.
 */

package com.baidu.ueditor.hunter;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.MultiState;
import com.baidu.ueditor.define.State;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class FileManager {

	private String dir = null;
	private String rootPath = null;
	private String[] allowFiles = null;
	private int count = 0;
	
	public FileManager ( Map<String, Object> conf ) {

		this.rootPath = (String)conf.get( "rootPath" );
		this.dir = this.rootPath + (String)conf.get( "dir" );
		this.allowFiles = this.getAllowFiles( conf.get("allowFiles") );
		this.count = (Integer)conf.get( "count" );
		
	}
	
	public State listFile ( int index ) {
		
		File dir2 = new File( this.dir );
		State state = null;

		if ( !dir2.exists() ) {
			return new BaseState( false, AppInfo.NOT_EXIST );
		}
		
		if ( !dir2.isDirectory() ) {
			return new BaseState( false, AppInfo.NOT_DIRECTORY );
		}
		
		Collection<File> list = FileUtils.listFiles( dir2, this.allowFiles, true );
		
		if ( index < 0 || index > list.size() ) {
			state = new MultiState( true );
		} else {
			Object[] fileList = Arrays.copyOfRange( list.toArray(), index, index + this.count );
			state = this.getState( fileList );
		}
		
		state.putInfo( "start", index );
		state.putInfo( "total", list.size() );
		
		return state;
		
	}
	
	private State getState ( Object[] files ) {
		
		MultiState state = new MultiState( true );
		BaseState fileState = null;
		
		File file = null;
		
		for ( Object obj : files ) {
			if ( obj == null ) {
				break;
			}
			file = (File)obj;
			fileState = new BaseState( true );
			fileState.putInfo( "url", PathFormat.format( this.getPath( file ) ) );
			state.addState( fileState );
		}
		
		return state;
		
	}
	
	private String getPath ( File file ) {
		
		String path = file.getAbsolutePath();
		
		return path.replace( this.rootPath, "/" );
		
	}
	
	private String[] getAllowFiles ( Object fileExt ) {
		
		String[] exts = null;
		String ext = null;
		
		if ( fileExt == null ) {
			return new String[ 0 ];
		}
		
		exts = (String[])fileExt;
		
		for ( int i = 0, len = exts.length; i < len; i++ ) {
			
			ext = exts[ i ];
			exts[ i ] = ext.replace( ".", "" );
			
		}
		
		return exts;
		
	}
	
}

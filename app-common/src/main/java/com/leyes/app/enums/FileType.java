package com.leyes.app.enums;

public enum FileType {
	text,
	image,
	video,
	audio;
	
	public static FileType valueOf(int code){
		FileType fileType=null;
		switch (code) {
		case 0:
			fileType= FileType.text;
			break;
		case 1:
			fileType= FileType.image;
			break;
		case 2:
			fileType= FileType.video;
			break;
		case 3:
			fileType= FileType.audio;
			break;
		}
		return fileType;
	}
}

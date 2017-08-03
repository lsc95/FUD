package com.coderli.entity;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int uid;
	private String uname;
	private String pwd;
	private String realName;
	private String photoName;
	private String type;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int uid, String uname, String pwd, String realName,
			String photoName, String type) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.pwd = pwd;
		this.realName = realName;
		this.photoName = photoName;
		this.type = type;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((photoName == null) ? 0 : photoName.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result
				+ ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + uid;
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (photoName == null) {
			if (other.photoName != null)
				return false;
		} else if (!photoName.equals(other.photoName))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uid != other.uid)
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", pwd=" + pwd
				+ ", realName=" + realName + ", photoName=" + photoName
				+ ", type=" + type + "]";
	}

}

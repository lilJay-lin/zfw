package com.mimi.zfw.web.shiro.tld;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

public class HasAnyPermissionsTag extends PermissionTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5910747645874327405L;

	public HasAnyPermissionsTag() {
	}

	protected boolean showTagBody(String permissions) {
		boolean hasAnyPermissions = false;
		Subject subject = getSubject();
		if (subject != null) {
			for (String perm : permissions.split(",")) {
				if (subject.isPermitted(perm.trim())) {
					hasAnyPermissions = true;
					break;
				}
			}
		}
		return hasAnyPermissions;
	}
}

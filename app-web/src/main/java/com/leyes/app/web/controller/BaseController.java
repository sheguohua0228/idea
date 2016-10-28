package com.leyes.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.leyes.app.web.utils.SessionContextUtils;

public class BaseController {

	@Autowired
	protected SessionContextUtils sessionContextUtils;
}

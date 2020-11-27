package com.iyad.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface Controller {

	 void write(HttpServletResponse response) throws IOException;
}

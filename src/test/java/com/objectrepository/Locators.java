package com.objectrepository;

import org.openqa.selenium.By;

public class Locators {
	// FB app
	public final By fbLogin_EmailEditBox = By.id("email");
	public final By fbLogin_PasswordEditBox = By.name("pass");
	public final By fbLogin_LoginButton = By.name("login");

	// Amazon App
	public final By amazon_SearchEditBox = By.id("twotabsearchtextbox");
	public final By amazon_SearchIcon = By.id("nav-search-submit-button");
	public final By amazon_SearchResults = By.xpath(
			"//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span");

	public final By amazon_AddToCart = By.id("add-to-cart-button");

	// JRI locators
	public final By jriSigninPage_EmailEditbox = By.name("txtUserName");
	public final By jriSigninPage_PasswordEditbox = By.name("txtPasswd");
	public final By jriSigninPage_CaptchaEditbox = By.name("txtCaptcha");
	public final By jriSigninPage_SecureSigninButton = By.name("imgbtnSignin");

}

package com.softserve.edu.opencart.pages.right;

import com.softserve.edu.opencart.data.user.IUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends AUnloggedRightMenuPage {

    public RegistrationPage(WebDriver driver){
        super(driver);
        initElements();
    }

    // Required fields
    private WebElement firstname;
    private WebElement lastname;
    private WebElement eMail;
    private WebElement telephone;
    private WebElement fax;
    private WebElement company;
    private WebElement address1;
    private WebElement address2;
    private WebElement city;
    private WebElement postCode;
    private WebElement country;
    private WebElement regionState;
    private WebElement password;
    private WebElement confirmPassword;
    private WebElement privatePolicy;
    private WebElement continueButton;

    private void initElements(){
        firstname = driver.findElement(By.name("firstname"));
        lastname = driver.findElement(By.name("lastname"));
        eMail = driver.findElement(By.name("email"));
        telephone = driver.findElement(By.name("telephone"));
        fax = driver.findElement(By.name("fax"));
        company = driver.findElement(By.name("company"));
        address1 = driver.findElement(By.name("address_1"));
        address2 = driver.findElement(By.name("address_2"));
        city = driver.findElement(By.name("city"));
        postCode = driver.findElement(By.name("postcode"));
        country = driver.findElement(By.id("input-country"));
        regionState = driver.findElement(By.id("input-zone"));
        password = driver.findElement(By.name("password"));
        confirmPassword = driver.findElement(By.name("confirm"));
        privatePolicy = driver.findElement(By.name("agree"));
        continueButton = driver.findElement(By.cssSelector(".btn.btn-primary"));

    }
    //First name
    public WebElement getFirstnameField(){
        return firstname;
    }
    public String getFirstnameFieldText(){
        return getFirstnameField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setFirstnameField(String text) {
        getFirstnameField().sendKeys(text);
    }
    public void clearFirstnameField() {
        getFirstnameField().clear();
    }
    public void clickFirstnameField() {
        getFirstnameField().click();
    }
    //Second name
    public WebElement getLastnameField(){
        return lastname;
    }
    public String getLastnameFieldText(){
        return getLastnameField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setLastnameField(String text) {
        getLastnameField().sendKeys(text);
    }
    public void clearLastnameField() {
        getLastnameField().clear();
    }
    public void clickLastnameField() {
        getLastnameField().click();
    }
    //E-mail
    public WebElement getEMailField(){
        return eMail;
    }
    public String getEMailFieldText(){
        return getEMailField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setEMailField(String text) {
        getEMailField().sendKeys(text);
    }
    public void clearEMailField() {
        getEMailField().clear();
    }
    public void clickEMailField() {
        getEMailField().click();
    }
    //Telephone
    public WebElement getTelephoneField(){
        return telephone;
    }
    public String getTelephoneFieldText(){
        return getTelephoneField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setTelephoneField(String text) {
        getTelephoneField().sendKeys(text);
    }
    public void clearTelephoneField() {
        getTelephoneField().clear();
    }
    public void clickTelephoneField() {
        getTelephoneField().click();
    }
    //Fax
    public WebElement getFaxField(){
        return fax;
    }
    public String getFaxFieldText(){
        return getFaxField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setFaxField(String text) {
        getFaxField().sendKeys(text);
    }
    public void clearFaxField() {
        getFaxField().clear();
    }
    public void clickFaxField() {
        getFaxField().click();
    }
    //Company
    public WebElement getCompanyField(){
        return company;
    }
    public String getCompanyFieldText(){
        return getCompanyField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setCompanyField(String text) {
        getCompanyField().sendKeys(text);
    }
    public void clearCompanyField() {
        getCompanyField().clear();
    }
    public void clickCompanyField() {
        getCompanyField().click();
    }
    //Address1
    public WebElement getAddress1Field(){
        return address1;
    }
    public String getAddress1FieldText(){
        return getAddress1Field().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setAddress1Field(String text) {
        getAddress1Field().sendKeys(text);
    }
    public void clearAddress1Field() {
        getAddress1Field().clear();
    }
    public void clickAddress1Field() {
        getAddress1Field().click();
    }
    //Address2
    public WebElement getAddress2Field(){
        return address2;
    }
    public String getAddress2FieldText(){
        return getAddress2Field().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setAddress2Field(String text) {
        getAddress2Field().sendKeys(text);
    }
    public void clearAddress2Field() {
        getAddress2Field().clear();
    }
    public void clickAddress2Field() {
        getAddress2Field().click();
    }
    //City
    public WebElement getCityField(){
        return city;
    }
    public String getCityFieldText(){
        return getCityField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setCityField(String text) {
        getCityField().sendKeys(text);
    }
    public void clearCityField() {
        getCityField().clear();
    }
    public void clickCityField() {
        getCityField().click();
    }
    //Post Code
    public WebElement getPostCodeField(){
        return postCode;
    }
    public String getPostCodeFieldText(){
        return getPostCodeField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setPostCodeField(String text) {
        getPostCodeField().sendKeys(text);
    }
    public void clearPostCodeField() {
        getPostCodeField().clear();
    }
    public void clickPostCodeField() {
        getPostCodeField().click();
    }
    //Country
    public WebElement getCountryField(){
        return country;
    }
    public String getCountryFieldText(){
        return getCountryField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setCountryField(String text) {
        getCountryField().sendKeys(text);
    }
    public void clearCountryField() {
        getCountryField().clear();
    }
    public void clickCountryField() {
        getCountryField().click();
    }
    //Region State
    public WebElement getRegionStateField(){
        return regionState;
    }
    public String getRegionStateFieldText(){
        return getRegionStateField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setRegionStateField(String text) {
        getRegionStateField().sendKeys(text);
    }
    public void clearRegionStateField() {
        getRegionStateField().clear();
    }
    public void clickRegionStateField() {
        getRegionStateField().click();
    }
    //Password
    public WebElement getPasswordField(){
        return password;
    }
    public String getPasswordFieldText(){
        return getPasswordField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setPasswordField(String text) {
        getPasswordField().sendKeys(text);
    }
    public void clearPasswordField() {
        getPasswordField().clear();
    }
    public void clickPasswordField() {
        getPasswordField().click();
    }
    //Confirm password
    public WebElement getConfirmPasswordField(){
        return confirmPassword;
    }
    public String getConfirmPasswordFieldText(){
        return getConfirmPasswordField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void setConfirmPasswordField(String text) {
        getConfirmPasswordField().sendKeys(text);
    }
    public void clearConfirmPasswordField() {
        getConfirmPasswordField().clear();
    }
    public void clickConfirmPasswordField() {
        getConfirmPasswordField().click();
    }
    //Private Policy
    public WebElement getPrivatePolicy(){
        return privatePolicy;
    }
    public void clickPrivatePolicy(){
        getPrivatePolicy().click();
    }
    //Continue button
    public WebElement getContinueButton() {
        return continueButton;
    }
    public String getContinueButtonText() {
        return getContinueButton().getAttribute(TAG_ATTRIBUTE_VALUE);
    }
    public void clickContinueButton() {
        getContinueButton().click();
    }

    // Functional Operations
    private void fillRegistrationForm(IUser user){
        //Type First name
        clickFirstnameField();
        clearFirstnameField();
        setFirstnameField(user.getFirstname());
        //Type Last name
        clickLastnameField();
        clearLastnameField();
        setLastnameField(user.getLastname());
        //Type E-mail
        clickEMailField();
        clearEMailField();
        setEMailField(user.getEMail());
        //Type Telephone
        clickTelephoneField();
        clearTelephoneField();
        setTelephoneField(user.getTelephone());
        //Type Fax
        clickFaxField();
        clearFaxField();
        setFaxField(user.getFax());
        //Type Company
        clickCompanyField();
        clearCompanyField();
        setCompanyField(user.getCompany());
        //Type Address1
        clickAddress1Field();
        clearAddress1Field();
        setAddress1Field(user.getAddress1());
        //Type Address2
        clickAddress2Field();
        clearAddress2Field();
        setAddress2Field(user.getAddress2());
        //Type City
        clickCityField();
        clearCityField();
        setCityField(user.getCity());
        //Type Post Code
        clickPostCodeField();
        clearPostCodeField();
        setPostCodeField(user.getPostCode());
        //Type Country
        clickCountryField();
        setCountryField(user.getCountry());
        //Type Region State
        clickRegionStateField();
        setRegionStateField(user.getRegionState());
        //Type Password
        clickPasswordField();
        clearPasswordField();
        setPasswordField(user.getPassword());
        //Type Confirm password
        clickConfirmPasswordField();
        clearConfirmPasswordField();
        setConfirmPasswordField(user.getPassword());

    }
    // Business Logic

    public RegistrationSuccessPage successRegistration(IUser user) {
        fillRegistrationForm(user);
        clickPrivatePolicy();
        clickContinueButton();
        return new RegistrationSuccessPage(driver);
    }

    public UnsuccessfulRegistrationPage withoutAccept(IUser user){
        fillRegistrationForm(user);
        clickContinueButton();
        return new UnsuccessfulRegistrationPage(driver);
    }

    public UnsuccessfulRegistrationPage unsuccessfulRegistrationPage(IUser InvalidUser){
        fillRegistrationForm(InvalidUser);
        clickPrivatePolicy();
        clickContinueButton();
        return new UnsuccessfulRegistrationPage(driver);
    }

}

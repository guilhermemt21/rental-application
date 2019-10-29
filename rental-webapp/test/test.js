var assert = require("assert").strict;
var webdriver = require('selenium-webdriver');
var chrome = require('selenium-webdriver/chrome');
require("geckodriver");// Application Server
var chromedriver = require('chromedriver');
const serverUri = "http://localhost:3000/admin";
const appTitle = "React Selenium App";
/**
 * Config for Chrome browser
 * @type {webdriver}
 */


/*
 chrome.setDefaultService(new chrome.ServiceBuilder(chromedriver.path).build());

 var browser = new webdriver.Builder()
 .withCapabilities(webdriver.Capabilities.chrome())
 .build();
 */


browser = new webdriver.Builder()
  .usingServer()
  .withCapabilities({browserName: "firefox"})
  .build();

/**
 * Config for Firefox browser (Comment Chrome config when you intent to test in Firefox)
 * @type {webdriver}
 */
/*
 var browser = new webdriver.Builder()
 .usingServer()
 .withCapabilities({ browserName: "firefox" })
 .build();
 */


describe("Should load the home page and get title", function () {
  /**
   * Test case to load our application and check the title.
   */
  it('open', function () {
    return new Promise((resolve, reject) => {
        browser
        .get(serverUri);
  });
  });

  it("Should load the home page and get type", function () {
    return new Promise((resolve, reject) => {
        browser.findElement({id: 'type'}).then(address => resolve(address.sendKeys("type")));
    });
  });

  it("Should load the home page and get address", function () {
    return new Promise((resolve, reject) => {
        browser.findElement({id: 'address'}).then(address => resolve(address.sendKeys("type")));
    });
  });

  after(function () {
    browser.quit();
  });
})
;
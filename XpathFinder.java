public class XpathFinder {
    public static void main(String[] args) {
        System.out.println("Hello World");

        // Assuming driver and element are initialized
        WebDriver driver = new ChromeDriver();
        WebElement element = driver.findElement(By.name("elementName"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "function getXPath(element) {" +
                        "    if (element.id !== '') {" +
                        "        return 'id(\\'' + element.id + '\\')';" +
                        "    }" +
                        "    if (element === document.body) {" +
                        "        return element.tagName;" +
                        "    }" +
                        "    var ix = 0;" +
                        "    var siblings = element.parentNode.childNodes;" +
                        "    for (var i = 0; i < siblings.length; i++) {" +
                        "        var sibling = siblings[i];" +
                        "        if (sibling === element) {" +
                        "            return getXPath(element.parentNode) + '/' + element.tagName + '[' + (ix + 1) + ']';" +
                        "        }" +
                        "        if (sibling.nodeType === 1 && sibling.tagName === element.tagName) {" +
                        "            ix++;" +
                        "        }" +
                        "    }" +
                        "}" +
                        "return getXPath(arguments[0]);";
        String xpath = (String) js.executeScript(script, element);
        System.out.println(xpath);
    }
}
package com.example.pricesscraper.common;
import com.example.pricesscraper.domain.Items;
import com.example.pricesscraper.exception.BusinessException;
import com.example.pricesscraper.utils.AbrasionUtils;
import com.example.pricesscraper.utils.ConstructUtils;

import com.example.pricesscraper.utils.ResultUtils;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.util.Set;

@Component
public class WebScraper {
    public static String CreateNewTab(WebDriver driver ,String url, String mainHandle){
        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> d.getWindowHandles().size() > 1);
        Set<String> AllTabs = driver.getWindowHandles();
        for(String handle : AllTabs){
            if(!handle.equals(mainHandle)){
                return handle;

            }

        }
        throw new RuntimeException("未找到新标签页");
    }
    public List<Items> ScrapeProductData(String key) throws IOException {

        List<Items> products = new ArrayList<>();
        try {
            System.out.println("进入try-catch模块");
            System.setProperty("webdriver.edge.driver", "D:\\JavaProject\\PricesScraper\\msedgedriver.exe");
            //WebDriverManager.edgedriver()
            //        .setup();
            System.out.println("自动管理edge驱动");
            System.out.println("访问网址：" + "https://steamdt.com/mkt?search=" + key);


            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless=new");     // 推荐使用新版 headless
            options.addArguments("--disable-gpu");      // 无头下必须加，避免渲染问题
            options.addArguments("--no-sandbox");       // 避免权限问题（部分系统）
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");  // 解决 websocket 错误的关键


            WebDriver webDriver = new EdgeDriver(options);
            System.out.println("创建浏览器实例");
            webDriver.get("https://steamdt.com/mkt?search="+key);
            System.out.println("抓取主页面！");
            String mainHandle = webDriver.getWindowHandle();

            WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            List<WebElement> elements = webDriverWait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tr[contains(@class, 'el-table__row') and not(contains(@class, 'h-30'))]"))
            );
            System.out.println("抓取物品数量为:"+elements.size());

            for(WebElement element : elements){
                System.out.println("已经进入for循环！");

                WebElement cell = webDriverWait.until(
                        ExpectedConditions.presenceOfNestedElementLocatedBy(element,
                        By.cssSelector("td.el-table_2_column_9 .mb-10")
                ));


                // todo "饰品名称"
                String productname = cell.getText();
                System.out.println("抓取到饰品名称:"+productname);
                // todo "磨损度"
                String abrasion = AbrasionUtils.GetAbrasion(productname);
                // 通过URL得到具体饰品的网页
                WebElement href = webDriverWait.until(
                        ExpectedConditions.presenceOfNestedElementLocatedBy(element,
                                By.cssSelector("td.el-table_2_column_9 a[href]"))
                );

                String URL =  href.getAttribute("href");
                System.out.println("前往饰品详情页："+URL);

                String detailURL = CreateNewTab(webDriver,URL,mainHandle);
                webDriver.switchTo().window(detailURL);


                // 等待你想要的 table-item 元素存在，且“某个关键字段”存在，比如前去购买按钮
                /*webDriverWait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.xpath("(//div[contains(@class, 'bg-white') and contains(@class, 'pl-20') and contains(@class, 'pr-20')]/div[contains(@class,'table-item')])[3]")
                        )
                );
                Thread.sleep(5000);*/
                webDriverWait.until(driver -> driver.findElements(By.cssSelector(".table-item")).size() >= 5);

                System.out.println("创建新标签页并前往");

                WebElement onsaleTab = webDriverWait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='tab-onsale' and contains(text(), '当前在售')]"))
                );

                // 点击“当前在售”标签页
                onsaleTab.click();
                System.out.println("点击当前在售按钮！");

                // 等待 B 表格容器变为可见
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='pane-onsale' and not(contains(@style, 'display: none'))]")
                ));
                // 得到该饰品在悠悠的具体数据
                WebElement section = webDriverWait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(@class, 'bg-white') and contains(@class, 'pl-20') and contains(@class, 'pr-20')]/div[contains(@class,'table-item')])[3]"))
                );
                System.out.println("section outerHTML: " + section.getAttribute("outerHTML"));
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                Boolean isVisible = (Boolean) js.executeScript(
                        "const el = arguments[0]; return !!(el && el.offsetParent !== null);", section);

                System.out.println("JS判断是否可见: " + isVisible);

                System.out.println("section是否显示: " + section.isDisplayed());


                String price1 = webDriverWait.until(
                        ExpectedConditions.presenceOfNestedElementLocatedBy(section,
                                By.cssSelector("div.items-table-c2.text-color-orange.text-16"))
                ).getText();
                String price2 =webDriverWait.until(
                        ExpectedConditions.presenceOfNestedElementLocatedBy(section,
                                By.cssSelector("div.items-table-c3.text-color-orange.text-16"))
                ).getText();
                String amount1 =webDriverWait.until(
                        ExpectedConditions.presenceOfNestedElementLocatedBy(section,
                                By.cssSelector("div.items-table-c4.text-14"))
                ).getText();
                String amount2 =webDriverWait.until(
                        ExpectedConditions.presenceOfNestedElementLocatedBy(section,
                                By.cssSelector("div.items-table-c5.text-14"))
                ).getText();
                System.out.println(price1 +"   "+ price2 +"   "+ amount1 +"   "+ amount2);
                // todo 得到 售价 求购价 在售量 求够量
                BigDecimal productPrice = new BigDecimal(price1.replaceAll("[^\\d.]", ""));
                BigDecimal seekPrice = new BigDecimal(price2.replaceAll("[^\\d.]", ""));
                int saleAmount = (int) Double.parseDouble(amount1.replaceAll("[^\\d.]", ""));
                int seekAmount = (int) Double.parseDouble(amount2.replaceAll("[^\\d.]", ""));
                // System.out.println(productPrice +"   "+ seekPrice +"   "+ saleAmount +"   "+ seekAmount);
                // todo 将数据赋值给实体 向产品列表添加
                Items item = ConstructUtils.setItems(productname,abrasion,productPrice,seekPrice,saleAmount,seekAmount);
                products.add(item);
                System.out.println("添加产品列表！");

        }


        } catch (NoSuchElementException e) {
            System.out.println("跳过！");
            e.printStackTrace();
        }
        return products;
    }
}

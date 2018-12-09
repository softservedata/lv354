package com.softserve.edu.opencart.data.application;

public final class ApplicationSourceRepository {

    private ApplicationSourceRepository() {
    }

    public static IApplicationSource defaultParameters() {
        return localChrome();
    }

    public static IApplicationSource localChrome(){
        return new ApplicationSource("ChromeTemporary",
                ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1),
                10,true,
                "http://192.168.234.130/opencart/upload/",
                "http://192.168.234.130/opencart/upload/index.php?route=account/logout");
    }

    public static IApplicationSource adminChromeWithoutUI(){
        return new ApplicationSource("ChromeWithoutUI",
                ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1)
                ,10
                ,true
                ,"http://192.168.234.130/opencart/upload/admin/index.php?route=common/login"
                ,"http://192.168.234.130/opencart/upload/admin/index.php?route=common/logout");
    }
    public static IApplicationSource adminLocalChrome(){
        return new ApplicationSource("ChromeTemporary",
        ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1)
                ,3
                ,true
                ,"http://192.168.234.130/opencart/upload/admin/index.php?route=common/login"
                ,"http://192.168.234.130/opencart/upload/admin/index.php?route=common/logout");

    }

}
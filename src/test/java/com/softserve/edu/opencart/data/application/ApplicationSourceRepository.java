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
                "http://taqc-opencart.epizy.com/",
                "http://taqc-opencart.epizy.com/index.php?route=account/logout");
    }

    public static IApplicationSource adminChromeWithoutUI(){
        return new ApplicationSource("ChromeWithoutUI",
                ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1)
                ,10
                ,true
                ,"http://taqc-opencart.epizy.com/admin/"
                ,"http://taqc-opencart.epizy.com/admin/index.php?route=common/logout");
    }
    public static IApplicationSource adminLocalChrome(){
        return new ApplicationSource("ChromeTemporary",
        ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1)
                ,10
                ,true
                ,"http://taqc-opencart.epizy.com/admin/"
                ,"http://taqc-opencart.epizy.com/admin/index.php?route=common/logout");

    }

}
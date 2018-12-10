package com.opencart.data.application;

public final class ApplicationSourceRepository {

	private ApplicationSourceRepository() {
	}

	public static IApplicationSource defaultParameters() {
		return localChrome();
	}

	public static IApplicationSource localChrome() {
		return new ApplicationSource("ChromeTemporary",
				ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1),
				10, true,
				"http://192.168.31.129/opencart/upload/",
				"http://192.168.31.129/opencart/upload/index.php?route=account/logout",
				"jdbc:mysql://192.168.31.129:3306/opencart", "lv354", "Lv354_TAQC"
		);
	}

	public static IApplicationSource epizyChrome() {
		return new ApplicationSource("ChromeTemporary",
			ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1),
			30, true,
			"http://taqc-opencart.epizy.com/",
			"http://taqc-opencart.epizy.com/opencart/upload/index.php?route=account/logout",
			"jdbc:mysql://192.168.103.210:3306/opencart", "lv354", "Lv354_TAQC");
	}

	public static IApplicationSource localChromeWithoutUI() {
		return new ApplicationSource("ChromeWithoutUI",
				ApplicationSourceRepository.class.getResource("/chromedriver-windows-32bit.exe").getPath().substring(1),
				10, true,
				"http://192.168.31.129/opencart/upload/",
				"http://192.168.31.129/opencart/upload/index.php?route=account/logout",
				"jdbc:mysql://192.168.31.129:3306/opencart", "lv354", "Lv354_TAQC"
		);
	}
}

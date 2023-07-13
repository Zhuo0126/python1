from telnetlib import EC
import time

from selenium import webdriver
from selenium.common import NoSuchElementException
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
# 创建Chrome浏览器的驱动程序
driver = webdriver.Chrome()
# driver = webdriver.Chrome('path_to_chromedriver')  # 替换为您的Chrome驱动程序路径

# 打开目标网站
# driver.get("https://example.com")
# driver.get("https://translate.google.com.tw/?hl=zh-TW") #google翻譯
# driver.get("https://www.baidu.com/")
driver.get("https://gfb.gameflier.com/Billing/Login/login.aspx?redct=G12&game=W2")

# 找到输入字段的元素，并输入文本
try:
    # search_box = driver.find_element("id", "kw").send_keys('python')

    search_box = driver.find_element("id", "GF_ID3").send_keys('test1')

    time.sleep(1)
    search_box = driver.find_element("id", "GF_ID22").send_keys('test2')

    # driver.find_element_by_id('kw').send_keys('python')
    time.sleep(1)
    # driver.find_element_by_id('su').click()
    time.sleep(1)

    # input_element = driver.find_element_by_class_name('anue-search-input')
except NoSuchElementException:
    print("找不到元素！程序停止执行。")

# input_element = driver.find_element_by_id('header-search-input')
# input_element = driver.find_element_by_id("source")
# text = "test"
# input_element.send_keys(text)

# 找到提交按钮的元素，并点击提交
# submit_button = driver.find_element_by_id("submit_button_id")
# submit_button.click()


# # 获取翻译结果
# translation_element = driver.find_element_by_xpath('//span[@class="tlid-translation translation"]')
# translation_text = translation_element.text

# 输出翻译结果
# print('翻译结果:', translation_text)
# 关闭浏览器
driver.quit()
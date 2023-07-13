from selenium import webdriver
from selenium.webdriver.common.keys import Keys

# 创建Chrome浏览器的驱动程序
driver = webdriver.Chrome()

# 打开目标网站
driver.get("https://example.com")

# 找到输入字段的元素，并输入文本
input_element = driver.find_element_by_id("input_field_id")
text = "这是要输入的文本内容"
input_element.send_keys(text)

# 找到提交按钮的元素，并点击提交
submit_button = driver.find_element_by_id("submit_button_id")
submit_button.click()

# 关闭浏览器
driver.quit()
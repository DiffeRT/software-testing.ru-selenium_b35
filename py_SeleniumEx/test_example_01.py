import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as ec


SEARCH_FIELD = "q"
SEARCH_BUTTON = "btnK"


@pytest.fixture
def driver(request):
    wd = webdriver.Chrome()
    wd.implicitly_wait(10)
    request.addfinalizer(wd.quit)
    return wd


def test_example(driver):
    driver.get("https://www.google.com/")
    driver.find_element(By.NAME, SEARCH_FIELD).send_keys("webdriver")
    driver.find_element(By.NAME, SEARCH_BUTTON).click()
    WebDriverWait(driver, 10).until(ec.title_is("webdriver - Поиск в Google"))

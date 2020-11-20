function check(id) { // 根据此时尝试的输入，动态检查结果
    var elem = document.getElementById(id); // 根据获取 html dom 对象
    var pattern = elem.pattern;
    var infor = elem.value;
    var regex = new RegExp('^' + pattern + '$');
    var re = regex.exec(infor);
    var temp = null;

    // 根据此时尝试的输入，显示对应的动态检查结果
    if ('u-name' == id)
        temp = document.getElementById('name-format');
    else if ('u-phone' == id)
        temp = document.getElementById('phone-format');
    else if ('u-email' == id)
        temp = document.getElementById('email-format');
    else if ('u-address' == id)
        temp = document.getElementById('address-format');
    else if ('u-qq' == id)
        temp = document.getElementById('qq-format');

    if ('' == infor)
    {
        temp.innerHTML = "请填写此栏";
        temp.style.color = "rgb(15, 75, 221)";
    }
    else if (null != re)
    {
        temp.innerHTML = "格式正确";
        temp.style.color = "rgb(94,221,15)";
    }
    else if (null == re)
    {
        temp.innerHTML = "格式错误";
        temp.style.color = "rgb(255, 62, 62)";
    }
}
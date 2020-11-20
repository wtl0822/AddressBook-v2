function add(elem) {
    var temp = document.createElement("form")
    temp.action = "/add"
    temp.method = "post"
    temp.style.display = "none"
    // 将 temp 元素加入 document 中
    document.body.appendChild(temp)

    temp.submit()
    return temp
}

function edit(elem) {
    // html dom 对象
    // 取 tr 行数
    var row = elem.parentNode.parentNode.rowIndex - 1

    var temp = document.createElement("form") // 创建 form 元素，指定姓名
    temp.action = "/modify" // modify 函数处理
    temp.method = "post" // post 请求
    temp.style.display = "none"

    // 创建 textarea 名称的元素
    var opt = document.createElement("textarea")
    opt.name = "row"
    opt.value = row.toString() // 行号
    temp.appendChild(opt) // 将 opt 加到 temp 对象中
    document.body.appendChild(temp) // 将 temp 加到 document 对象中
    temp.submit() // 代表一个提交按钮？
    return temp
}

function del(elem) {
    // 取行号
    var row = elem.parentNode.parentNode.rowIndex - 1
    // 删除 tr 行
    var tr = elem.parentNode.parentNode
    var tbody = tr.parentNode
    tbody.removeChild(tr)

    // AJAX
    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = callBack
    xmlHttpRequest.open("post", "/del", true)
    // 设置请求头
    xmlHttpRequest.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded")
    xmlHttpRequest.send("row=" + row)
}

function callBack() { // 暂时不需要执行回调操作
}

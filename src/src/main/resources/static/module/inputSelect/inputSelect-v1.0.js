/**
 * 搜索框提示插件||输入框提示插件
 * https://fly.layui.com/extend/yutons_sug/#doc
 * ------------------------------------------------
 *   inputSelect.render({
 *        id: "userCode",
 *        height: "200",
 *        cols: [
 *            [{
 *                field: 'userName',
 *                title: '员工姓名'
 *            }, {
 *               field: 'userCode',
 *                title: '员工工号'
 *            }, {
 *                field: 'deptName',
 *               title: '所属部门'
 *            }]
 *        ],
 *        type: 'table',
 *        url: uri + "?params="
 *    });
 *
 *    //初始化部门输入提示框
 *    inputSelect.render({
 *        id: "deptName",
 *        url: uri + "?params="
 *   });
 * ------------------------------------------------
 */
layui.define(['jquery', 'table'], function (exports) {
    "use strict";
    const $ = layui.jquery,
        table = layui.table;

    let inputSelect = function () {
        this.v = '1.0';
    };
    /**
     * 渲染函数
     */
    inputSelect.prototype.render = function (opt) {
        opt.urlBak = opt.url;
        //设置默认初始化参数
        opt.type = opt.type || 'sug';
        opt.elem = "#inputSelect_" + opt.id;
        opt.height = opt.height || '229';
        opt.cellMinWidth = opt.cellMinWidth || '80'; //最小列宽
        opt.page = opt.page || true;
        opt.limits = opt.limits || [3];
        opt.loading = opt.loading || true;
        opt.limit = opt.limit || 3;
        opt.size = opt.size || 'sm'; //小尺寸的表格

        //初始化输入框提示容器
        $("#" + opt.id).after("<div id='sugItem' style='background-color: #fff;display: none;z-index:1;position: absolute;width:100%;'></div>");

        //输入框提示容器移出事件：鼠标移出隐藏输入提示框
        $("#" + opt.id).parent().mouseleave(function () {
            $("#" + opt.id).next().hide().html("");
        });

        // 初始化下拉表格
        if (opt.type == "table") {
            /* 输入框鼠标松开事件 */
            $("#" + opt.id).mouseup(function (e) {
                opt.obj = this;
                getTable(opt);
            })
            /* 输入框键盘抬起事件 */
            $("#" + opt.id).keyup(function (e) {
                opt.obj = this;
                getTable(opt);
            })
        } else {
            // 默认初始化下拉框
            $("#" + opt.id).next().css("border", "solid #e6e6e6 0.5px");
            /* 输入框鼠标松开事件 */
            $("#" + opt.id).mouseup(function (e) {
                opt.obj = this;
                getSug(opt);
            })
            /* 输入框键盘抬起事件 */
            $("#" + opt.id).keyup(function (e) {
                opt.obj = this;
                getSug(opt);
            })
        }
    }

    //搜索框提示插件||输入框提示插件--table-下拉表格
    function getTable(opt) {
        //如果输入信息为"",则隐藏输入提示框,不再执行下边代码
        let kw = $.trim($(opt.obj).val());
        if (kw == "") {
            $("#" + opt.id).next().hide().html("");
            return false;
        }
        //下拉表格初始化table容器
        let html = '<table id="inputSelect_' + opt.obj.getAttribute("id") + '" lay-filter="inputSelect_'
            + opt.obj.getAttribute("id") + '"></table>';
        $("#" + opt.obj.getAttribute("id")).next().show().html(html);

        //下拉表格初始化
        opt.url = opt.urlBak + kw;
        table.render(opt);
        //设置下拉表格样式
        $(opt.elem).next().css("margin-top", "0").css("background-color", "#ffffff");
        //监听下拉表格行单击事件（单击||双击事件为：row||rowDouble）设置单击或双击选中对应的行
        table.on('rowDouble(' + "inputSelect_" + opt.id + ')', function (obj) {
            //获取选中行所传入字段的值
            $("#" + opt.id).val(obj.data[opt.id]);
            $("#" + opt.id).next().hide().html("");
        });
    }

    //搜索框提示插件||输入框提示插件--sug-下拉框
    function getSug(opt) {
        sessionStorage.setItem("inputId", opt.id)
        let kw = $.trim($(opt.obj).val());
        if (kw == "") {
            $("#" + opt.id).next().hide().html("");
            return false;
        }
        //sug下拉框异步加载数据并渲染下拉框
        $.ajax({
            type: "get",
            url: opt.urlBak + kw,
            success: function (data) {
                let html = "";
                layui.each(data.data, (index, item) => {
                    //if (item[sessionStorage.getItem("inputId")].indexOf(decodeURI(kw)) >= 0) {
                    html += "<div class='item' style='padding: 3px 10px;cursor: pointer;' onmouseenter='getFocus(this)' onClick='getCon(this);'>" +
                        item[sessionStorage.getItem("inputId")] + "</div>";
                    //}
                });
                if (html != "") {
                    $("#" + sessionStorage.getItem("inputId")).next().show().html(html);
                } else {
                    $("#" + sessionStorage.getItem("inputId")).next().hide().html("");
                }
            }
        });
    }

    //搜索框提示插件||输入框提示插件--sug-下拉框上下键移动事件和回车事件
    $(document).keydown(function (e) {
        e = e || window.event;
        let keycode = e.which ? e.which : e.keyCode;
        if (keycode == 38) {
            //上键事件
            if ($.trim($("#" + sessionStorage.getItem("inputId")).next().html()) == "") {
                return;
            }
            movePrev(sessionStorage.getItem("inputId"));
        } else if (keycode == 40) {
            //下键事件
            if ($.trim($("#" + sessionStorage.getItem("inputId")).next().html()) == "") {
                return;
            }
            $("#" + sessionStorage.getItem("inputId")).blur();
            if ($(".item").hasClass("iptscls")) {
                moveNext();
            } else {
                $(".item").removeClass('iptscls').css("background", "").eq(0).addClass('iptscls').css("background", "#e6e6e6");
            }
        } else if (keycode == 13) {
            //回车事件
            dojob();
        }
    });
    //上键事件
    let movePrev = function (id) {
        $("#" + id).blur();
        let index = $(".iptscls").prevAll().length;
        if (index == 0) {
            $(".item").removeClass('iptscls').css("background", "").eq($(".item").length - 1).addClass('iptscls').css(
                "background", "#e6e6e6");
        } else {
            $(".item").removeClass('iptscls').css("background", "").eq(index - 1).addClass('iptscls').css("background", "#e6e6e6");
        }
    }
    //下键事件
    let moveNext = function () {
        let index = $(".iptscls").prevAll().length;
        if (index == $(".item").length) {
            $(".item").removeClass('iptscls').css("background", "").eq(0).addClass('iptscls').css("background", "#e6e6e6");
        } else {
            $(".item").removeClass('iptscls').css("background", "").eq(index + 1).addClass('iptscls').css("background", "#e6e6e6");
        }
    }
    //回车事件
    let dojob = function () {
        let value = $(".iptscls").text();
        $("#" + sessionStorage.getItem("inputId")).blur();
        $("#" + sessionStorage.getItem("inputId")).val(value);
        $("#" + sessionStorage.getItem("inputId")).next().hide().html("");
    }

    //上下键选择和鼠标选择事件改变颜色
    window.getFocus = function (obj) {
        $(".item").css("background", "");
        $(obj).css("background", "#e6e6e6");
    }

    //点击选中事件，获取选中内容并回显到输入框
    window.getCon = function (obj) {
        let value = $(obj).text();
        $("#" + $(".item").parent().prev().attr("id")).val(value);
        $("#" + $(".item").parent().prev().attr("id")).next().hide().html("");
    }

    //自动完成渲染
    inputSelect = new inputSelect();
    //暴露方法
    exports("inputSelect", inputSelect);
});
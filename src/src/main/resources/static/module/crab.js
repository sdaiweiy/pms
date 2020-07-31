/**
 * crab 1.0
 * http://crab.baomidou.com
 */
layui.define(['config', 'layer'], function (exports) {
    var $ = layui.jquery;
    var userInfo = 'ui', userResource = 'ur', lockStatus = 'lk';
    var config = layui.config;
    var layer = layui.layer;
    var popupRightIndex, popupCenterIndex, popupCenterParam;

    var crab = {
        isRefresh: false,
        // 设置侧栏折叠
        flexible: function (expand) {
            var isExpand = $('.layui-layout-admin').hasClass('admin-nav-mini');
            if (isExpand == !expand) {
                return;
            }
            if (expand) {
                $('.layui-layout-admin').removeClass('admin-nav-mini');
            } else {
                $('.layui-layout-admin').addClass('admin-nav-mini');
            }
            $('.admin-nav-hover>.layui-nav-child').css('top', 'auto');
            $('.admin-nav-hover').removeClass('admin-nav-hover');
            onAdminReSize();
        },
        // 设置导航栏选中
        activeNav: function (url) {
            $('.layui-layout-admin .layui-side .layui-nav .layui-nav-item .layui-nav-child dd').removeClass('layui-this');
            $('.layui-layout-admin .layui-side .layui-nav .layui-nav-item').removeClass('layui-this');
            if (url && url != '') {
                $('.layui-layout-admin .layui-side .layui-nav .layui-nav-item').removeClass('layui-nav-itemed');
                var thirdFlag = false,
                    $a = $('.layui-layout-admin .layui-side .layui-nav>.layui-nav-item>.layui-nav-child>dd>a[href="#' + url + '"]');
                if ($a.length === 0) {
                    thirdFlag = true;
                    $a = $('.layui-layout-admin .layui-side .layui-nav>.layui-nav-item>.layui-nav-child>.layui-nav-item-second>.layui-nav-child>dd>a[href="#' + url + '"]');
                }
                $a.parent('dd').addClass('layui-this');
                $a.parent('li').addClass('layui-this');
                if (thirdFlag) {
                    $a.parent('dd').parent('.layui-nav-child').parent('.layui-nav-item-second').addClass('layui-nav-itemed');
                    $a.parent('dd').parent('.layui-nav-child').parent('.layui-nav-item-second').parent('.layui-nav-child').parent('.layui-nav-item').addClass('layui-nav-itemed');
                } else {
                    $a.parent('dd').parent('.layui-nav-child').parent('.layui-nav-item').addClass('layui-nav-itemed');
                }
            }
        },
        // 右侧弹出
        popupRight: function (path) {
            var param = new Object();
            param.path = path;
            param.id = 'adminPopupR';
            param.title = false;
            param.anim = 2;
            param.isOutAnim = false;
            param.closeBtn = false;
            param.offset = 'r';
            param.shadeClose = true;
            param.area = '336px';
            param.skin = 'layui-layer-adminRight';
            param.end = function () {
                layer.closeAll('tips');
            };
            popupRightIndex = crab.open(param);
            return popupRightIndex;
        },
        // 关闭右侧弹出
        closePopupRight: function () {
            layer.close(popupRightIndex);
        },
        // 中间弹出
        popupCenter: function (param) {
            param.id = param.id ? param.id : 'adminPopupC';
            popupCenterParam = param;
            popupCenterIndex = crab.open(param);
            return popupCenterIndex;
        },
        // 关闭中间弹出并且触发finish回调
        finishPopupCenter: function (data) {
            layer.close(popupCenterIndex);
            popupCenterParam.finish ? popupCenterParam.finish(data) : '';
        },
        // 关闭中间弹出
        closePopupCenter: function () {
            layer.close(popupCenterIndex);
        },
        // 封装layer.open
        open: function (param) {
            var sCallBack = param.success;
            param.type = param.type ? param.type : 1;
            param.area = param.area ? param.area : '450px';
            param.offset = param.offset ? param.offset : '60px';
            param.resize ? param.resize : false;
            param.success = function (layero, index) {
                sCallBack ? sCallBack(layero, index) : '';
                $(layero).children('.layui-layer-content').load(param.path);
            };
            param.cancel = function () {
                if (param.cancelCallback) {
                    param.cancelCallback();
                }
            };
            return layer.open(param);
        },
        getMenus: function (uri, data, callback) {
            this.request(uri, data, function (data) {
                if (data.length > 0) {
                    var menus = new Array(), fucs = new Array(), map = {}, resourceMap = {};
                    for (var i = 0; i < data.length; i++) {
                        var d = data[i];
                        '' + d.type === '0' ? menus.push(d) : fucs.push(d);
                        map['' + d.id] = d;
                    }
                    for (var j = 0; j < fucs.length; j++) {
                        if (resourceMap[map['' + fucs[j].pid].path]) {
                            resourceMap[map['' + fucs[j].pid].path].push(fucs[j]);
                        } else {
                            resourceMap[map['' + fucs[j].pid].path] = new Array();
                            resourceMap[map['' + fucs[j].pid].path].push(fucs[j]);
                        }
                    }
                    crab.putUserResource(resourceMap);
                    var authMenus = new Array(), l = menus.length;
                    if (l > 0) {
                        for (var i = 0; i < l; i++) {
                            // Tab 菜单
                            var tabMenu = menus[i];
                            if (0 == tabMenu.pid) {
                                // 一级菜单
                                var firstMenus = new Array();
                                for (var j = 0; j < l; j++) {
                                    var firstMenu = menus[j];
                                    if (firstMenu.pid == tabMenu.id) {
                                        // 二级菜单
                                        var secondMenus = new Array();
                                        for (var k = 0; k < l; k++) {
                                            var secondMenu = menus[k];
                                            if (secondMenu.pid == firstMenu.id) {
                                                // 三级菜单
                                                var thirdSubMenus = new Array();
                                                for (var t = 0; t < l; t++) {
                                                    var thirdSubMenu = menus[t];
                                                    if (thirdSubMenu.pid == secondMenu.id) {
                                                        thirdSubMenus.push(thirdSubMenu);
                                                    }
                                                }
                                                if (thirdSubMenus.length > 0) {
                                                    secondMenu.subMenus = thirdSubMenus;
                                                }
                                                secondMenus.push(secondMenu);
                                            }
                                        }
                                        firstMenu.subMenus = secondMenus;
                                        firstMenus.push(firstMenu);
                                    }
                                }
                                tabMenu.firstMenus = firstMenus;
                                authMenus.push(tabMenu);
                            }
                        }

                        callback(authMenus);
                    }
                } else {
                    callback();
                }
            });
        },
        getUser: function () {
            return JSON.parse(sessionStorage.getItem(userInfo));
        },
        putUser: function (user) {
            sessionStorage.setItem(userInfo, JSON.stringify(user));
        },
        clearUser: function () {
            sessionStorage.removeItem(userInfo);
            sessionStorage.removeItem(userResource);
        },
        getUserResource: function () {
            return JSON.parse(sessionStorage.getItem(userResource));
        },
        putUserResource: function (resource) {
            sessionStorage.setItem(userResource, JSON.stringify(resource));
        },
        msg: function (data, icon) {
            layer.msg(data, {icon: icon ? icon : 1});
        },
        // 删除一行记录
        deleteRow: function (obj, url, data, tip) {
            this.delete(url, data, function () {
                obj.del();
                layer.msg(tip ? tip : config.lang.deleteRow, {icon: 1});
            });
        },
        // Ajax Get 请求
        get: function (url, data, callback, json, async, errCallback) {
            return this.request(url, data, callback, "GET", json, async, errCallback);
        },
        // Ajax Post 请求
        post: function (url, data, callback, json, async, errCallback) {
            return this.request(url, data, callback, "POST", json, async, errCallback);
        },
        // Ajax Put 请求
        put: function (url, data, callback, json, async, errCallback) {
            return this.request(url, data, callback, "PUT", json, async, errCallback);
        },
        // Ajax Delete 请求
        delete: function (url, data, callback, json, async, errCallback) {
            return this.request(url, data, callback, "DELETE", json, async, errCallback);
        },
        // 封装 Ajax 请求
        request: function (url, data, callback, method, json, async, errCallback) {
            if (!data) {
                data = {};
            }
            data._method = (method ? method : 'GET');
            var contentType = 'application/';
            contentType += (json ? 'json' : 'x-www-form-urlencoded');
            contentType += '; charset=UTF-8';
            layer.load(2);
            $.ajax({
                url: config.base_server + url,
                async: async !== undefined ? async : true,
                data: data,
                type: method,
                contentType: contentType,
                dataType: 'json',
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true,
                success: function (data) {
                    layer.closeAll('loading');
                    if (data.code == 0) {
                        callback(data.data);
                    } else {
                        layer.msg(data.msg, {icon: 2});
                        if (errCallback) errCallback();
                    }
                },
                error: function (xhr) {
                    layer.closeAll('loading');
                    console.log(xhr);
                },
                beforeSend: function (xhr) {
                    // var token = config.getToken();
                    // if (token) {
                    //     xhr.setRequestHeader('Authorization', 'Basic ' + token.access_token);
                    // }
                }
            });
        },
        // 判断是否有权限
        hasPerm: function (auth) {
            var user = config.getUser();
            if (user.authorities) {
                for (var i = 0; i < user.authorities.length; i++) {
                    if (auth == user.authorities[i].authority) {
                        return true;
                    }
                }
            }
            return false;
        },
        // 显示加载动画
        showLoading: function (element) {
            $(element).append('<i class="layui-icon layui-icon-loading layui-anim layui-anim-rotate layui-anim-loop admin-loading"></i>');
        },
        // 移除加载动画
        removeLoading: function (element) {
            $(element + '>.admin-loading').remove();
        },
        // 缓存临时数据
        putTempData: function (key, value) {
            if (value) {
                layui.sessionData('tempData', {key: key, value: value});
            } else {
                layui.sessionData('tempData', {key: key, remove: true});
            }
        },
        // 获取缓存临时数据
        getTempData: function (key) {
            return layui.sessionData('tempData')[key];
        },
        // 滑动选项卡
        rollPage: function (d) {
            var $tabTitle = $('.layui-layout-admin .layui-body .layui-tab .layui-tab-title');
            var left = $tabTitle.scrollLeft();
            if ('left' === d) {
                $tabTitle.scrollLeft(left - 120);
            } else if ('auto' === d) {
                var autoLeft = 0;
                $tabTitle.children("li").each(function () {
                    if ($(this).hasClass('layui-this')) {
                        return false;
                    } else {
                        autoLeft += $(this).outerWidth();
                    }
                });
                // console.log(autoLeft);
                $tabTitle.scrollLeft(autoLeft - 47);
            } else {
                $tabTitle.scrollLeft(left + 120);
            }
        },
        refresh: function () {
            crab.isRefresh = true;
            Q.refresh();
        },
        // 判断是否为json
        parseJSON: function (str) {
            if (typeof str == 'string') {
                try {
                    var obj = JSON.parse(str);
                    if (typeof obj == 'object' && obj) {
                        return obj;
                    }
                } catch (e) {
                }
            }
        },
        // 关闭选项卡操作菜单
        closeTabOperNav: function () {
            $('.layui-icon-down .layui-nav .layui-nav-child').removeClass('layui-show');
        },
        // 缓存持久数据
        putLastingData: function (key, value) {
            if (value) {
                layui.data('lastingData', {key: key, value: value});
            } else {
                layui.data('lastingData', {key: key, remove: true});
            }
        },
        // 获取缓存持久数据
        getLastingData: function (key) {
            return layui.data('lastingData')[key];
        },
        putFormData: function (value) {
            return this.putTempData('form_data', value);
        },
        getFormData: function () {
            return this.getTempData('form_data');
        },
        // 获取搜索条件
        getSearchForm: function (key) {
            var d = {};
            var data = $(key ? key : '#searchForm').find(':input').filter(function () {
                return $.trim(this.value).length > 0
            }).serializeArray();
            console.log('searchData：');
            console.log(data);
            $(data).each(function () {
                if (d[this.name] !== undefined) {
                    if (!Array.isArray(d[this.name])) {
                        d[this.name] = [d[this.name]];
                    }
                    d[this.name].push(this.value);
                } else {
                    d[this.name] = this.value;
                }
            });
            console.log('searchD：');
            console.log(d);
            return d;
        },
        // 锁屏弹出框
        lockPage: function (lock) {
            if (lock || sessionStorage.getItem(lockStatus) === '1') {
                layer.prompt({
                    formType: 1,
                    closeBtn: 0,
                    title: config.lang.unlockTip,
                    btn: [config.lang.unlockBtn]
                }, function (val, index) {
                    crab.post('/sys/user/unlock', {password: val}, function () {
                        sessionStorage.removeItem(lockStatus);
                        layer.close(index);
                    });
                });
            }
        },
        // 配置初始化
        init: function () {
            // 加载设置的侧栏
            var side = layui.data('crab').side;
            if (side || false == side) {
                config.side = side;
            }
            crab.flexible(config.side);
            // 加载设置的主题
            var theme = layui.data('crab').theme;
            if (theme) layui.link('/assets/css/' + (theme ? theme : config.theme) + '.css');
            // 加载设置的多标签
            var pageTabs = layui.data('crab').pageTabs;
            if (pageTabs || false == pageTabs) {
                config.pageTabs = pageTabs;
            }
            // 锁屏检查
            crab.lockPage(false);
        },
        // 设置的多标签
        setPageTabs: function (pageTabs) {
            if (pageTabs || false == pageTabs) {
                config.pageTabs = pageTabs;
            }
            layui.data('crab', {
                key: 'pageTabs',
                value: pageTabs
            });
        },
        // 选中缓存主题
        setCurrentTheme: function () {
            $('.btnTheme').removeClass('active');
            var theme = layui.data('crab').theme;
            if (theme) {
                $('.btnTheme[theme=' + theme + ']').addClass('active');
            } else {
                $('.btnTheme').eq(0).addClass('active');
            }
        },
        // 切换主题
        switchTheme: function (t) {
            $('link[id^=layuicss-assetscsstheme]').remove();
            var theme = $(t).attr('theme');
            if (theme) {
                layui.data('crab', {
                    key: 'theme',
                    value: theme
                });
                layui.link('/assets/css/' + theme + '.css');
            } else {
                layui.data('crab', {
                    key: 'theme',
                    remove: true
                });
            }
            this.setCurrentTheme();
        },
        // 音频播放
        audioPlay: function (url) {
            var audio = $('#' + config.audioId);
            if (!url) {
                url = config.audioMsg;
            }
            audio.attr('src', url);
            audio[0].play();
        },
        // array生成tree (nodes:array,treeRootPid:顶级的pid)
        createTreeData: function (nodes, treeRootPid) {
            var groups = {};
            // 按父节点将节点分组
            for (var i in nodes) {
                if (!groups[nodes[i].pid]) {
                    groups[nodes[i].pid] = [];
                }
                groups[nodes[i].pid].push(nodes[i]);
                if (treeRootPid && treeRootPid === nodes[i].id) { // 发现传入的根节点id作为节点id时，将根节点设置为该节点的父节点
                    treeRootPid = nodes[i].pid;
                }
            }

            var rootNodes = groups[treeRootPid];
            groups[treeRootPid] = null; // [SAFEGUARD]防止自为父节点或互为父节点（有环图结构）导致的死循环
            function traverseTreeNodeGroup(treeNodeGroup) {
                for (var i in treeNodeGroup) {
                    var node = treeNodeGroup[i];
                    if (groups[node.id]) {
                        node.children = groups[node.id];
                        groups[node.id] = null; // [SAFEGUARD]防止自为父节点或互为父节点（有环图结构）导致的死循环
                        traverseTreeNodeGroup(node.children);
                    }
                }
            }

            traverseTreeNodeGroup(rootNodes);
            return rootNodes;
        }
    };

    // ewAdmin提供的事件
    crab.events = {
        flexible: function (e) {  // 折叠侧导航
            var expand = $('.layui-layout-admin').hasClass('admin-nav-mini');
            layui.data('crab', {
                key: 'side',
                value: expand
            });
            crab.flexible(expand);
        },
        refresh: function () {  // 刷新主体部分
            crab.refresh();
        },
        back: function () {  //后退
            history.back();
        },
        theme: function () {  // 设置主题
            crab.popupRight('/components/tpl/theme.html');
        },
        // 锁屏
        lockScreen: function () {
            sessionStorage.setItem(lockStatus, '1');
            crab.lockPage(true);
        },
        fullScreen: function (e) {  // 全屏
            var ac = 'layui-icon-screen-full', ic = 'layui-icon-screen-restore';
            var ti = $(this).find('i');

            var isFullscreen = document.fullscreenElement || document.msFullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement || false;
            if (isFullscreen) {
                var efs = document.exitFullscreen || document.webkitExitFullscreen || document.mozCancelFullScreen || document.msExitFullscreen;
                if (efs) {
                    efs.call(document);
                } else if (window.ActiveXObject) {
                    var ws = new ActiveXObject('WScript.Shell');
                    ws && ws.SendKeys('{F11}');
                }
                ti.addClass(ac).removeClass(ic);
            } else {
                var el = document.documentElement;
                var rfs = el.requestFullscreen || el.webkitRequestFullscreen || el.mozRequestFullScreen || el.msRequestFullscreen;
                if (rfs) {
                    rfs.call(el);
                } else if (window.ActiveXObject) {
                    var ws = new ActiveXObject('WScript.Shell');
                    ws && ws.SendKeys('{F11}');
                }
                ti.addClass(ic).removeClass(ac);
            }
        },
        // 左滑动tab
        leftPage: function () {
            crab.rollPage("left");
        },
        // 右滑动tab
        rightPage: function () {
            crab.rollPage();
        },
        // 关闭当前选项卡
        closeThisTabs: function () {
            crab.closeTabOperNav();
            var $title = $('.layui-layout-admin .layui-body .layui-tab .layui-tab-title');
            if ($title.find('li').first().hasClass('layui-this')) {
                layer.msg('主页不能关闭', {icon: 2});
                return;
            }
            $title.find('li.layui-this').find(".layui-tab-close").trigger("click");
        },
        // 关闭其他选项卡
        closeOtherTabs: function () {
            $('.layui-layout-admin .layui-body .layui-tab .layui-tab-title li:gt(0):not(.layui-this)').find(".layui-tab-close").trigger("click");
            crab.closeTabOperNav();
        },
        // 关闭所有选项卡
        closeAllTabs: function () {
            $('.layui-layout-admin .layui-body .layui-tab .layui-tab-title li:gt(0)').find(".layui-tab-close").trigger("click");
            crab.closeTabOperNav();
        },
        // 关闭所有弹窗
        closeDialog: function () {
            layer.closeAll('page');
        }
    };

    // 所有ew-event
    $('body').on('click', '*[ew-event]', function () {
        var event = $(this).attr('ew-event');
        var te = crab.events[event];
        te && te.call(this, $(this));
    });

    // 移动设备遮罩层点击事件
    $('.site-mobile-shade').click(function () {
        crab.flexible(true);
    });

    // 侧导航折叠状态下鼠标经过显示提示
    var isHover = false;
    $('body').on('mouseenter', '.layui-layout-admin.admin-nav-mini .layui-side .layui-nav .layui-nav-item>a', function () {
        if (document.body.clientWidth > 750) {
            var $that = $(this);
            $('.admin-nav-hover>.layui-nav-child').css('top', 'auto');
            $('.admin-nav-hover').removeClass('admin-nav-hover');
            $that.parent().addClass('admin-nav-hover');
            var $nav = $('.admin-nav-hover>.layui-nav-child');
            if ($nav.length > 0) {
                var isOver = ($that.offset().top + $nav.outerHeight()) > window.innerHeight;  // 是否溢出屏幕
                if (isOver) {
                    $nav.css('top', $that.offset().top - $nav.outerHeight() + $that.outerHeight());
                } else {
                    $nav.css('top', $that.offset().top);
                }
                isHover = true;
            } else {
                var tipText = $that.find('cite').text();
                var bgColor = $('.layui-layout-admin .layui-side').css('background-color');
                bgColor = (bgColor == 'rgb(255, 255, 255)' ? '#009688' : bgColor);
                layer.tips(tipText, $that, {tips: [2, bgColor], time: -1});
            }
        }
    }).on('mouseleave', '.layui-layout-admin.admin-nav-mini .layui-side .layui-nav .layui-nav-item>a', function () {
        layer.closeAll('tips');
    });

    // 鼠标离开侧导航关闭折叠浮窗
    $('body').on('mouseleave', '.layui-layout-admin.admin-nav-mini .layui-side', function () {
        isHover = false;
        setTimeout(function () {
            if (!isHover) {
                $('.admin-nav-hover>.layui-nav-child').css('top', 'auto');
                $('.admin-nav-hover').removeClass('admin-nav-hover');
            }
        }, 500);
    });

    $('body').on('mouseenter', '.layui-layout-admin.admin-nav-mini .layui-side .layui-nav .layui-nav-item.admin-nav-hover .layui-nav-child', function () {
        isHover = true;
    });

    // 侧导航折叠状态下点击展开
    $('body').on('click', '.layui-layout-admin.admin-nav-mini .layui-side .layui-nav .layui-nav-item>a', function () {
        if (document.body.clientWidth > 750) {
            layer.closeAll('tips');
            crab.flexible(true);
            $('li.layui-nav-itemed').removeClass('layui-nav-itemed');
            $(this).parent().addClass('layui-nav-itemed');
        }
    });

    // 所有lay-tips处理
    $('body').on('mouseenter', '*[lay-tips]', function () {
        var tipText = $(this).attr('lay-tips');
        var dt = $(this).attr('lay-direction');
        layer.tips(tipText, this, {tips: [dt || 3, '#333333'], time: -1});
    }).on('mouseleave', '*[lay-tips]', function () {
        layer.closeAll('tips');
    });

    // 折叠侧导航触发window.resize
    var isResize = true;
    var onAdminReSize = function () {
        isResize = false;
        setTimeout(function () {
            isResize = false;
            $(window).resize();
            setTimeout(function () {
                isResize = true;
            }, 100);
        }, 500);
    };

    // 解决从手机屏幕跟电脑屏幕切换表格宽度自适应
    $(window).on('resize', function () {
        if (isResize && $('.layui-table-view').length > 0) {
            setTimeout(function () {
                isResize = false;
                $(window).resize();
                setTimeout(function () {
                    isResize = true;
                }, 100);
            }, 500);
        }
    });

    exports('crab', crab);
});
